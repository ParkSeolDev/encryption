package twim.encryption.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

@Component
public class AESEncryptionUtil {

    private static final String KEY_ALGORITHM = "AES";


    private static final String ALGORITHMS = "AES/CBC/PKCS5Padding";


    public static final String PRIVATE_KEY_FILE = "src/keys/private.pem";
    public static final String PUBLIC_KEY_FILE = "src/keys/public.pem";
  

    public static String genarateRandomKey() {
        KeyGenerator keygen = null;
        try {
            keygen = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(" genarateRandomKey fail!", e);
        }
        SecureRandom random = new SecureRandom();
        keygen.init(random);
        Key key = keygen.generateKey();
        return Base64.encode(key.getEncoded());
    }

    public static boolean isGeneratedKey() {
        File privateKey = new File(PRIVATE_KEY_FILE);
        File publicKey = new File(PUBLIC_KEY_FILE);
    
        if (privateKey.exists() && publicKey.exists()) {
                return true;
        } else {
                return false;
        }
      }

    public String decrypt(String encryptStr, String key) {
        String result = StringUtils.EMPTY;
        try {
            byte[] keyBytes = Base64.decode(key);
            byte[] dataBytes = HexUtil.decodeHex((encryptStr.toCharArray()));
            byte[] resultBytes = this.AES_CBC_Decrypt(dataBytes, keyBytes, keyBytes);
            result = new String(resultBytes);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public String encrypt(String content, String key) {
        String enc = StringUtils.EMPTY;
        try {
            byte[] keyBytes = Base64.decode(key);
            byte[] dataBytes = StrUtil.bytes(content, StandardCharsets.UTF_8);
            byte[] resultBytes = this.AES_CBC_Encrypt(dataBytes, keyBytes, keyBytes);
            // enc = new String(resultBytes);
            enc = HexUtil.encodeHexStr(resultBytes);
            return enc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return enc;
    }


    private byte[] AES_CBC_Decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, key, iv);
        return cipher.doFinal(data);
    }

    private byte[] AES_CBC_Encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(data);
    }


    private Cipher getCipher(int mode, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHMS);
        //因为AES的加密块大小是128bit(16byte), 所以key是128、192、256bit无关
        //System.out.println("cipher.getBlockSize()： " + cipher.getBlockSize());
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        cipher.init(mode, secretKeySpec, new IvParameterSpec(iv));

        return cipher;
    }


    @SuppressWarnings("resource")
    public static PublicKey getPublicKey() throws FileNotFoundException, IOException, ClassNotFoundException {
	  ObjectInputStream inputStream = null;
	  inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
	  final PublicKey publicKey = (PublicKey) inputStream.readObject();
	  System.out.println ("-----BEGIN PUBLIC KEY-----");
	  System.out.println (java.util.Base64.getMimeEncoder().encodeToString(publicKey.getEncoded()));
	  System.out.println ("-----END PUBLIC KEY-----");
	  
	  return publicKey;
  }
  
  @SuppressWarnings("resource")
  public static PrivateKey getPrivateKey() throws FileNotFoundException, IOException, ClassNotFoundException {
	  ObjectInputStream inputStream = null;
	  inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
	  final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
	  System.out.println ("-----BEGIN PRIVATE KEY-----");
	  System.out.println (java.util.Base64.getMimeEncoder().encodeToString(privateKey.getEncoded()));
	  System.out.println ("-----END PRIVATE KEY-----");
	  
	  return privateKey;
  }
}
