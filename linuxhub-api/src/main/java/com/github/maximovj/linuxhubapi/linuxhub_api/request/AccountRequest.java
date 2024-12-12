package com.github.maximovj.linuxhubapi.linuxhub_api.request;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.Role;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.State;
import com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_enum.ValidEnum;
import com.github.maximovj.linuxhubapi.linuxhub_api.validation.valid_role.ValidRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest 
{

    // Permite null
    String avatar;
    
    @NotEmpty(message = "username es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", 
    message = "username no tiene un formato válido")
    @Size(min = 8, max = 26, message = "Se requiere entre 8 y 26 carácteres")
    String username;
    
    @NotEmpty(message = "email es requerido")
    @Email
    String email;
    
    @NotEmpty(message = "password es requerido")
    @Size(min = 8, max = 26, message = "Se requiere entre 8 y 26 carácteres")
    String password;
    
    @NotEmpty(message = "role es requerido")
    @ValidEnum(enumClass = Role.class)
    String role;
    
    @NotEmpty(message = "state es requerido")
    @ValidEnum(enumClass = State.class)
    String state;

}
