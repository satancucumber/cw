package LETI.cw.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "shop", schema = "shopadmin")
//@ToString(of = {"id", "address", "info"})
//@EqualsAndHashCode(of = {"id"})
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;
    @Column(name = "info")
    private String info;
    @OneToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn (name="director_id")
    private Seller director;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Seller getDirector() {
        return director;
    }

    public void setDirector(Seller director) {
        this.director = director;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
