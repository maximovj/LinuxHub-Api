package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.document.Distribution;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.response.DistributionResponse;

public interface IDistributionServiceImpl 
{
    
    // Listar todas las distribucines linux
    public ResponseEntity<DistributionResponse> listDistribution();

    // Eliminar una distribución de linux mediante _id
    public ResponseEntity<DistributionResponse> deleteDistribution(String id);

    // Crear una distribución de linux mediante json
    public ResponseEntity<DistributionResponse> createDistribution(Distribution body);

    // Buscar una distribución de linux mediante _id
    public ResponseEntity<DistributionResponse> findDistribution(String id);

}
