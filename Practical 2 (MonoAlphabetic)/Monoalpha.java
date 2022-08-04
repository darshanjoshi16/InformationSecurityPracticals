import java.io.*;

public class Monoalpha{

    public static char normalChar[]
        = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static char codedChar[] = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O',
            'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K',
            'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M' };
    
    public static char decodedChar[];
    
    static char[] getDecodeCharArray(){
        char ans[] = new char[26];
        int tmp;
        for(int i=0;i<ans.length;i++){
            tmp = (int)codedChar[i]-65;
            ans[tmp] = normalChar[i];
            // System.out.println(codedChar[i]+ " : "+ ans[i]);
        }        
        return ans;
    }

    static String encryptFile(String filename) throws Exception{
        // FileReader file = new FileReader(filename);
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        // System.out.println("File Length :- "+ file.length());

        StringBuilder newStr = new StringBuilder((int)file.length());
        char ch;
        int tmp;
        String st;

        // newStr = new StringBuilder(st.length()+1);
        while ((st = br.readLine()) != null){
            st = st.toUpperCase();
            for(int i=0;i<st.length();i++){
                ch = st.charAt(i);
                if( ch <= 'Z' && ch >= 'A'){
                    tmp = (int)ch - 65;
                    newStr.append(codedChar[tmp]);
                    // System.out.println(ch + " : "+ codedChar[tmp]);
                }
                else{
                    newStr.append(ch);
                }
            }
            newStr.append('\n');
        }

        br.close();

        return newStr.toString();
    }

    //encrypt Text 
    static String encrypt(String st) throws Exception{
        StringBuilder newStr = new StringBuilder((int)st.length());
        char ch;
        int tmp;
        st = st.toUpperCase();
        for(int i=0;i<st.length();i++){
            ch = st.charAt(i);
            if( ch <= 'Z' && ch >= 'A'){
                tmp = (int)ch - 65;
                newStr.append(codedChar[tmp]);
                // System.out.println(ch + " : "+ codedChar[tmp]);
            }
            else{
                newStr.append(ch);
            }
        }
        newStr.append('\n');
        return newStr.toString();
    }

    //decrypt text from File 
    static String decryptFile(String filename) throws Exception{
		
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder newStr = new StringBuilder((int)file.length());
        char ch;
        int tmp;
        String st;
        while ((st = br.readLine()) != null){
            st = st.toUpperCase();
            for(int i=0;i<st.length();i++){
                ch = st.charAt(i);
                if( ch <= 'Z' && ch >= 'A'){
                    tmp = (int)ch - 65;
                    newStr.append(decodedChar[tmp]);
                    // System.out.println(ch + " : "+ codedChar[tmp]);
                }
                else{
                    newStr.append(ch);
                }
            }
            newStr.append('\n');
        }

        return newStr.toString();
    }


    //decrypt Text 
    static String decrypt(String st) throws Exception{
		
        StringBuilder newStr = new StringBuilder((int)st.length());
        char ch;
        int tmp;
        st = st.toUpperCase();
        for(int i=0;i<st.length();i++){
            ch = st.charAt(i);
            if( ch <= 'Z' && ch >= 'A'){
                tmp = (int)ch - 65;
                newStr.append(decodedChar[tmp]);
            }
            else{
                newStr.append(ch);
            }
        }
        newStr.append('\n');
    
        return newStr.toString();
    }

    static String readFile(String filename) {
        String st;
        StringBuilder newStr;
        BufferedReader br;
        try {
            File file = new File(filename);
            br = new BufferedReader(new FileReader(file));
            newStr = new StringBuilder((int)file.length());            
            while ((st = br.readLine()) != null){
                newStr.append(st);
                newStr.append("\n");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File Does Not Exist \n");
            return "";
        }
        return newStr.toString();
    }

    static void  putInFile(String filename, String str) throws Exception{
        File path = new File(filename);
      
        //passing file instance in filewriter
          FileWriter wr = new FileWriter(path);
           wr.write(str);
           
          //flushing the writer
          wr.flush();
           
          //closing the writer
          wr.close();

   }

    

    static String getInputChoice() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice;
        String ans = "";
        System.out.println("#####################################################");
        System.out.println("1. File Input ");
        System.out.println("2. Text Input ");
        System.out.print("Select Choice :- ");
        choice = Integer.parseInt(br.readLine());
        switch (choice) {
            case 1:
                String tmpFileName;
                System.out.println("Enter File Name :- ");
                tmpFileName = br.readLine();
                ans = readFile(tmpFileName);
                break;
            case 2:
                System.out.println("Enter input Text :- ");
                ans = br.readLine();
                break;
            default:
                break;
        }
        br.close();
        return ans;
    }

   public static void main(String[] args) throws Exception {
        decodedChar = getDecodeCharArray();

        // #################################################################
        //                      Simple Program 
        // #################################################################
        // showMapping();

        // String inputFile = readFile("text.txt");
        // String outputfile = "output.txt";
        // String str = encrypt(inputFile);
        // System.out.println("=======================================================");
        // System.out.println("Encrypted Text :- ");
        // System.out.println(str);
        // putInFile(outputfile, str);

        // System.out.println("=======================================================");
        // String str1 = decrypt(str);
        // System.out.println("Decrypted Text :- ");
        // System.out.println(str1);
        


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("================ Menu =======================");
        System.out.println("1. Encryption ");
        System.out.println("2. Decryption ");
       
        int choice = Integer.parseInt(br.readLine());
        String text;

        switch (choice) {
            case 1:
                text = getInputChoice();
                String enc = encrypt(text);
                System.out.println("Encrypted Text :- ");
                System.out.println(enc);
                break;
            case 2: 
                text = getInputChoice();
                String dec = decrypt(text);
                System.out.println("Decrypted Text :- ");
                System.out.println(dec);
                break;
            
            default:
                System.out.println("Choice Correct Option ");
                break;
        }
    }    
}