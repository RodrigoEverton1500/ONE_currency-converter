package models;

import java.util.Map;

public record CurrencyMap(String result, String error_type, String base_code, Map<String, Double> conversion_rates) {
}
