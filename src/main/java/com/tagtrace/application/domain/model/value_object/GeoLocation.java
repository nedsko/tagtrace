package com.tagtrace.application.domain.model.value_object;

public record GeoLocation(double latitude, double longitude) {
    public GeoLocation {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude for geo location must be between -90 and 90");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude for geo location must be between -180 and 180");
        }
    }
}
