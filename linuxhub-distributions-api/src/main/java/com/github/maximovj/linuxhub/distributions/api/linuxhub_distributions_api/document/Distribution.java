package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.document;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.data.distribution.Download;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.data.distribution.Feature;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.data.distribution.Technician;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "distributions")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Distribution 
{

    @Id
    String id;

    @Field("name")
    @Nullable
    String name;
    
    @Field("description")
    @Nullable
    String description;
    
    @Field("enterprise_support")
    @Nullable
    String enterprise_support;

    @Field("official_site")
    @Nullable
    String official_site;

    @Field("logo")
    @Nullable
    String logo;

    @Field("processor")
    @Nullable
    String processor;
    
    @Field("license")
    @Nullable
    String license; // GPL

    @Field("feature")
    @Nullable
    Feature feature;

    @Field("technician")
    @Nullable
    Technician technician;

    @Field("tags")
    @Nullable
    List<String> tags;
    
    @Field("downloads")
    @Nullable
    List<Download> downloads;

    @CreatedDate
    Instant created_at;

    @LastModifiedDate
    Instant updated_at;
    
}
