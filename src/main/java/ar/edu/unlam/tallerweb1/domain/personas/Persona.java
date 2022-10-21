package ar.edu.unlam.tallerweb1.domain.personas;

import ar.edu.unlam.tallerweb1.domain.recordatorio.Recordatorio;

import javax.persistence.*;
import java.util.List;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String nombre;
    private Integer edad;
    private Double peso;
    private Double altura;
    private Character sexo;

    //constructor requerido por Hibernate
    public Persona() {
    }

    public Persona(String email, String password, String nombre, Integer edad, Double peso, Double altura, Character sexo) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
        this.sexo = sexo;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public String toString() {
        return "ID: " + this.id +
                " Nombre: " + this.nombre +
                " Mail: " + this.email +
                " Edad: " + this.edad +
                " Altura: " + this.altura +
                " Peso: " + this.peso;
    }
}
