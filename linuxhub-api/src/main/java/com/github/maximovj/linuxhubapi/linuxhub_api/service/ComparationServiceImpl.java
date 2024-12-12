package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.distribution.Technician;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribution;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.DistributionRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.ComparationResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IComparationServiceImpl;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.utils.ComparativeUtil;

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

        ComparativeUtil comparative = ComparativeUtil.builder()
            .distro_after(distro_after.get())
            .distro_before(distro_before.get())
            .build();

        if(comparative.getTechnicianBefore() != null) {
            this.data.put(
                distro_before.get().getId(), 
                comparative.getTechnician(comparative.getTechnicianBefore())
            );
        }
        
        if(comparative.getTechnicianAfter() != null) {
            this.data.put(
                distro_after.get().getId(), 
                comparative.getTechnician(comparative.getTechnicianAfter())
            );
        }

        if( comparative.getTechnicianBefore() != null &&
            comparative.getTechnicianAfter() != null) {
            this.data.put("suggest", comparative.getSuggest());
            this.data.put("comparative", comparative.getComparative());
        }

        return this.buildSuccessResponse("Comparado exitosamnte", this.data);
    }
    
}
