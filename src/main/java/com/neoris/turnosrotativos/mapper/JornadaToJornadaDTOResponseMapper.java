package com.neoris.turnosrotativos.mapper;

import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.entities.Jornada;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class JornadaToJornadaDTOResponseMapper {
    public JornadaDTOResponse jornadaToDTOResponse(Jornada jornada){
        JornadaDTOResponse jornadaDTOResponse= new JornadaDTOResponse();
            jornadaDTOResponse.setNroDocumento(jornada.getEmpleado().getNroDocumento());
            jornadaDTOResponse.setNombreCompleto(jornada.getEmpleado().completeName());
            jornadaDTOResponse.setFecha(jornada.getFecha());
            jornadaDTOResponse.setConcepto(jornada.getConcepto().getNombre());
            jornadaDTOResponse.setHsTrabajadas(jornada.getHorasTrabajadas());

        return jornadaDTOResponse;

//        {
//            "nroDocumento": 32135640,
//                "nombreCompleto": "German Gonzalez",
//                "fecha": "2023-01-01",
//                "concepto": "Turno Normal",
//                "hsTrabajadas": 6
//        }
    }
}
