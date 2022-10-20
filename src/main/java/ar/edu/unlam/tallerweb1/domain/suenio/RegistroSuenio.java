package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import javax.persistence.*;

@Entity
public class RegistroSuenio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Long horaInicio;
    private Long horaFin;
    private Long cantidadHoras;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private boolean eliminado = false;

    public RegistroSuenio() {
    }

    public RegistroSuenio(Persona persona, Long horaInicio, Long horaFin) {
        this.persona = persona;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cantidadHoras = horaFin - horaInicio;
    }

    public RegistroSuenio(Persona persona, Long cantidadHoras) {
        this.persona = persona;
        this.cantidadHoras = cantidadHoras;
    }

    public Long getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Long horaInicio) {
        this.horaInicio = horaInicio;

        if (this.horaFin != null) {
            this.cantidadHoras = this.horaFin - this.horaInicio;
        }
    }

    public Long getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Long horaFin) {
        this.horaFin = horaFin;

        if (this.horaInicio != null) {
            this.cantidadHoras = this.horaFin - this.horaInicio;
        }
    }

    public Long getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(Long cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public void setInicioyFin(Long horaInicio, Long horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cantidadHoras = horaFin - horaInicio;
    }

    public void eliminar() {
        this.eliminado = true;
    }

}
