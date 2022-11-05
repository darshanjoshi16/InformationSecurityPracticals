import java.util.*;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets;  
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec; 
  

public class menu
{
    public static void main(String[] args)
    {
        System.out.println("===================Hashing Using Libraries=====================");
        System.out.println("\n Select the option among these algorithm for cryptographic hash function:");
        System.out.println("1: SHA-1");
        System.out.println("2: HMAC");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the hashing technique you want to apply: ");
        int choice = sc.nextInt();
        
        //case for the SHA-1 Algorithm
        if(choice == 1)
        {
            try 
            {  
                System.out.println("=====================SHA-1==================");
                System.out.println("Please enter the message you want to apply SHA-1 hashing:");
                String input = sc.next();
                System.out.println("\n Hashed Value for given message : " + toHexString(getSHA(input))); 
            }  
            catch (NoSuchAlgorithmException e) 
            {  
                System.out.println("The Exception for wrong hashing algorithm: " + e);  
            }  
        }

        else if(choice == 2)
        {
           System.out.println("==================HMAC========================");
           System.out.println("Enter the  messages you want to apply HMAC technique by giving space between them: ");
           String input1 = sc.next();
           System.out.println("\n First Input: "+input1);
           String input2 = sc.next();
           System.out.println("\n Second Input: "+input2);
           try {
            byte[] hmacSha256 = menu.calcHmacSha256(input1.getBytes("UTF-8"),input2.getBytes("UTF-8"));
            System.out.println(String.format("\n Hex formatted Hashed Value: %064x", new BigInteger(1, hmacSha256))); 
        } 
        
        catch (UnsupportedEncodingException e) 
        {
            e.printStackTrace();
        }

        }
        else
        {
            System.out.println("Wrong choice! Please enter the right choice");
        }
        
        sc.close();
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // The Static method " getInstance() " is called to initiate hashing with SHA  
               MessageDigest md = MessageDigest.getInstance("SHA-1");  
         
               // The static method called in the JAVA program for calculating the message digest of a given input and results in an array of byte  
               return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    }  
         
    public static String toHexString(byte[] hash) 
    {  
               // calling the " BigInteger " function in JAVA programming language.  
               BigInteger number = new BigInteger(1, hash);  
         
               // Converting the message digest into a Hexa decimal value.  
               StringBuilder hexString = new StringBuilder(number.toString(16));  
         
               while (hexString.length() < 64) 
               {  
                   hexString.insert(0, '0');  
               }  
         
               return hexString.toString();  
    }

    static public byte[] calcHmacSha256(byte[] secretKey, byte[] message) 
    {
        byte[] hmacSha256 = null;
        try {
                Mac mac = Mac.getInstance("HmacSHA256");
                SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");
                mac.init(secretKeySpec);
                hmacSha256 = mac.doFinal(message);
        } 
        catch (Exception e) 
        {
          throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return hmacSha256;
    }
    
    
}