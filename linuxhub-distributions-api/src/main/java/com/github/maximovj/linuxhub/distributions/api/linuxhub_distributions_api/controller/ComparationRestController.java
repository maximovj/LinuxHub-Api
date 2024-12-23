package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.response.ComparationResponse;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service.interfaces.IComparationServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/v1")
public class ComparationRestController implements IComparationServiceImpl
{

    @Autowired
    private IComparationServiceImpl serviceImpl;

    @GetMapping("/comparation/{id_before}/between/{id_after}")
    @Override
    public ResponseEntity<ComparationResponse> distributionCompration(@PathVariable String id_before, @PathVariable String id_after) 
    {
        return this.serviceImpl.distributionCompration(id_before, id_after);    
    }

}