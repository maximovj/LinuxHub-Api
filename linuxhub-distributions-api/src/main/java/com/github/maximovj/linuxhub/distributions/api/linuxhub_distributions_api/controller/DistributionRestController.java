package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.document.Distribution;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.response.DistributionResponse;
import com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.service.interfaces.IDistributionServiceImpl;

@RestController
@CrossOrigin()
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