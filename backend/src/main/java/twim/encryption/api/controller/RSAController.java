package twim.encryption.api.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import twim.encryption.util.RSAEncryptionUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rsa")
public class RSAController {
	
	private final RSAEncryptionUtil rsaEncryptionUtil;

	@GetMapping("/generateKey")
	public KeyPair generateKey() throws NoSuchAlgorithmException {
		return rsaEncryptionUtil.genRSAKeyPair();
	}
	
	@GetMapping("/getPublicKey")
	public PublicKey getPublicKey(String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return rsaEncryptionUtil.getPublicKeyFromBase64Encrypted(base64PublicKey);
	}

	@GetMapping("/getPrivateKey")
	public PrivateKey getPrivateKey(String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		return rsaEncryptionUtil.getPrivateKeyFromBase64Encrypted(base64PrivateKey);
	}

	@PostMapping("/encrypt")
	public String encrypt(@RequestParam(value = "plainText") String plainText, PublicKey publicKey) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
		return rsaEncryptionUtil.encryptRSA(plainText, publicKey);
	}
	
	@PostMapping("/decrypt")
	public String decrypt(@RequestParam(value = "encryptText") String encryptText, PrivateKey privateKey) throws NoSuchPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
		return rsaEncryptionUtil.decryptRSA(encryptText, privateKey);
	}

}
