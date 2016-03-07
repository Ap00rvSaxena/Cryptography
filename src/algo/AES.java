package algo;

import java.security.Key;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


public class AES {


    private static final String ALGO = "AES";
    private byte[] keyvalue;

    public AES(String key)
    {
    keyvalue = key.getBytes();
    }
    
    public String encrypt(String data) throws Exception
    {
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGO);
    c.init(Cipher.ENCRYPT_MODE, key);
    byte[] encVal = c.doFinal(data.getBytes());
    String encryptedValue = new Base64().encodeToString(encVal);
    return encryptedValue;
    }
    
    public String decrypt(String encdata) throws Exception
    {
    Key key = generateKey();
    Cipher c = Cipher.getInstance(ALGO);
    c.init(c.DECRYPT_MODE, key);
    byte[] decordedvalue = new Base64().decode(encdata);
    byte[] decvalue = c.doFinal(decordedvalue);
    String decryptedvalue = new String(decvalue);
    return decryptedvalue;
    }
    
    private Key generateKey() throws Exception
    {
    Key key = new SecretKeySpec(keyvalue, ALGO);
//    KeyGenerator keyGen = KeyGenerator.getInstance(ALGO);
//    keyGen.init(256); // for example
//    SecureRandom random = new SecureRandom(); // cryptograph. secure random 
//    keyGen.init(random);
//    SecretKey secretKey = keyGen.generateKey();
    return key;
    }
    
    
    
//    public static void main(String[] args) {
//    
//        try {
//            AES cry = new AES("fbdsfsadsadasdda");
//            String encryp = cry.encrypt("Apoorv Saxena");
//            System.out.println("Encrypted:-" + encryp);
//            String decryp = cry.decrypt(encryp);
//            System.out.println("Decrypted:-" + decryp);
//        } catch (Exception ex) {
//            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
//        }   
//        
//        
//    }
	
}
