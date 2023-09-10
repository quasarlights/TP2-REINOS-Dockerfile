package com.neoris.turnosrotativos.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "jornadas")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJornada;

    @NotNull(message = "El idEmpleado no puede ser nulo")
    @Column(nullable = false)
    private Long idEmpleado;

    @NotNull(message = "El idConcepto no puede ser nulo")
    @Column(nullable = false)
    private Integer idConcepto;

    @NotNull(message = "La fecha no puede ser nulo")
    @Column(nullable = false)
    private LocalDate fecha;

    @NotNull(message = "Las horasTrabajadas no puede ser nulo")
    @Column(nullable = false)
    private Integer horasTrabajadas;

    public Jornada() {
    }

    public Jornada(Long idJornada, Long idEmpleado, Integer idConcepto, LocalDate fecha, Integer horasTrabajadas) {
        this.idJornada = idJornada;
        this.idEmpleado = idEmpleado;
        this.idConcepto = idConcepto;
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Long idJornada) {
        this.idJornada = idJornada;
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
        return "Jornada{" +
                "id=" + idJornada +
                ", idEmpleado=" + idEmpleado +
                ", idConcepto=" + idConcepto +
                ", fecha=" + fecha +
                ", horasTrabajadas=" + horasTrabajadas +
                '}';
    }
}
