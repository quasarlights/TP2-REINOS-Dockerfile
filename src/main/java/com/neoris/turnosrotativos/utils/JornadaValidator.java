package com.neoris.turnosrotativos.utils;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.InvalidHoursRangeException;
import com.neoris.turnosrotativos.exceptions.NotRequiredHsTrabajadas;
import com.neoris.turnosrotativos.exceptions.NullConceptoException;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import com.neoris.turnosrotativos.services.EmpleadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JornadaValidator {

    @Autowired
    private ConceptoRepository conceptoRepository;
    @Autowired
    JornadaRepository jornadaRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);
    public void validateRange(JornadaDTO jornadaDTO, Concepto existingConcepto) {
        String nombreConcepto = existingConcepto.getNombre();
        validateHoursRange(jornadaDTO.getHorasTrabajadas(), existingConcepto);
    }

    private void validateHoursRange(Integer horasTrabajadas, Concepto concepto) {
        if (horasTrabajadas < concepto.getHsMinimo() || horasTrabajadas > concepto.getHsMaximo()) {
            Integer hsMin= concepto.getHsMinimo();
            Integer hsMax= concepto.getHsMaximo();
            throw new InvalidHoursRangeException(hsMin, hsMax);
        }
    }

    public void checkDiaLibre(JornadaDTO jornadaDTO){
        List<Jornada> jornadasExistentes = jornadaRepository.findJornadasByEmpleadoAndFecha(jornadaDTO.getIdEmpleado(), jornadaDTO.getFecha());

        for (Jornada jornadaExistente : jornadasExistentes) {
            String diaLibre= "Dia Libre";
            String conceptoNombre= jornadaExistente.getConcepto().getNombre();
            if (diaLibre.equals(conceptoNombre)) {
                throw new RuntimeException("El empleado ingresado cuenta con un día libre en esa fecha.");
            }
            logger.info(conceptoNombre);
            logger.info("checkDiaLibre: " + jornadasExistentes + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }

}
