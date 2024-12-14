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
public class Download 
{

    @Field("link")
    @Nullable
    String link;

    @Field("format")
    @Nullable
    String format;
    
    @Field("size")
    @Nullable
    String size;
    
}
