package com.example.TabelaFipe.controller;

import com.example.TabelaFipe.model.FipeEntity;
import com.example.TabelaFipe.service.FipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/marcas")
public class FipeController {
    @Autowired
    private FipeService fipeService;

    @GetMapping("/marcas")
    public String consultarMarcas(){
        return fipeService.consultarMarcas();
    }
    @GetMapping("/modelos/{idMarca}")
    public String consultarModelos(@PathVariable("idMarca") int idMarca){
        return fipeService.consultarModelos(idMarca);
    }
    @GetMapping("/anos/{idMarca}/{idModelo}")
    public String consultarAnos(@PathVariable int idMarca, @PathVariable int idModelo){
        return fipeService.consultarAnos(idMarca, idModelo);
    }
    @GetMapping("/valor/{idMarca}/{idModelo}/{ano}")
    public String consultarValor(@PathVariable int marca, @PathVariable int modelo, @PathVariable String ano){
        return fipeService.consultarValor(marca, modelo, ano);
    }

    @PutMapping("/{idMarca}")
    public FipeEntity atualizar(@PathVariable String id, @RequestBody FipeEntity newModelo){
        return fipeService.atualizar(id,newModelo);
    }



}
