package com.neoris.turnosrotativos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Jornada> jornadas = new ArrayList<>();
    //CONSTRUCTORS
    public Empleado() {
    }

    public Empleado(Long id, Long nroDocumento, String nombre, String apellido, String email, LocalDate fechaNacimiento, LocalDate fechaIngreso) {
        this.id = id;
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
    }

    //METHODS
    public String completeName(){
        return this.nombre+" "+this.apellido;
    }

    //GETTERS & SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    //TO STRING
    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nroDocumento=" + nroDocumento +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
