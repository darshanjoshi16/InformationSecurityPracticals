// Java Program to illustrate Reading from FileReader
// using BufferedReader Class

// Importing input output classes
import java.io.*;
import java.util.*;

// Main class
public class ceasercipher
{

    //creating the encrypting method which will take filename and key as the input parameter
    static String encrypt(String filename, int key) throws Exception
    {
		//initializing the file object using the file class
        File file = new File(filename);

       //initializing the bufferedreader object with new instance of filereader class which will take the file object as the input parameter
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        //initializing the stringbuilder object which will take length of file as the parameter.
        StringBuilder newStr = new StringBuilder((int)file.length());
        
        //initializing the variables we are going to use while execution
        char ch;
        int tmp;
        String st;

        //the loop will  execute untill it encounters the null in the bufferreader object
        while ((st = br.readLine()) != null)
        {
            //for an individual string or set of character, it will iterate all over the string, character by character
            for(int i=0;i<st.length();i++)
            {
                //determining the character at i index using charAt
                ch = st.charAt(i);

                //comparing the character with its ascii value
                if( (ch <= 'z' && ch >= 'a') || ( ch <= 'Z' && ch >= 'A') )
                {
                    //creating temporary variable which will sum up the key and ascii value of character at i index
                    tmp = (int)ch + key;
                    
                    //it will adjust the value in the range from 97 to 122
                    if(tmp > 122)
                    {
                        tmp = 96 + ( tmp - 122);
                    }    
                    
                    //it will adjust the value in the range from 65 to 91
                    if ( tmp > 90 && tmp < 97 ){
                        tmp = 64 + ( tmp - 90);
                    }

                    //typecasting the value from int to charactee
                    ch = (char)tmp;
                }

                //appending the converted character into the stream
                newStr.append(ch);
              
                
            }
            //adding the new line
            newStr.append('\n');
            System.out.println();
        }
        //closing the buffer reader
        br.close();

        //returning the new string
        return newStr.toString();
    }



    //creating the decrypting method which will take the file name and key as the input parameters
    //it is same as the encrypt method but the changes will be occuring in the tmp variable because here we need to minus the value to regain original message 
    static String decrypt(String filename, int key) throws Exception
    {
		
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder newStr = new StringBuilder((int)file.length());
        char ch;
        int tmp;
        String st;

       
        while ((st = br.readLine()) != null)
        {
            for(int i=0;i<st.length();i++)
            {
                ch = st.charAt(i);
                if( (ch <= 'z' && ch >= 'a') || ( ch <= 'Z' && ch >= 'A') )
                {
                    tmp = (int)ch - key;
                    if(tmp < 97 && tmp >= 90)
                    {
                        tmp = 122 - ( 96 - tmp);
                    }    
                    if ( tmp < 65 )
                    {
                        tmp = 90 - ( 64 - tmp);
                    }
                    ch = (char)tmp;
                }
                newStr.append(ch);
               
            }
            newStr.append('\n');
            System.out.println();
        }

        br.close();

        return newStr.toString();
    }

    

    //creating the method which will write the string in the file using the filewriter class instance
    static void  putInFile(String filename, String str) throws Exception
    {
         File path = new File(filename);
       
         //passing file instance in filewriter
           FileWriter wr = new FileWriter(path);
            wr.write(str);
            
           //flushing the writer
           wr.flush();
            
           //closing the writer
           wr.close();

    }

	// main driver method
	public static void main(String[] args) throws Exception
	{
        //taking the key value from the user
        System.out.println("Please Enter the key value for substitution: ");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        sc.close();
         
        //calling the encryption method
        String str = encrypt("plaintext.txt",key);
        System.out.println("The Data Found in the input file after the encryption:- "+ str);
        putInFile("output.txt",str);
        
        //calling the decrypting method (It doesnt need to be put in any file due to it is available in input file)
        String str1 = decrypt("output.txt", 7);
       System.out.println("The Data after implementing the decryption  :- "+ str1);
    }
}
