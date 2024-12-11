package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.HashMap;
import java.util.List;

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
    private DistributionResponse apiResponse;

    public ResponseEntity<DistributionResponse> buildSuccessResponse(String content, HashMap<String,Object> data) 
    {
        this.apiResponse.setRes_title("API Distribution");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(data);
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
        this.apiResponse = new DistributionResponse();
        this.apiResponse.setType("GET");
        this.apiResponse.setUri("/v1/distribution");
        this.apiResponse.setBase_url("http://localhot:5808/v1/distribution");
        HashMap<String, Object> data = new HashMap<>();

        List<Distribution> list =  this.distribucionRepository.findAll();
        data.put("items", list);

        return this.buildSuccessResponse("Lista de distribuciones linux", data);
    }
    
}
