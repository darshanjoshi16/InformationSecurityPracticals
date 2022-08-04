//Practical 2
//Implement the Monoalpha cipher using the file handling


//importing the library
import java.io.*;

//public method for the implementation of the monoalpha cipher
public class Monoalpha{

    //created the normal character array
    public static char normalChar[]
        = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',

            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    //created the character array for monoalpha mapping
    public static char codedChar[] = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O',
            'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K',
            'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M' };
    
    //array which will be used for storing the decoded character
    public static char decodedChar[];
    
    //method for decoding the character using method
    static char[] getDecodeCharArray()
    {
        //character array for decoding the mapping
        char ans[] = new char[26];
        int tmp;
        
        //generating the decoded mapping
        for(int i=0;i<ans.length;i++)
        {
            tmp = (int)codedChar[i]-65;
            ans[tmp] = normalChar[i];
           
        }        
        return ans;
    }

    //Created the method for encrypting the content we are getting from the input file using file handling
    static String encryptFile(String filename) throws Exception
    {
        //creating the instance of file class and bufferedreader class which will be useful for file handling
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        //string builder object instance created of length of input file
        StringBuilder newStr = new StringBuilder((int)file.length());
        
        //variable defination
        char ch;
        int tmp;
        String st;

        //it will run the loop until It founds the null character
        while ((st = br.readLine()) != null)
        {
            //converts every character into Upper case
            st = st.toUpperCase();
            for(int i=0;i<st.length();i++)
            {
                //ciphering (enrypting) logic for individual characters
                ch = st.charAt(i);
                if( ch <= 'Z' && ch >= 'A')
                {
                    tmp = (int)ch - 65;
                    newStr.append(codedChar[tmp]);
                    
                }
                else{
                    newStr.append(ch);
                }
            }
            newStr.append('\n');
        }

        //closing the bufferedreader instance
        br.close();

        //returning the encrypted string
        return newStr.toString();
    }


  //Created the method for decrypting the content we are getting from the input file using file handling
  //which is same structure as above method just logic is different due to appending the different characters in output string
    static String decryptFile(String filename) throws Exception
    {
		
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder newStr = new StringBuilder((int)file.length());
        
        char ch;
        int tmp;
        String st;
        while ((st = br.readLine()) != null)
        {
            st = st.toUpperCase();
            for(int i=0;i<st.length();i++)
            {
                ch = st.charAt(i);
                if( ch <= 'Z' && ch >= 'A')
                {
                    tmp = (int)ch - 65;
                    newStr.append(decodedChar[tmp]);
                   
                }
                else
                {
                    newStr.append(ch);
                }
            }
            newStr.append('\n');
        }
        br.close();
        return newStr.toString();
    }

    //created the method which will write the content from string into file using filewriter instance 
    static void  putInFile(String filename, String str) throws Exception
    {
        //file instance creation
        File path = new File(filename);
      
        //passing file instance in filewriter
          FileWriter wr = new FileWriter(path);
           wr.write(str);
           
          //flushing the writer
          wr.flush();
           
          //closing the writer
          wr.close();

   }
   public static void main(String[] args) throws Exception 
   {
       //initialize the decodedcharacter array which we have created the method as per above
        decodedChar = getDecodeCharArray();

       

        //initialized the bufferedreader for taking the input from the user
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("================ Menu =======================");
        System.out.println("1. Encryption ");
        System.out.println("2. Decryption ");
       
        //taking the choice from the user
        int choice = Integer.parseInt(br.readLine());
        
        //variable defining
        String text;
        String data;
        String outputfile;
        
        //defining the menu driven programming technique
        switch (choice) 
        {
            //if user chooses the encryption
            case 1:
                System.out.println("Enter File name To which you want to encrypt : ");
                text = br.readLine();
                System.out.println("Enter the file name you want to store the encrypted text: ");
                outputfile = br.readLine();
                data = encryptFile(text);        
                System.out.println("Encrypted Text :- ");
                System.out.println(data);
                putInFile(outputfile, data);
                break;

            //if user chooses the decryption 
            case 2: 
                System.out.println("Enter File name To which you want to decrypt : ");
                text = br.readLine();
                System.out.println("Enter the file name you want to store the decrypted text: ");
                outputfile = br.readLine();
                data = decryptFile(text);
                System.out.println("Decrypted Text :- ");
                System.out.println(data);
                putInFile(outputfile, data);
                break;
            
            default:
                System.out.println("Choice Correct Option ");
                break;
        }
    }    
}