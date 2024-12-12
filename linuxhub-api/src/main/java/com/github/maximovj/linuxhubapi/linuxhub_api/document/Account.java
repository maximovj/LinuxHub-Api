package com.github.maximovj.linuxhubapi.linuxhub_api.document;

import java.time.Instant;

import javax.management.relation.Role;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.State;
import com.mongodb.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Account {

    @Id
    String id;

    @Field("avatar")
    @Nullable
    String avatar;

    @Field("username")
    @Nullable
    String username;

    @Field("email")
    @Nullable
    String email;
    
    @Field("password")
    @Nullable
    String password;

    @Field("role")
    Role role;

    @Field("state")
    State state;

    @CreatedDate
    Instant created_at;
    
    @LastModifiedDate
    Instant updated_at;
    
}
