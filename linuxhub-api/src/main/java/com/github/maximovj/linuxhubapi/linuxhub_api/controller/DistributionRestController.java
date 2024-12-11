package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IDistributionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/v1")
public class DistributionRestController 
{

    @Autowired
    private IDistributionServiceImpl serviceImpl;

    @GetMapping("/distribution")
    public String getDistritucion() {
        return this.serviceImpl.getDistribution();
    }
    
}
