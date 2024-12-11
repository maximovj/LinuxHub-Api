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
    private DistributionResponse apiResponse;

    public void init(String type, String uri) 
    {
        this.apiResponse = new DistributionResponse();
        this.apiResponse.setType(type);
        this.apiResponse.setUri(uri);
        this.apiResponse.setBase_url("http://localhot:5808"+uri);
        this.data = new HashMap<>();
    }

    public ResponseEntity<DistributionResponse> buildSuccessResponse(String content, HashMap<String,Object> data) 
    {
        this.apiResponse.setRes_title("API Distribution");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(this.data);
        return ResponseEntity.ok(this.apiResponse);
    }

    public ResponseEntity<DistributionResponse> buildErrorResponse(HttpStatus status, String content) 
    {
        this.apiResponse.setRes_title("API Distribution");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("error");
        this.apiResponse.setSuccess(false);
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
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Opps id no proporcionado");
        }
        
        Optional<Distribution> distro = this.distribucionRepository.findById(id);
        if(!distro.isPresent()) {
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Opps id no encontrado en el sistema");
        }

        this.data.put("item", distro.get());
        this.distribucionRepository.delete(distro.get());
        return this.buildSuccessResponse("Distribución de linux eliminado correctamente", data);
    }
    
}
