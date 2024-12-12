package com.github.maximovj.linuxhubapi.linuxhub_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.maximovj.linuxhubapi.linuxhub_api.document.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> 
{

    @Query("{ '$or' : [ { 'email' : ?0 }, { 'username' : ?1 } ] }")
    List<Account> findByEmailOrUsername(String email, String username);
    
}
