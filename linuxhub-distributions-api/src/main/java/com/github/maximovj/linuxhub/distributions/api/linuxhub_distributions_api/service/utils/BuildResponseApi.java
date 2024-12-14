package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service.utils;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.function.Consumer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuildResponseApi<T extends ApiResponse>
{

    private final Class<T> type;
    protected HashMap<String, Object> data;
    protected HashMap<String, Object> errors;
    protected T apiResponse;

    public BuildResponseApi() {
        // Inferir el tipo genérico automáticamente
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                    .getActualTypeArguments()[0];
    }
    
    protected void initEndPoint(String type, String uri) {
        try {
            this.apiResponse = this.type.getDeclaredConstructor().newInstance();
            this.apiResponse.setType(type);
            this.apiResponse.setUri(uri);
            this.apiResponse.setBaseUrl(uri);
            this.data = new HashMap<>();
            this.errors = new HashMap<>();
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando la respuesta de API", e);
        }
    }

    protected ResponseEntity<T> buildSuccessResponse(HttpStatus status, String content) {
        this.apiResponse.setResTitle("API Projects");
        this.apiResponse.setResContent(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(this.data);
        this.apiResponse.setErrors(null);
        return ResponseEntity.status(status).body(this.apiResponse);
    }

    protected ResponseEntity<T> buildErrorResponse(HttpStatus status, String content) {
        this.apiResponse.setResTitle("API Projects");
        this.apiResponse.setResContent(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("error");
        this.apiResponse.setSuccess(false);
        this.apiResponse.setData(null);
        this.apiResponse.setErrors(this.errors);
        return ResponseEntity.status(status).body(this.apiResponse);
    }

    // Método utilitario
    protected <T> void updateIfNotEmpty(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }

}
