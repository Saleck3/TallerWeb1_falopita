package ar.edu.unlam.tallerweb1.domain.suenio;

public class EdadNegativaException extends Throwable {

    @Override
    public String getMessage() {
        return  "Error: datos incorrectos, la edad no puede ser negativa";
    }
}
