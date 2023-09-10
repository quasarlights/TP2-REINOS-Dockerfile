package com.neoris.turnosrotativos.services;


import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import org.springframework.stereotype.Service;

@Service
public interface JornadaService {
    JornadaDTOResponse createJornada(JornadaDTO jornadaDTO);
}
