package ar.edu.unlam.tallerweb1.domain.nota;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import javax.persistence.*;

@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String titulo;
    private String contenido;

    private estadosPosibles estado;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    public enum estadosPosibles {
        ACTIVO,
        ANCLADO,
        ARCHIVADO,
        ELIMINADO
    }

    public Nota() {
    }

    public Nota(Persona persona, String titulo, String contenido) {
        this.persona = persona;
        this.titulo = titulo;
        this.contenido = contenido;
        this.estado = estadosPosibles.ACTIVO;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public estadosPosibles getEstado() {
        return estado;
    }

    public void activar() {
        this.estado = estadosPosibles.ACTIVO;
    }

    public void anclar() {
        this.estado = estadosPosibles.ANCLADO;
    }

    public void desanclar() {
        this.estado = estadosPosibles.ACTIVO;
    }

    public void archivar() {
        this.estado = estadosPosibles.ARCHIVADO;
    }

    public void desarchivar() {
        this.estado = estadosPosibles.ACTIVO;
    }

    public Long getID() {
        return ID;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona usuario) {
        this.persona = usuario;
    }

    public void eliminar() {
        this.estado = estadosPosibles.ELIMINADO;
    }

}
