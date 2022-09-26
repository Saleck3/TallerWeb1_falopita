package ar.edu.unlam.tallerweb1.domain.suenio;

public class Suenio {

    public Suenio(){
    }
    public Integer obtenerCantidadHorasSuenio(Integer edad) {
        Integer horaCalculada = 0;
        if (edad > 18){
            horaCalculada = 7;
        } else if (edad >= 1 || edad <= 2){
            horaCalculada = 12;
        } else if (edad >= 3 || edad <= 5 ) {
            horaCalculada = 11;
        } else if (edad >= 6 || edad <= 12) {
            horaCalculada = 10;
        } else if (edad >= 13 || edad <= 18) {
            horaCalculada = 9;
        }
        return horaCalculada;
    }
}
