package twim.encryption.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import twim.encryption.api.service.RSAService;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rsa")
public class RSAController {
	
	private final RSAService rsaService;

	@GetMapping("/generateKey")
	public String generateKey() {
		return rsaService.generatedKey().toString();
	}
	
	@GetMapping("/getPublicKey")
	public String getPublicKey() {
		return rsaService.getPublicKeyString();
	}
	
	@PostMapping("/encryptByPublicKey")
	public String encryptByPublicKey(@RequestParam(value = "plainText") String plainText) {
		return rsaService.encryptByPublicKey(plainText);
	}
	
	@PostMapping("/decryptByPrivateKey")
	public String decryptByPrivateKey(@RequestParam(value = "encryptText") String encryptText) {
		return rsaService.decryptByPrivateKey(encryptText);
	}
	
	@PostMapping("/encryptWithPublicKeyParam")
	public String encryptWithPublicKeyParam(@RequestParam(value = "plainText") String plainText, @RequestParam(value = "publicKey") String publicKey) {
		return rsaService.encryptWithPublicKeyParam(plainText, publicKey);
	}
}
