package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.dtos.EmpleadoDTOResponse;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoDTOResponse createEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado= modelMapper.map(empleadoDTO, Empleado.class);

        empleadoRepository.save(empleado);

        EmpleadoDTOResponse empleadoDTOResponse= modelMapper.map(empleado, EmpleadoDTOResponse.class);

        return empleadoDTOResponse;
    }
}
