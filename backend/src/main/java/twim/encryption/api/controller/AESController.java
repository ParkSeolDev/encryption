package twim.encryption.api.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
//import twim.encryption.util.AESEncryptionUtil;
import twim.encryption.util.AES256Utils;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/aes")
public class AESController {
    private final AES256Utils aes256Utils;

    @PostMapping("/encrypt")
    public String encrypt(String key, String data) throws Exception {
        return aes256Utils.encrypt(key, data);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestParam(value = "key") String key, @RequestParam(value = "encryptedData") String encryptedData) throws Exception {
        return aes256Utils.decrypt(key, encryptedData);
    }
}
