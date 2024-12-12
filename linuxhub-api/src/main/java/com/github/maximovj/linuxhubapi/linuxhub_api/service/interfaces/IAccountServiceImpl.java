package com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhubapi.linuxhub_api.response.AccountResponse;

public interface IAccountServiceImpl 
{
    
    // Crear una cuenda usando el body
    public ResponseEntity<AccountResponse> createAccount();
    
}
