package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
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

    @Override
    public Recordatorio crearRecordatorio(DatosRecordatorio datosRecordatorio, Persona personaAsociada) {

        Recordatorio recordatorioAGuardar;

        try {
            recordatorioAGuardar = new Recordatorio(personaAsociada, datosRecordatorio.getContenido(), parsearFecha(datosRecordatorio.getFechaCompleta()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Recordatorio recordatorioGuardado = repositorioRecordatorio.guardar(recordatorioAGuardar);

        return recordatorioGuardado;
    }

    @Override
    public List<Recordatorio> listarRecordatorios(Persona persona)
    {
        return repositorioRecordatorio.listar(persona);
    }

    private Date parsearFecha(String fechaHora) throws ParseException {
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        return formateador.parse(fechaHora);
    }
}
