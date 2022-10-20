package ar.edu.unlam.tallerweb1.domain.suenio;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegistroSuenio {

    private Long horaInicio;
    private Long horaFin;
    private Long cantidadHoras;
    private Long id;

    public RegistroSuenio(){}

    public RegistroSuenio(Long horaInicio, Long horaFin){
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cantidadHoras = horaFin - horaInicio;
    }

    public RegistroSuenio(Long cantidadHoras){
        this.cantidadHoras = cantidadHoras;
    }

    public Long getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Long horaInicio) {
        this.horaInicio = horaInicio;

        if(this.horaFin != null){
            this.cantidadHoras = this.horaFin - this.horaInicio;
        }
    }

    public Long getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Long horaFin) {
        this.horaFin = horaFin;

        if(this.horaInicio != null){
            this.cantidadHoras = this.horaFin - this.horaInicio;
        }
    }

    public Long getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(Long cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInicioyFin(Long horaInicio, Long horaFin){
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cantidadHoras = horaFin - horaInicio;
    }

    @Id
    public Long getId() {
        return id;
    }
}
