package testcode;

import utils.CryptoUtil;

public class CryptoUtilTest {

	public static void main(String[] args) {
		
		final String secretKey = "ssshhhhhhhhhhh!!!!";
	     
	    String originalString = "howtodoinjava.com";
	    
	    CryptoUtil cryptoUtil = new CryptoUtil();
	    
	    String encryptedString = cryptoUtil.encrypt(originalString, secretKey) ;
	    String decryptedString = cryptoUtil.decrypt(encryptedString, secretKey) ;
	     
	    System.out.println(originalString);
	    System.out.println(encryptedString);
	    System.out.println(decryptedString);

	}

}
