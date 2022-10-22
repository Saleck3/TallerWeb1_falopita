package ar.edu.unlam.tallerweb1.domain.suenio;

import ar.edu.unlam.tallerweb1.domain.personas.Persona;
import ar.edu.unlam.tallerweb1.infrastructure.suenio.RepositorioSuenio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServicioSuenioImpl implements ServicioSuenio {

    @Autowired
    private RepositorioSuenio repositorioSuenio;

    public ServicioSuenioImpl() {
    }

    public ServicioSuenioImpl(RepositorioSuenio repositorioSuenio) {
        this.repositorioSuenio = repositorioSuenio;
    }

    @Override
    public ValorRecomendado obtenerCantidadHorasSuenio(Persona persona) throws Exception {


        if (persona.getEdad() < 0) {
            throw new Exception("Una persona no puede tener edad negativa");
        }

        //primero calculo las horas segun la edad
        ValorRecomendado recomendacion = horasSegunEdad(persona.getEdad());

        //Agrego horas si hizo ejercicio
        recomendacion.sumarMinimoYMaximo(agregadoHorasPorEjercicio(persona));
        //Segun como viene durmiendo, agrego o saco horas
        recomendacion.sumarMinimoYMaximo(sumaDeHorasPorPocoSuenio(persona, recomendacion));
        recomendacion.restarMinimoYMaximo(restaDeHorasPorMuchoSuenio(persona, recomendacion));

        return recomendacion;
    }

    private ValorRecomendado horasSegunEdad(Integer edad) {
        //Por ahora me baso en esto: https://www.elcorreo.com/content-local/cuantas-horas-necesitamos-dormir-en-funcion-de-la-edad/
        if (edad <= 1) {
            return new ValorRecomendado(12D, 15D);
        }
        if (edad <= 2) {
            return new ValorRecomendado(11d, 14d);
        }
        if (edad <= 5) {
            return new ValorRecomendado(10d, 13d);
        }
        if (edad <= 13) {
            return new ValorRecomendado(9d, 11d);
        }
        if (edad <= 18) {
            return new ValorRecomendado(8d, 10d);
        }
        if (edad <= 64) {
            return new ValorRecomendado(7d, 9d);
        }
        return new ValorRecomendado(7d, 8d);
    }


    @Override
    public RegistroSuenio guardarRegistroSuenio(RegistroSuenio registroSuenioAGuardar) {
        return repositorioSuenio.guardar(registroSuenioAGuardar);
    }

    @Override
    public List<RegistroSuenio> obtenerRegistrosSuenio(Persona persona) {
        return repositorioSuenio.obtener(persona);
    }

    @Override
    public void eliminarRegistroSuenio(RegistroSuenio registroSuenio) {
        repositorioSuenio.eliminar(registroSuenio);
    }

    @Override
    public Double cantidadHorasDormidaEnLosUltimosXDias(Persona persona, int cantidadDias) {
        //TODO
        return null;
    }

    private Double agregadoHorasPorEjercicio(Persona persona) {
        /* Si hace ejercicio, deberia dormir mas
         * Voy a subir 30 minutos por cada hora de ejercicio
         * Tengo que ver de medirlo en puntos para ver si hace falta mas o menos
         */

        //TODO Despues de hacer la clase ejercicio
        return 0.0;
    }

    private Double restaDeHorasPorMuchoSuenio(Persona persona, ValorRecomendado recomendacion) {


        // Si venis durmiendo mucho tendrias que dormir menos horas para que el cuerpo no se acostumbre
        Double tiempo = 0D;
        Double ultimos2Dias = cantidadHorasDormidaEnLosUltimosXDias(persona, 2);

        //si dormiste mas de 3 horas de la maxima recomendadas en los ultimos dos dias, disminuyo media hora
        //EJ: mas de 21 horas (9*2+3) disminuyo media hora
        if (ultimos2Dias - recomendacion.getMaximo() * 2 >= 3) {
            tiempo += 0.5;
        }

        // A partir de ahi, bajo una hora por cada hora que se haya pasado
        // EJ: si durmio 23 horas (9*2+3+2) disminuyo dos horas y media
        if (ultimos2Dias - recomendacion.getMaximo() * 2 > 4) {
            tiempo += ultimos2Dias - recomendacion.getMaximo() * 2 - 3;
        }

        return tiempo;
    }

    private Double sumaDeHorasPorPocoSuenio(Persona persona, ValorRecomendado recomendacion) {
        /*
         ** Para los ejemplos asumo horas recomendadas (despues del ejercicio) de 7 a 9
         * Si viene durmiendo poco, deberia dormir mas
         * Voy a setear poco en tres cuartos de las horas minimas recomendadas en los ultimos 2 dias
         ** EJ: Menos de 10.5 (7*2*0.75=10.5)
         * Si pasa esto, voy a agregar una hora
         * Voy a setear muy poco en la mitad de las horas maximas recomendadas en los ultimos 2 dias
         ** EJ: Menos de 9 horas (9*2/2)
         * Si pasa esto, voy a agregar dos horas
         */
//TODO
        return null;
    }

}
