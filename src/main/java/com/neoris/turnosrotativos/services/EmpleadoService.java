package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.dtos.EmpleadoDTOResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpleadoService {
    EmpleadoDTOResponse createEmpleado(EmpleadoDTO empleadoDTO);
    List<EmpleadoDTOResponse> findAllEmpleados();
    EmpleadoDTOResponse findEmpleadoById(Long empleadoId);
}
