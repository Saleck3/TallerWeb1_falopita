package ar.edu.unlam.tallerweb1.domain.suenio;

public class ValorRecomendado {
    private Double minimo;
    private Double maximo;
    private String mensaje;

    public ValorRecomendado() {
        this.minimo = this.maximo = 0D;
    }

    public ValorRecomendado(Double minimo, Double maximo) {
        this.minimo = minimo;
        this.maximo = maximo;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public void setMinMax(double minimo, double maximo) {
        this.minimo = minimo;
        this.maximo = maximo;
    }

    public void sumarAMinimo(Double cantidadHoras) {
        this.minimo += cantidadHoras;
    }

    public void sumarAMaximo(Double cantidadHoras) {
        this.maximo += cantidadHoras;
    }

    public void sumarMinimoYMaximo(Double cantidadHoras) {
        this.minimo += cantidadHoras;
        this.maximo += cantidadHoras;
    }

    public void restarAMinimo(Double cantidadHoras) {
        this.minimo -= cantidadHoras;
    }

    public void restarAMaximo(Double cantidadHoras) {
        this.maximo -= cantidadHoras;
    }

    public void restarMinimoYMaximo(Double cantidadHoras) {
        this.minimo += cantidadHoras;
        this.maximo += cantidadHoras;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = this.mensaje + "<br>" + mensaje;
    }
}
