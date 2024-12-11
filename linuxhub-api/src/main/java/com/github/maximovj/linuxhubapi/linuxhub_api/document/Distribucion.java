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
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Document(collection = "distribucion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = false, chain = true)
@Builder
public class Distribucion {

    @Id
    String id;

    @Field("name")
    String name;
    
    @Field("description")
    String description;
    
    @Field("company")
    String company;
    
    @Field("website")
    String website;
    
    @Field("tags")
    List<String> tags;
    
    @Field("downloads")
    List<Download> downloads;

    @CreatedDate
    Instant created_at;

    @LastModifiedDate
    Instant updated_at;
    
}
