package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateAccountRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.UpdateAccountRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.AccountResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IAccountServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/v1")
public class AccountRestController implements IAccountServiceImpl
{

    @Autowired
    private IAccountServiceImpl serviceImpl;

    @GetMapping("/accounts")
    @Override
    public ResponseEntity<AccountResponse> listAccount() {
        return this.serviceImpl.listAccount();
    }
    
    @PostMapping("/accounts")
    @Override
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest body) {
        return this.serviceImpl.createAccount(body);
    }

    @PutMapping("/accounts/{id}")
    @Override
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable String id, @Valid @RequestBody UpdateAccountRequest body) {
        return this.serviceImpl.updateAccount(id, body);
    }

    @GetMapping("/accounts/{id}")
    @Override
    public ResponseEntity<AccountResponse> findAccount(@PathVariable String id) {
        return this.serviceImpl.findAccount(id);
    }

    @DeleteMapping("/accounts/{id}")
    @Override
    public ResponseEntity<AccountResponse> deleteAccount(@PathVariable String id) {
        return this.serviceImpl.deleteAccount(id);
    }

}
