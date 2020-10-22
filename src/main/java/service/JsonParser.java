package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Coordinates;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonParser {

    private List<Coordinates> coordinatesList = new ArrayList<>();

    public List<Double> getListPoints(String city) {
        return null;
    }

    public void sendRequest(String city) {
        try {
            String url = "https://nominatim.openstreetmap.org/search.php?q="+ city + "&polygon_geojson=1&format=jsonv2";
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.toString()).get(0);
            JsonNode responseNode = rootNode.path("geojson");
            JsonNode parametersNode = responseNode.path("coordinates");

            getAllElementsFromArray(parametersNode);
//            for (JsonNode coordinates: parametersNode) {
//                Double first = Double.parseDouble(coordinates.get(0).toString());
//                Double second = Double.parseDouble(coordinates.get(1).toString());
//                coordinatesList.add(new Coordinates(first, second));
//            }

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

            System.out.println("Результат: " + maxLat + " - " + minLat + " - " + maxLon + " - " + minLon);

        } catch (Exception e) {
            System.out.println("Oops.... " + e.getMessage());
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
