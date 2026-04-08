package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner = new Scanner(System.in);

    public double pedirValorImovel() {
        System.out.print("Digite o valor do imóvel: R$ ");
        return scanner.nextDouble();
    }

    public int pedirPrazoFinanciamento() {
        System.out.print("Digite o prazo do financiamento (em anos): ");
        return scanner.nextInt();
    }

    public double pedirTaxaJurosAnual() {
        System.out.print("Digite a taxa de juros anual: ");
        return scanner.nextDouble();
    }

    public double pedirAreaConstruida() {
        System.out.print("Digite a área construída (m²): ");
        return scanner.nextDouble();
    }

    public int pedirAndar() {
        System.out.print("Digite o número do andar: ");
        return scanner.nextInt();
    }
}