package com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhubapi.linuxhub_api.request.AccountRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.AccountResponse;

public interface IAccountServiceImpl 
{
    
    // Crear una cuenda usando el body
    public ResponseEntity<AccountResponse> createAccount(AccountRequest body);

    // Buscar/Leer una cuenta usando su _id
    public ResponseEntity<AccountResponse> findAccount(String id);
    
}
