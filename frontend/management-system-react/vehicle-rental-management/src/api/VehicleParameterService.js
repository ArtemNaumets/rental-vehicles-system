import axiosInstance from '../configs/axiosConfig';
import { API_URLS } from '../configs/apiConfig';

export const getVehicleMakes = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleMakes}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle makes:', error);
    throw error;
  }
};

export const getVehicleMake = async (makeId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleMakes}findById/${makeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle make:', error);
    throw error;
  }
};

export const saveVehicleMake = async (makeData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicleMakes}add`, makeData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle make:', error);
    throw error;
  }
};

export const updateVehicleMake = async (makeId, makeData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicleMakes}update/${makeId}`, makeData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle make:', error);
    throw error;
  }
};

export const deleteVehicleMake = async (makeId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicleMakes}delete/${makeId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle make:', error);
    throw error;
  }
};

export const getVehicleModels = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleModels}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle models:', error);
    throw error;
  }
};

export const getVehicleModel = async (modelId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleModels}findById/${modelId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle model:', error);
    throw error;
  }
};

export const saveVehicleModel = async (modelData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicleModels}add`, modelData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle model:', error);
    throw error;
  }
};

export const updateVehicleModel = async (modelId, modelData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicleModels}update/${modelId}`, modelData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle model:', error);
    throw error;
  }
};

export const deleteVehicleModel = async (modelId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicleModels}delete/${modelId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle model:', error);
    throw error;
  }
};

export const getVehicleTypes = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleTypes}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle types:', error);
    throw error;
  }
};

export const getVehicleType = async (typeId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleTypes}findById/${typeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle type:', error);
    throw error;
  }
};

export const saveVehicleType = async (typeData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicleTypes}add`, typeData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle type:', error);
    throw error;
  }
};

export const updateVehicleType = async (typeId, typeData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicleTypes}update/${typeId}`, typeData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle type:', error);
    throw error;
  }
};

export const deleteVehicleType = async (typeId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicleTypes}delete/${typeId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle type:', error);
    throw error;
  }
};

export const getVehicleStatuses = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleStatuses}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle statuses:', error);
    throw error;
  }
};

export const getVehicleStatus = async (statusId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.vehicleStatuses}findById/${statusId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching vehicle status:', error);
    throw error;
  }
};

export const saveVehicleStatus = async (statusData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.vehicleStatuses}add`, statusData);
    return response.data;
  } catch (error) {
    console.error('Error creating vehicle status:', error);
    throw error;
  }
};

export const updateVehicleStatus = async (statusId, statusData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.vehicleStatuses}update/${statusId}`, statusData);
    return response.data;
  } catch (error) {
    console.error('Error updating vehicle status:', error);
    throw error;
  }
};

export const deleteVehicleStatus = async (statusId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.vehicleStatuses}delete/${statusId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting vehicle status:', error);
    throw error;
  }
};
