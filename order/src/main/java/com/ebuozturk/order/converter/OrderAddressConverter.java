package com.ebuozturk.order.converter;

import com.ebuozturk.order.dto.orderaddress.OrderAddressDto;
import com.ebuozturk.order.model.OrderAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderAddressConverter {


    public OrderAddressDto convert(OrderAddress address){
        return new OrderAddressDto(address.getId(),
                address.getAddressName(),
                address.getPhoneNumber(),
                address.getCountry(),
                address.getCity(),
                address.getFirstName(),
                address.getLastName(),
                address.getFullAddress());
    }

    public List<OrderAddressDto> convert(List<OrderAddress> addressList){

        return addressList.stream().map(this::convert).collect(Collectors.toList());
    }
}