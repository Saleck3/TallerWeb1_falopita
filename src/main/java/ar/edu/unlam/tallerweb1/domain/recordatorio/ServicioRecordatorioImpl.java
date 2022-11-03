package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.recordatorio.FechaRecordatorio.EstadosFecha;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioFechaRecordatorio;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service("servicioRecordatorio")
@Transactional
public class ServicioRecordatorioImpl implements ServicioRecordatorio{

    private RepositorioRecordatorio repositorioRecordatorio;
    private RepositorioFechaRecordatorio repositorioFechaRecordatorio;
    private ServicioPersona servicioPersona;

    public ServicioRecordatorioImpl(){}

    @Autowired
    public ServicioRecordatorioImpl(ServicioPersona servicioPersona,
                                    RepositorioRecordatorio repositorioRecordatorio,
                                    RepositorioFechaRecordatorio repositorioFechaRecordatorio){
        this.servicioPersona = servicioPersona;
        this.repositorioRecordatorio = repositorioRecordatorio;
        this.repositorioFechaRecordatorio = repositorioFechaRecordatorio;
    }

    @Override
    public Recordatorio obtenerRecordatorio(Long idRecordatorio) {
        return repositorioRecordatorio.obtener(idRecordatorio);
    }

    @Override
    public Recordatorio crearRecordatorio(DatosRecordatorio datos, Long idPersona) {
        Persona persona = obtenerPersona(idPersona);

        Recordatorio recordatorio = guardarRecordatorio(datos, persona);

        if(!seRepite(datos)){
            guardarFecha(datos.getFecha(), datos.getHora(), recordatorio);
        }
        else{
            guardarFechasRecordatorio(datos.getFecha(), datos.getHora(), datos.getFrecuenciaEnum(), datos.getCantidadOcurrencias(), recordatorio);
        }

        return recordatorio;
    }

    @Override
    public void eliminarRecordatorio(Long idRecordatorio) {
        Recordatorio recordatorio = repositorioRecordatorio.obtener(idRecordatorio);
        repositorioRecordatorio.eliminar(recordatorio);
    }

    @Override
    public List<Recordatorio> listarTodos(Long id) {
        Persona persona = servicioPersona.obtenerPersona(id);
        return repositorioRecordatorio.listarPorPersona(persona);
    }

    @Override
    public List<FechaRecordatorio> listarFechasANotificar() {
        List<FechaRecordatorio> listaFechas = repositorioFechaRecordatorio.listarPorEstado(EstadosFecha.NOTIFICADO);
        return listaFechas;
    }

    @Override
    public Integer actualizarEstado() {
        LocalDate hoy = LocalDate.now();
        LocalTime ahora = LocalTime.now();
        List<FechaRecordatorio> listaFechas = repositorioFechaRecordatorio.listarPorFecha(hoy);
        Integer actualizados = 0;

        if(!listaFechas.isEmpty()){
            for (FechaRecordatorio fecha : listaFechas){
                if(fecha.getHora().isBefore(ahora)){ //si la hora ya pasó, se notifica
                    fecha.setEstado(EstadosFecha.NOTIFICADO);
                    repositorioFechaRecordatorio.modificar(fecha);
                    actualizados++;
                }
            }
        }

        return actualizados;
    }

    private Persona obtenerPersona(Long idPersona) {
        Persona persona = servicioPersona.obtenerPersona(idPersona);
        return persona;
    }

    private Recordatorio guardarRecordatorio(DatosRecordatorio datos, Persona persona) {
        Recordatorio recordatorio = new Recordatorio(persona, datos.getContenido(), datos.getTipoEnum());
        repositorioRecordatorio.guardar(recordatorio);
        return recordatorio;
    }

    private void guardarFechasRecordatorio(LocalDate fecha, LocalTime hora,
                                           Recordatorio.TipoFrecuencia frecuencia, Integer ocurrencias,
                                           Recordatorio recordatorio) {
        LocalDate siguienteFecha = fecha;

        for(int i = 0; i < ocurrencias; i++){
            guardarFecha(siguienteFecha, hora, recordatorio);
            siguienteFecha = calcularSiguienteFecha(siguienteFecha, frecuencia);
        }
    }

    private void guardarFecha(LocalDate fecha, LocalTime hora, Recordatorio recordatorio){
        FechaRecordatorio fechaRecordatorio = new FechaRecordatorio(fecha, hora);
        recordatorio.agregarFecha(fechaRecordatorio);
        repositorioFechaRecordatorio.guardar(fechaRecordatorio);
    }

    private LocalDate calcularSiguienteFecha(LocalDate siguienteFecha, Recordatorio.TipoFrecuencia frecuencia) {
        if(esDiario(frecuencia)) return siguienteFecha.plusDays(1);

        if(esSemanal(frecuencia)) return siguienteFecha.plusWeeks(1);

        if(esMensual(frecuencia)) return siguienteFecha.plusMonths(1);

        return siguienteFecha.plusYears(1);
    }

    private boolean seRepite(DatosRecordatorio datos) {
        return datos.getTipoEnum() == Recordatorio.TipoRecordatorio.RECURRENTE;
    }

    private boolean esDiario(Recordatorio.TipoFrecuencia frecuencia) {
        return frecuencia == Recordatorio.TipoFrecuencia.DIARIA;
    }

    private boolean esSemanal(Recordatorio.TipoFrecuencia frecuencia) {
        return frecuencia == Recordatorio.TipoFrecuencia.SEMANAL;
    }

    private boolean esMensual(Recordatorio.TipoFrecuencia frecuencia) {
        return frecuencia == Recordatorio.TipoFrecuencia.MENSUAL;
    }

    private boolean esAnual(Recordatorio.TipoFrecuencia frecuencia) {
        return frecuencia == Recordatorio.TipoFrecuencia.ANUAL;
    }
}
