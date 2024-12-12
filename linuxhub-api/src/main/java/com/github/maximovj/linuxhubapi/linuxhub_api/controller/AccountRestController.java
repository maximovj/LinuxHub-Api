package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.response.AccountResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IAccountServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/v1")
public class AccountRestController implements IAccountServiceImpl
{

    @Autowired
    private IAccountServiceImpl serviceImpl;

    @PostMapping("/accounts")
    @Override
    public ResponseEntity<AccountResponse> createAccount() {
        return this.serviceImpl.createAccount();
    }
    
}
