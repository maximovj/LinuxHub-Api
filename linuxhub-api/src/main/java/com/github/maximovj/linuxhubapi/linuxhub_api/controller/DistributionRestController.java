package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.response.DistributionResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IDistributionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/v1")
public class DistributionRestController implements IDistributionServiceImpl
{

    @Autowired
    private IDistributionServiceImpl serviceImpl;

    @GetMapping("/distribution")
    @Override
    public ResponseEntity<DistributionResponse> listDistribution() {
        return this.serviceImpl.listDistribution();
    }
    
}
