package com.example.ClimaAPI.controller;

import com.example.ClimaAPI.model.ClimaEntity;
import com.example.ClimaAPI.service.ClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clima")
public class Controller {
    @Autowired
    private ClimaService climaService;

    @GetMapping("/clima")
    public String preverTempo() {
        return climaService.preverTempo();
    }
    @GetMapping("/{id}")
    public ClimaEntity obterPorId(@PathVariable String id){
        return climaService.obterPorId(id);
    }
    @PostMapping
    public ClimaEntity inserir(@RequestBody ClimaEntity clima){return climaService.inserir(clima);}

    @PutMapping("/{id}")
    public ClimaEntity atualizar(@PathVariable String id, @RequestBody ClimaEntity clima){
        return climaService.atualizar(id, clima);
    }
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable String id){climaService.excluir(id);}

}

