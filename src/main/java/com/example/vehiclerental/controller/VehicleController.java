package com.example.vehiclerental.controller;

import com.example.vehiclerental.dto.VehicleDTO;
import com.example.vehiclerental.entity.Vehicle;
import com.example.vehiclerental.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // ============================
    //  CUSTOMER APIs (Public)
    // ============================

    /**
     * GET /api/vehicles - Get all vehicles
     */
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    /**
     * GET /api/vehicles/available - Get only available vehicles
     */
    @GetMapping("/available")
    public ResponseEntity<List<Vehicle>> getAvailableVehicles() {
        return ResponseEntity.ok(vehicleService.getAvailableVehicles());
    }

    /**
     * GET /api/vehicles/{id} - Get a single vehicle by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    /**
     * GET /api/vehicles/type/{type} - Get vehicles by type (Bike or Car)
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Vehicle>> getVehiclesByType(@PathVariable String type) {
        return ResponseEntity.ok(vehicleService.getVehiclesByType(type));
    }

    /**
     * GET /api/vehicles/search?q=query - Search vehicles by brand or model
     */
    @GetMapping("/search")
    public ResponseEntity<List<Vehicle>> searchVehicles(@RequestParam String q) {
        return ResponseEntity.ok(vehicleService.searchVehicles(q));
    }

    // ============================
    //  ADMIN APIs
    // ============================

    /**
     * POST /api/vehicles - Add a new vehicle
     */
    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@Valid @RequestBody VehicleDTO vehicleDTO) {
        Vehicle created = vehicleService.addVehicle(vehicleDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/vehicles/{id} - Update an existing vehicle
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id,
                                                  @Valid @RequestBody VehicleDTO vehicleDTO) {
        Vehicle updated = vehicleService.updateVehicle(id, vehicleDTO);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE /api/vehicles/{id} - Delete a vehicle
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok(Map.of("message", "Vehicle deleted successfully"));
    }

    /**
     * PATCH /api/vehicles/{id}/availability - Change vehicle availability
     */
    @PatchMapping("/{id}/availability")
    public ResponseEntity<Vehicle> toggleAvailability(@PathVariable Long id,
                                                       @RequestBody Map<String, Boolean> body) {
        boolean available = body.getOrDefault("available", true);
        Vehicle updated = vehicleService.toggleAvailability(id, available);
        return ResponseEntity.ok(updated);
    }
}
