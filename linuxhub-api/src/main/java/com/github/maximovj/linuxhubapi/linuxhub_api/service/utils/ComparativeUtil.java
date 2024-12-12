package com.github.maximovj.linuxhubapi.linuxhub_api.service.utils;

import java.util.HashMap;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.distribution.Feature;
import com.github.maximovj.linuxhubapi.linuxhub_api.data.distribution.Technician;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Distribution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComparativeUtil 
{

    Distribution distro_before;
    Distribution distro_after;

    public HashMap<String, Object> getTechnician(Technician technician) {
        HashMap<String, Object> dataDistro = new HashMap<>();
        dataDistro.put("ram", technician.getRam());
        dataDistro.put("disk", technician.getDisk());
        dataDistro.put("installation_time", technician.getInstallation_time());
        dataDistro.put("configuration_time", technician.getConfiguration_time());
        dataDistro.put("boot_speed", technician.getBoot_speed());
        dataDistro.put("speed", technician.getSpeed());
        dataDistro.put("ram_consumption_idle", technician.getRam_consumption_idle());
        dataDistro.put("cpu_consumption_idle", technician.getCpu_consumption_idle());
        dataDistro.put("score", this.getScore(technician));
        return dataDistro;
    }

    public HashMap<String, Object> getComparative() {
        HashMap<String, Object> dataComparative = new HashMap<>();
        dataComparative.put("ram", this.getRam());
        dataComparative.put("disk", this.getDisk());
        dataComparative.put("installation_time", this.getInstalationTime());
        dataComparative.put("configuration_time", this.getConfigurationTime());
        dataComparative.put("boot_speed", this.getBootSpeed());
        dataComparative.put("speed", this.getSpeed());
        dataComparative.put("ram_consumption_idle", this.getRamConsumptionIdle());
        dataComparative.put("cpu_consumption_idle", this.getCpuConsumptionIdle());
        return dataComparative;
    }

    public HashMap<String, Object> getSuggest()
    {

        HashMap<String, Object> technician_before = this.getTechnician(this.getTechnicianBefore());
        HashMap<String, Object> technician_after =  this.getTechnician(this.getTechnicianAfter());

        if( (Double) technician_before.get("score") > (Double) technician_after.get("score") ) 
        {
            return technician_before;
        }
        
        return technician_after;
    }

    public Technician getTechnicianBefore()
    {
        return this.distro_before.getTechnician();  
    }

    public Technician getTechnicianAfter()
    {
        return this.distro_after.getTechnician();  
    }

    public Long getRam() 
    {
        Long ram_before = this.getTechnicianBefore().getRam();
        Long ram_after = this.getTechnicianAfter().getRam();
        return ram_before < ram_after ? ram_before : ram_after;
    }
    
    public Long getDisk() 
    {
        Long disk_before = this.getTechnicianBefore().getDisk();
        Long disk_after = this.getTechnicianAfter().getDisk();
        return disk_before < disk_after ? disk_before : disk_after;
    }

    public Long getInstalationTime() 
    {
        Long instalation_before = this.getTechnicianBefore().getInstallation_time();
        Long instalation_after = this.getTechnicianAfter().getInstallation_time();
        return instalation_before < instalation_after ? instalation_before : instalation_after;
    }

    public Long getConfigurationTime() 
    {
        Long configuration_before = this.getTechnicianBefore().getConfiguration_time();
        Long configuration_after = this.getTechnicianAfter().getConfiguration_time();
        return configuration_before < configuration_after ? configuration_before : configuration_after;
    }

    public Long getBootSpeed() 
    {
        Long boot_speed_before = this.getTechnicianBefore().getBoot_speed();
        Long boot_speed_after = this.getTechnicianAfter().getBoot_speed();
        return boot_speed_before < boot_speed_after ? boot_speed_before : boot_speed_after;
    }

    public Double getSpeed() 
    {
        Double speed_before = this.getTechnicianBefore().getSpeed();
        Double speed_after = this.getTechnicianAfter().getSpeed();
        return speed_before < speed_after ? speed_before : speed_after;
    }

    public Long getRamConsumptionIdle() 
    {
        Long ram_idle_before = this.getTechnicianBefore().getRam_consumption_idle();
        Long ram_idle_after = this.getTechnicianAfter().getRam_consumption_idle();
        return ram_idle_before < ram_idle_after ? ram_idle_before : ram_idle_after;
    }

    public Long getCpuConsumptionIdle() 
    {
        Long cpu_idle_before = this.getTechnicianBefore().getCpu_consumption_idle();
        Long cpu_idle_after = this.getTechnicianAfter().getCpu_consumption_idle();
        return cpu_idle_before < cpu_idle_after ? cpu_idle_before : cpu_idle_after;
    }

    public Double getScore(Technician technician) 
    {
        double score = 0;
        HashMap<String, Object>  comparative = this.getComparative();

        if(technician.getRam() 
         == comparative.get("ram")) {
            score++;
        }

        if(technician.getDisk() 
            == comparative.get("disk")) {
            score++;
        }

        if(technician.getInstallation_time()
         == comparative.get("installation_time")) {
            score++;
        }

        if(technician.getConfiguration_time()
         == comparative.get("configuration_time")) {
            score++;
        }

        if(technician.getBoot_speed()
         == comparative.get("boot_speed")) {
            score++;
        }

        if(technician.getSpeed()
         == comparative.get("speed")) {
            score++;
        }

        if(technician.getRam_consumption_idle()
         == comparative.get("ram_consumption_idle")) {
            score++;
        }

        if(technician.getCpu_consumption_idle()
         == comparative.get("cpu_consumption_idle")) {
            score++;
        }

        return score;
    }
   
}
