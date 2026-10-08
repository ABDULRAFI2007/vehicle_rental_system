package com.example.vehiclerental.repository;

import com.example.vehiclerental.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByAvailableTrue();

    List<Vehicle> findByVehicleTypeIgnoreCase(String vehicleType);

    List<Vehicle> findByVehicleTypeIgnoreCaseAndAvailableTrue(String vehicleType);

    List<Vehicle> findByLocationContainingIgnoreCase(String location);

    List<Vehicle> findByBrandContainingIgnoreCaseOrModelContainingIgnoreCase(String brand, String model);
}
