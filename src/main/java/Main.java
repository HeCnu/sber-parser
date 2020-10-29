import service.CoordinateJsonParserForLocation;
import service.FileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CoordinateJsonParserForLocation coordinateJsonParserForLocation = new CoordinateJsonParserForLocation();
        FileReader fileReader = new FileReader();
        String filename = "C:\\ftp\\test.xlsx";
        List<String> cities = fileReader.getAllCities(filename);
        System.out.println(cities);
        for (String city: cities) {
            coordinateJsonParserForLocation.sendRequest(city);
        }
    }
}
