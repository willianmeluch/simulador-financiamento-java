package modelo;

public class Apartamento extends Financiamento {
    private int andar;

    public Apartamento(double valorImovel, int prazo, double taxa, int andar) {
        super(valorImovel, prazo, taxa);
        this.andar = andar;
    }

    @Override
    public String getInfoExtra() {
        return "Andar: " + andar;
    }
}