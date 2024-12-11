package com.github.maximovj.linuxhubapi.linuxhub_api.data;

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
public class Technician {

    @Field("ram")
    @Nullable
    String ram;
    
    @Field("memory")
    @Nullable
    String memory;
    
    @Field("process")
    @Nullable
    String process;
    
}
