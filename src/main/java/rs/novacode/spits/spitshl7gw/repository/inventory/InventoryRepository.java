package rs.novacode.spits.spitshl7gw.repository.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.novacode.spits.spitshl7gw.model.Inventory;


import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

   List<Inventory> findAll();
   Optional<Inventory> findByMedicineName(String nameMedicine);
}
