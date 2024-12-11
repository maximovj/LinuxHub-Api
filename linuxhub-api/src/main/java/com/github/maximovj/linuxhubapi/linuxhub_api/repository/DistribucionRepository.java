package com.github.maximovj.linuxhubapi.linuxhub_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribucion;

@Repository
public interface DistribucionRepository extends MongoRepository<Distribucion, String> 
{

}
