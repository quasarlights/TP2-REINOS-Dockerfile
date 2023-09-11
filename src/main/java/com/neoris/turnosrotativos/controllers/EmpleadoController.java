package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.dtos.EmpleadoDTOResponse;
import com.neoris.turnosrotativos.services.EmpleadoService;
import com.neoris.turnosrotativos.services.EmpleadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

    @PostMapping
    public EmpleadoDTOResponse createEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO){
        logger.info("LOGGEANDO en Controller FIRST empleadoDTO: "+ empleadoDTO);
        return empleadoService.createEmpleado(empleadoDTO);
    }

    @GetMapping
    public List<EmpleadoDTOResponse> findAllEmpleados(){
        return empleadoService.findAllEmpleados();
    }

    @GetMapping("/{empleadoId}")
    public EmpleadoDTOResponse findEmpleadoById(@PathVariable Long empleadoId){
        return empleadoService.findEmpleadoById(empleadoId);
    }

    @PutMapping("/{empleadoId}")
    public EmpleadoDTOResponse updateEmpleado(@PathVariable Long empleadoId,
                                              @Valid @RequestBody EmpleadoDTO empleadoDTO){
        return empleadoService.updateEmpleado(empleadoId, empleadoDTO);
    }

    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<Void> deleteEmpleadoById(@PathVariable Long empleadoId){
        empleadoService.deleteEmpleadoById(empleadoId);
        return ResponseEntity.noContent().build();
    }
}
