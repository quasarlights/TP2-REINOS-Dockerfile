package com.neoris.turnosrotativos.utils;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.*;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import com.neoris.turnosrotativos.services.EmpleadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
                throw new NotSameDiaLibreException();
            }
            logger.info(conceptoNombre);
            logger.info("checkDiaLibre: " + jornadasExistentes + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }

    public void checkConceptoDuplicado(JornadaDTO jornadaDTO){
        Optional<Jornada> jornadaExistente = jornadaRepository.findJornadaByEmpleadoFechaAndConcepto(
                jornadaDTO.getIdEmpleado(),
                jornadaDTO.getFecha(),
                jornadaDTO.getIdConcepto());

        if (jornadaExistente.isPresent()) {
            throw new DuplicateConceptoException();
        }
    }

    public void checkMaxHsTrabajadasEnUnDia(JornadaDTO jornadaDTO){
        List<Jornada> jornadasDelDia = jornadaRepository.findJornadasByEmpleadoAndFecha(
                jornadaDTO.getIdEmpleado(),
                jornadaDTO.getFecha());
        int totalHoras = jornadaDTO.getHorasTrabajadas() != null ? jornadaDTO.getHorasTrabajadas() : 0;

        for (Jornada jornadaExistente : jornadasDelDia) {
            if ("Turno Normal".equals(jornadaExistente.getConcepto().getNombre()) ||
                    "Turno Extra".equals(jornadaExistente.getConcepto().getNombre())) {
                totalHoras += jornadaExistente.getHorasTrabajadas() != null ? jornadaExistente.getHorasTrabajadas() : 0;
            }
        }
        if (totalHoras > 12) {
            throw new MaxHsTrabajadasDiaException();
        }
    }

    public void checkDiaLibreConOtrosTurnos(JornadaDTO jornadaDTO){
        List<Jornada> jornadasDelDia = jornadaRepository.findJornadasByEmpleadoAndFecha(
                jornadaDTO.getIdEmpleado(),
                jornadaDTO.getFecha());

        if ("Dia Libre".equals(conceptoRepository.findConceptoById(jornadaDTO.getIdConcepto()).getNombre()) && !jornadasDelDia.isEmpty()) {
            for (Jornada jornadaExistente : jornadasDelDia) {
                if (!"Dia Libre".equals(jornadaExistente.getConcepto().getNombre())) {
                    throw new DiaLibreVsTurnosCargadosException();
                }
            }
        }
    }

    public void checkMaxHorasSemanales(JornadaDTO jornadaDTO) {
        LocalDate fechaIngresada = jornadaDTO.getFecha();
        LocalDate startOfWeek = fechaIngresada.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = fechaIngresada.with(DayOfWeek.SUNDAY);

        List<Jornada> jornadasDeLaSemana = jornadaRepository.findJornadasByEmpleadoAndWeek(
                jornadaDTO.getIdEmpleado(), startOfWeek, endOfWeek);

        int totalHorasSemanales = jornadasDeLaSemana.stream()
                .mapToInt(j -> j.getHorasTrabajadas() != null ? j.getHorasTrabajadas() : 0)
                .sum();
        totalHorasSemanales += (jornadaDTO.getHorasTrabajadas() != null ? jornadaDTO.getHorasTrabajadas() : 0);

        if (totalHorasSemanales > 48) {
            throw new MaxHorasSemanalesExceptions();
        }
    }

    public void checkMaxExtraTurnsPerWeek(JornadaDTO jornadaDTO) {
        LocalDate startDateOfWeek = jornadaDTO.getFecha().with(DayOfWeek.MONDAY);
        LocalDate endDateOfWeek = jornadaDTO.getFecha().with(DayOfWeek.SUNDAY);

        List<Jornada> jornadasThisWeek = jornadaRepository.findJornadasByEmpleadoAndWeek(
                jornadaDTO.getIdEmpleado(), startDateOfWeek, endDateOfWeek);

            long countExtraTurns = jornadasThisWeek.stream()
                    .filter(j -> "Turno Extra".equals(j.getConcepto().getNombre()))
                    .count();

        if (countExtraTurns >= 3) {
            throw new MaxExtraTurnsExceptions();
        }
    }
    public void checkMaxNormalTurnsPerWeek(JornadaDTO jornadaDTO) {
        LocalDate startDateOfWeek = jornadaDTO.getFecha().with(DayOfWeek.MONDAY);
        LocalDate endDateOfWeek = jornadaDTO.getFecha().with(DayOfWeek.SUNDAY);
        List<Jornada> jornadasThisWeek = jornadaRepository.findJornadasByEmpleadoAndWeek(
                jornadaDTO.getIdEmpleado(), startDateOfWeek, endDateOfWeek);

        long countNormalTurns = jornadasThisWeek.stream()
                .filter(j -> "Turno Normal".equals(j.getConcepto().getNombre()))
                .count();

        if (countNormalTurns >= 5) {
            throw new MaxNormalTurnsExceptions();
        }
    }

    public void checkMaxDiaLibrePerWeek(JornadaDTO jornadaDTO) {
        LocalDate startDateOfWeek = jornadaDTO.getFecha().with(DayOfWeek.MONDAY);
        LocalDate endDateOfWeek = jornadaDTO.getFecha().with(DayOfWeek.SUNDAY);
        List<Jornada> jornadasThisWeek = jornadaRepository.findJornadasByEmpleadoAndWeek(
                jornadaDTO.getIdEmpleado(), startDateOfWeek, endDateOfWeek);

        long countFreeDays = jornadasThisWeek.stream()
                .filter(j -> "Dia Libre".equals(j.getConcepto().getNombre()))
                .count();

        if (countFreeDays >= 2) {
            throw new MaxDiaLibreException();
        }
    }


}
