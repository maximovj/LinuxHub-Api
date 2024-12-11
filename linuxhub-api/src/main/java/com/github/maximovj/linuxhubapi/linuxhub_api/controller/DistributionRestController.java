package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribution;
import com.github.maximovj.linuxhubapi.linuxhub_api.response.DistributionResponse;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IDistributionServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/v1")
public class DistributionRestController implements IDistributionServiceImpl
{

    @Autowired
    private IDistributionServiceImpl serviceImpl;

    @PostMapping("/distribution")
    @Override
    public ResponseEntity<DistributionResponse> createDistribution(@RequestBody Distribution body) {
        return this.serviceImpl.createDistribution(body);
    }

    @GetMapping("/distribution")
    @Override
    public ResponseEntity<DistributionResponse> listDistribution() {
        return this.serviceImpl.listDistribution();
    }

    @GetMapping("/distribution/{id}")
    @Override
    public ResponseEntity<DistributionResponse> findDistribution(@PathVariable String id) {
        return this.serviceImpl.findDistribution(id);
    }
    
    @DeleteMapping("/distribution/{id}")
    @Override
    public ResponseEntity<DistributionResponse> deleteDistribution(@PathVariable String id) {
        return this.serviceImpl.deleteDistribution(id);
    }
    
}
