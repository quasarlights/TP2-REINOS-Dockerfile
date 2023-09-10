package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.ConceptoDTOResponse;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConceptoServiceImpl implements ConceptoService{
    @Autowired
    ConceptoRepository conceptoRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ConceptoDTOResponse> findAllConceptos() {
        List<Concepto> conceptos= conceptoRepository.findAll();

        return conceptos.stream()
                .map(concepto -> modelMapper.map(concepto, ConceptoDTOResponse.class))
                .collect(Collectors.toList());
    }
}
