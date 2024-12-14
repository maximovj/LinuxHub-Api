package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.response.ComparationResponse;

public interface IComparationServiceImpl 
{

    // Comparar entre dos distribuciones linux
    public ResponseEntity<ComparationResponse> distributionCompration(String id_before, String id_after);
    
}
