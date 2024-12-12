package com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateAccountRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.UpdateAccountRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.AccountResponse;

public interface IAccountServiceImpl 
{
    
    // Listar todas las cuentas
    public ResponseEntity<AccountResponse> listAccount();

    // Crear una cuenda usando el body
    public ResponseEntity<AccountResponse> createAccount(CreateAccountRequest body);

    // Actualizar una cuenta usando su _id y el body
    public ResponseEntity<AccountResponse> updateAccount(String id, UpdateAccountRequest body);

    // Buscar/Leer una cuenta usando su _id
    public ResponseEntity<AccountResponse> findAccount(String id);

    // Eliminar una cuenta usando su _id
    public ResponseEntity<AccountResponse> deleteAccount(String id);
    
}
