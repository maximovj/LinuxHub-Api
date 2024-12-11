package com.github.maximovj.linuxhubapi.linuxhub_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.Download;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribucion;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.DistribucionRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IDistribucionServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistribucionServiceImpl implements IDistribucionServiceImpl
{

    private final DistribucionRepository distribucionRepository;

    @Override
    public String getDistribucion() 
    {
        log.info("DistribucionServiceImpl::getDistribucion | Iniciando");
        
        Distribucion d = Distribucion.builder()
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
