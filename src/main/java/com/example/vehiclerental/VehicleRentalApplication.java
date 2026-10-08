package com.example.vehiclerental;

import com.example.vehiclerental.entity.Admin;
import com.example.vehiclerental.entity.Vehicle;
import com.example.vehiclerental.repository.AdminRepository;
import com.example.vehiclerental.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class VehicleRentalApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehicleRentalApplication.class, args);
    }

    /**
     * Seeds the database with sample data on first run.
     */
    @Bean
    CommandLineRunner initData(AdminRepository adminRepository, VehicleRepository vehicleRepository) {
        return args -> {
            // Create default admin if none exists
            if (adminRepository.count() == 0) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                adminRepository.save(admin);
                System.out.println("✅ Default admin created: username=admin, password=admin123");
            }

            // Create sample vehicles if none exist
            if (vehicleRepository.count() == 0) {
                Vehicle[] sampleVehicles = {
                    createVehicle("Bike", "Honda", "Activa 6G", "TN-45-AB-1234", new BigDecimal("400"),
                            "9876543210", "Kattur, Trichy", "https://images.unsplash.com/photo-1558618666-fcd25c85f82e?w=600", true),
                    createVehicle("Bike", "Royal Enfield", "Classic 350", "TN-45-CD-5678", new BigDecimal("800"),
                            "9876543211", "Srirangam, Trichy", "https://images.unsplash.com/photo-1558981806-ec527fa84c39?w=600", true),
                    createVehicle("Bike", "TVS", "Apache RTR 200", "TN-45-EF-9012", new BigDecimal("600"),
                            "9876543212", "Thillai Nagar, Trichy", "https://images.unsplash.com/photo-1609630875171-b1321377ee65?w=600", true),
                    createVehicle("Car", "Maruti Suzuki", "Swift Dzire", "TN-45-GH-3456", new BigDecimal("1500"),
                            "9876543213", "Cantonment, Trichy", "https://images.unsplash.com/photo-1549317661-bd32c8ce0afa?w=600", true),
                    createVehicle("Car", "Hyundai", "i20", "TN-45-IJ-7890", new BigDecimal("1800"),
                            "9876543214", "KK Nagar, Trichy", "https://images.unsplash.com/photo-1619767886558-efdc259cde1a?w=600", true),
                    createVehicle("Car", "Toyota", "Innova Crysta", "TN-45-KL-2345", new BigDecimal("3000"),
                            "9876543215", "Woraiyur, Trichy", "https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=600", true),
                    createVehicle("Bike", "Yamaha", "FZ-S V3", "TN-45-MN-6789", new BigDecimal("500"),
                            "9876543216", "Anna Nagar, Trichy", "https://images.unsplash.com/photo-1568772585407-9361f9bf3a87?w=600", false),
                    createVehicle("Car", "Honda", "City", "TN-45-OP-0123", new BigDecimal("2000"),
                            "9876543217", "Puthur, Trichy", "https://images.unsplash.com/photo-1590362891991-f776e747a588?w=600", true),
                };

                for (Vehicle v : sampleVehicles) {
                    vehicleRepository.save(v);
                }
                System.out.println("✅ Sample vehicles inserted: " + sampleVehicles.length + " vehicles");
            }
        };
    }

    private Vehicle createVehicle(String type, String brand, String model, String vehicleNumber,
                                  BigDecimal price, String phone, String location, String imageUrl, boolean available) {
        Vehicle v = new Vehicle();
        v.setVehicleType(type);
        v.setBrand(brand);
        v.setModel(model);
        v.setVehicleNumber(vehicleNumber);
        v.setPricePerDay(price);
        v.setOwnerPhone(phone);
        v.setLocation(location);
        v.setImageUrl(imageUrl);
        v.setAvailable(available);
        v.setCreatedAt(LocalDateTime.now());
        return v;
    }
}
