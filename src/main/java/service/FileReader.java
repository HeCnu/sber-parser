package service;

import com.ibm.icu.text.CharsetDetector;
import com.monitorjbl.xlsx.StreamingReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileReader {

    public List<String> getAllCities(String filename) {

        List<String> cities = new ArrayList<>();
        try {
            final int COUNT_OF_CELLS = 2;
            FileWriter writer = new FileWriter("text.txt", false);
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
                String cell1Str = new String(cell1.getStringCellValue().getBytes("UTF-8"),"windows-1251");
                String cell2Str = new String(cell2.getStringCellValue().getBytes("UTF-8"),"windows-1251");
                cities.add(cell1Str + ", " + cell2Str);
            }
            writer.write(cities.get(0));
            writer.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cities;
    }

}
