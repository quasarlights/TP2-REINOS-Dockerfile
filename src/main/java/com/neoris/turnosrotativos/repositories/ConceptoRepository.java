package com.neoris.turnosrotativos.repositories;

import com.neoris.turnosrotativos.entities.Concepto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptoRepository extends JpaRepository<Concepto, Integer> {
    public Concepto findConceptoById(Integer id);
}
