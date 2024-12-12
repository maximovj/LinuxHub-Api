package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribution;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.DistributionRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.DistributionResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IDistributionServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistributionServiceImpl implements IDistributionServiceImpl
{

    private final DistributionRepository distribucionRepository;
    private HashMap<String, Object> data;
    private HashMap<String, Object> errors;
    private DistributionResponse apiResponse;

    public void init(String type, String uri) 
    {
        this.apiResponse = new DistributionResponse();
        this.apiResponse.setType(type);
        this.apiResponse.setUri(uri);
        this.apiResponse.setBaseUrl("http://localhot:5808"+uri);
        this.data = new HashMap<>();
        this.errors = new HashMap<>();
    }

    public ResponseEntity<DistributionResponse> buildSuccessResponse(String content, HashMap<String,Object> data) 
    {
        this.apiResponse.setResTitle("API Distribution");
        this.apiResponse.setResContent(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(this.data);
        return ResponseEntity.ok(this.apiResponse);
    }

    public ResponseEntity<DistributionResponse> buildErrorResponse(HttpStatus status, String content,  HashMap<String, Object> errors) 
    {
        this.apiResponse.setResTitle("API Distribution");
        this.apiResponse.setResContent(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("error");
        this.apiResponse.setSuccess(false);
        this.apiResponse.setErrors(errors);
        return ResponseEntity.status(status).body(this.apiResponse);
    }

    @Override
    public ResponseEntity<DistributionResponse> listDistribution() 
    {
        this.init("GET", "/v1/distribution");
        
        List<Distribution> list =  this.distribucionRepository.findAll();
        this.data.put("items", list);

        return this.buildSuccessResponse("Lista de distribuciones linux", data);
    }

    @Override
    public ResponseEntity<DistributionResponse> deleteDistribution(String id) 
    {
        this.init("DELETE", "/v1/distribution/"+id);

        if(id.isEmpty()) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Opps id no proporcionado", this.errors);
        }
        
        Optional<Distribution> distro = this.distribucionRepository.findById(id);
        if(!distro.isPresent()) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Opps id no encontrado en el sistema", this.errors);
        }

        this.data.put("item", distro.get());
        this.distribucionRepository.delete(distro.get());
        return this.buildSuccessResponse("Distribución de linux eliminado correctamente", data);
    }

    @Override
    public ResponseEntity<DistributionResponse> createDistribution(Distribution body) 
    {
        this.init("POST", "/v1/distribution");
        this.data.put("item", body);
        this.distribucionRepository.save(body);
        return this.buildSuccessResponse("Distribución linux creado exitosamente", data);
    }

    @Override
    public ResponseEntity<DistributionResponse> findDistribution(String id) 
    {
        this.init("GET", "/v1/distribution/"+id);

        if(id.isEmpty()) {
            this.errors.put("id", "El id ("+id+") es obligatorio");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops id es requerido", this.errors);
        }    
        
        Optional<Distribution> distro = this.distribucionRepository.findById(id);
        if(!distro.isPresent()) {
            this.errors.put("id", "El id ("+id+") no fue encontrado");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops id no encontrado en el sistema", this.errors);
        }

        this.data.put("item", distro.get());
        return this.buildSuccessResponse("Distribución de linux encotrado exitosamente", this.data);
    }
    
}
