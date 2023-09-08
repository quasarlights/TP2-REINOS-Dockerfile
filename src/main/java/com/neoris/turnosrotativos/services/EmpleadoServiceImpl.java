package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.dtos.EmpleadoDTOResponse;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.*;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.neoris.turnosrotativos.utils.EmailUtils.esEmailValido;
import static com.neoris.turnosrotativos.utils.StringUtils.esCampoTextoValido;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoDTOResponse createEmpleado(EmpleadoDTO empleadoDTO) {
    //CHEQUEO QUE LA EDAD NO SEA MENOR A 18 AÑOS
        int edad= DateUtils.calcularEdad(empleadoDTO.getFechaNacimiento());
            if(edad<18){
                throw new NotUnderageException();
            }
    //CHEQUEO SI EXISTE EMPLEADO CON MISMO NRO DOCUMENTO
        Empleado empleadoByNroDocumento= empleadoRepository.findEmpleadoByNroDocumento(empleadoDTO.getNroDocumento());
            if (empleadoByNroDocumento !=null){
                throw new SameNroDocumentoException();
            }
    //CHEQUEO SI EXISTE EMPLEADO CON MISMO EMAIL
        Empleado empleadoByEmail= empleadoRepository.findEmpleadoByEmail(empleadoDTO.getEmail());
            if (empleadoByEmail !=null){
                throw new SameEmailException();
            }
    //CHEQUEO SI LA FECHA DE INGRESO ES POSTERIOR A LA DEL DIA
        LocalDate fechaIngreso= empleadoDTO.getFechaIngreso();
        LocalDate hoy= LocalDate.now();
            if (fechaIngreso.isAfter(hoy)){
                throw new InvalidFechaIngresoException();
            }
    //CHEQUEO SI FECHA DE NACIMIENTO ES POSTERIOR A HOY
        LocalDate fechaNacimiento= empleadoDTO.getFechaNacimiento();
            if (fechaNacimiento.isAfter(hoy)){
            throw new InvalidFechaNacimientoException();
        }
    //CHEQUEO SI EL EMAIL TIENE FORMATO VALIDO
        String email = empleadoDTO.getEmail();
            if (!esEmailValido(email)){
                throw new InvalidEmailException();
            }
    //CHEQUEO QUE LOS CAMPOS STRING TENGAN SOLO LETRAS
        String nombre = empleadoDTO.getNombre();
        String apellido = empleadoDTO.getApellido();
        if (!esCampoTextoValido(nombre)) {
            throw new CampoInvalidoException("nombre");
        }
        if (!esCampoTextoValido(apellido)) {
            throw new CampoInvalidoException("apellido");
        }
        Empleado empleado= modelMapper.map(empleadoDTO, Empleado.class);
        empleadoRepository.save(empleado);
        EmpleadoDTOResponse empleadoDTOResponse= modelMapper.map(empleado, EmpleadoDTOResponse.class);
        return empleadoDTOResponse;
    }
}
