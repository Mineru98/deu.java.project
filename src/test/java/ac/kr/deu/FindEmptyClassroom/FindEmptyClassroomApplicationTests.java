package ac.kr.deu.FindEmptyClassroom;

import java.io.*;
import java.util.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.poi.ss.usermodel.CellType.STRING;

@SpringBootTest
class FindEmptyClassroomApplicationTests {
  private String[] buildingArray = {
          "공유대학",
          "병원",
          "보건소",
          "대학본관",
          "제2인문관",
          "효민체육관",
          "중앙도서관",
          "의료보건관",
          "생활과학관",
          "음악관",
          "창의관",
          "지천관",
          "법정관",
          "산학협력관",
          "건윤관",
          "공학관",
          "정보공학관",
          "학생군사교육단",
          "상경관",
          "국제관",
          "동의스포츠센터",
          "제1인문관",
          "효민야구장",
          "효민축구장",
          "테니스장",
          "한의학관",
          "간호학관"
  };

  private String[] roomArray = {
          "공유대학",
          "병원",
          "보건소",
          "대학본관1",
          "제2인문관10",
          "효민체육관11",
          "중앙도서관12",
          "의료보건관15",
          "생활과학관16",
          "음악관17",
          "창의관18",
          "지천관19",
          "법정관2",
          "산학협력관20",
          "건윤관21",
          "공학관22",
          "정보공학관23",
          "학생군사교육단25",
          "상경관3",
          "국제관5",
          "동의스포츠센터6",
          "제1인문관9",
          "효민야구장A",
          "효민축구장B",
          "테니스장G",
          "한의학관양정캠퍼스-1",
          "간호학관양정캠퍼스-2"
  };

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
      Set<String> roomSql = new HashSet<>();
      List<String> courseSql = new ArrayList<>();
      HashMap<String, Integer> buildingMap = new HashMap<String, Integer>();
      HashMap<String, Integer> roomMap = new HashMap<String, Integer>();
      for (int i = 0; i < buildingArray.length; i++) {
        buildingMap.put(buildingArray[i], i + 1);
      }for (int i = 0; i < roomArray.length; i++) {
        roomMap.put(roomArray[i], i + 1);
      }
      int courseId = 1;
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
            StringBuilder building_row_sql = new StringBuilder("INSERT INTO Building (universityId, buildingNumber, buildingName, createdAt, updatedAt) VALUES (1, ");
            StringBuilder room_row_sql = new StringBuilder("INSERT INTO Room (universityId, buildingId, roomNumber, createdAt, updatedAt) VALUES (1, ");
            StringBuilder course_row_sql = new StringBuilder("INSERT INTO Course (universityId, courseId, buildingId, roomId, courseName, courseNumber, divideNumber, department, courseDayOf, courseTime, professor, createdAt, updatedAt) VALUES (1, ");
            String buildName = "";
            String roomName = "";
            while (k <= 10) {
              Cell cell = row.getCell(k);
              if (cell != null && cell.getCellType() == STRING) {
                if (idx == 0) {
                  buildName = cell.getStringCellValue();
                  String building_number = switch (cell.getStringCellValue()) {
                    case "간호학관" -> "양정캠퍼스-2";
                    case "건윤관" -> "21";
                    case "국제관" -> "5";
                    case "공유대학" -> "";
                    case "공학관" -> "22";
                    case "대학본관" -> "1";
                    case "동의스포츠센터" -> "6";
                    case "법정관" -> "2";
                    case "병원" -> "";
                    case "보건소" -> "";
                    case "산학협력관" -> "20";
                    case "상경관" -> "3";
                    case "생활과학관" -> "16";
                    case "음악관" -> "17";
                    case "의료보건관" -> "15";
                    case "정보공학관" -> "23";
                    case "제1인문관" -> "9";
                    case "제2인문관" -> "10";
                    case "중앙도서관" -> "12";
                    case "지천관" -> "19";
                    case "창의관" -> "18";
                    case "테니스장" -> "G";
                    case "학생군사교육단" -> "25";
                    case "한의학관" -> "양정캠퍼스-1";
                    case "효민야구장" -> "A";
                    case "효민체육관" -> "11";
                    case "효민축구장" -> "B";
                    default -> "";
                  };
                  roomName = buildName + building_number;
                  building_row_sql.append("'").append(building_number).append("', '").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                } else if (idx == 1) {
                  room_row_sql.append(buildingMap.get(buildName)).append(", ").append("'").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                } else if (idx < 6) {
                  if (idx == 2) {
                    course_row_sql.append(courseId).append(", ");
                    courseId += 1;
                    course_row_sql.append(buildingMap.get(buildName)).append(", ").append(roomMap.get(roomName)).append(", ");
                  }
                  if (!(idx == 5 && k == 9)) {
                     course_row_sql.append("'").append(cell.getStringCellValue()).append("', ");
                  }
                } else if (idx == 6 && k == 10) {
                  // 간혹 셀이 비어있는 경우에 대한 처리
                  course_row_sql.append("'', ").append(course_day_of).append(", ").append(course_time).append(", ").append("'").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                } else if (idx == 7) {
                  course_row_sql.append(course_day_of).append(", ").append(course_time).append(", ").append("'").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                }
                idx += 1;
              }
              k += 1;
            }
            if (idx > 2) {
              buildingSql.add(building_row_sql.toString());
              roomSql.add(room_row_sql.toString());
              courseSql.add(course_row_sql.toString());
            }
          }
        }
        workbook.close();
        fileInputStream.close();
        try {
          BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/sql/bilding.sql"));
          for (String line : buildingSql.stream().sorted().toList()) {
            writer.write(line);
            writer.newLine();
          }
          writer.close();

          writer = new BufferedWriter(new FileWriter("src/main/resources/sql/room.sql"));
          for (String line : roomSql.stream().sorted().toList()) {
            writer.write(line);
            writer.newLine();
          }
          writer.close();

          writer = new BufferedWriter(new FileWriter("src/main/resources/sql/course.sql"));
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
