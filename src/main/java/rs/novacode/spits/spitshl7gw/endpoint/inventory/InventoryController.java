package rs.novacode.spits.spitshl7gw.endpoint.inventory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import rs.novacode.spits.spitshl7gw.model.Inventory;
import rs.novacode.spits.spitshl7gw.repository.inventory.InventoryRepository;

import java.util.Optional;

@RestController
public class InventoryController {
    private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);

    private static final String MEDICINES_PATH = "/medicine";

    @Autowired
    private InventoryRepository inventoryRepository;


//    @GetMapping(path = MEDICINES_PATH)
//    public ResponseEntity<List<Inventory>> getMedicines() {
//        List<Inventory> medicines = inventoryRepository.findAll();
//
//        return ResponseEntity.ok().body(medicines);
//
//    }

    @GetMapping("/medicine/{name}")
    public ResponseEntity<Optional<Inventory>> getMedicineByName(@PathVariable(value = "name") String nameMedicine) {
        Optional<Inventory> inventory = inventoryRepository.findByMedicineName(nameMedicine);
        return ResponseEntity.ok().body(inventory);
    }

}
