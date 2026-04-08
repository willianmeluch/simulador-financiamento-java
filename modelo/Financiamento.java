package modelo;

import java.io.Serializable;

public abstract class Financiamento implements Serializable {
    private double valorImovel;
    private int prazoFinanciamento;
    private double taxaJurosAnual;

    public Financiamento(double valorImovel, int prazoFinanciamento, double taxaJurosAnual) {
        if (valorImovel <= 0 || prazoFinanciamento <= 0 || taxaJurosAnual < 0) {
            throw new IllegalArgumentException("Valores inválidos para financiamento.");
        }
        this.valorImovel = valorImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    public double getValorImovel() {
        return valorImovel;
    }

    public int getPrazoFinanciamento() {
        return prazoFinanciamento;
    }

    public double getTaxaJurosAnual() {
        return taxaJurosAnual;
    }

    public double calcularPagamentoMensal() {
        double taxaMensal = this.taxaJurosAnual / 12;
        int numeroDePagamentos = this.prazoFinanciamento * 12;
        double pagamentoMensal = this.valorImovel * (taxaMensal * Math.pow(1 + taxaMensal, numeroDePagamentos)) / (Math.pow(1 + taxaMensal, numeroDePagamentos) - 1);
        return pagamentoMensal;
    }

    public double calcularTotalPagamento() {
        return calcularPagamentoMensal() * this.prazoFinanciamento * 12;
    }

    public abstract String getInfoExtra();
}