package com.ebuozturk.user.converter;

import com.ebuozturk.user.dto.user.UserDto;
import com.ebuozturk.user.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {


    public UserDto convert(User user){
        return new UserDto(user.getId(),user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getEmail());
    }

    public List<UserDto> convert(List<User> userList){

        return userList.stream().map(this::convert).collect(Collectors.toList());
    }
}
