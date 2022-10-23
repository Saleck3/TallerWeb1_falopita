package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Entity
public class RegistroSuenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private boolean eliminado;

    public RegistroSuenio() {
        this.eliminado = false;
    }

    public RegistroSuenio(Persona persona, LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.persona = persona;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.eliminado = false;
    }

    public RegistroSuenio(Persona persona, Long cantidadHoras) {
        this.persona = persona;
        this.eliminado = false;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public String printHoraInicio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return horaInicio.format(formatter);
    }

    public String printHoraFin() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return horaFin.format(formatter);
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Long getCantidadHoras() {
        return horaInicio.until(horaFin, ChronoUnit.HOURS);
    }


    public void setInicioyFin(LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

    }

    public void eliminar() {
        this.eliminado = true;
    }

    public Long getID() {
        return ID;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public boolean isEliminado() {
        return eliminado;
    }
}
