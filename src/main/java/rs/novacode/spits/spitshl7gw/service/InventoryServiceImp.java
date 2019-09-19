package rs.novacode.spits.spitshl7gw.service;

import rs.novacode.spits.spitshl7gw.model.Inventory;
import rs.novacode.spits.spitshl7gw.repository.InventoryRepository;

import java.util.List;
import java.util.Optional;

public class InventoryServiceImp implements InventoryService{

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImp(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Optional<Inventory> findByMedicineName(String nameMedicine) {
        return inventoryRepository.findByMedicineName(nameMedicine);
    }

    @Override
    public List<Inventory> findAll() {

        List<Inventory> inventorys = (List<Inventory>) inventoryRepository.findAll();
        return inventorys;
    }
}

