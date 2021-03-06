package com.example.qwert.merger;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public class Retrofit {

    private static final String ENDPOINT = "http://api.fixer.io";
    private static ApiReferences apiReferences;

    static {
        initialize();
    }

    private static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiReferences = restAdapter.create(ApiReferences.class);
    }

    interface ApiReferences {
        @GET("/latest")
        void getCurrencies(@Query("base") String currency, Callback<Currency> callback);

        @GET("/{date}")
        void getCurrenciesByDate(@Path("date") String date, @Query("?base") String currency, Callback<Currency> callback);
    }

    public static void getCurrencies(List<String> currencyList, Callback<Currency> callback) {
        for (String s : currencyList) {
            apiReferences.getCurrencies(s, callback);
        }
    }

    public static void getCurrenciesByDate(String date, String base, Callback<Currency> callback) {
        apiReferences.getCurrenciesByDate(date, base, callback);
    }

}
