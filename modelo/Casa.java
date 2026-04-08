package modelo;

public class Casa extends Financiamento {
    private double areaConstruida;

    public Casa(double valorImovel, int prazo, double taxa, double areaConstruida) {
        super(valorImovel, prazo, taxa);
        this.areaConstruida = areaConstruida;
    }

    @Override
    public String getInfoExtra() {
        return "Área construída: " + areaConstruida + "m²";
    }
}