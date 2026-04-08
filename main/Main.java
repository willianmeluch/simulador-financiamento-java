package main;

import modelo.Apartamento;
import modelo.Casa;
import modelo.Financiamento;
import util.InterfaceUsuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();
        Scanner inputOpcao = new Scanner(System.in);
        List<Financiamento> listaDeFinanciamentos = new ArrayList<>();

        System.out.println("--- SIMULADOR DE FINANCIAMENTOS ---");

        for (int i = 1; i <= 4; i++) {
            System.out.printf("\n--- Dados do Financiamento %d ---\n", i);
            double valorImovel = interfaceUsuario.pedirValorImovel();
            int prazo = interfaceUsuario.pedirPrazoFinanciamento();
            double taxa = interfaceUsuario.pedirTaxaJurosAnual();

            System.out.print("O financiamento é para (1) Casa ou (2) Apartamento? ");
            int tipoImovel = inputOpcao.nextInt();

            if (tipoImovel == 1) {
                double areaConstruida = interfaceUsuario.pedirAreaConstruida();
                listaDeFinanciamentos.add(new Casa(valorImovel, prazo, taxa, areaConstruida));
            } else if (tipoImovel == 2) {
                int andar = interfaceUsuario.pedirAndar();
                listaDeFinanciamentos.add(new Apartamento(valorImovel, prazo, taxa, andar));
            }
        }

        inputOpcao.close();

        double totalImoveis = 0;
        double totalFinanciamentos = 0;

        System.out.println("\n--- Resumo dos Financiamentos ---");
        for (Financiamento f : listaDeFinanciamentos) {
            System.out.printf("Financiamento: Valor do Imóvel: R$ %.2f, Valor Total a Pagar: R$ %.2f\n",
                    f.getValorImovel(), f.calcularTotalPagamento());
            totalImoveis += f.getValorImovel();
            totalFinanciamentos += f.calcularTotalPagamento();
        }

        System.out.printf("\nTotal de todos os imóveis: R$ %.2f\n", totalImoveis);
        System.out.printf("Total de todos os financiamentos: R$ %.2f\n", totalFinanciamentos);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("financiamentos.txt"))) {
            for (Financiamento f : listaDeFinanciamentos) {
                writer.write(f.getClass().getSimpleName() + "," +
                        f.getValorImovel() + "," +
                        f.getPrazoFinanciamento() + "," +
                        f.getTaxaJurosAnual() + "," +
                        f.getInfoExtra());
                writer.newLine();
            }
            System.out.println("\nArquivo de texto 'financiamentos.txt' salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo de texto: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("financiamentos.dat"))) {
            oos.writeObject(listaDeFinanciamentos);
            System.out.println("Arquivo binário 'financiamentos.dat' salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo binário: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("financiamentos.dat"))) {
            List<Financiamento> listaLida = (List<Financiamento>) ois.readObject();
            System.out.println("\n--- Comprovação da leitura do arquivo binário ---");
            for (Financiamento f : listaLida) {
                System.out.printf("Objeto lido: %s - Valor do Imóvel: R$ %.2f, %s\n",
                        f.getClass().getSimpleName(),
                        f.getValorImovel(),
                        f.getInfoExtra());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao ler arquivo binário: " + e.getMessage());
        }
    }
}