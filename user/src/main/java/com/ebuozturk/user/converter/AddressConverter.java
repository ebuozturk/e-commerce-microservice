package com.ebuozturk.user.converter;

import com.ebuozturk.user.dto.address.AddressDto;
import com.ebuozturk.user.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressConverter {


    public AddressDto convert(Address address){
        return new AddressDto(address.getId(),
                address.getAddressName(),
                address.getPhoneNumber(),
                address.getCountry(),
                address.getCity(),
                address.getFirstName(),
                address.getLastName(),
                address.getFullAddress());
    }

    public List<AddressDto> convert(List<Address> addressList){

        return addressList.stream().map(this::convert).collect(Collectors.toList());
    }
}
