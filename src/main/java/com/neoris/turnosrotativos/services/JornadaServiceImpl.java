package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.dtos.JornadaDTOResponse;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.NotRequiredHsTrabajadas;
import com.neoris.turnosrotativos.exceptions.NullConceptoException;
import com.neoris.turnosrotativos.exceptions.NullEmpleadoException;
import com.neoris.turnosrotativos.exceptions.RequiredHsTrabajadasExeption;
import com.neoris.turnosrotativos.mapper.JornadaMapper;
import com.neoris.turnosrotativos.mapper.JornadaMapper0;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);
    @Override
    public JornadaDTOResponse createJornada(JornadaDTO jornadaDTO) {
        Empleado existingEmpleado= empleadoRepository.findEmpleadoById(jornadaDTO.getIdEmpleado());
            if (existingEmpleado==null){
                throw new NullEmpleadoException();
            }
        Concepto existingConcepto= conceptoRepository.findConceptoById(jornadaDTO.getIdConcepto());
            if (existingConcepto==null){
                throw new NullConceptoException();
            }
        String nombreConcepto= conceptoRepository.findConceptoById(jornadaDTO.getIdConcepto()).getNombre();
        logger.info("NOMBRE DE CONCEPTO: "+nombreConcepto+"##############################################");
            if (nombreConcepto.equals("Turno Normal") || nombreConcepto.equals("Turno Extra")){
                throw new RequiredHsTrabajadasExeption();
            }
            if (nombreConcepto.equals("Dia Libre")){
                throw new NotRequiredHsTrabajadas();
            }

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
