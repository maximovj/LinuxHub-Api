package com.github.maximovj.linuxhubapi.linuxhub_api.document;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.Download;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Document(collection = "distribution")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS) // Excluye campos nulos de la serialización
public class Distribution {

    @Id
    String id;

    @Field("name")
    @Nullable
    String name;
    
    @Field("description")
    @Nullable
    String description;
    
    @Field("company")
    @Nullable
    String company;
    
    @Field("website")
    @Nullable
    String website;
    
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
