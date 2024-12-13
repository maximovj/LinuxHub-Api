package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.Role;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.project.Environment;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Account;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Project;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.AccountRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.ProjectRespository;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.UpdateProjectRequest;
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

    @Override
    public ResponseEntity<ProjectResponse> updateProject(String id, UpdateProjectRequest body) {
        this.initEndPoint("PUT", "/v1/projects/" + id);

        if(body == null) {
            this.errors.put("body", "El body es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops no hay body");
        }

        if(id.isEmpty()) {
            this.errors.put("id", "El proyecto id es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops proyecto id es requerido");
        }
        
        Optional<Project> find_project = this.projectRespository.findById(id);
        if(!find_project.isPresent()) { 
            this.errors.put("project", "El proyecto no existe");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops proyecto no encontrado en el sistema");
        }

        Project project = find_project.get();
        this.updateIfNotEmpty(body.getCover(), project::setCover);
        this.updateIfNotEmpty(body.getName() , project::setName);
        this.updateIfNotEmpty(body.getDescription(), project::setDescription);
        this.updateIfNotEmpty(body.getBase_url(), project::setBaseUrl);
        this.updateIfNotEmpty(body.getHost(), project::setHost);
        this.updateIfNotEmpty(body.getEnvironment() , environment -> project.setEnvironment(Environment.valueOf(environment)));
        this.updateIfNotEmpty(body.getAccount_id(), project::setAccountId);
        
        this.projectRespository.save(project);
        this.data.put("project", project);
        return this.buildSuccessResponse(HttpStatus.OK, "Proyecto actualizado correctamente.");
    }

    @Override
    public ResponseEntity<ProjectResponse> deleteProject(String id) {
        this.initEndPoint("DELETE", "/v1/projects/" + id);

        if(id.isEmpty()) {
            this.errors.put("id", "El proyecto id es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops proyecto id es requerido");
        }
        
        Optional<Project> find_project = this.projectRespository.findById(id);
        if(!find_project.isPresent()) {
            this.errors.put("project", "El proyecto no existe");
            return this.buildErrorResponse(HttpStatus.NOT_FOUND, "Oops proyecto no fue encontrada en el sistema");
        }

        this.projectRespository.delete(find_project.get());
        this.data.put("project", find_project.get());
        return this.buildSuccessResponse(HttpStatus.OK, "Proyecto eliminado correctamente");
    }

    @Override
    public ResponseEntity<ProjectResponse> findProject(String id) 
    {
        this.initEndPoint("GET", "/v1/projects/" + id);

        if(id.isEmpty()) {
            this.errors.put("id", "El proyecto id es requerido");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops proyecto id es requerido");
        }
        
        Optional<Project> find_project = this.projectRespository.findById(id);
        if(!find_project.isPresent()) {
            this.errors.put("project", "El proyecto no existe");
            return this.buildErrorResponse(HttpStatus.BAD_REQUEST, "Oops proyecto no encontrado en el sistema");
        }

        this.data.put("project", find_project.get());
        return this.buildSuccessResponse(HttpStatus.OK, "El proyecto fue localizado exitosamente");
    }

    @Override
    public ResponseEntity<ProjectResponse> listProject() 
    {
        this.initEndPoint("GET", "/v1/projects");

        List<Project> projects = this.projectRespository.findAll(Sort.by(Sort.Order.desc("updated_at")));

        this.data.put("projects", projects);
        return this.buildSuccessResponse(HttpStatus.OK, "Lista de proyectos");
    }
    
}
