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
        for (int i = 0; i < cities.size(); i++) {
            Row row = writeToRowCells(sheet.createRow(i), cities.get(i));
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

    private Row writeToRowCells(Row row, City city) {

        try {
            Cell cell1 = row.createCell(0);
            String cityName = new String(city.getName().getBytes("windows-1251"),"UTF-8");
            cell1.setCellValue(cityName);

            Cell cell2 = row.createCell(1);
            String cityRegion = new String(city.getRegion().getBytes("windows-1251"),"UTF-8");
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

        }

        return row;

    }

}
