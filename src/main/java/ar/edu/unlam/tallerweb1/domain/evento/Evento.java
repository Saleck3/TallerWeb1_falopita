package ar.edu.unlam.tallerweb1.domain.evento;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import javax.persistence.*;

@Entity
public class Evento {

@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Long ID;

private String name;

private String description;

private String date;

private Boolean everyYear;

@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
@JoinColumn(name = "persona_id")
private Persona persona;


public Evento(Persona persona, String name, String description, String date, Boolean everyYear){
    this.persona= persona;
    this.name=name;
    this.description=description;
    this.date=date;
    this.everyYear=everyYear;
}

    public Evento() {

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getEveryYear() {
        return everyYear;
    }

    public void setEveryYear(Boolean everyYear) {
        this.everyYear = everyYear;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }


}
