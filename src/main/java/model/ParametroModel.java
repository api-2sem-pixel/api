package model;

public class ParametroModel {
    private String parametro;

    private String valor;

    public ParametroModel() {
    }

    public ParametroModel(String parametro, String valor) {
        this.parametro = parametro;
        this.valor = valor;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
