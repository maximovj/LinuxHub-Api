package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribution;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.DistributionRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.ComparationResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IComparationServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
@RequiredArgsConstructor
public class ComparationServiceImpl implements IComparationServiceImpl
{

    private final DistributionRepository distributionRepository;
    private HashMap<String, Object> data;
    private HashMap<String, Object> errors;
    private ComparationResponse apiResponse;

    private void initEndPoint(String type, String uri) 
    {
        this.apiResponse = new ComparationResponse();
        this.apiResponse.setType(type);
        this.apiResponse.setUri(uri);
        this.apiResponse.setBase_url("http://localhost:5808"+uri);
        this.data = new HashMap<>();
        this.errors = new HashMap<>();
    }

    private ResponseEntity<ComparationResponse> buildSuccessResponse(String content, HashMap<String, Object> data)
    {
        this.apiResponse.setRes_title("API Comparation");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(HttpStatus.OK.value());
        this.apiResponse.setStatus("success");
        this.apiResponse.setSuccess(true);
        this.apiResponse.setData(this.data);
        return ResponseEntity.ok(this.apiResponse);
    }

    private ResponseEntity<ComparationResponse> buildErrorResponse(HttpStatus status, String content, HashMap<String, Object> errors)
    {
        this.apiResponse.setRes_title("API Comparation");
        this.apiResponse.setRes_content(content);
        this.apiResponse.setCode(status.value());
        this.apiResponse.setStatus("error");
        this.apiResponse.setSuccess(false);
        this.apiResponse.setErrors(errors);
        return ResponseEntity.status(status).body(this.apiResponse);
    }

    private long convertToMB(String size) {
        if (size == null || size.isEmpty()) {
            return 0;
        }
        size = size.trim().toUpperCase();
    
        if (size.endsWith("GB")) {
            return (long) (Double.parseDouble(size.replace("GB", "").trim()) * 1024);
        } else if (size.endsWith("MB")) {
            return (long) Double.parseDouble(size.replace("MB", "").trim());
        } else if (size.endsWith("TB")) {
            return (long) (Double.parseDouble(size.replace("TB", "").trim()) * 1024 * 1024);
        }
    
        throw new IllegalArgumentException("Unsupported size format: " + size);
    }

    @Override
    public ResponseEntity<ComparationResponse> distributionCompration(String id_before, String id_after) {
        this.initEndPoint("GET", "/comparation/"+id_before+"/between"+"/"+id_after);

        if(id_before.isEmpty() || id_after.isEmpty()) {
            this.errors.put("id_before", "El id_before es requerido");
            this.errors.put("id_after", "El id_after es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops se requiere dos id válidos", errors);
        }
        
        Optional<Distribution> distro_before = this.distributionRepository.findById(id_before);
        Optional<Distribution> distro_after = this.distributionRepository.findById(id_after);
        
        if(!distro_before.isPresent() || !distro_after.isPresent()) {
            this.errors.put("id_before", "El id_before no encontrado en el sistema");
            this.errors.put("id_after", "El id_after no encontrado en el sistema");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops distribuciones no encontrados en el sistema", errors);
        }
        
        HashMap<String, Object> dataBeforeDistro = new HashMap<>();
        if(distro_before.get().getTechnician() != null) {
            dataBeforeDistro.put("ram", distro_before.get().getTechnician().getRam());
            dataBeforeDistro.put("memory", distro_before.get().getTechnician().getMemory());
            dataBeforeDistro.put("process", distro_before.get().getTechnician().getProcess());
            this.data.put(distro_before.get().getName(), dataBeforeDistro);
        }

        HashMap<String, Object> dataAfterDistro = new HashMap<>();
        if(distro_after.get().getTechnician() != null) {
            dataAfterDistro.put("ram", distro_after.get().getTechnician().getRam());
            dataAfterDistro.put("memory", distro_after.get().getTechnician().getMemory());
            dataAfterDistro.put("process", distro_after.get().getTechnician().getProcess());
            this.data.put(distro_after.get().getName(), dataAfterDistro);
        }

        if(distro_before.get().getTechnician() != null &&
        distro_after.get().getTechnician() != null) {
            HashMap<String, Object> better = new HashMap<>();
            better.put("ram", "1GB");
            better.put("memory", "15GB");
            better.put("proccess", "AMD 2");
            this.data.put("better", better);
        }

        return this.buildSuccessResponse("Comparado exitosamnte", this.data);
    }

    
    
}
