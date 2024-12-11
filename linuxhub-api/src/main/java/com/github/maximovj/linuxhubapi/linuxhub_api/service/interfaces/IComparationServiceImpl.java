package com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.maximovj.linuxhubapi.linuxhub_api.response.ComparationResponse;

public interface IComparationServiceImpl 
{

    // Comparar entre dos distribuciones linux
    public ResponseEntity<ComparationResponse> distributionCompration(String id_before, String id_after);
    
}
