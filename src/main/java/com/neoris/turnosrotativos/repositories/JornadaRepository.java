package com.neoris.turnosrotativos.repositories;

import com.neoris.turnosrotativos.entities.Jornada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JornadaRepository extends JpaRepository<Jornada, Long> {
    //Jornada findByEmpleado_IdAndFechaAndConcepto_Nombre(Long empleadoId, LocalDate fecha, String conceptoName);
    @Query("SELECT j FROM Jornada j WHERE j.empleado.id = :empleadoId AND j.fecha = :fecha")
    List<Jornada> findJornadasByEmpleadoAndFecha(@Param("empleadoId") Long empleadoId, @Param("fecha") LocalDate fecha);

    @Query("SELECT j FROM Jornada j WHERE j.empleado.id = :empleadoId AND j.fecha = :fecha AND j.concepto.id = :conceptoId")
    Optional<Jornada> findJornadaByEmpleadoFechaAndConcepto(@Param("empleadoId") Long empleadoId,
                                                            @Param("fecha") LocalDate fecha,
                                                            @Param("conceptoId") Integer conceptoId);


}
