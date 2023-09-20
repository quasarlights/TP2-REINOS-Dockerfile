package com.neoris.turnosrotativos.mapper;

import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { EmpleadoRepository.class, ConceptoRepository.class })
public interface JornadaMapper {
/*
    @Mapping(target = "idJornada", source = "idJornada")
    @Mapping(target = "nroDocumento", expression = "java(empleadoRepository.findEmpleadoById(jornada.getIdEmpleado()).getNroDocumento())")
    @Mapping(target = "nombreCompleto", expression = "java(empleadoRepository.findEmpleadoById(jornada.getIdEmpleado()).completeName())")
    @Mapping(target = "fecha", source = "fecha")
    @Mapping(target = "concepto", expression = "java(conceptoRepository.findConceptoById(jornada.getIdConcepto()).getNombre())")
    @Mapping(target = "hsTrabajadas", source = "horasTrabajadas")
    JornadaDTOResponse toDtoResponse(Jornada jornada, @Context EmpleadoRepository empleadoRepository, @Context ConceptoRepository conceptoRepository);
*/
}