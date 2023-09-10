package com.neoris.turnosrotativos.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class JornadaDTO {
    @NotNull(message = "El idEmpleado no puede ser nulo")
    @Column(nullable = false)
    private Long idEmpleado;
    @NotNull(message = "El idConcepto no puede ser nulo")
    @Column(nullable = false)
    private Integer idConcepto;
    @NotNull(message = "La fecha no puede ser nulo")
    @Column(nullable = false)
    private LocalDate fecha;
    private Integer horasTrabajadas;

    public JornadaDTO() {
    }

    public JornadaDTO(Long idEmpleado, Integer idConcepto, LocalDate fecha, Integer horasTrabajadas) {
        this.idEmpleado = idEmpleado;
        this.idConcepto = idConcepto;
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    @Override
    public String toString() {
        return "JornadaDTO{" +
                "idEmpleado=" + idEmpleado +
                ", idConcepto=" + idConcepto +
                ", fecha=" + fecha +
                ", horasTrabajadas=" + horasTrabajadas +
                '}';
    }
}
