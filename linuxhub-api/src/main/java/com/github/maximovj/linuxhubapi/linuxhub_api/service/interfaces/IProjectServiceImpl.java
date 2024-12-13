package com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces;

import org.springframework.http.ResponseEntity;

import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.UpdateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.ProjectResponse;

public interface IProjectServiceImpl 
{
    // Crear un nuevo proyecto usando el body
    public ResponseEntity<ProjectResponse> createProject(CreateProjectRequest body);
    
    // Modificar un proyecto usando el id y el body
    public ResponseEntity<ProjectResponse> updateProject(String id, UpdateProjectRequest body); 

}
