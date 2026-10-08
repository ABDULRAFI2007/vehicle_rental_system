-- ==========================================
-- Vehicle Rental System — Database Setup
-- ==========================================

-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS vehicle_rental;
USE vehicle_rental;

-- Step 2: Create admins table
CREATE TABLE IF NOT EXISTS admins (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Step 3: Create vehicles table
CREATE TABLE IF NOT EXISTS vehicles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    vehicle_type VARCHAR(10) NOT NULL,
    brand VARCHAR(100) NOT NULL,
    model VARCHAR(100) NOT NULL,
    vehicle_number VARCHAR(20) NOT NULL UNIQUE,
    price_per_day DECIMAL(10, 2) NOT NULL,
    owner_phone VARCHAR(15) NOT NULL,
    location VARCHAR(255) NOT NULL,
    image_url VARCHAR(500),
    available BOOLEAN DEFAULT TRUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Step 4: Insert default admin
INSERT INTO admins (username, password) VALUES ('admin', 'admin123')
ON DUPLICATE KEY UPDATE username = username;

-- Step 5: Insert sample vehicles
INSERT INTO vehicles (vehicle_type, brand, model, vehicle_number, price_per_day, owner_phone, location, image_url, available) VALUES
('Bike', 'Honda', 'Activa 6G', 'TN-45-AB-1234', 400.00, '9876543210', 'Kattur, Trichy', 'https://images.unsplash.com/photo-1558618666-fcd25c85f82e?w=600', TRUE),
('Bike', 'Royal Enfield', 'Classic 350', 'TN-45-CD-5678', 800.00, '9876543211', 'Srirangam, Trichy', 'https://images.unsplash.com/photo-1558981806-ec527fa84c39?w=600', TRUE),
('Bike', 'TVS', 'Apache RTR 200', 'TN-45-EF-9012', 600.00, '9876543212', 'Thillai Nagar, Trichy', 'https://images.unsplash.com/photo-1609630875171-b1321377ee65?w=600', TRUE),
('Car', 'Maruti Suzuki', 'Swift Dzire', 'TN-45-GH-3456', 1500.00, '9876543213', 'Cantonment, Trichy', 'https://images.unsplash.com/photo-1549317661-bd32c8ce0afa?w=600', TRUE),
('Car', 'Hyundai', 'i20', 'TN-45-IJ-7890', 1800.00, '9876543214', 'KK Nagar, Trichy', 'https://images.unsplash.com/photo-1619767886558-efdc259cde1a?w=600', TRUE),
('Car', 'Toyota', 'Innova Crysta', 'TN-45-KL-2345', 3000.00, '9876543215', 'Woraiyur, Trichy', 'https://images.unsplash.com/photo-1533473359331-0135ef1b58bf?w=600', TRUE),
('Bike', 'Yamaha', 'FZ-S V3', 'TN-45-MN-6789', 500.00, '9876543216', 'Anna Nagar, Trichy', 'https://images.unsplash.com/photo-1568772585407-9361f9bf3a87?w=600', FALSE),
('Car', 'Honda', 'City', 'TN-45-OP-0123', 2000.00, '9876543217', 'Puthur, Trichy', 'https://images.unsplash.com/photo-1590362891991-f776e747a588?w=600', TRUE);

-- Verification
SELECT CONCAT('✅ Database setup complete! ', COUNT(*), ' vehicles inserted.') AS status FROM vehicles;
