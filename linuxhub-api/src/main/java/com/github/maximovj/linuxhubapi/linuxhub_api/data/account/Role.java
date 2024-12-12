package com.github.maximovj.linuxhubapi.linuxhub_api.data.account;

public enum Role {
    admin, // Acceso a CRUD de cuentas, perfiles, distribuciones, proyectos, tokens etc. 
    maintainer, // Acceso a CRUD de distribuciones
    user, // Acceso a CRUD una cuenta(Leer, Actualizar y Eliminar), un perfil(Leer, Actualizar), proyectos, tokens
    guest, // Acceso de solo lectura a distribuciones 
}
