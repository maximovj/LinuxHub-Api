package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.response.AccountResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IAccountServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountServiceImpl
{

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
        this.apiResponse.setRes_title("API Comparation");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(this.data);
        return ResponseEntity.ok(this.apiResponse);
    }

    private ResponseEntity<AccountResponse> buildErrorResponse(HttpStatus status, String content, HashMap<String, Object> errors)
    {
        this.apiResponse.setRes_title("API Comparation");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("error");
        this.apiResponse.setSuccess(false);
        this.apiResponse.setErrors(errors);
        return ResponseEntity.status(status).body(this.apiResponse);
    }

    @Override
    public ResponseEntity<AccountResponse> createAccount() {
        this.initEndPoint("POST", "/v1/account");
        return this.buildSuccessResponse("Funciona", this.data);
    }

}
