package rs.novacode.spits.spitshl7gw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.novacode.spits.spitshl7gw.model.Inventory;


import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

   List<Inventory> findAllBy();
   Optional<Inventory> findByMedicineName(String nameMedicine);
}
