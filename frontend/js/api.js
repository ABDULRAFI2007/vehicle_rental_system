/**
 * Vehicle Rental System — API Helper
 * Centralizes all fetch() calls to the Java Spring Boot backend.
 */

const API_BASE = 'http://localhost:8080/api';

const api = {
    // ============================
    //  Customer APIs
    // ============================

    async getAllVehicles() {
        const res = await fetch(`${API_BASE}/vehicles`);
        if (!res.ok) throw new Error('Failed to fetch vehicles');
        return res.json();
    },

    async getAvailableVehicles() {
        const res = await fetch(`${API_BASE}/vehicles/available`);
        if (!res.ok) throw new Error('Failed to fetch available vehicles');
        return res.json();
    },

    async getVehicleById(id) {
        const res = await fetch(`${API_BASE}/vehicles/${id}`);
        if (!res.ok) throw new Error('Vehicle not found');
        return res.json();
    },

    async getVehiclesByType(type) {
        const res = await fetch(`${API_BASE}/vehicles/type/${type}`);
        if (!res.ok) throw new Error('Failed to fetch vehicles by type');
        return res.json();
    },

    async searchVehicles(query) {
        const res = await fetch(`${API_BASE}/vehicles/search?q=${encodeURIComponent(query)}`);
        if (!res.ok) throw new Error('Search failed');
        return res.json();
    },

    // ============================
    //  Admin APIs
    // ============================

    async adminLogin(username, password) {
        const res = await fetch(`${API_BASE}/admin/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        if (!res.ok) {
            const err = await res.json();
            throw new Error(err.message || 'Login failed');
        }
        return res.json();
    },

    async addVehicle(vehicleData) {
        const res = await fetch(`${API_BASE}/vehicles`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(vehicleData)
        });
        if (!res.ok) {
            const err = await res.json();
            throw new Error(err.message || JSON.stringify(err.messages) || 'Failed to add vehicle');
        }
        return res.json();
    },

    async updateVehicle(id, vehicleData) {
        const res = await fetch(`${API_BASE}/vehicles/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(vehicleData)
        });
        if (!res.ok) {
            const err = await res.json();
            throw new Error(err.message || JSON.stringify(err.messages) || 'Failed to update vehicle');
        }
        return res.json();
    },

    async deleteVehicle(id) {
        const res = await fetch(`${API_BASE}/vehicles/${id}`, {
            method: 'DELETE'
        });
        if (!res.ok) throw new Error('Failed to delete vehicle');
        return res.json();
    },

    async toggleAvailability(id, available) {
        const res = await fetch(`${API_BASE}/vehicles/${id}/availability`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ available })
        });
        if (!res.ok) throw new Error('Failed to update availability');
        return res.json();
    }
};

// ============================
//  Toast Notification Utility
// ============================

function showToast(message, type = 'info') {
    let container = document.getElementById('toast-container');
    if (!container) {
        container = document.createElement('div');
        container.id = 'toast-container';
        container.className = 'toast-container';
        document.body.appendChild(container);
    }

    const icons = { success: '✅', error: '❌', info: 'ℹ️' };
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    toast.innerHTML = `<span>${icons[type] || 'ℹ️'}</span><span>${message}</span>`;
    container.appendChild(toast);

    setTimeout(() => {
        toast.classList.add('exit');
        setTimeout(() => toast.remove(), 300);
    }, 3500);
}

// ============================
//  Admin Session Helpers
// ============================

function isAdminLoggedIn() {
    return sessionStorage.getItem('adminLoggedIn') === 'true';
}

function requireAdmin() {
    if (!isAdminLoggedIn()) {
        window.location.href = 'admin-login.html';
        return false;
    }
    return true;
}

function adminLogout() {
    sessionStorage.removeItem('adminLoggedIn');
    sessionStorage.removeItem('adminUsername');
    window.location.href = 'admin-login.html';
}

// ============================
//  Navbar Scroll Effect
// ============================

window.addEventListener('scroll', () => {
    const navbar = document.querySelector('.navbar');
    if (navbar) {
        navbar.classList.toggle('scrolled', window.scrollY > 20);
    }
});

// ============================
//  Mobile Nav Toggle
// ============================

function toggleNav() {
    const links = document.querySelector('.nav-links');
    if (links) links.classList.toggle('open');
}

// ============================
//  Currency Formatter
// ============================

function formatPrice(price) {
    return '₹' + Number(price).toLocaleString('en-IN');
}

// ============================
//  Default image on error
// ============================

function handleImageError(img) {
    img.onerror = null;
    img.src = 'data:image/svg+xml,' + encodeURIComponent(`
        <svg xmlns="http://www.w3.org/2000/svg" width="400" height="200" viewBox="0 0 400 200">
            <rect fill="#1A1A2E" width="400" height="200"/>
            <text fill="#6C6C8A" font-family="sans-serif" font-size="16" x="50%" y="50%" text-anchor="middle" dy=".3em">No Image Available</text>
        </svg>
    `);
}
