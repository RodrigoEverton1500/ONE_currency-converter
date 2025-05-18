package models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public record CurrencyMap(String result,
                          @SerializedName("error-type") String error_type,
                          String base_code,
                          Map<String, Double> conversion_rates)
{}
