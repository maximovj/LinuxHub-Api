package com.github.maximovj.linuxhubapi.linuxhub_api.request;

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

    String avatar;
    
    @NotEmpty(message = "username es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", 
    message = "username no tiene un formato válido")
    @Size(min = 8, max = 26, message = "Se requiere entre 8 y 26 carácteres")
    String username;
    
    @NotEmpty(message = "email es requerido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", 
    message = "email no tiene un formato válido")
    String email;
    
    @NotEmpty(message = "password es requerido")
    @Size(min = 8, max = 26, message = "Se requiere entre 8 y 26 carácteres")
    String password;
    
    @NotNull(message = "role es obligatorio")
    @Pattern(regexp = "admin|maintainer|user|guest", message = "El rol debe ser uno de: admin, maintainer, user, guest")
    String role;
    
    @NotNull(message = "state es obligatorio")
    @Pattern(regexp = "active|block", message = "El valor debe ser active, block")
    String state;

}
