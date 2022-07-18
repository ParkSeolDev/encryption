package twim.encryption.api.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import twim.encryption.api.service.RSAService;
import twim.encryption.util.RSAEncryptionUtil;

import java.io.IOException;
import java.security.*;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rsa")
public class RSAController {
	
	private final RSAService rsaService;
	private final RSAEncryptionUtil rsaEncryptionUtil;

	@GetMapping("/generateKey")
	public String generateKey() throws NoSuchAlgorithmException, IOException {
		return rsaService.generateKey();
	}
	
//	@GetMapping("/getPublicKey")
//	public PublicKey getPublicKey(String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ClassNotFoundException {
//		return rsaEncryptionUtil.getPublicKey(base64PublicKey);
//	}
//
//	@GetMapping("/getPrivateKey")
//	public PrivateKey getPrivateKey(String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ClassNotFoundException {
//		return rsaEncryptionUtil.getPrivateKey(base64PrivateKey);
//	}

	@PostMapping("/encrypt")
	public String encrypt(@RequestParam(value = "plainText") String encryptText, String publicKey) throws Exception {
		return rsaEncryptionUtil.encode(encryptText, publicKey);
	}

	@PostMapping("/decrypt")
	public String decrypt(@RequestParam(value = "encryptText") String encryptText, @RequestParam(value = "publicKey") String publicKey) throws Exception {
		return rsaService.decode(encryptText, publicKey);
	}

}
