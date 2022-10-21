package ar.edu.unlam.tallerweb1.delivery;

public class DatosRecordatorio {
    private String contenido;
    private String strFecha;
    private String strHora;

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getStrFecha() {
        return strFecha;
    }

    public void setStrFecha(String strFecha) {
        this.strFecha = strFecha;
    }

    public String getStrHora() {
        return strHora;
    }

    public void setStrHora(String strHora) {
        this.strHora = strHora;
    }

    public String getFechaCompleta(){
        return this.strFecha.concat(this.strHora);
    }
}
