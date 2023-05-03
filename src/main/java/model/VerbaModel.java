package model;

public class VerbaModel {
    private Integer verba;

    private Double multiplicador;

    public VerbaModel() {
    }

    public VerbaModel(Integer verba, Double multiplicador) {
        this.verba = verba;
        this.multiplicador = multiplicador;
    }

    public Integer getVerba() {
        return verba;
    }

    public void setVerba(Integer verba) {
        this.verba = verba;
    }

    public Double getMultiplicador() {
        return multiplicador;
    }

    public void setMultiplicador(Double multiplicador) {
        this.multiplicador = multiplicador;
    }
}
