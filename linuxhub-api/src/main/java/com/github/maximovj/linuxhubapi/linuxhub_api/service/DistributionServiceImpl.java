package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.Download;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribution;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.DistributionRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IDistributionServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistributionServiceImpl implements IDistributionServiceImpl
{

    private final DistributionRepository distribucionRepository;

    @Override
    public String getDistribution() 
    {
        log.info("DistribucionServiceImpl::getDistribucion | Iniciando");
        
        Distribution d = Distribution.builder()
        .name("Ubuntu")
        .website("https://www.ubuntu.com")
        .description(null)
        .company(null)
        .downloads(List.of(new Download("http://localhost/f/iso_122.ison", "ISO", "2GB")))
        .build();
        
        this.distribucionRepository.save(d);
        
        log.info("DistribucionServiceImpl::getDistribucion | Finalizando-");
        return "Funciona";
    }
    
}
