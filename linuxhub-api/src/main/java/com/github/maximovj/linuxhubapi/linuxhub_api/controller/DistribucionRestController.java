package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IDistribucionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/v1")
public class DistribucionRestController 
{

    @Autowired
    private IDistribucionServiceImpl serviceImpl;

    @GetMapping("/distribucion")
    public String getDistribucion() {
        return this.serviceImpl.getDistribucion();
    }
    
}
