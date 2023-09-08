package com.neoris.turnosrotativos.repositories;

import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    public Empleado findEmpleadoByNroDocumento(int nroDocumento);
    public Empleado findEmpleadoByEmail(String email);
}
