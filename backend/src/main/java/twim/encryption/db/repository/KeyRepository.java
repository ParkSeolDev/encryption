package twim.encryption.db.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import twim.encryption.db.entity.Twimkey;
import twim.encryption.db.mapper.TwimmapMapper;

import javax.persistence.EntityManager;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class KeyRepository {
    private final EntityManager em;
    private final TwimmapMapper twimmapMapper;

//    @Transactional
//    public Twimkey save(Map<String, String> inputKeyMap){
//        Twimkey twimKey = new Twimkey();
//        twimKey.setTwimmap(inputKeyMap);
//        em.persist(twimKey);
//        return twimKey;
//    }

    @Transactional
    public Twimkey save(Map<String, String> inputKeyMap){
        Twimkey twimKey = new Twimkey();
        twimKey = twimmapMapper.toEntity(inputKeyMap);
        em.persist(twimKey);
        return twimKey;
    }
}
