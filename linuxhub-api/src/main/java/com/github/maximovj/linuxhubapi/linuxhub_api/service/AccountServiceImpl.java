package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.data.domain.Sort;
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
import com.github.maximovj.linuxhubapi.linuxhub_api.request.UpdateAccountRequest;
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
        this.apiResponse.setBaseUrl("http://localhost:5808"+uri);
        this.data = new HashMap<>();
        this.errors = new HashMap<>();
    }

    private ResponseEntity<AccountResponse> buildSuccessResponse(String content, HashMap<String, Object> data)
    {
        this.apiResponse.setResTitle("API Accounts");
        this.apiResponse.setResContent(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(this.data);
        return ResponseEntity.ok(this.apiResponse);
    }

    private ResponseEntity<AccountResponse> buildErrorResponse(HttpStatus status, String content, HashMap<String, Object> errors)
    {
        this.apiResponse.setResTitle("API Accounts");
        this.apiResponse.setResContent(content);
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

    // Método utilitario
    private <T> void updateIfNotEmpty(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
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

    @Override
    public ResponseEntity<AccountResponse> findAccount(String id) 
    {
        this.initEndPoint("GET", "/v1/accounts/" + id);

        if(id.isEmpty()) {
            this.errors.put("id", "La cuenta id es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops cuenta no proporcionada", this.errors);
        }

        Optional<Account> account = this.accountRepository.findById(id);
        if(!account.isPresent()) {
            this.errors.put("id", "La cuenta id no existe en el sistema");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops cuenta no encontrada en el sistema", this.errors);
        }

        this.data.put("account", account.get());
        return this.buildSuccessResponse("Cuenta localizada correctamente", this.data);
    }

    @Override
    public ResponseEntity<AccountResponse> deleteAccount(String id) {
        this.initEndPoint("DELETE", "/v1/accounts/" + id);

        if(id.isEmpty()) {
            this.errors.put("id", "La cuenta id es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops cuenta no proporcionada", this.errors);
        }

        Optional<Account> account = this.accountRepository.findById(id);
        if(!account.isPresent()) {
            this.errors.put("id", "La cuenta id no existe en el sistema");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops cuenta no encontrada en el sistema", this.errors);
        }

        this.accountRepository.delete(account.get());
        this.data.put("account", account.get());
        return this.buildSuccessResponse("Cuenta eliminada exitosamente", this.data);
    }

    @Override
    public ResponseEntity<AccountResponse> listAccount() 
    {
        this.initEndPoint("GET", "/v1/accounts");
        List<Account> accounts = this.accountRepository.findAll(Sort.by(Sort.Order.asc("updated_at")));
        this.data.put("accounts", accounts);
        return this.buildSuccessResponse("Listar todas las cuentas", this.data);
    }

    @Override
    public ResponseEntity<AccountResponse> updateAccount(String id, UpdateAccountRequest body) 
    {
        this.initEndPoint("PUT", "/v1/accounts/" + id);
        if(body == null) {
            this.errors.put("body", "El body es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "No hay cuerpo de la solicitud", this.errors);
        }
        
        // Verificar que los campos "emal", "username" no estan registrados
        List<Account> accounts = this.accountRepository.findByEmailOrUsername(body.getEmail(), body.getUsername());
        if(accounts.size() > 1) {
            this.errors.put("email", "El email no disponible");
            this.errors.put("username", "El username no disponible");
            return this.buildErrorResponse(HttpStatus.CONFLICT, "Oops correo electrónico y/o cuenta de usuario no disponibles", errors);
        }

        Optional<Account> find_account = this.accountRepository.findById(id);
        if(!find_account.isPresent()) {
            this.errors.put("cuenta", "La cuenta no fue encontrada");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops cuenta no encontrada en el sistema", this.errors);
        }

        // Encriptar la contraseña
        if(body.getPassword() != null) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(body.getPassword());
            body.setPassword(password);
        }

        Account account = find_account.get();
        updateIfNotEmpty(body.getAvatar(), account::setAvatar);
        updateIfNotEmpty(body.getUsername(), account::setUsername);
        updateIfNotEmpty(body.getEmail(), account::setEmail);
        updateIfNotEmpty(body.getPassword(), account::setPassword);
        updateIfNotEmpty(body.getRole(), role -> account.setRole(Role.valueOf(role)));
        updateIfNotEmpty(body.getState(), state -> account.setState(State.valueOf(state)));

        this.accountRepository.save(account);
        this.data.put("account", account);
        return this.buildSuccessResponse("Cuenta actualizada exitosamente", this.data);
    }

}
