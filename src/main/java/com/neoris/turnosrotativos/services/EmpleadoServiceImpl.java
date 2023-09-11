package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.dtos.EmpleadoDTOResponse;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.*;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.neoris.turnosrotativos.utils.EmailUtils.esEmailValido;
import static com.neoris.turnosrotativos.utils.StringUtils.esCampoTextoValido;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    EmpleadoRepository empleadoRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);
    @Override
    @Transactional
    public EmpleadoDTOResponse createEmpleado(EmpleadoDTO empleadoDTO) {
    logger.info("LOGGEANDO FIRST empleadoDTO: "+ empleadoDTO);
    //CHEQUEO QUE LA EDAD NO SEA MENOR A 18 AÑOS
        int edad= DateUtils.calcularEdad(empleadoDTO.getFechaNacimiento());
    logger.info("LA EDAD ES: "+edad+" TIPO DE DATO"+  "**********************************");
            if(edad<=18 && edad>=0){
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
        //LocalDate fechaNacimiento= empleadoDTO.getFechaNacimiento();
            //if (fechaNacimiento.isAfter(hoy))
            if(edad<0){
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
        logger.info("LOGGEANDO empleadoDto (se va a mappear a tipo Empleado): "+ empleadoDTO.toString());
        Empleado empleado= modelMapper.map(empleadoDTO, Empleado.class);
        logger.info("LOGGEANDO empleado (se va a guardar con .save): "+ empleado.toString());
        empleadoRepository.save(empleado);
        EmpleadoDTOResponse empleadoDTOResponse= modelMapper.map(empleado, EmpleadoDTOResponse.class);
        logger.info("LOGGEANDO empleadoDTOResponse: (response)"+ empleadoDTOResponse.toString());
        return empleadoDTOResponse;
    }

    @Override
    public List<EmpleadoDTOResponse> findAllEmpleados() {
        List<Empleado> empleados = empleadoRepository.findAll();

        return empleados.stream()
                .map(empleado -> modelMapper.map(empleado, EmpleadoDTOResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoDTOResponse findEmpleadoById(Long empleadoId) {
        Empleado empleado = empleadoRepository.findEmpleadoById(empleadoId);
            if(empleado == null){
                throw new NotFoundEmpleado(empleadoId);
            }
        EmpleadoDTOResponse empleadoDTOResponse= modelMapper.map(empleado, EmpleadoDTOResponse.class);
        return empleadoDTOResponse;
    }

    @Override
    public EmpleadoDTOResponse updateEmpleado(Long empleadoId, EmpleadoDTO empleadoDTO) {
        Empleado empleadoExisting= empleadoRepository.findEmpleadoById(empleadoId);
            if(empleadoExisting==null){
                throw new NotFoundEmpleado(empleadoId);
            }
        Empleado empleadoUpdated= modelMapper.map(empleadoDTO, Empleado.class);
        empleadoUpdated.setId(empleadoExisting.getId());
        empleadoUpdated.setFechaCreacion(empleadoExisting.getFechaCreacion());

        return update(empleadoUpdated);
    }

    @Transactional
    public EmpleadoDTOResponse update(Empleado empleadoUpdate) {
        logger.info("LOGGEANDO FIRST empleadoDTO: "+ empleadoUpdate);
        //CHEQUEO QUE LA EDAD NO SEA MENOR A 18 AÑOS
        int edad= DateUtils.calcularEdad(empleadoUpdate.getFechaNacimiento());
        logger.info("LA EDAD ES: "+edad+" TIPO DE DATO"+  "**********************************");
        if(edad<=18 && edad>=0){
            throw new NotUnderageException();
        }
        //CHEQUEO SI EXISTE EMPLEADO CON MISMO NRO DOCUMENTO
        Empleado empleadoByNroDocumento = empleadoRepository.findEmpleadoByNroDocumento(empleadoUpdate.getNroDocumento());
        if (empleadoByNroDocumento != null && !empleadoByNroDocumento.getId().equals(empleadoUpdate.getId())) {
            throw new SameNroDocumentoException();
        }
//        Empleado empleadoByNroDocumento= empleadoRepository.findEmpleadoByNroDocumento(empleadoUpdate.getNroDocumento());
//        if (empleadoByNroDocumento !=null){
//            throw new SameNroDocumentoException();
//        }
        //CHEQUEO SI EXISTE EMPLEADO CON MISMO EMAIL
        Empleado empleadoByEmail = empleadoRepository.findEmpleadoByEmail(empleadoUpdate.getEmail());
        if (empleadoByEmail != null && !empleadoByEmail.getId().equals(empleadoUpdate.getId())) {
            throw new SameEmailException();
        }
//        Empleado empleadoByEmail= empleadoRepository.findEmpleadoByEmail(empleadoUpdate.getEmail());
//        if (empleadoByEmail !=null){
//            throw new SameEmailException();
//        }
        //CHEQUEO SI LA FECHA DE INGRESO ES POSTERIOR A LA DEL DIA
        LocalDate fechaIngreso= empleadoUpdate.getFechaIngreso();
        LocalDate hoy= LocalDate.now();
        if (fechaIngreso.isAfter(hoy)){
            throw new InvalidFechaIngresoException();
        }
        //CHEQUEO SI FECHA DE NACIMIENTO ES POSTERIOR A HOY
        //LocalDate fechaNacimiento= empleadoDTO.getFechaNacimiento();
        //if (fechaNacimiento.isAfter(hoy))
        if(edad<0){
            throw new InvalidFechaNacimientoException();
        }
        //CHEQUEO SI EL EMAIL TIENE FORMATO VALIDO
        String email = empleadoUpdate.getEmail();
        if (!esEmailValido(email)){
            throw new InvalidEmailException();
        }
        //CHEQUEO QUE LOS CAMPOS STRING TENGAN SOLO LETRAS
        String nombre = empleadoUpdate.getNombre();
        String apellido = empleadoUpdate.getApellido();
        if (!esCampoTextoValido(nombre)) {
            throw new CampoInvalidoException("nombre");
        }
        if (!esCampoTextoValido(apellido)) {
            throw new CampoInvalidoException("apellido");
        }
        //////////////////////////////////////////////////////
        empleadoRepository.save(empleadoUpdate);
        EmpleadoDTOResponse empleadoDTOResponse= modelMapper.map(empleadoUpdate, EmpleadoDTOResponse.class);
        logger.info("LOGGEANDO empleadoDTOResponse: (response)"+ empleadoDTOResponse.toString());

        return empleadoDTOResponse;
    }

    public void deleteEmpleadoById(Long idEmpleado){
        try {
            empleadoRepository.deleteById(idEmpleado);
        }catch (Exception e){
            throw  new DeleteEmpleadoException(idEmpleado);
        }

    }

}
