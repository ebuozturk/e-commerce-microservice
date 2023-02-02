package com.ebuozturk.payment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin({"*"})
@RestController
@RequestMapping({"/v1/payment"})
public class PaymentController {

    @PostMapping({"/createPayment"})
    public ResponseEntity<Boolean> createPayment() {
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }
}
