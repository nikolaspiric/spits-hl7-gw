package rs.novacode.spits.spitshl7gw.service;

import rs.novacode.spits.spitshl7gw.model.Inventory;

import java.util.List;
import java.util.Optional;


public interface InventoryService {

    Optional<Inventory> findByMedicineName(String nameMedicine);
    List<Inventory> findAll();
}
