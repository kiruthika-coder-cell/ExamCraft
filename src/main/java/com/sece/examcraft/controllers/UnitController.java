package com.sece.examcraft.controllers;

import com.sece.examcraft.entities.Unit;
import com.sece.examcraft.services.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    public Unit addUnit(@RequestBody Unit unit) {
        return unitService.addUnit(unit);
    }

    @GetMapping
    public List<Unit> getAllUnits() {
        return unitService.getAllUnits();
    }

    @GetMapping("/{id}")
    public Unit getUnitById(@PathVariable Long id) {
        return unitService.getUnitById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUnit(@PathVariable Long id) {
        unitService.deleteUnit(id);
        return "Unit deleted successfully";
    }
}
