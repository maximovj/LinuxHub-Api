package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.document.Distribution;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.repository.DistributionRepository;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.response.DistributionResponse;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service.interfaces.IDistributionServiceImpl;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service.utils.BuildResponseApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class DistributionServiceImpl 
extends BuildResponseApi<DistributionResponse>
implements IDistributionServiceImpl
{

    private final DistributionRepository distribucionRepository;

    @Override
    public ResponseEntity<DistributionResponse> listDistribution() 
    {
        this.initEndPoint("GET", "/v1/distribution");
        
        List<Distribution> list =  this.distribucionRepository.findAll(Sort.by(Sort.Order.desc("updated_at")));
        this.data.put("items", list);

        return this.buildSuccessResponse(HttpStatus.OK,"Lista de distribuciones linux");
    }

    @Override
    public ResponseEntity<DistributionResponse> deleteDistribution(String id) 
    {
        this.initEndPoint("DELETE", "/v1/distribution/"+id);

        if(id.isEmpty()) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Opps id no proporcionado");
        }
        
        Optional<Distribution> distro = this.distribucionRepository.findById(id);
        if(!distro.isPresent()) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Opps id no encontrado en el sistema");
        }

        this.data.put("item", distro.get());
        this.distribucionRepository.delete(distro.get());
        return this.buildSuccessResponse(HttpStatus.OK, "Distribución de linux eliminado correctamente");
    }

    @Override
    public ResponseEntity<DistributionResponse> createDistribution(Distribution body) 
    {
        this.initEndPoint("POST", "/v1/distribution");
        this.data.put("item", body);
        this.distribucionRepository.save(body);
        return this.buildSuccessResponse(HttpStatus.OK, "Distribución linux creado exitosamente");
    }

    @Override
    public ResponseEntity<DistributionResponse> findDistribution(String id) 
    {
        this.initEndPoint("GET", "/v1/distribution/"+id);

        if(id.isEmpty()) {
            this.errors.put("id", "El id ("+id+") es obligatorio");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops id es requerido");
        }    
        
        Optional<Distribution> distro = this.distribucionRepository.findById(id);
        if(!distro.isPresent()) {
            this.errors.put("id", "El id ("+id+") no fue encontrado");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops id no encontrado en el sistema");
        }

        this.data.put("item", distro.get());
        return this.buildSuccessResponse(HttpStatus.OK, "Distribución de linux encotrado exitosamente");
    }
    
}
