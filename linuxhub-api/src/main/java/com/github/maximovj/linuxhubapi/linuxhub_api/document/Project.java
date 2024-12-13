package com.github.maximovj.linuxhubapi.linuxhub_api.document;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.project.Environment;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS) // Incluir valores nulos
public class Project 
{

    @Id
    String id;

    @Field("cover")
    @Nullable
    String cover; 
    
    @Field("name")
    @Nullable
    String name;
    
    @Field("description")
    @Nullable
    String description;
    
    @Field("environment")
    @Nullable
    Environment environment;
    
    @Field("host")
    @Nullable
    String host;
    
    @Field("base_url")
    @Nullable
    @JsonProperty("base_url")
    String baseUrl;

    @Field("account_id")
    @Nullable
    @JsonProperty("account_id")
    String accountId; // Referencia a una cuenta (similar a una clave foránea)
    
    @Field("created_at")
    @CreatedDate
    @JsonProperty("created_at")
    Instant createdAt;
    
    @Field("updated_at")
    @LastModifiedDate
    @JsonProperty("updated_at")
    Instant updatedAt;

}
