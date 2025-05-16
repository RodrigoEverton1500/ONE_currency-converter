import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.CurrencyMap;

import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double value = 0.0;
        System.out.print("Digite código da moeda: ");
        String currencyID = scanner.nextLine();

        String address = "https://v6.exchangerate-api.com/v6/3ecb8af07f9216332d83b17f/latest/" + currencyID.toUpperCase();

        System.out.print("Digite valor: ");
        value = scanner.nextDouble();
        scanner.nextLine();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(address)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().create();
            CurrencyMap currencyMap = gson.fromJson(response.body(), CurrencyMap.class);

            if(currencyMap.result().equals("error")) {
                String errorType = (String) currencyMap.error_type();
                System.out.println(errorType);
                return;
            }

            System.out.print("Digite código da moeda: ");
            currencyID = scanner.nextLine();

            Double base_value = (Double) currencyMap.conversion_rates().get(currencyID.toUpperCase());
            if(base_value == null) {
                base_value = 0.0;
            }
            value *= base_value;
            System.out.printf("valor * " + currencyMap.base_code() + "/" + currencyID.toUpperCase() + " = %.2f", value);
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }
}