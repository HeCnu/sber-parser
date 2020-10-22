import service.JsonParser;

public class Main {
    public static void main(String[] args) {
        String city = "Москва";
        JsonParser jsonParser = new JsonParser();
        jsonParser.sendRequest(city);
    }
}
