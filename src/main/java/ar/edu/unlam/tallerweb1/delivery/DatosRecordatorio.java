package ar.edu.unlam.tallerweb1.delivery;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatosRecordatorio {
    private String contenido;
    private String strFecha;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public Date getFecha() {
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        return dateFormat.parse(this.strFecha);
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }
}
