package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.domain.personas.ServicioPersona;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        Recordatorio recordatorioAGuardar;
        Persona personaAsociada;

        try {
            personaAsociada =  servicioPersona.obtenerPersona(idPersonaAsociada);
            recordatorioAGuardar = new Recordatorio(personaAsociada,
                                                    datos.getContenido(),
                                                    parsearFecha(datos.getFechaCompleta())
                                                   );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

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
    public void eliminarRecordatorio(Long idRecordatorio) {
        Recordatorio recordatorioAEliminar = repositorioRecordatorio.obtener(idRecordatorio);
        repositorioRecordatorio.eliminar(recordatorioAEliminar);
    }

    //esto puede ir en util
    private Date parsearFecha(String fechaHora) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        return formateador.parse(fechaHora);
    }
}
