// package twim.encryption.api.service;



// import java.util.HashMap;
// import java.util.Map;

// import org.apache.tomcat.util.codec.binary.Base64;
// import org.springframework.stereotype.Service;
// import java.security.PrivateKey;
// import java.security.PublicKey;
// import twim.encryption.util.AESEncryptionUtil;

// @Service
// public class AESService {
    	
// 	public Map<String, String> generatedKey() {
// 		Map<String, String> result = new HashMap<>();
		
// 		try {
// 			System.out.println("********************* START GENERATED KEY *********************");
// 			if (!AESEncryptionUtil.isGeneratedKey()) {
// 				AESEncryptionUtil.genarateRandomKey();
// 			}
// 			System.out.println("********************* END GENERATED KEY *********************");
			
// 			PublicKey publicKey = AESEncryptionUtil.getPublicKey(); 
// 			PrivateKey privateKey = AESEncryptionUtil.getPrivateKey();
			  
// 			result.put("publicKey", java.util.Base64.getMimeEncoder().encodeToString(publicKey.getEncoded()));
// 			result.put("privateKey", java.util.Base64.getMimeEncoder().encodeToString(privateKey.getEncoded()));	
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 		}
		
// 		return result;
// 	}

//     public String getPublicKeyString() {
// 		String result = "";
		
// 		try {
// 			PublicKey publicKey = AESEncryptionUtil.getPublicKey(); 
// 			result = java.util.Base64.getMimeEncoder().encodeToString(publicKey.getEncoded());
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 			result = e.toString();
// 		}
		
// 		return result;
// 	}

//     public String encryptByPublicKey(String plainText) {
// 		String result = "";
		
// 		try {
// 			System.out.println("********************* START ENCRYPT BY PUBLIC KEY *********************");
			
// 			PublicKey publicKey = AESEncryptionUtil.getPublicKey();
// 			final byte[] cipherText = AESEncryptionUtil.encrypt(plainText, publicKey);
			
// 			result = java.util.Base64.getMimeEncoder().encodeToString(cipherText);
					
// 			System.out.println("Original: " + plainText);
// 		    System.out.println("Encrypted: " + result);
		    
// 		    System.out.println("********************* END ENCRYPT BY PUBLIC KEY *********************");
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 			result = e.toString();
// 		}
		
// 		return result;
// 	}

//     public String decryptByPrivateKey(String encryptText) {
// 		String result = "";
		
// 		try {
// 			System.out.println("********************* START DECRYPT BY PRIVATE KEY *********************");
			
// 			PrivateKey privateKey = AESEncryptionUtil.getPrivateKey();
// 			final byte[] cipherText = Base64.decodeBase64(encryptText);
            
// 			result = AESEncryptionUtil.decrypt(cipherText, privateKey);
								
// 		    System.out.println("Encrypted: " + encryptText);
// 		    System.out.println("Decrypted: " + result);
		    
// 		    System.out.println("********************* END DECRYPT BY PRIVATE KEY *********************");
// 		} catch (Exception e) {
// 			e.printStackTrace();
// 			result = e.toString();
// 		}
		
// 		return result;
// 	}
// }
