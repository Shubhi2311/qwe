package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CountryLocator {
    private static List<Country> countries = new ArrayList<>();

    static {
        loadCountries("/Users/shubhi/Desktop/Work/Java Assignment/Assignment/src/main/resources/countries.csv");
    }

    public static void loadCountries(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String countryCode = parts[0];
                    double minLat = Double.parseDouble(parts[1]);
                    double minLon = Double.parseDouble(parts[2]);
                    double maxLat = Double.parseDouble(parts[3]);
                    double maxLon = Double.parseDouble(parts[4]);
                    countries.add(new Country(countryCode, minLat, minLon, maxLat, maxLon));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the countries file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing latitude/longitude values: " + e.getMessage());
        }
    }

    public String getCountryCode(double latitude, double longitude) {
        for (Country country : countries) {
            if (country.contains(latitude, longitude)) {
                return country.countryCode;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        CountryLocator locator = new CountryLocator();
        double latitude = 37.7749;
        double longitude = -122.4194;
        String countryCode = locator.getCountryCode(latitude, longitude);
        System.out.println("Country code: " + countryCode);
    }
}
