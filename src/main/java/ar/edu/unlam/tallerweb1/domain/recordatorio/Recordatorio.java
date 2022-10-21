package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNotificacion;

    private Boolean oculto = false;

    public Recordatorio(){}

    public Recordatorio(Persona persona, String contenido, Date fechaNotificacion){
        this.persona = persona;
        this.contenido = contenido;
        this.fechaNotificacion = fechaNotificacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public Boolean getOculto() {
        return oculto;
    }

    public void setOculto(Boolean eliminado) {
        this.oculto = eliminado;
    }
}
