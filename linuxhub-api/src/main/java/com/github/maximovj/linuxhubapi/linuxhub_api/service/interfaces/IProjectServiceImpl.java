package com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.ProjectResponse;

public interface IProjectServiceImpl 
{
    // Crear un nuevo proyecto usando el body
    public ResponseEntity<ProjectResponse> createProject(CreateProjectRequest body);

}
