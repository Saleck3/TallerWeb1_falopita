package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

public class DatosPerfil {

    private String email;
    private String password;
    private String nombre;
    private Character sexo;
    private Integer edad;
    private Double altura;
    private Double peso;

    public DatosPerfil(Persona p) {
        this.email = p.getEmail();
        this.password = p.getPassword();
        this.nombre = p.getNombre();
        this.sexo = p.getSexo();
        this.edad = p.getEdad();
        this.altura = p.getAltura();
        this.peso = p.getPeso();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
