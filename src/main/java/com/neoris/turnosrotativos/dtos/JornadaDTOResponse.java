package com.neoris.turnosrotativos.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public class JornadaDTOResponse {
    private Long idJornada;
    private Long nroDocumento;
    private String nombreCompleto;
    private LocalDate fecha;
    private String concepto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer hsTrabajadas;

    public JornadaDTOResponse() {
    }

    public JornadaDTOResponse(Long idJornada, Long nroDocumento, String nombreCompleto, LocalDate fecha, String concepto, Integer hsTrabajadas) {
        this.idJornada = idJornada;
        this.nroDocumento = nroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.concepto = concepto;
        this.hsTrabajadas = hsTrabajadas;
    }

    public Long getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Long idJornada) {
        this.idJornada = idJornada;
    }

    public Long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    public void setHsTrabajadas(Integer hsTrabajadas) {
        this.hsTrabajadas = hsTrabajadas;
    }

    @Override
    public String toString() {
        return "JornadaDTOResponse{" +
                "id=" + idJornada +
                ", nroDocumento=" + nroDocumento +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fecha=" + fecha +
                ", concepto='" + concepto + '\'' +
                ", hsTrabajadas=" + hsTrabajadas +
                '}';
    }
}
