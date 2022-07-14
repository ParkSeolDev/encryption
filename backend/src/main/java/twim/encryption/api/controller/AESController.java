package twim.encryption.api.controller;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import twim.encryption.util.AESEncryptionUtil;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/aes")
public class AESController {
    private final AESEncryptionUtil aesEncryptionUtil;


    @PostMapping("/encrypt")
    public String encrypt(String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        return aesEncryptionUtil.encrypt(str);
    }

    @PostMapping("/decrypt")
    public String decrypt(String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        return aesEncryptionUtil.decrypt(str);
    }
}
