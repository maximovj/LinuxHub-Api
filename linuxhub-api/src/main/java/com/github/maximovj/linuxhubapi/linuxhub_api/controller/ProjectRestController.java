package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.request.CreateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.request.UpdateProjectRequest;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.ProjectResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IProjectServiceImpl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @PutMapping("/projects/{id}")
    @Override
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable String id, @Validated @RequestBody UpdateProjectRequest body) 
    {
        return this.serviceImpl.updateProject(id, body);
    }

    @DeleteMapping("/projects/{id}")
    @Override
    public ResponseEntity<ProjectResponse> deleteProject(@PathVariable String id) 
    {
        return this.serviceImpl.deleteProject(id);
    }

    @GetMapping("/projects/{id}")
    @Override
    public ResponseEntity<ProjectResponse> findProject(@PathVariable String id) 
    {
        return this.serviceImpl.findProject(id);
    }
    
}
