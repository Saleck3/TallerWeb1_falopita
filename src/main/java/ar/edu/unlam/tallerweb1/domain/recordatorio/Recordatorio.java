package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;

    /*
    @OneToMany
    private List<Etiqueta> etiquetas;
    */

    @OneToMany(mappedBy = "recordatorio", orphanRemoval = true)
    private List<FechaRecordatorio> fechas = new ArrayList<>();

    private String contenido;

    private TipoRecordatorio tipo;

    public Recordatorio(){}

    public Recordatorio(Persona persona, String contenido, TipoRecordatorio tipo) {
        this.persona = persona;
        this.contenido = contenido;
        this.tipo = tipo;
    }

    public Recordatorio(List<FechaRecordatorio> listaFechas){
        this.fechas = listaFechas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public TipoRecordatorio getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecordatorio tipo) {
        this.tipo = tipo;
    }

    public List<FechaRecordatorio> getFechas() {
        return fechas;
    }

    public void setFechas(List<FechaRecordatorio> fechas) {
        this.fechas = fechas;
    }

    public void agregarFecha(FechaRecordatorio fechaRecordatorio) {
        fechaRecordatorio.setRecordatorio(this);
        this.fechas.add(fechaRecordatorio);
    }

    public enum TipoRecordatorio {
        UNICO,
        RECURRENTE
    }

    public enum TipoFrecuencia {
        DIARIA,
        SEMANAL,
        MENSUAL,
        ANUAL
    }
}
