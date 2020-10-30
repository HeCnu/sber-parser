import dto.City;
import service.CoordinateJsonParserForLocation;
import service.FileReader;
import service.FileWriter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        FileWriter fileWriter = new FileWriter();
        CoordinateJsonParserForLocation coordinateJsonParserForLocation = new CoordinateJsonParserForLocation();
        String fileRead = "C:\\ftp\\test.xlsx";
        String fileWrite = "C:\\ftp\\data.xlsx";
        List<City> cities = fileReader.getAllCities(fileRead);
        System.out.println(cities);
        for (City city: cities) {
            city = coordinateJsonParserForLocation.sendRequest(city);
        }
        fileWriter.writeToFile(cities, fileWrite);
    }
}
