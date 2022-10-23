package ar.edu.unlam.tallerweb1.domain.suenio;

public class NoTieneRegistroDeSuenio extends Exception {
    @Override
    public String getMessage() {
        return "No posee suficientes registros de sueño";
    }
}
