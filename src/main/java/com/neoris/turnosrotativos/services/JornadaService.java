package com.neoris.turnosrotativos.services;


import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface JornadaService {
    JornadaDTOResponse createJornada(JornadaDTO jornadaDTO);
    List<JornadaDTOResponse> findJornadas(Long nroDocumento, LocalDate fecha);
}
