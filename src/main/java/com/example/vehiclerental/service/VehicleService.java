package com.example.vehiclerental.service;

import com.example.vehiclerental.dto.VehicleDTO;
import com.example.vehiclerental.entity.Vehicle;
import com.example.vehiclerental.exception.ResourceNotFoundException;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Get all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Get available vehicles only
    public List<Vehicle> getAvailableVehicles() {
        return vehicleRepository.findByAvailableTrue();
    }

    // Get vehicle by ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
    }

    // Get vehicles by type (Bike or Car)
    public List<Vehicle> getVehiclesByType(String type) {
        return vehicleRepository.findByVehicleTypeIgnoreCaseAndAvailableTrue(type);
    }

    // Get all vehicles by type (including unavailable - for admin)
    public List<Vehicle> getAllVehiclesByType(String type) {
        return vehicleRepository.findByVehicleTypeIgnoreCase(type);
    }

    // Search vehicles by brand or model
    public List<Vehicle> searchVehicles(String query) {
        return vehicleRepository.findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(query, query);
    }

    // Add a new vehicle
    public Vehicle addVehicle(VehicleDTO dto) {
        Vehicle vehicle = mapDtoToEntity(dto, new Vehicle());
        return vehicleRepository.save(vehicle);
    }

    // Update an existing vehicle
    public Vehicle updateVehicle(Long id, VehicleDTO dto) {
        Vehicle vehicle = getVehicleById(id);
        mapDtoToEntity(dto, vehicle);
        return vehicleRepository.save(vehicle);
    }

    // Delete a vehicle
    public void deleteVehicle(Long id) {
        Vehicle vehicle = getVehicleById(id);
        vehicleRepository.delete(vehicle);
    }

    // Toggle vehicle availability
    public Vehicle toggleAvailability(Long id, boolean available) {
        Vehicle vehicle = getVehicleById(id);
        vehicle.setAvailable(available);
        return vehicleRepository.save(vehicle);
    }

    // Helper: Map DTO to Entity
    private Vehicle mapDtoToEntity(VehicleDTO dto, Vehicle vehicle) {
        vehicle.setVehicleType(dto.getVehicleType());
        vehicle.setBrand(dto.getBrand());
        vehicle.setModel(dto.getModel());
        vehicle.setVehicleNumber(dto.getVehicleNumber());
        vehicle.setPricePerDay(dto.getPricePerDay());
        vehicle.setOwnerPhone(dto.getOwnerPhone());
        vehicle.setLocation(dto.getLocation());
        vehicle.setImageUrl(dto.getImageUrl());
        vehicle.setAvailable(dto.isAvailable());
        return vehicle;
    }
}
