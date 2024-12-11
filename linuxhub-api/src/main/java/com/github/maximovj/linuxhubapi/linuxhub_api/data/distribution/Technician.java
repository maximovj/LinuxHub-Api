package com.github.maximovj.linuxhubapi.linuxhub_api.data.distribution;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Technician 
{

    @Field("distrowatch_ranking")
    @Nullable
    Integer distrowatch_ranking; // En entero
    
    @Field("steam_popularity")
    @Nullable
    Integer steam_popularity; // En entero

    @Field("ram")
    @Nullable
    Long ram; // En MB
    
    @Field("disk")
    @Nullable
    Long disk; // En MB
    
    @Field("installation_time")
    @Nullable
    Long installation_time; // En minutos (Decimal)
    
    @Field("configuration_time")
    @Nullable
    Long configuration_time; // En minutos (Decimal)
    
    @Field("boot_speed")
    @Nullable
    Long boot_speed; // En segundos (Decimal)
    
    @Field("speed")
    @Nullable
    Double speed; // En segundos (Decimal)

    @Field("ram_consumption_idle")
    @Nullable
    Long ram_consumption_idle; // En MB

    @Field("cpu_consumption_idle")
    @Nullable
    Long cpu_consumption_idle; // En MB

}
