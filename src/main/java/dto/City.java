package dto;

public class City {
    private String name;
    private String region;
    private Polygon coordinates;

    public City(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public void setCoordinates(Polygon coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public Polygon getCoordinates() {
        return coordinates;
    }
}
