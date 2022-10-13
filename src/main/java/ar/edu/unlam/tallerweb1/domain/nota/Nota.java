package ar.edu.unlam.tallerweb1.domain.nota;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nota {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String titulo;
    private String contenido;

    private estadosPosibles estado;



    public enum estadosPosibles {
        ACTIVO,
        ANCLADO,
        ARCHIVADO
    }

    public Nota() {
    }

    public Nota(String titulo, String contenido) {
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
    public void activar(){
        this.estado = estadosPosibles.ACTIVO;
    }

    public void anclar(){
        this.estado = estadosPosibles.ANCLADO;
    }

    public void desanclar(){
        this.estado = estadosPosibles.ACTIVO;
    }

    public void archivar(){
        this.estado = estadosPosibles.ARCHIVADO;
    }

    public void desarchivar(){
        this.estado = estadosPosibles.ACTIVO;
    }

}
