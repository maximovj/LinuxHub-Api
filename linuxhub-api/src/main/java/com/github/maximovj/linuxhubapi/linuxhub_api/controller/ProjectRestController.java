package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.ProjectResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IProjectServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1")
public class ProjectRestController implements IProjectServiceImpl
{

    @Autowired
    private IProjectServiceImpl serviceImpl;

    @PostMapping("/projects")
    @Override
    public ResponseEntity<ProjectResponse> createProject(@Validated @RequestBody CreateProjectRequest body) 
    {
        return this.serviceImpl.createProject(body);
    }
    
}
