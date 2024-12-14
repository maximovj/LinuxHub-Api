package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.data.distribution;

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
public class Feature 
{

    @Field("focus")
    @Nullable
    String focus; // Server, Desk
    
    @Field("package_manager")
    @Nullable
    String package_manager; // APT, DFN, Pacman
    
    @Field("default_environment")
    @Nullable
    String default_environment; // GNOME, KDE, None
    
    @Field("gpu_support")
    @Nullable
    String gpu_support; // NVIDIA, AMD, Intel
    
    @Field("uefi_compatibility")
    @Nullable
    Boolean uefi_compatibility;
    
    @Field("bios_compatibility")
    @Nullable
    Boolean bios_compatibility;
    
    @Field("certifications")
    @Nullable
    Boolean certifications;
    
    @Field("memory_efficiency")
    @Nullable
    Level memory_efficiency;

    @Field("energy_consumption")
    @Nullable
    Level energy_consumption;
    
    @Field("community_size")
    @Nullable
    Level community_size;
    
    @Field("documentation_quality")
    @Nullable
    Level documentation_quality;

}
