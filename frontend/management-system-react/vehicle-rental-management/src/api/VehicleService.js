import axiosInstance from '../configs/axiosConfig';
import { API_URLS } from '../configs/apiConfig';

export const getVehicles = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicles}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicles:', error);
    throw error;
  }
};

export const getVehicle = async (vehicleId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicles}findById/${vehicleId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle:', error);
    throw error;
  }
};

export const saveVehicle = async (vehicleData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicles}add`, vehicleData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle:', error);
    throw error;
  }
};

export const updateVehicle = async (vehicleId, vehicleData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicles}update/${vehicleId}`, vehicleData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle:', error);
    throw error;
  }
};

export const deleteVehicle = async (vehicleId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicles}delete/${vehicleId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle:', error);
    throw error;
  }
};

export const getVehicleHires = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleHires}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle hires:', error);
    throw error;
  }
};

export const getVehicleHire = async (hireId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleHires}findById/${hireId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle hire:', error);
    throw error;
  }
};

export const saveVehicleHire = async (hireData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicleHires}add`, hireData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle hire:', error);
    throw error;
  }
};

export const updateVehicleHire = async (hireId, hireData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicleHires}update/${hireId}`, hireData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle hire:', error);
    throw error;
  }
};

export const deleteVehicleHire = async (hireId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicleHires}delete/${hireId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle hire:', error);
    throw error;
  }
};

export const getVehicleMaintenances = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleMaintenances}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle maintenances:', error);
    throw error;
  }
};

export const getVehicleMaintenance = async (maintenanceId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleMaintenances}findById/${maintenanceId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle maintenance:', error);
    throw error;
  }
};

export const saveVehicleMaintenance = async (maintenanceData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicleMaintenances}add`, maintenanceData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle maintenance:', error);
    throw error;
  }
};

export const updateVehicleMaintenance = async (maintenanceId, maintenanceData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicleMaintenances}update/${maintenanceId}`, maintenanceData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle maintenance:', error);
    throw error;
  }
};

export const deleteVehicleMaintenance = async (maintenanceId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicleMaintenances}delete/${maintenanceId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle maintenance:', error);
    throw error;
  }
};

export const getVehicleMovements = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleMovements}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle movements:', error);
    throw error;
  }
};

export const getVehicleMovement = async (movementId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleMovements}findById/${movementId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle movement:', error);
    throw error;
  }
};

export const saveVehicleMovement = async (movementData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicleMovements}add`, movementData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle movement:', error);
    throw error;
  }
};

export const updateVehicleMovement = async (movementId, movementData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicleMovements}update/${movementId}`, movementData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle movement:', error);
    throw error;
  }
};

export const deleteVehicleMovement = async (movementId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicleMovements}delete/${movementId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle movement:', error);
    throw error;
  }
};
