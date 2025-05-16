package models;

import java.util.Map;

public record CurrencyMap(String result,
                          @JsonProperty("error-type")String error_type,
                          String base_code,
                          Map<String, Double> conversion_rates)
{}
