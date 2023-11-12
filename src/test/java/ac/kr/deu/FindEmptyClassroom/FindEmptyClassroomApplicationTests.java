package ac.kr.deu.FindEmptyClassroom;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.poi.ss.usermodel.CellType.STRING;

@SpringBootTest
class FindEmptyClassroomApplicationTests {

  public List<String> findXlsFiles(String directoryPath) {
    File directory = new File(directoryPath);
    File[] fList = directory.listFiles();
    List<String> xlsFiles = new ArrayList<String>();

    if (fList != null) {
      for (File file : fList) {
        if (file.isFile()) {
          if (file.getName().endsWith(".xls")) {
            xlsFiles.add(file.getAbsolutePath());
          }
        } else if (file.isDirectory()) {
          xlsFiles.addAll(findXlsFiles(file.getAbsolutePath()));
        }
      }
    }
    return xlsFiles;
  }

  @Test
  void fetchExcelData() {
    try {
      List<String> files = this.findXlsFiles("src/main/resources/schedule/");
      Set<String> buildingSql = new HashSet<>();
      List<String> courseSql = new ArrayList<>();
      for (String filePath: files) {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        int sheetCount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetCount; i++) {
          Sheet sheet = workbook.getSheetAt(i);
          String date_and_class = sheet.getRow(3).getCell(1).getStringCellValue();
          String[] course_date = date_and_class.split(" ");
          int course_day_of = 0;
          int course_time = 0;
          switch (course_date[0]) {
            case "월요일" -> course_day_of = 1;
            case "화요일" -> course_day_of = 2;
            case "수요일" -> course_day_of = 3;
            case "목요일" -> course_day_of = 4;
            case "금요일" -> course_day_of = 5;
            case "토요일" -> course_day_of = 6;
          }
          switch (course_date[1]) {
            case "1교시" -> course_time = 1;
            case "2교시" -> course_time = 2;
            case "3교시" -> course_time = 3;
            case "4교시" -> course_time = 4;
            case "5교시" -> course_time = 5;
            case "6교시" -> course_time = 6;
            case "7교시" -> course_time = 7;
            case "8교시" -> course_time = 8;
            case "9교시" -> course_time = 9;
            case "10교시" -> course_time = 10;
            case "11교시" -> course_time = 11;
            case "12교시" -> course_time = 12;
            case "13교시" -> course_time = 13;
            case "14교시" -> course_time = 14;
            case "15교시" -> course_time = 15;
          }
          for (int j = 5; j < sheet.getPhysicalNumberOfRows(); j++) {
            Row row = sheet.getRow(j);
            int idx = 0;
            int k = 1;
            StringBuilder building_row_sql = new StringBuilder("INSERT INTO Building (building_number, building_name) VALUES (");
            StringBuilder course_row_sql = new StringBuilder("INSERT INTO Course (course_name, department, divide_number, course_number, course_day_of, course_time, professor) VALUES (");
            while (k <= 10) {
              Cell cell = row.getCell(k);
              if (cell != null && cell.getCellType() == STRING) {
                if (idx == 0) {
                  building_row_sql.append("'").append(cell.getStringCellValue()).append("', ");
                } else if (idx == 1) {
                  building_row_sql.append("'").append(cell.getStringCellValue()).append("');");
                } else if (idx < 7) {
                  if (idx == 6 && k == 10) {
                    // 간혹 셀이 비어있는 경우에 대한 처리
                    course_row_sql.append(course_day_of).append(", ").append(course_time).append(", ").append("'").append(cell.getStringCellValue()).append("');");
                  } else if (idx == 5 && k == 9) {
                    // 간혹 셀이 비어있는 경우에 대한 처리
                    course_row_sql.append("'', '").append(cell.getStringCellValue()).append("', ");
                  } else {
                    course_row_sql.append("'").append(cell.getStringCellValue()).append("', ");
                  }
                } else if (idx == 7) {
                  course_row_sql.append(course_day_of).append(", ").append(course_time).append(", ").append("'").append(cell.getStringCellValue()).append("');");
                }
                idx += 1;
              }
              k += 1;
            }
            if (idx > 2) {
              buildingSql.add(building_row_sql.toString());
              courseSql.add(course_row_sql.toString());
            }
          }
        }
        workbook.close();
        fileInputStream.close();
        try {
          BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/bilding.sql"));
          for (String line : buildingSql.stream().sorted().collect(Collectors.toList())) {
            writer.write(line);
            writer.newLine();
          }
          writer.close();
          writer = new BufferedWriter(new FileWriter("src/main/resources/course.sql"));
          for (String line : courseSql) {
            writer.write(line);
            writer.newLine();
          }
          writer.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException | EncryptedDocumentException e) {
      e.printStackTrace();
    }
  }
}
