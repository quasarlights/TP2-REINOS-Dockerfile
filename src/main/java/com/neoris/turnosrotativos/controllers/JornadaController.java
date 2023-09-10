package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/jornada")
public class JornadaController {
    @Autowired
    JornadaService jornadaService;

    @PostMapping
    public JornadaDTOResponse createJornada(@Valid @RequestBody JornadaDTO jornadaDTO){
        return jornadaService.createJornada(jornadaDTO);
    }
}
