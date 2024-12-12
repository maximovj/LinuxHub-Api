package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.response.ComparationResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IComparationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
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
