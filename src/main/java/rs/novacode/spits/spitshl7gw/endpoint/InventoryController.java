package rs.novacode.spits.spitshl7gw.endpoint;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rs.novacode.spits.spitshl7gw.model.Inventory;
import rs.novacode.spits.spitshl7gw.repository.InventoryRepository;

import java.util.Optional;

@RestController
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/medicine")
    public Iterable<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/medicine/{nameMedicine}")
    public Optional<Inventory> findByMedicineName(@PathVariable String nameMedicine) {
        return inventoryRepository.findByMedicineName(nameMedicine);
    }
}
