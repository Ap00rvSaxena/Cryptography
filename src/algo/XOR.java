package algo;

public class XOR {
	
	public static String EncryptDecrypt(String input,char[] key) {
		//char[] key = {'K', 'C', 'Q'}; //Can be any chars, and any length array
		StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < input.length(); i++) {
			output.append((char) (input.charAt(i) ^ key[i % key.length]));
		}
 		
		return output.toString();
	}
	
//	public static void main(String[] args) {
//		String encrypted = XOR.encryptDecrypt("kylewbanks.com");
//		System.out.println("Encrypted:" + encrypted);
//		
//		String decrypted = XOR.encryptDecrypt(encrypted);
//		System.out.println("Decrypted:" + decrypted);
//	}

}
