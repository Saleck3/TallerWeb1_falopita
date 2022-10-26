package ar.edu.unlam.tallerweb1.domain.suenio;

public class NoTieneRegistroDeSuenio extends Exception {

    public NoTieneRegistroDeSuenio() {
        super();
    }

    public NoTieneRegistroDeSuenio(String mensaje) {
        super(mensaje);
    }

    @Override
    public String getMessage() {
        String mensaje = super.getMessage();
        if (mensaje == null || mensaje.isEmpty()) {
            return "No posee suficientes registros de sueño";
        }
        return mensaje;
    }
}
