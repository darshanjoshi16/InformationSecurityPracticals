//Practical 1
//Implement the Ceaser Cipher Encryption Decryption Technique (It works on ASCII Values)

//importing the libraries header files 
#include<iostream>
#include<string.h>

using namespace std;

int main() 
{
   //taking the input from the user and storing into the character array declared here
   cout<<"Enter the message:\n";
   char msg[100];
   cin.getline(msg,100); 
   
   //defining the variables which is needed for the execution
   int i, j, length,choice,key;
   
   //taking the key from the user which is usefull for encrypt or decrypt the message
   cout << "Enter key: ";
   cin >> key; //take the key as input
  
   length = strlen(msg);
   
   //defining the choice whether user need to encrypt or decrypt the input
   cout<<"Enter your choice \n1. Encryption \n2. Decryption \n";
   cin>>choice;
   
   //execution logic for encryption the message given in input using the key given by the user
   if (choice==1) 
   {
      char ch;

      //it will iterate the character array untill it find the null character
      for(int i = 0; msg[i] != '\0'; ++i) 
       {
            //encrypting for the individual characters
            ch = msg[i];
         
            //encryption using key for the lower case characters
            if (ch >= 'a' && ch <= 'z')
            {
                ch = ch + key;
                
                if (ch > 'z') 
                {
                    ch = ch - 'z' + 'a' - 1;
                }  
                msg[i] = ch;
            }
         
            //encryption using the key for the upper case characters
            else if (ch >= 'A' && ch <= 'Z')
            {
                ch = ch + key;
                
                if (ch > 'Z')
                {
                    ch = ch - 'Z' + 'A' - 1;
                }
                msg[i] = ch;
            }
        }
      printf("Encrypted message: %s", msg);
   }

   //execution logic for decryption the message given in input using the key given by the user
   else if (choice == 2) 
   { 
      char ch;
       
      //it will iterate the character array untill it find the null character
      for(int i = 0; msg[i] != '\0'; ++i) 
       { 
            ch = msg[i];
         
            //decryption using key for the lower case characters
            if(ch >= 'a' && ch <= 'z') 
            {
                ch = ch - key;
               
                if(ch < 'a')
                {
                    ch = ch + 'z' - 'a' + 1;
                }
                msg[i] = ch;
            }
         
            //decryption using key for the upper case characters
            else if(ch >= 'A' && ch <= 'Z') 
            {
                ch = ch - key;
               
                if(ch < 'A') 
                {
                    ch = ch + 'Z' - 'A' + 1;
                }
                msg[i] = ch;
            }
        }
      cout << "Decrypted message: " << msg;
   }
}
