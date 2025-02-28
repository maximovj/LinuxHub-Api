package com.github.maximovj.linuxhubapi.linuxhub_api.controller;

import java.util.EnumSet;
import java.util.List;

import javax.management.relation.RoleList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.maximovj.linuxhubapi.linuxhub_api.data.account.Role;
import com.github.maximovj.linuxhubapi.linuxhub_api.document.Account;
import com.github.maximovj.linuxhubapi.linuxhub_api.repository.AccountRepository;
import com.github.maximovj.linuxhubapi.linuxhub_api.service.interfaces.IAccountServiceImpl;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class WebStaticController
{

    private final AccountRepository accountRepository;
    
    @GetMapping("/home")
    public String getUsers(Model model) {
        List<Account> accounts = this.accountRepository.findAll();
        model.addAttribute("accounts", accounts);
        return "home";
    }

    @GetMapping("/views/accounts")
    public String getUsers(@RequestParam(name = "nombre", required = false, defaultValue = "mundo") String nombre, Model model) 
    {
        List<Account> accounts = this.accountRepository.findAll();
        model.addAttribute("nombre", nombre);
        model.addAttribute("accounts", accounts);
        return "/views/accounts/index";
    }

    @GetMapping("/views/accounts/create")
    public String getViewsAccountsCreate(Model model) 
    {
        Account account = new Account();
        EnumSet<Role> roles = EnumSet.allOf(Role.class);
        model.addAttribute("account", account);
        model.addAttribute("roles", roles);

        return "/views/accounts/create";
    }
    
}
