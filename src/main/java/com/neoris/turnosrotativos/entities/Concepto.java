package com.neoris.turnosrotativos.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "conceptos")
public class Concepto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private Boolean laborable;
    private Integer hsMinimo;
    private Integer hsMaximo;

    @OneToMany(mappedBy = "concepto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Jornada> jornadas = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getLaborable() {
        return laborable;
    }

    public void setLaborable(Boolean laborable) {
        this.laborable = laborable;
    }

    public Integer getHsMinimo() {
        return hsMinimo;
    }

    public void setHsMinimo(Integer hsMinimo) {
        this.hsMinimo = hsMinimo;
    }

    public Integer getHsMaximo() {
        return hsMaximo;
    }

    public void setHsMaximo(Integer hsMaximo) {
        this.hsMaximo = hsMaximo;
    }

    @Override
    public String toString() {
        return "Concepto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", laborable=" + laborable +
                ", hsMinimo=" + hsMinimo +
                ", hsMaximo=" + hsMaximo +
                ", jornadas=" + jornadas +
                '}';
    }
}
