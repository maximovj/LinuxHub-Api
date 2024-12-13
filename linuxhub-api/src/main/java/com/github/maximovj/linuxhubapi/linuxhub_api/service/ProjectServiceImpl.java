package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.project.Environment;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Account;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Project;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.AccountRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.ProjectRespository;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.ProjectResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IProjectServiceImpl;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.utils.BuildResponseApi;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl 
extends BuildResponseApi<ProjectResponse> 
implements IProjectServiceImpl
{
    private final ProjectRespository projectRespository;
    private final AccountRepository accountRepository;

    @Override
    public ResponseEntity<ProjectResponse> createProject(CreateProjectRequest body) 
    {
        this.initEndPoint("POST", "/v1/projects");

        if(body == null) {
            this.errors.put("body", "El body es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops no hay un cuerpo");
        }
        
        Optional<Account> account = this.accountRepository.findById(body.getAccount_id());
        if(!account.isPresent()) {
            this.errors.put("account", "La cuenta id no fue encontrada");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "La cuenta no fue encontrada");
        }

        Project project = Project
                .builder()
                .name(body.getName())
                .description(body.getDescription())
                .accountId(body.getAccount_id())
                .baseUrl(body.getBaseUrl())
                .host(body.getHost())
                .environment(Environment.valueOf(body.getEnvironment()))
                .build();

        this.projectRespository.save(project);
        this.data.put("project", body);
        return this.buildSuccessResponse(HttpStatus.CREATED, "Proyecto creado exitosamente");
    }
    
}
