package algo;

import java.util.Scanner;

public class CaesarCipher {
	 public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	 
	 public static String encrypt(String str,int keyLength)
	 {
	     String encrypted="";
	     for(int i=0;i<str.length();i++)
	     {
	         //stores ascii value of character in the string at index 'i'
	         int c=str.charAt(i);
	         //encryption logic for uppercase letters
	         if(Character.isUpperCase(c))
	         {
	             c=c+(keyLength%26);
	             //if c value exceeds the ascii value of 'Z' reduce it by subtracting 26(no.of alphabets) to keep in boundaries of ascii values of 'A' and 'Z'
	             if(c>'Z')
	                 c=c-26;
	         }
	         //encryption logic for lowercase letters
	         else if(Character.isLowerCase(c))
	         {
	             c=c+(keyLength%26);
	             //if c value exceeds the ascii value of 'z' reduce it by subtracting 26(no.of alphabets) to keep in boundaries of ascii values of 'a' and 'z'
	             if(c>'z')
	                 c=c-26;
	         }
	         //concatinate the encrypted characters/strings
	         encrypted=encrypted+(char) c;
	     }
	     return encrypted;
	 }
	 
	 public static String decrypt(String str,int keyLength)
	 {
	     String decrypted="";
	     for(int i=0;i<str.length();i++)
	     {
	         //stores ascii value of character in the string at index 'i'
	         int c=str.charAt(i);
	         //decryption logic for uppercase letters
	         if(Character.isUpperCase(c))
	         {
	             c=c-(keyLength%26);
	             //if c value deceed the ascii value of 'A' increase it by adding 26(no.of alphabets) to keep in boundaries of ascii values of 'A' and 'Z'
	             if(c<'A')
	                 c=c+26;
	         }
	         //decryption logic for uppercase letters
	         else if(Character.isLowerCase(c))
	         {
	             c=c-(keyLength%26);
	             //if c value deceed the ascii value of 'A' increase it by adding 26(no.of alphabets) to keep in boundaries of ascii values of 'A' and 'Z'
	             if(c<'a')
	                 c=c+26;
	         }
	         //concatinate the decrypted characters/strings
	         decrypted=decrypted+(char) c;
	     }
	     return decrypted;
	 }
	 
//	    public static void main(String[] args)
//	    {
//	    	 Scanner sc=new Scanner(System.in);
//	    	    String str;
//	    	    String key;
//	    	    int keyLength;
//	    	    str="Apoorv ";
//	    	    keyLength = 3;
//	    	    System.out.println("Encrypted message..."+encrypt(str,keyLength));
//	    	    System.out.println("Decryptedmessage..."+decrypt(encrypt(str,keyLength),keyLength));
//	    }
}
