package twim.encryption.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import twim.encryption.db.entity.Twimkey;
import twim.encryption.db.repository.KeyRepository;
import twim.encryption.util.RSAEncryptionUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RSAService {
    private final RSAEncryptionUtil rsaEncryptionUtil;
    private final KeyRepository keyRepository;

    @Transactional
    public String generateKey() throws NoSuchAlgorithmException, IOException {
        HashMap<String, String> stringKeypair = rsaEncryptionUtil.createKeypairAsString();
        Twimkey key = keyRepository.save(stringKeypair);
        return key.getPublickey();
    }
}
