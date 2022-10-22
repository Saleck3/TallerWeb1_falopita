package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("servicioRecordatorio")
@Transactional
public class ServicioRecordatorioImpl implements ServicioRecordatorio{

    @Autowired
    private RepositorioRecordatorio repositorioRecordatorio;

    //https://stackoverflow.com/questions/51988182/spring-boot-service-class-calling-another-service-class
    //no es mala practica, y creo que es mejor utilizarlo aca
    @Autowired
    private ServicioPersona servicioPersona;

    @Override
    public Recordatorio obtenerRecordatorio(Long idRecordatorio) {
        return repositorioRecordatorio.obtener(idRecordatorio);
    }

    @Override
    public Recordatorio crearRecordatorio(DatosRecordatorio datos, Long idPersonaAsociada) {

        Persona personaAsociada =  servicioPersona.obtenerPersona(idPersonaAsociada);
        Recordatorio recordatorioAGuardar = new Recordatorio(personaAsociada, datos.getContenido(),
                                                    datos.getFechaHora());

        Recordatorio recordatorioGuardado = repositorioRecordatorio.guardar(recordatorioAGuardar);

        return recordatorioGuardado;
    }

    @Override
    public List<Recordatorio> listarRecordatorios(Long idPersonaAsociada)
    {
        Persona personaAsociada = servicioPersona.obtenerPersona(idPersonaAsociada);
        return repositorioRecordatorio.listar(personaAsociada);
    }

    @Override
    public List<Recordatorio> listarRecordatorios(Long idPersonaAsociada, Date fechaFiltro)
    {
        return new ArrayList<>();
    }

    @Override
    public Recordatorio ocultarRecordatorio(Long idRecordatorio) {
        Recordatorio recordatorioAModificar = repositorioRecordatorio.obtener(idRecordatorio);
        recordatorioAModificar.setOculto(true);
        return repositorioRecordatorio.modificar(recordatorioAModificar);
    }

    @Override
    public void eliminarRecordatorio(Long idRecordatorio) {
        Recordatorio recordatorioAEliminar = repositorioRecordatorio.obtener(idRecordatorio);
        repositorioRecordatorio.eliminar(recordatorioAEliminar);
    }
}
