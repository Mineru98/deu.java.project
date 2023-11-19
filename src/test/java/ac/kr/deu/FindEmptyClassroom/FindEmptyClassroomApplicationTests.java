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
          "원격,1",
          "131,10",
          "132,10",
          "133,10",
          "143,10",
          "201,10",
          "203,10",
          "204,10",
          "205,10",
          "206,10",
          "207,10",
          "209,10",
          "210,10",
          "212,10",
          "213,10",
          "214,10",
          "215,10",
          "301,10",
          "301-1,10",
          "302,10",
          "303,10",
          "304,10",
          "305,10",
          "306,10",
          "307,10",
          "401,10",
          "402,10",
          "404,10",
          "407,10",
          "409,10",
          "412,10",
          "416,10",
          "417,10",
          "418,10",
          "419,10",
          "424,10",
          "426,10",
          "427,10",
          "101,11",
          "102,11",
          "103,11",
          "105,11",
          "106,11",
          "107,11",
          "120,11",
          "220,11",
          "221,11",
          "222,11",
          "223-1,11",
          "223-2,11",
          "223-4,11",
          "301,11",
          "303,11",
          "306,11",
          "307,11",
          "308,11",
          "309,11",
          "317,11",
          "318,11",
          "319,11",
          "324,11",
          "325,11",
          "326,11",
          "404,11",
          "405,11",
          "406,11",
          "407,11",
          "408,11",
          "416,11",
          "417,11",
          "418,11",
          "429,11",
          "502,11",
          "505,11",
          "506,11",
          "507,11",
          "509,11",
          "517,11",
          "518,11",
          "520,11",
          "521,11",
          "524,11",
          "525,11",
          "607,11",
          "611,11",
          "616,11",
          "618,11",
          "619,11",
          "620,11",
          "620-2,11",
          "622,11",
          "623,11",
          "625,11",
          "627,11",
          "628,11",
          "713,11",
          "716,11",
          "717,11",
          "718,11",
          "719,11",
          "720,11",
          "724,11",
          "725,11",
          "726,11",
          "727,11",
          "730,11",
          "B115-1,11",
          "B115-3,11",
          "B115-4,11",
          "304,12",
          "415,12",
          "416,12",
          "417,12",
          "418,12",
          "421,12",
          "502,12",
          "503,12",
          "505,12",
          "507,12",
          "514,12",
          "515,12",
          "519,12",
          "521,12",
          "601,12",
          "606,12",
          "607,12",
          "608,12",
          "609-1,12",
          "620,12",
          "621,12",
          "622,12",
          "111,13",
          "112,13",
          "115,13",
          "201,13",
          "206,13",
          "207,13",
          "208-1,13",
          "211,13",
          "212,13",
          "215,13",
          "301,13",
          "302,13",
          "304,13",
          "305,13",
          "306,13",
          "307,13",
          "309,13",
          "310,13",
          "311,13",
          "312,13",
          "314,13",
          "315,13",
          "402,13",
          "403,13",
          "404,13",
          "405,13",
          "406-1,13",
          "406-2,13",
          "407,13",
          "408,13",
          "409,13",
          "410,13",
          "411,13",
          "412,13",
          "415,13",
          "416,13",
          "501,13",
          "504,13",
          "505,13",
          "509,13",
          "510,13",
          "512,13",
          "513,13",
          "514,13",
          "515,13",
          "516,13",
          "517,13",
          "518,13",
          "109,14",
          "201,14",
          "314,14",
          "410,14",
          "411,14",
          "415,14",
          "416-3,14",
          "417,14",
          "419,14",
          "507-1,14",
          "510,14",
          "517,14",
          "519,14",
          "522,14",
          "612-B,14",
          "614,14",
          "616,14",
          "618,14",
          "620,14",
          "622,14",
          "623,14",
          "711,14",
          "712,14",
          "713,14",
          "714,14",
          "715,14",
          "717,14",
          "718,14",
          "719,14",
          "720,14",
          "801,14",
          "811,14",
          "813,14",
          "814-1,14",
          "102,15",
          "201,15",
          "101,16",
          "102,16",
          "103,16",
          "104,16",
          "105,16",
          "112,16",
          "113,16",
          "115-B,16",
          "119,16",
          "120,16",
          "121,16",
          "125,16",
          "126,16",
          "127,16",
          "129,16",
          "130,16",
          "208,16",
          "208-A,16",
          "215,16",
          "216,16",
          "218,16",
          "221,16",
          "225,16",
          "226,16",
          "227,16",
          "228,16",
          "229,16",
          "230,16",
          "301,16",
          "302,16",
          "304,16",
          "304-A,16",
          "305-A,16",
          "306-A,16",
          "312,16",
          "313,16",
          "318,16",
          "319,16",
          "320,16",
          "323,16",
          "325,16",
          "326,16",
          "327,16",
          "329,16",
          "330-1,16",
          "401-A,16",
          "402,16",
          "403,16",
          "404-1,16",
          "405,16",
          "406,16",
          "407,16",
          "409,16",
          "411,16",
          "413,16",
          "414,16",
          "416,16",
          "418-A,16",
          "419,16",
          "420,16",
          "421,16",
          "424,16",
          "425,16",
          "426,16",
          "427,16",
          "431,16",
          "501,16",
          "502,16",
          "503,16",
          "504,16",
          "513,16",
          "514,16",
          "516,16",
          "518,16",
          "519,16",
          "520,16",
          "521,16",
          "527,16",
          "529,16",
          "602-1,16",
          "602-2,16",
          "602-3,16",
          "603-1,16",
          "603-2,16",
          "603-3,16",
          "605-A,16",
          "613,16",
          "614,16",
          "616,16",
          "618,16",
          "619,16",
          "620,16",
          "627-C,16",
          "701-B,16",
          "701-C,16",
          "702-B,16",
          "702-C,16",
          "703-B,16",
          "703-C,16",
          "703-D,16",
          "703-Ⅰ,16",
          "703-Ⅱ,16",
          "704-B,16",
          "704-C,16",
          "704-D,16",
          "704-E,16",
          "704-Ⅰ,16",
          "704-Ⅱ,16",
          "706,16",
          "707,16",
          "708,16",
          "709,16",
          "710,16",
          "717,16",
          "723,16",
          "724,16",
          "725,16",
          "728,16",
          "729,16",
          "733-A,16",
          "733-B,16",
          "733-C,16",
          "734-A,16",
          "735-A,16",
          "735-B,16",
          "735-C,16",
          "107,17",
          "108,17",
          "109,17",
          "110,17",
          "205,17",
          "206,17",
          "209,17",
          "210,17",
          "306,17",
          "322,17",
          "324,17",
          "410,17",
          "411,17",
          "412,17",
          "415,17",
          "417,17",
          "418,17",
          "419,17",
          "510,17",
          "511,17",
          "512,17",
          "514,17",
          "516,17",
          "520,17",
          "521,17",
          "610,17",
          "611,17",
          "613,17",
          "614,17",
          "615,17",
          "617,17",
          "620,17",
          "710,17",
          "713,17",
          "714,17",
          "715,17",
          "716,17",
          "717,17",
          "810,17",
          "811,17",
          "812,17",
          "814,17",
          "815,17",
          "816,17",
          "817,17",
          "908,17",
          "911,17",
          "912,17",
          "913,17",
          "914,17",
          "915,17",
          "916,17",
          "918,17",
          "301,18",
          "208,19",
          "211,19",
          "212,19",
          "213,19",
          "214,19",
          "215,19",
          "302,19",
          "304,19",
          "309,19",
          "311,19",
          "312,19",
          "313,19",
          "314,19",
          "315,19",
          "403,19",
          "404,19",
          "408,19",
          "409,19",
          "411,19",
          "412,19",
          "413,19",
          "414,19",
          "415,19",
          "506,19",
          "510,19",
          "511,19",
          "512,19",
          "513,19",
          "514,19",
          "515,19",
          "603,19",
          "604,19",
          "606,19",
          "612,19",
          "613,19",
          "111,2",
          "113,2",
          "114,2",
          "115,2",
          "116,2",
          "117,2",
          "118,2",
          "120,2",
          "121,2",
          "222,2",
          "303,20",
          "305,20",
          "306,20",
          "313,20",
          "401,20",
          "402,20",
          "403,20",
          "404,20",
          "405,20",
          "406,20",
          "407,20",
          "502,20",
          "503-1,20",
          "504,20",
          "505,20",
          "506,20",
          "507,20",
          "601,20",
          "602,20",
          "603,20",
          "604,20",
          "605,20",
          "607,20",
          "609,20",
          "612,20",
          "613,20",
          "614,20",
          "615,20",
          "616,20",
          "618,20",
          "619,20",
          "620,20",
          "621,20",
          "622,20",
          "704,20",
          "707,20",
          "708,20",
          "709,20",
          "712,20",
          "713,20",
          "714,20",
          "715,20",
          "716,20",
          "717,20",
          "718,20",
          "720,20",
          "721,20",
          "722,20",
          "801,20",
          "803,20",
          "804,20",
          "805-1,20",
          "901,20",
          "903,20",
          "904,20",
          "907,20",
          "908,20",
          "909,20",
          "910,20",
          "101,21",
          "105,21",
          "201,21",
          "401,21",
          "403,21",
          "201,22",
          "202,22",
          "203,22",
          "208,22",
          "301-1,22",
          "302,22",
          "303,22",
          "314,22",
          "315,22",
          "401,22",
          "404,22",
          "407,22",
          "408,22",
          "410,22",
          "411,22",
          "413,22",
          "414,22",
          "415,22",
          "501,22",
          "502,22",
          "503,22",
          "504,22",
          "506,22",
          "509,22",
          "514,22",
          "601,22",
          "602,22",
          "603,22",
          "607,22",
          "608,22",
          "609,22",
          "610,22",
          "611,22",
          "612,22",
          "000,23",
          "000,24",
          "000,25",
          "201,26",
          "205,26",
          "206,26",
          "207,26",
          "214,26",
          "302,26",
          "303,26",
          "306-2,26",
          "307,26",
          "308,26",
          "309,26",
          "311,26",
          "314,26",
          "402,26",
          "403,26",
          "404,26",
          "405,26",
          "406,26",
          "407-1,26",
          "408-2,26",
          "409-1,26",
          "412,26",
          "414,26",
          "501,26",
          "502,26",
          "509,26",
          "512,26",
          "101,27",
          "102,27",
          "102-1,27",
          "103,27",
          "204,27",
          "206,27",
          "207,27",
          "208,27",
          "209,27",
          "210,27",
          "211,27",
          "101,3",
          "504,4",
          "505,4",
          "506,4",
          "507,4",
          "508,4",
          "201,5",
          "202,5",
          "206,5",
          "207,5",
          "208,5",
          "209,5",
          "210,5",
          "213,5",
          "215,5",
          "220,5",
          "304,5",
          "307,5",
          "311,5",
          "313,5",
          "315,5",
          "316,5",
          "317,5",
          "322,5",
          "323,5",
          "402,5",
          "404,5",
          "408,5",
          "409,5",
          "410,5",
          "411,5",
          "412,5",
          "414,5",
          "415,5",
          "416,5",
          "420,5",
          "501,5",
          "503,5",
          "505,5",
          "506,5",
          "507,5",
          "508,5",
          "509,5",
          "511-1,5",
          "512,5",
          "513,5",
          "514,5",
          "516,5",
          "517,5",
          "519,5",
          "603,5",
          "604,5",
          "107,6",
          "B101,6",
          "B103,6",
          "704,7",
          "1003,8",
          "1006,8",
          "1009,8",
          "211,8",
          "212,8",
          "213,8",
          "315,8",
          "401,8",
          "407,8",
          "413,8",
          "414,8",
          "415,8",
          "416,8",
          "417,8",
          "507,8",
          "512,8",
          "513,8",
          "515,8",
          "601,8",
          "603,8",
          "607,8",
          "608,8",
          "704,8",
          "705,8",
          "708,8",
          "804,8",
          "810,8",
          "814,8",
          "901,8",
          "903,8",
          "905,8",
          "906,8",
          "907,8",
          "909,8",
          "910,8",
          "103,9",
          "104,9",
          "105,9",
          "106,9",
          "115,9",
          "116,9",
          "201,9",
          "202,9",
          "203,9",
          "204,9",
          "217,9",
          "220,9",
          "222,9",
          "223,9",
          "224,9",
          "225,9",
          "302,9",
          "305,9",
          "306,9",
          "308,9",
          "309,9",
          "310,9",
          "311,9",
          "315,9",
          "316,9",
          "318,9",
          "319,9",
          "322,9",
          "323,9",
          "324,9",
          "325,9",
          "326,9",
          "328,9",
          "328-1,9",
          "330,9",
          "401,9",
          "403,9",
          "405,9",
          "406,9",
          "416,9",
          "420,9",
          "421,9",
          "422,9",
          "423,9",
          "433,9",
          "434,9",
          "501,9",
          "502,9",
          "503,9",
          "504,9",
          "505,9",
          "507,9",
          "508,9",
          "513,9",
          "607,9",
          "610,9",
          "707,9",
          "712,9",
          "801,9",
          "803,9",
          "804,9",
          "805,9",
          "810,9",
          "812,9",
          "813,9",
          "B106,9"
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
      String applyYear = "2023";
      String applySemester = "2";
      List<String> files = this.findXlsFiles("src/main/resources/schedule/");
      Set<String> buildingSql = new HashSet<>();
      Set<String> roomSql = new HashSet<>();
      List<String> courseSql = new ArrayList<>();
      Set<String> boardSql = new HashSet<>();
      HashMap<String, Integer> buildingMap = new HashMap<String, Integer>();
      HashMap<String, Integer> roomMap = new HashMap<String, Integer>();
      for (int i = 0; i < buildingArray.length; i++) {
        buildingMap.put(buildingArray[i], i + 1);
      }for (int i = 0; i < roomArray.length; i++) {
        roomMap.put(roomArray[i], i + 1);
      }
      int courseId = 0;
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
            StringBuilder building_row_sql = new StringBuilder("INSERT INTO Building (universityId, buildingNumber, buildingName, createdAt, updatedAt) VALUES (1, ");
            StringBuilder room_row_sql = new StringBuilder("INSERT INTO Room (universityId, buildingId, roomNumber, createdAt, updatedAt) VALUES (1, ");
            StringBuilder course_row_sql = new StringBuilder("INSERT INTO Course (universityId, applyYear, applySemester, courseId, buildingId, roomId, courseName, courseNumber, divideNumber, department, courseDayOf, courseTime, professor, createdAt, updatedAt) VALUES (1, " + applyYear + ", " + applySemester + ", ");
            StringBuilder check_sum = new StringBuilder("INSERT INTO Board (viewCount, createdAt, updatedAt, applyYear, applySemester, boardId, title) VALUES (0, NOW(), NOW(), " + applyYear + ", " + applySemester + ", ");
            StringBuilder board_row_sql = new StringBuilder("INSERT INTO Board (viewCount, createdAt, updatedAt, applyYear, applySemester, title, roomId, boardId) VALUES (0, NOW(), NOW(), " + applyYear + ", " + applySemester + ", ");
            String buildName = "";
            String roomName = "";
            int idx = 0;
            int k = 1;
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
                  building_row_sql.append("'").append(building_number).append("', '").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                } else if (idx == 1) {
                  roomName = cell.getStringCellValue();
                  board_row_sql.append("'").append(roomName).append("', ").append(roomMap.get(roomName + "," + buildingMap.get(buildName))).append(", ");
                  room_row_sql.append(buildingMap.get(buildName)).append(", ").append("'").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                } else if (idx < 6) {
                  if (idx == 2) {
                    courseId += 1;
                    check_sum.append(courseId).append(", ").append("'").append(cell.getStringCellValue()).append(" - ");
                    course_row_sql.append(courseId).append(", ");
                    course_row_sql.append(buildingMap.get(buildName)).append(", ").append(roomMap.get(roomName + "," + buildingMap.get(buildName))).append(", ");
                  }
                  if (!(idx == 5 && k == 9)) {
                     course_row_sql.append("'").append(cell.getStringCellValue()).append("', ");
                  }
                } else if (idx == 6 && k == 10) {
                  // 간혹 셀이 비어있는 경우에 대한 처리
                  check_sum.append(cell.getStringCellValue()).append("');");
                  course_row_sql.append("'', ").append(course_day_of).append(", ").append(course_time).append(", ").append("'").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                } else if (idx == 7) {
                  check_sum.append(cell.getStringCellValue()).append("');");
                  course_row_sql.append(course_day_of).append(", ").append(course_time).append(", ").append("'").append(cell.getStringCellValue()).append("', NOW(), NOW());");
                }
                idx += 1;
              }
              k += 1;
            }
            if (idx > 2) {
              if (check_sum.toString().endsWith(";")) {
                courseSql.add(course_row_sql.toString());
              } else {
                check_sum.append("없음');");
                course_row_sql.append(course_day_of).append(", ").append(course_time).append(", ").append("'").append("', NOW(), NOW());");
                courseSql.add(course_row_sql.toString());
              }
              buildingSql.add(building_row_sql.toString());
              boardSql.add(board_row_sql.toString());
              roomSql.add(room_row_sql.toString());
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

          writer = new BufferedWriter(new FileWriter("src/main/resources/sql/board.sql"));
          int i = 1;
          for (String line : boardSql.stream().sorted().toList()) {
            line += i + ");";
            i += 1;
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
