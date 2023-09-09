package com.neoris.turnosrotativos.dtos;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmpleadoDTO {
    @NotNull(message = "El nro. de Documento no puede ser nulo")
    @Column(nullable = false)
    private Long nroDocumento;
    @NotNull(message = "El Nombre no puede ser nulo")
    @NotBlank(message = "El Nombre no puede estar vacío")
    @Column(length =100,nullable = false)
    private String nombre;
    @NotNull(message = "El Apellido no puede ser nulo")
    @NotBlank(message = "El Apellido no puede estar vacío")
    @Column(length =100,nullable = false)
    private String apellido;
    @NotNull(message = "El Email no puede ser nulo")
    @NotBlank(message = "El Email no puede estar vacío")
    @Column(length =100,nullable = false)
    private String email;
    @NotNull(message = "La Fecha de Nacimiento no puede ser nula")
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
    @NotNull(message = "La Fecha de Ingreso no puede ser nula")
    @Column(nullable = false)
    private LocalDate fechaIngreso;


    public EmpleadoDTO(Long nroDocumento, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
    }

    public Long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "nroDocumento=" + nroDocumento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
