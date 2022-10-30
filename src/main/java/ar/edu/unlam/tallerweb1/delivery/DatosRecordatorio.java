package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class DatosRecordatorio {
    private String contenido;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora;

    private Recordatorio.TipoRecordatorio tipo;

    private Recordatorio.TipoFrecuencia frecuencia;

    private Integer cantidadOcurrencias;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Recordatorio.TipoRecordatorio getTipo() {
        return tipo;
    }

    public void setTipo(Recordatorio.TipoRecordatorio tipo) {
        this.tipo = tipo;
    }

    public Recordatorio.TipoFrecuencia getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Recordatorio.TipoFrecuencia frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Integer getCantidadOcurrencias() {
        return cantidadOcurrencias;
    }

    public void setCantidadOcurrencias(Integer cantidadOcurrencias) {
        this.cantidadOcurrencias = cantidadOcurrencias;
    }
}
