package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Coordinates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CoordinateJsonParserForLocation {

    private List<Coordinates> coordinatesList = new ArrayList<>();

    public List<Double> getListPoints(String city) {
        return null;
    }

    public void sendRequest(String city) {
        try {
            String city1 = "Москва";
            String url = "https://nominatim.openstreetmap.org/search.php?q=" + city + "&polygon_geojson=1&format=jsonv2";
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new URL(url).openStream()
                    ));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(reader).get(0);
            JsonNode responseNode = rootNode.path("geojson");
            JsonNode parametersNode = responseNode.path("coordinates");
            getAllElementsFromArray(parametersNode);

            List<Double> latList = coordinatesList.stream()
                    .map(x -> x.getLat())
                    .collect(Collectors.toList());
            List<Double> lonList = coordinatesList.stream()
                    .map(x -> x.getLon())
                    .collect(Collectors.toList());
            Double maxLat = latList.stream()
                    .max(Comparator.comparing(Double::valueOf))
                    .get();
            Double minLat = latList.stream()
                    .min(Comparator.comparing(Double::valueOf))
                    .get();
            Double maxLon = lonList.stream()
                    .max(Comparator.comparing(Double::valueOf))
                    .get();
            Double minLon = lonList.stream()
                    .min(Comparator.comparing(Double::valueOf))
                    .get();

            System.out.println("Результат для " + city + ": " + maxLat + " - " + minLat + " - " + maxLon + " - " + minLon);

        } catch (Exception e) {
            System.out.println("Oops...\n" + e.getMessage());
        }
    }

    private boolean getAllElementsFromArray(JsonNode node) {
        for (int i = 0; i < node.size(); i++) {
            boolean flag = false;
            if (node.has(i)) {
                flag = getAllElementsFromArray(node.get(i));
                if (flag == true)
                    continue;
            }
            try  {
                Double first = Double.parseDouble(node.get(0).toString());
                Double second = Double.parseDouble(node.get(1).toString());
                coordinatesList.add(new Coordinates(first, second));
            } catch (Exception e) {
                continue;
            }
            return true;
        }
        return false;
    }

}