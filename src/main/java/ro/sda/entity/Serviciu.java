package ro.sda.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Serviciu")
public class Serviciu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int id;

    @Column
    private String denumire;

    @ManyToMany(mappedBy = "servicii")
    private List<Medic> medici = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "servicii_produse",
            joinColumns = @JoinColumn(name = "serviciu_id"),
            inverseJoinColumns = @JoinColumn(name = "produs_id")
    )
    private List<Produs> produse = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public List<Medic> getMedici() {
        return medici;
    }

    public void setMedici(List<Medic> medici) {
        this.medici = medici;
    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void setProduse(List<Produs> produse) {
        this.produse = produse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serviciu serviciu = (Serviciu) o;
        return id == serviciu.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
