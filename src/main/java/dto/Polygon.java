package dto;

public class Polygon {
    private Double maxLat;
    private Double minLat;
    private Double maxLon;
    private Double minLon;

    public Polygon(Double maxLat, Double minLat, Double maxLon, Double minLon) {
        this.maxLat = maxLat;
        this.minLat = minLat;
        this.maxLon = maxLon;
        this.minLon = minLon;
    }

    public Double getMaxLat() {
        return maxLat;
    }

    public Double getMinLat() {
        return minLat;
    }

    public Double getMaxLon() {
        return maxLon;
    }

    public Double getMinLon() {
        return minLon;
    }
}
