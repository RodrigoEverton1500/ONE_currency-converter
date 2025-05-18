package middleware;

import io.github.cdimascio.dotenv.Dotenv;

public class Converter {
    private String firstCurrencyID = "";
    private String secondCurrencyID = "";
    private Double value = 0.0;
    private String address = "https://v6.exchangerate-api.com/v6/";

    public Converter() {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("EXCHANGE_API_KEY");
        if(apiKey == null) {
            throw new IllegalStateException("Missing API key");
        }

        appendToAddress(apiKey);
        appendToAddress("latest");
    }

    public void appendToAddress(String string) {
        this.address = this.address + string + "/";
    }

    public String getFirstCurrencyID() {
        return firstCurrencyID;
    }

    public void setFirstCurrencyID(String firstCurrencyID) {
        this.firstCurrencyID = firstCurrencyID;
    }

    public String getSecondCurrencyID() {
        return secondCurrencyID;
    }

    public void setSecondCurrencyID(String secondCurrencyID) {
        this.secondCurrencyID = secondCurrencyID;
    }

    public String getAddress() {
        return address;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
