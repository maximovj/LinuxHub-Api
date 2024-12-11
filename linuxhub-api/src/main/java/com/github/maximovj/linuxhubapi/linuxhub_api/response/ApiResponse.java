package com.github.maximovj.linuxhubapi.linuxhub_api.response;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

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
    
    String res_title;
    String res_content;
    String uri;
    String base_url;
    String type;
    String status;
    Integer code;
    Boolean success;
    HashMap<String, Object> data;
    HashMap<String, Object> errors;    
}
