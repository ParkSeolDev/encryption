package twim.encryption.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
public class Twimkey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "publicKey")
    @Column(name = "privateKey")
    private Map<String, String> twimmap = new HashMap<String, String>();

    public Twimkey() {

    }
}
