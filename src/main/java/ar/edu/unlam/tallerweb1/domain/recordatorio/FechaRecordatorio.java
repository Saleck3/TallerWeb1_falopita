package ar.edu.unlam.tallerweb1.domain.recordatorio;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class FechaRecordatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_recordatorio_id")
    private Recordatorio recordatorio;

    private LocalDate fecha;

    private LocalTime hora;

    public FechaRecordatorio(){}

    public FechaRecordatorio(LocalDate fecha, LocalTime hora){
        this.fecha = fecha;
        this.hora = hora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Recordatorio getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(Recordatorio recordatorio) {
        this.recordatorio = recordatorio;
    }
}
