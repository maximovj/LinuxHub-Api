package com.github.maximovj.linuxhubapi.linuxhub_api.request;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class UpdateProjectRequest 
{

    String cover;

    String name;

    String description;

    @NotEmpty( message = "environment es requerido")
    @ValidEnum(enumClass = Environment.class, nullable = false)
    String environment;

    String host;

    @NotEmpty(message = "account_id es requerido")
    @Size(min = 24, max = 24, message = "account_id debe ser de 24 carácteres")
    String account_id;

    String base_url;
    
}
