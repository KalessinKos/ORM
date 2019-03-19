package ro.sda.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ServiciuCumparat")
public class ServiciuCumparat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column
    private Date dataAleasa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviciu_id")
    private Serviciu serviciu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataAleasa() {
        return dataAleasa;
    }

    public void setDataAleasa(Date dataAleasa) {
        this.dataAleasa = dataAleasa;
    }

    public Serviciu getServiciu() {
        return serviciu;
    }

    public void setServiciu(Serviciu serviciu) {
        this.serviciu = serviciu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiciuCumparat that = (ServiciuCumparat) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
