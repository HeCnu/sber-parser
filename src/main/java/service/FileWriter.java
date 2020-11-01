package service;

import dto.City;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.List;

public class FileWriter {

    private final Integer COUNT_OF_COLUMNS = 6;

    public void writeToFile(List<City> cities, String filename) {
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("DATA");
        Row firstRow = createFirstRow(sheet.createRow(0));

        for(int i = 0; i < cities.size(); i++) {
            if(!cities.get(i).getCoordinates().equals(null)) {
                Row row = writeToRowCells(sheet.createRow(i+1), cities.get(i));
            }
        }
        writeWorkbookToFile(wb, filename);
    }

    private void writeWorkbookToFile(Workbook wb, String filename) {
        try {
            FileOutputStream outputStream = new FileOutputStream(filename);
            wb.write(outputStream);
            outputStream.close();
        } catch (Exception e) {

        }
    }

    private Row createFirstRow(Row row) {
        try {
            Cell cell1 = row.createCell(0);
            cell1.setCellValue("City");

            Cell cell2 = row.createCell(1);
            cell2.setCellValue("Region");

            Cell cell3 = row.createCell(2);
            cell3.setCellValue("MaxLat");

            Cell cell4 = row.createCell(3);
            cell4.setCellValue("MinLat");

            Cell cell5 = row.createCell(4);
            cell5.setCellValue("MaxLon");

            Cell cell6 = row.createCell(5);
            cell6.setCellValue("MinLon");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return row;
    }

    private Row writeToRowCells(Row row, City city) {
        try {
            Cell cell1 = row.createCell(0);
            String cityName = city.getName();
            cell1.setCellValue(cityName);

            Cell cell2 = row.createCell(1);
            String cityRegion = city.getRegion();
            cell2.setCellValue(cityRegion);

            Cell cell3 = row.createCell(2);
            cell3.setCellValue(city.getCoordinates().getMaxLat());

            Cell cell4 = row.createCell(3);
            cell4.setCellValue(city.getCoordinates().getMinLat());

            Cell cell5 = row.createCell(4);
            cell5.setCellValue(city.getCoordinates().getMaxLon());

            Cell cell6 = row.createCell(5);
            cell6.setCellValue(city.getCoordinates().getMinLon());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return row;
    }

}
