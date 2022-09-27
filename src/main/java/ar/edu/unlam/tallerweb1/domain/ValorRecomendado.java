package ar.edu.unlam.tallerweb1.domain;

public class ValorRecomendado {
    private Double minimo;
    private Double maximo;

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
}
