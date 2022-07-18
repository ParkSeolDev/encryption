package twim.encryption.db.mapper;

import org.springframework.stereotype.Component;
import twim.encryption.db.entity.Twimkey;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class TwimmapMapper {
    public Twimkey toEntity(Map<String, String> inputKeyMap) {
        Iterator<Map.Entry<String, String>> keys = inputKeyMap.entrySet().iterator();
        while (keys.hasNext()) {
            Map.Entry entry = (Map.Entry) keys.next();
            return Twimkey.builder()
                    .publickey((String) entry.getKey())
                    .privatekey((String) entry.getValue())
                    .build();

        }
        return null;
    }
}
