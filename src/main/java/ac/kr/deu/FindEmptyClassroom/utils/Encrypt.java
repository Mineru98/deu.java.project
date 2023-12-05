package ac.kr.deu.FindEmptyClassroom.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

  public static String encode(String val) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      byte[] hashedPassword = md.digest(val.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : hashedPassword) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }
}
