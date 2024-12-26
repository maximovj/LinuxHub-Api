package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class WebStaticController
{
    
    @GetMapping("/home")
    public String getUsers(@RequestParam(name = "nombre", required = false, defaultValue = "mundo") String nombre, Model model) {
        model.addAttribute("nombre", nombre);
        return "home";
    }
    
}
