package rs.novacode.spits.spitshl7gw.model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "tbl_inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id")
    private String idMedicine;

    @Column(name = "name")
    private String nameMedicine;

    @Column(name = "quantity")
    private int quantityMedicine;

    public Inventory(String idMedicine, String nameMedicine, int quantityMedicine) {
        this.idMedicine = idMedicine;
        this.nameMedicine = nameMedicine;
        this.quantityMedicine = quantityMedicine;
    }
}
