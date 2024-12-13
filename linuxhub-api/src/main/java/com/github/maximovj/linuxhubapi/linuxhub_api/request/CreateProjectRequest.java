package com.github.maximovj.linuxhubapi.linuxhub_api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.project.Environment;
import com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_enum.ValidEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class CreateProjectRequest 
{

    // No es requerido (puede ser null)
    String cover;

    @NotEmpty(message = "name es requerido")
    @Size(min = 6, max = 32, message = "name debe ser entre 6 y 32 carácteres")
    String name;

    @NotEmpty(message = "description es requerido")
    @Size(min = 3, max = 360, message = "description debe ser entre 6 y 32 carácteres")
    String description;
    
    @NotEmpty(message = "environment es requerido")
    @ValidEnum(enumClass = Environment.class, nullable = false)
    String environment;

    String host;

    @JsonProperty("base_url")
    String baseUrl;

    @NotEmpty(message = "account_id es requerido")
    @Size(min = 24, max = 24, message = "account_id debe ser de 24 carácteres")
    String account_id;

}
