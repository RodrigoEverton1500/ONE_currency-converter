import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import middleware.Converter;
import middleware.Menu;
import models.CurrencyMap;

import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Converter converter = new Converter();
        menu.main(converter);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(converter.getAddress())).build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().create();
            CurrencyMap currencyMap = gson.fromJson(response.body(), CurrencyMap.class);

            if(currencyMap.result().equals("error")) {
                String errorType = (String) currencyMap.error_type();
                System.out.println(errorType);
                return;
            }

            Double base_value = (Double) currencyMap.conversion_rates().get(converter.getSecondCurrencyID());
            if(base_value == null) {
                base_value = 0.0;
            }
            converter.setValue(converter.getValue() * base_value);
            System.out.printf("valor * " + converter.getFirstCurrencyID() + "/" + converter.getSecondCurrencyID() + " = %.2f", converter.getValue());
        } catch (Exception exception) {
            System.out.println(exception.toString());
        }
    }
}