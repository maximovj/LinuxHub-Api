package com.github.maximovj.linuxhub.distributions.api.linuxhub_distributions_api.response;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS)
public class ApiResponse 
{

   @JsonProperty("res_title")
    String resTitle;
    
    @JsonProperty("res_content")
    String resContent;
    
    String uri;
    
    @JsonProperty("base_url")
    String baseUrl;
    
    String type;
    
    String status;
    
    Integer code;
    
    Boolean success;
    
    HashMap<String, Object> data;
    
    HashMap<String, Object> errors;

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = "http://localhost:5908" + baseUrl;
    } 
    
}
