package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import javax.persistence.*;
import java.time.Period;
import java.util.Date;

@Entity
public class RegistroSuenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Date horaInicio;
    private Date horaFin;
    public static final int MILISEGUNDOS_A_HORA = 1000 * 60;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private boolean eliminado = false;

    public RegistroSuenio() {
    }

    public RegistroSuenio(Persona persona, Date horaInicio, Date horaFin) {
        this.persona = persona;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public RegistroSuenio(Persona persona, Long cantidadHoras) {
        this.persona = persona;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Long getCantidadHoras() {
        return (horaFin.getTime() - horaInicio.getTime()) * MILISEGUNDOS_A_HORA;
    }


    public void setInicioyFin(Date horaInicio, Date horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;

    }

    public void eliminar() {
        this.eliminado = true;
    }

}
