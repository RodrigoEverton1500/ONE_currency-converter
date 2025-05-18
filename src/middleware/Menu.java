package middleware;

import java.util.Scanner;

public class Menu {
    public void main(Converter converter) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite código da moeda: ");
        converter.setFirstCurrencyID(scanner.nextLine().toUpperCase());
        converter.appendToAddress(converter.getFirstCurrencyID());

        System.out.print("Digite valor: ");
        converter.setValue(scanner.nextDouble());
        scanner.nextLine();

        System.out.print("Digite código da moeda: ");
        converter.setSecondCurrencyID(scanner.nextLine().toUpperCase());
    }
}