package Seperate;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class hmac {
  
    static public byte[] calcHmacSha256(byte[] secretKey, byte[] message) {
    byte[] hmacSha256 = null;
    try {
      Mac mac = Mac.getInstance("HmacSHA256");
      SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
      mac.init(secretKeySpec);
      hmacSha256 = mac.doFinal(message);
    } catch (Exception e) {
      throw new RuntimeException("Failed to calculate hmac-sha256", e);
    }
    return hmacSha256;
  }

  public static void main(String[] args) {
    try {
      byte[] hmacSha256 = hmac.calcHmacSha256("secret123".getBytes("UTF-8"), "I love you".getBytes("UTF-8"));
      System.out.println(String.format("Hex: %064x", new BigInteger(1, hmacSha256))); 
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }
}
