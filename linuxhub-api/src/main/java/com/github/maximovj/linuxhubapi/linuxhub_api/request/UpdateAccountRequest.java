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
public class UpdateAccountRequest 
{

    // Permite null
    String avatar;
    
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", 
    message = "username no tiene un formato válido")
    @Size(min = 4, max = 24, message = "Se requiere entre 4 y 24 carácteres")
    String username;
    
    @Email
    String email;
    
    @Size(min = 8, max = 26, message = "Se requiere entre 8 y 26 carácteres")
    @StrongPassword(nullable = true)
    String password;
    
    @ValidEnum(enumClass = Role.class, nullable = true)
    String role;
    
    @ValidEnum(enumClass = State.class, nullable = true)
    String state;

}
