package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<JornadaDTOResponse>> getJornadas(
            @RequestParam(name="nroDocumento", required=false) Long nroDocumento,
            @RequestParam(name="fecha", required=false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
        List<JornadaDTOResponse> jornadas = jornadaService.findJornadas(nroDocumento, fecha);
        return ResponseEntity.ok(jornadas);
    }

}
