package com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhubapi.linuxhub_api.response.DistributionResponse;

public interface IDistributionServiceImpl 
{

    // Listar todas las distribucines linux
    public ResponseEntity<DistributionResponse> listDistribution();
    
}
