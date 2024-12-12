package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.Role;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.State;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Account;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.AccountRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.AccountRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.AccountResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IAccountServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountServiceImpl
{

    private final AccountRepository accountRepository;
    private HashMap<String, Object> data;
    private HashMap<String, Object> errors;
    private AccountResponse apiResponse;

    private void initEndPoint(String type, String uri) 
    {
        this.apiResponse = new AccountResponse();
        this.apiResponse.setType(type);
        this.apiResponse.setUri(uri);
        this.apiResponse.setBase_url("http://localhost:5808"+uri);
        this.data = new HashMap<>();
        this.errors = new HashMap<>();
    }

    private ResponseEntity<AccountResponse> buildSuccessResponse(String content, HashMap<String, Object> data)
    {
        this.apiResponse.setRes_title("API Accounts");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(this.data);
        return ResponseEntity.ok(this.apiResponse);
    }

    private ResponseEntity<AccountResponse> buildErrorResponse(HttpStatus status, String content, HashMap<String, Object> errors)
    {
        this.apiResponse.setRes_title("API Accounts");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("error");
        this.apiResponse.setSuccess(false);
        this.apiResponse.setErrors(errors);
        return ResponseEntity.status(status).body(this.apiResponse);
    }

    private boolean isValidState(String state) {
        // Verifica que el estado sea válido según la enumeración
        return state != null && Arrays.asList(State.values()).contains(State.valueOf(state));
    }

    private boolean isValidRole(String role) {
        // Verifica que el rol sea válido según la enumeración
        return role != null && Arrays.asList(Role.values()).contains(Role.valueOf(role));
    }

    @Override
    public ResponseEntity<AccountResponse> createAccount(AccountRequest body) {
        this.initEndPoint("POST", "/v1/accounts");
        
        // Verificar que el body no sea vacío
        if(body == null) {
            this.errors.put("body", "El cuerpo / body de la petición es requerido en JSON");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops el cuerpo no existe", errors);
        }

        // Verificar que los campos "State", "Role", "Password" no esten vacíos
        if(body.getState() == null || body.getRole() == null || body.getPassword().isEmpty()) {
            this.errors.put("state", "El state es requerido");
            this.errors.put("role", "El role es requerido");
            this.errors.put("password", "El password es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops campos son requeridos", errors);
        }
        
        try {
            // Verificar que los campos "State", "Role" sean válidos
            if (!this.isValidRole(body.getRole()) || !this.isValidState(body.getState()) ) {
                this.errors.put("state", "El state no es válido");
                this.errors.put("role", "El role no es válido");
                return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops role y/o state no son válidos", errors);
            }
        } catch (Exception e) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops role y/o state no son válidos", errors);
        }

        // Verificar que los campos "emal", "username" no esten vacíos
        if(body.getEmail().isEmpty() || body.getUsername().isEmpty()) {
            this.errors.put("email", "El email es requerido");
            this.errors.put("username", "El username es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops correo electrónico y/o cuenta de usuario no verificados", errors);
        }
        
        // Verificar que los campos "emal", "username" sean válidos
        if(!body.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") || !body.getUsername().matches("^[a-zA-Z0-9_-]+$")) {
            this.errors.put("email", "El email no es válido");
            this.errors.put("username", "El username no es válido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops correo electrónico y/o cuenta de usuario no son váldidos", errors);
        }
        
        // Verificar que los campos "emal", "username" no estan registrados
        List<Account> account = this.accountRepository.findByEmailOrUsername(body.getEmail(), body.getUsername());
        if(account.size() > 0) {
            this.errors.put("email", "El email no disponible");
            this.errors.put("username", "El username no disponible");
            return this.buildErrorResponse(HttpStatus.CONFLICT, "Oops correo electrónico y/o cuenta de usuario no disponibles", errors);
        }

        // Encriptar la contraseña antes de guardarla
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(body.getPassword());
        body.setPassword(encryptedPassword);

        Account new_account = new Account();
        new_account.setAvatar(body.getAvatar());
        new_account.setUsername(body.getUsername());
        new_account.setEmail(body.getEmail());
        new_account.setPassword(body.getPassword());
        new_account.setRole(Role.valueOf(body.getRole()));
        new_account.setState(State.valueOf(body.getState()));
        this.accountRepository.save(new_account);

        this.data.put("account", new_account);
        return this.buildSuccessResponse("Cuenta creada exitosamente", this.data);
    }

}
