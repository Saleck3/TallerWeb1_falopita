package ar.edu.unlam.tallerweb1.domain.suenio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Suenio {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int edad;
    private int horasQueNecesitaDormir;

    public Suenio(int edad, int horasQueNecesitaDormir){
        this.edad= edad;
        this.horasQueNecesitaDormir=horasQueNecesitaDormir;
    }

    public Suenio() {

    }

    public int getHorasQueNecesitaDormir() {
        return horasQueNecesitaDormir;
    }

    public void setHorasQueNecesitaDormir(int horasQueNecesitaDormir) {
        this.horasQueNecesitaDormir = horasQueNecesitaDormir;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
