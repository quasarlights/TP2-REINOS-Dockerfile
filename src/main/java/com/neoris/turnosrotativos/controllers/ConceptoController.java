package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.ConceptoDTOResponse;
import com.neoris.turnosrotativos.services.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/concepto")
public class ConceptoController {
    @Autowired
    ConceptoService conceptoService;

    @GetMapping
    public List<ConceptoDTOResponse> findAllConceptos(){
        return conceptoService.findAllConceptos();
    }
}
