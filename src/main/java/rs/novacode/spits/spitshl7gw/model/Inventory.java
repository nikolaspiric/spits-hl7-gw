package rs.novacode.spits.spitshl7gw.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_medicine")
    private int idMedicine;

    @Column(name = "name_medicine")
    private String nameMedicine;

    @Column(name = "quantity_medicine")
    private int quantityMedicine;

    Inventory(int idMedicine, String nameMedicine, int quantityMedicine) {
        this.idMedicine = idMedicine;
        this.nameMedicine = nameMedicine;
        this.quantityMedicine = quantityMedicine;
    }
}
