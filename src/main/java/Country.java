package main.java;

class Country {
    String countryCode;
    double minLat;
    double minLon;
    double maxLat;
    double maxLon;

    Country(String countryCode, double minLat, double minLon, double maxLat, double maxLon) {
        this.countryCode = countryCode;
        this.minLat = minLat;
        this.minLon = minLon;
        this.maxLat = maxLat;
        this.maxLon = maxLon;
    }

    boolean contains(double lat, double lon) {
        return lat >= minLat && lat <= maxLat && lon >= minLon && lon <= maxLon;
    }
}
