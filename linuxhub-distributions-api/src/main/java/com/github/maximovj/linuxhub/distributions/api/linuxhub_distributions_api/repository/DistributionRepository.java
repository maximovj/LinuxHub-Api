package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.document.Distribution;

@Repository
public interface DistributionRepository extends MongoRepository<Distribution, String>
{
    
}
