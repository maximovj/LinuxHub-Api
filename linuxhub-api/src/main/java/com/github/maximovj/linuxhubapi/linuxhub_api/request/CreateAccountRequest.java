package com.github.maximovj.linuxhubapi.linuxhub_api.request;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.Role;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.State;
import com.github.maximovj.linuxhubapi.linuxhub_api.validation.strong_password.StrongPassword;
import com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_enum.ValidEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest 
{

    // Permite null
    String avatar;
    
    @NotEmpty(message = "username es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", 
    message = "username no tiene un formato válido")
    @Size(min = 4, max = 24, message = "Se requiere entre 4 y 24 carácteres")
    String username;
    
    @NotEmpty(message = "email es requerido")
    @Email
    String email;
    
    @NotEmpty(message = "password es requerido")
    @Size(min = 8, max = 26, message = "Se requiere entre 8 y 26 carácteres")
    @StrongPassword(nullable = false)
    String password;
    
    @NotEmpty(message = "role es requerido")
    @ValidEnum(enumClass = Role.class, nullable = false)
    String role;
    
    @NotEmpty(message = "state es requerido")
    @ValidEnum(enumClass = State.class, nullable = false)
    String state;

}
