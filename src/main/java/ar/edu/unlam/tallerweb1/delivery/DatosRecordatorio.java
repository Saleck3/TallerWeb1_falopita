package ar.edu.unlam.tallerweb1.delivery;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DatosRecordatorio {
    private String contenido;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fechaHora;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }


    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }
}
