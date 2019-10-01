package rs.novacode.spits.spitshl7gw.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Data
@Entity
@Table(name = "tbl_inventory_update")
public class InventoryUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_medicine")
    private int idMedicine;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public InventoryUpdate(int idMedicine, LocalDateTime updateTime) {
        this.idMedicine = idMedicine;
        this.updateTime = updateTime;
    }
}
