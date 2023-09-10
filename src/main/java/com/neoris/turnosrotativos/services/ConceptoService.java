package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.ConceptoDTOResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConceptoService {
    public List<ConceptoDTOResponse> findAllConceptos();
}
