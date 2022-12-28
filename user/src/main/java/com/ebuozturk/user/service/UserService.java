package com.ebuozturk.user.service;

import com.ebuozturk.user.converter.UserConverter;
import com.ebuozturk.user.dto.user.UserDto;
import com.ebuozturk.user.model.User;
import com.ebuozturk.user.exception.UserNotFoundException;
import com.ebuozturk.user.dto.user.CreateUserRequest;
import com.ebuozturk.user.dto.user.UpdateUserRequest;
import com.ebuozturk.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private final WebClient.Builder webClientBuilder;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserConverter converter, WebClient.Builder webClientBuilder, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.webClientBuilder = webClientBuilder;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(final CreateUserRequest createUser){

        User user = new User(
                createUser.getFirstName(),
                createUser.getMiddleName(),
                createUser.getLastName(),
                createUser.getEmail(),
                passwordEncoder.encode(createUser.getPassword())
                );

        User savedUser = userRepository.save(user);

        ResponseEntity<String> response = webClientBuilder.build()
                .post()
                .uri("http://BASKET/v1/basket/"+savedUser.getId())
                .retrieve()
                .toEntity(String.class)
                .block();

        if(response.getStatusCode().value() != 201) {
            deleteUser(savedUser.getId());
            throw new IllegalStateException();
        }
        return converter.convert(savedUser);
    }

    public List<UserDto> getAllUsers(){

        return converter.convert(userRepository.findAll());
    }

    public UserDto updateUser(String id, UpdateUserRequest request) {
        User user = findById(id);
        User updateUser = new User(user.getId(),
                request.getFirstName(),
                request.getMiddleName(),
                request.getLastName(),
                request.getEmail());
       return converter.convert(userRepository.save(updateUser));
    }

    public void deleteUser(String id) {
      if(doesUserExist(id)){
          userRepository.deleteById(id);
      }
      else{
          throw new UserNotFoundException("User is not found by following id: "+id);
      }
    }
    public UserDto getUserByMail(String mail){
        return converter.convert(findByMail(mail));
    }

    public UserDto getUserById(String id) {
        return converter.convert(findById(id));
    }
    public void deactivateUser(String id){
        changeActivateUser(id,false);
    }

    public void activateUser(String id) {
        changeActivateUser(id,true);
    }

    public User changeActivateUser(String id, Boolean isActive){
        User user = findById(id);
        User changedUser = new User(user.getId(),
                user.getFirstName(),
                user.getMiddleName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                isActive
        );
        return userRepository.save(changedUser);
    }

    protected User findById(String id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("User is not found by following id: "+id));
    }
    protected User findByMail(String mail){
        return userRepository.findByEmail(mail).orElseThrow(()-> new UserNotFoundException("User is not found by following email: "+mail));
    }
    public boolean doesUserExist(String id){
        return userRepository.existsById(id);
    }

}
