package algo;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Base64InputStream;

public class DES {
	  Cipher ecipher;

	  Cipher dcipher;

	  public DES(String key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
	    ecipher = Cipher.getInstance("DES");
	    dcipher = Cipher.getInstance("DES");
	    ecipher.init(Cipher.ENCRYPT_MODE, desKey);
	    dcipher.init(Cipher.DECRYPT_MODE, desKey);
	  }

	  public String encrypt(String str) throws Exception {
	    // Encode the string into bytes using utf-8
	    byte[] utf8 = str.getBytes("UTF8");

	    // Encrypt
	    byte[] enc = ecipher.doFinal(utf8);

	    // Encode bytes to base64 to get a string
//	    return new sun.misc.BASE64Encoder().encode(enc);
	    return new Base64().encodeToString(enc);
	  }

	  public String decrypt(String str) throws Exception {
	    // Decode base64 to get bytes
	    //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
	    byte[] dec = new Base64().decode(str);
	    
	    byte[] utf8 = dcipher.doFinal(dec);

	    // Decode using utf-8
	    return new String(utf8, "UTF8");
	  }

//	  public static void main(String[] argv) throws Exception {
//		
//		  
//		  
//	    //SecretKey key = KeyGenerator.getInstance("DES").generateKey();
//	    DES encrypter = new DES("abcd1234");
//	    String encrypted = encrypter.encrypt("Some Text \nApoorv Saxena");
//	    System.out.println(encrypted);
//	    String decrypted = encrypter.decrypt(encrypted);
//	    System.out.println(decrypted);
//	  }
}