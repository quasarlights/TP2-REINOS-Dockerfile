package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.mapper.JornadaMapper;
import com.neoris.turnosrotativos.mapper.JornadaMapper0;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JornadaServiceImpl implements JornadaService{
    @Autowired
    JornadaRepository jornadaRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmpleadoRepository empleadoRepository;
    @Autowired
    ConceptoRepository conceptoRepository;
    @Autowired
    JornadaMapper0 jornadaMapper;
    @Override
    public JornadaDTOResponse createJornada(JornadaDTO jornadaDTO) {
        Jornada jornada= jornadaMapper.dtoToJornada(jornadaDTO);
        jornadaRepository.save(jornada);

    JornadaDTOResponse jornadaDTOResponse= new JornadaDTOResponse();
        Long jornadaId= jornada.getIdJornada();
            jornadaDTOResponse.setIdJornada(jornadaId);
        Long nroDocumento= empleadoRepository.findEmpleadoById(jornada.getIdEmpleado()).getNroDocumento();
            jornadaDTOResponse.setNroDocumento(nroDocumento);
        String nombreCompleto= empleadoRepository.findEmpleadoById(jornada.getIdEmpleado()).completeName();
            jornadaDTOResponse.setNombreCompleto(nombreCompleto);
            jornadaDTOResponse.setFecha(jornada.getFecha());
        String concepto= conceptoRepository.findConceptoById(jornada.getIdConcepto()).getNombre();
            jornadaDTOResponse.setConcepto(concepto);
            jornadaDTOResponse.setHsTrabajadas(jornada.getHorasTrabajadas());

        return jornadaDTOResponse;
    }
}
