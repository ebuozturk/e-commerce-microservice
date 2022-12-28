package com.ebuozturk.user.service;

import com.ebuozturk.user.converter.AddressConverter;
import com.ebuozturk.user.dto.address.AddressDto;
import com.ebuozturk.user.dto.address.CreateAddressRequest;
import com.ebuozturk.user.dto.address.UpdateAddressRequest;
import com.ebuozturk.user.exception.AddressNotFoundException;
import com.ebuozturk.user.model.Address;
import com.ebuozturk.user.model.User;
import com.ebuozturk.user.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserService userService;
    private final AddressConverter converter;

    public AddressService(AddressRepository addressRepository, UserService userService, AddressConverter converter) {
        this.addressRepository = addressRepository;
        this.userService = userService;
        this.converter = converter;
    }

    public AddressDto createAddress(final CreateAddressRequest request){
        User user = userService.findById(request.getUserId());
        final Address address = new Address( request.getAddressName(),
                request.getPhoneNumber(),
                request.getFirstName(),
                request.getLastName(),
                request.getCountry(),
                request.getCity(),
                request.getFullAddress(),
                user);

        return converter.convert(addressRepository.save(address));
    }

    public AddressDto updateAddress(final String id,final UpdateAddressRequest request){
            User user = userService.findById(request.getUserId());
            findById(id);
            Address updateAddress = new Address(id,
                    request.getAddressName(),
                    request.getPhoneNumber(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getCountry(),
                    request.getCity(),
                    request.getFullAddress(),
                    user
                    );
            return converter.convert(addressRepository.save(updateAddress));
    }

    public void deleteAddress(String id){
        if(doesAddressExist(id)){
            addressRepository.deleteById(id);
        }else
            throw new AddressNotFoundException("Address is not found by following id: "+id);
    }

    public AddressDto getAddressById(String id){
        return converter.convert(findById(id));
    }

    public List<AddressDto> getAllAddressesByUserId(String userId){
        return converter.convert(addressRepository.findAddressByUser_id(userId));
    }
    public List<AddressDto> getAddressesByUserId(String userId){
        return converter.convert(addressRepository.findAddressByUser_id(userId));
    }
    private Boolean doesAddressExist(String id){
        return addressRepository.existsById(id);
    }
    protected Address findById(String id){
        return addressRepository.findById(id).orElseThrow(()-> new AddressNotFoundException("Address is not found by following id: "+id));
    }


}
