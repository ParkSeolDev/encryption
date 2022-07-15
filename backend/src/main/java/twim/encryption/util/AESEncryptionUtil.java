//package twim.encryption.util;
//
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.security.GeneralSecurityException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import javax.crypto.Cipher;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//import com.sun.istack.NotNull;
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.stereotype.Component;
//
////양방향 암호화 알고리즘인 AES256 암호화를 지원하는 클래스
//@Component
//public class AESEncryptionUtil {
//
//
//	/**
//	 * 16자리의 키값을 입력하여 객체를 생성
//	 *
//	 * @param key 암/복호화를 위한 키값
//	 * @throws UnsupportedEncodingException 키값의 길이가 16이하일 경우 발생
//	 */
////	final static String key = "UnsupportedEncodingException";
//
////	public AESEncryptionUtil(String key) throws UnsupportedEncodingException {
////		this.iv = key.substring(0, 16);
////		byte[] keyBytes = new byte[16];
////		byte[] b = key.getBytes(StandardCharsets.UTF_8);
////		int len = b.length;
////		if (len > keyBytes.length) {
////			len = keyBytes.length;
////		}
////		System.arraycopy(b, 0, keyBytes, 0, len);
////		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
////
////		this.keySpec = keySpec;
////	}
//
//	/**
//	 * AES256 으로 암호화
//	 *
//	 * @param str 암호화할 문자열
//	 * @return
//	 * @throws NoSuchAlgorithmException
//	 * @throws GeneralSecurityException
//	 * @throws UnsupportedEncodingException
//	 */
//	public String encrypt(String str, String key) throws NoSuchAlgorithmException,
//			GeneralSecurityException, UnsupportedEncodingException {
//		String iv;
//		Key keySpec;
//		iv = key.substring(0, 16);
//		byte[] keyBytes = new byte[16];
//		byte[] b = key.getBytes(StandardCharsets.UTF_8);
//		int len = b.length;
//		if (len > keyBytes.length) {
//			len = keyBytes.length;
//		}
//		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
//		byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
//		String enStr = new String(Base64.encodeBase64(encrypted));
//		return enStr;
//	}
//
//	/**
//	 * AES256으로 암호화된 txt를 복호화
//	 *
//	 * @param str 복호화할 문자열
//	 * @return
//	 * @throws NoSuchAlgorithmException
//	 * @throws GeneralSecurityException
//	 * @throws UnsupportedEncodingException
//	 */
//	public String decrypt(String str, String key) throws NoSuchAlgorithmException,
//			GeneralSecurityException, UnsupportedEncodingException {
//		this.iv = key.substring(0, 16);
//		byte[] keyBytes = new byte[16];
//		byte[] b = key.getBytes(StandardCharsets.UTF_8);
//		int len = b.length;
//		if (len > keyBytes.length) {
//			len = keyBytes.length;
//		}
//		System.arraycopy(b, 0, keyBytes, 0, len);
//		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
//
//		this.keySpec = keySpec;
//		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
//		c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
//		byte[] byteStr = Base64.decodeBase64(str.getBytes());
//		return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
//	}
//}