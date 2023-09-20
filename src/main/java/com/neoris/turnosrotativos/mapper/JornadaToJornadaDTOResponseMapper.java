package com.neoris.turnosrotativos.mapper;

import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public class JornadaToJornadaDTOResponseMapper {
    public JornadaDTOResponse jornadaToDTOResponse0(Jornada jornada){
        JornadaDTOResponse jornadaDTOResponse= new JornadaDTOResponse();
            jornadaDTOResponse.setNroDocumento(jornada.getEmpleado().getNroDocumento());
            jornadaDTOResponse.setNombreCompleto(jornada.getEmpleado().completeName());
            jornadaDTOResponse.setFecha(jornada.getFecha());
            jornadaDTOResponse.setConcepto(jornada.getConcepto().getNombre());
            jornadaDTOResponse.setHsTrabajadas(jornada.getHorasTrabajadas());

        return jornadaDTOResponse;
        }
//        {
//            "nroDocumento": 32135640,
//                "nombreCompleto": "German Gonzalez",
//                "fecha": "2023-01-01",
//                "concepto": "Turno Normal",
//                "hsTrabajadas": 6
//        }

    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    ConceptoRepository conceptoRepository;
    //, EmpleadoRepository empleadoRepository, ConceptoRepository conceptoRepository)
    public JornadaDTOResponse jornadaToDTOResponse(Jornada jornada){
    if ( jornada == null ) {
        return null;
    }

    JornadaDTOResponse jornadaDTOResponse = new JornadaDTOResponse();

    jornadaDTOResponse.setIdJornada( jornada.getIdJornada() );
    jornadaDTOResponse.setFecha( jornada.getFecha() );
    jornadaDTOResponse.setHsTrabajadas( jornada.getHorasTrabajadas() );

    jornadaDTOResponse.setNroDocumento( empleadoRepository.findEmpleadoById(jornada.getEmpleado().getId()).getNroDocumento() );
    jornadaDTOResponse.setNombreCompleto( empleadoRepository.findEmpleadoById(jornada.getEmpleado().getId()).completeName() );
    jornadaDTOResponse.setConcepto( conceptoRepository.findConceptoById(jornada.getConcepto().getId()).getNombre() );

    return jornadaDTOResponse;
}
}
