package service;

import com.monitorjbl.xlsx.StreamingReader;

import dto.City;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class FileReader {

    public List<City> getAllCities(String filename) {
        List<City> cities = new ArrayList<>();
        try {
            File file = new File(filename);
            Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(10240).open(file);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row currentRow = (Row) rowIterator.next();
                if (currentRow.getRowNum() == 0) {
                    continue;
                }
                Cell cell1 = currentRow.getCell(0);
                Cell cell2 = currentRow.getCell(1);
                String cell1Str = cell1.getStringCellValue();
                String cell2Str = cell2.getStringCellValue();
                City city = new City(cell1Str, cell2Str);
                cities.add(city);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

}
