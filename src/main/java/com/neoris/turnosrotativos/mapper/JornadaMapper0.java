package com.neoris.turnosrotativos.mapper;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class JornadaMapper0 {
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    ConceptoRepository conceptoRepository;
    public Jornada dtoToJornada(JornadaDTO jornadaDTO){
        Jornada jornada= new Jornada();

        jornada.setEmpleado(empleadoRepository.findEmpleadoById(jornadaDTO.getIdEmpleado()));
        jornada.setConcepto(conceptoRepository.findConceptoById(jornadaDTO.getIdConcepto()));
        jornada.setFecha(jornadaDTO.getFecha());
        jornada.setHorasTrabajadas(jornadaDTO.getHorasTrabajadas());

        return jornada;
    }

//    @Autowired
//    EmpleadoRepository empleadoRepository;
//    @Autowired
//    ConceptoRepository conceptoRepository;
//
//    public JornadaDTOResponse jornadaToDTOResponse(Jornada jornada){
//        JornadaDTOResponse jornadaDTOResponse= new JornadaDTOResponse();
//
//            jornadaDTOResponse.setIdJornada(jornada.getIdJornada());
//            jornadaDTOResponse.setNroDocumento(empleadoRepository.findEmpleadoById(jornada.getIdEmpleado()).getNroDocumento());
//            jornadaDTOResponse.setNombreCompleto(empleadoRepository.findEmpleadoById(jornada.getIdEmpleado()).completeName());
//            jornadaDTOResponse.setFecha(jornada.getFecha());
//            jornadaDTOResponse.setConcepto(conceptoRepository.findConceptoById(jornada.getIdConcepto()).getNombre());
//            jornadaDTOResponse.setHsTrabajadas(jornada.getHorasTrabajadas());
//
//        return jornadaDTOResponse;
//    }
}
