package twim.encryption.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Twimkey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String publickey;
    @Column(length = 1000)
    private String privatekey;

    public Twimkey() {

    }
}
