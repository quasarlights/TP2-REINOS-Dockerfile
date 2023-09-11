package com.neoris.turnosrotativos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @ManyToOne
    @JoinColumn(name = "idEmpleado")
    @JsonManagedReference
    @JsonIgnore
    private Empleado empleado;

    @NotNull(message = "El idConcepto no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "idConcepto")
    @JsonManagedReference
    @JsonIgnore
    private Concepto concepto;

    @NotNull(message = "La fecha no puede ser nulo")
    @Column(nullable = false)
    private LocalDate fecha;

    private Integer horasTrabajadas;

    public Jornada() {
    }

    public Jornada(Long idJornada, Empleado empleado, Concepto concepto, LocalDate fecha, Integer horasTrabajadas) {
        this.idJornada = idJornada;
        this.empleado = empleado;
        this.concepto = concepto;
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
    }

    public Long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Long idJornada) {
        this.idJornada = idJornada;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
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
                "idJornada=" + idJornada +
                //", empleado=" + empleado +
                //", concepto=" + concepto +
                ", fecha=" + fecha +
                ", horasTrabajadas=" + horasTrabajadas +
                '}';
    }
}
