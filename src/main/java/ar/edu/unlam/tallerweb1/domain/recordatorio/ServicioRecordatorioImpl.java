package ar.edu.unlam.tallerweb1.domain.recordatorio;

import ar.edu.unlam.tallerweb1.delivery.DatosRecordatorio;
import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.infrastructure.recordatorio.RepositorioRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioRecordatorio")
@Transactional
public class ServicioRecordatorioImpl implements ServicioRecordatorio{

    @Autowired
    private RepositorioRecordatorio repositorioRecordatorio;

    @Override
    public Recordatorio crearRecordatorio(DatosRecordatorio datosRecordatorio, Persona personaAsociada) {
        Recordatorio recordatorioAGuardar = new Recordatorio(personaAsociada, datosRecordatorio.getContenido(), datosRecordatorio.getFecha());

        return repositorioRecordatorio.guardar(recordatorioAGuardar);
    }

    @Override
    public List<Recordatorio> listarRecordatorios(Persona persona) {
        return null;
    }
}
