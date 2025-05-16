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
        System.out.print("Digite código da moeda: ");
        String currencyID = scanner.nextLine();

        String address = "https://v6.exchangerate-api.com/v6/3ecb8af07f9216332d83b17f/latest/" + currencyID.toUpperCase();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(address)).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().create();
            CurrencyMap currencyMap = gson.fromJson(response.body(), CurrencyMap.class);

            System.out.print("Digite código da moeda: ");
            currencyID = scanner.nextLine();

            Double value = (Double) currencyMap.conversion_rates().get(currencyID.toUpperCase());
            System.out.println(currencyMap.base_code() + "/" + currencyID.toUpperCase() + " = " + value);
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }
}