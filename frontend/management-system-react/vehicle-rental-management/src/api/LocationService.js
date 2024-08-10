import axiosInstance from '../configs/axiosConfig';
import { API_URLS } from '../configs/apiConfig';

export const getCountries = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.countries}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching countries:', error);
    throw error;
  }
};

export const getCountry = async (countryId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.countries}findById/${countryId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching country:', error);
    throw error;
  }
};

export const saveCountry = async (countryData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.countries}add`, countryData);
    return response.data;
  } catch (error) {
    console.error('Error creating country:', error);
    throw error;
  }
};

export const updateCountry = async (countryId, countryData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.countries}update/${countryId}`, countryData);
    return response.data;
  } catch (error) {
    console.error('Error updating country:', error);
    throw error;
  }
};

export const deleteCountry = async (countryId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.countries}delete/${countryId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting country:', error);
    throw error;
  }
};

export const getStates = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.states}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching states:', error);
    throw error;
  }
};

export const getState = async (stateId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.states}findById/${stateId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching state:', error);
    throw error;
  }
};

export const saveState = async (stateData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.states}add`, stateData);
    return response.data;
  } catch (error) {
    console.error('Error creating state:', error);
    throw error;
  }
};

export const updateState = async (stateId, stateData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.states}update/${stateId}`, stateData);
    return response.data;
  } catch (error) {
    console.error('Error updating state:', error);
    throw error;
  }
};

export const deleteState = async (stateId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.states}delete/${stateId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting state:', error);
    throw error;
  }
};

export const getLocations = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.locations}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching locations:', error);
    throw error;
  }
};

export const getLocation = async (locationId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.locations}findById/${locationId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching location:', error);
    throw error;
  }
};

export const saveLocation = async (locationData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.locations}add`, locationData);
    return response.data;
  } catch (error) {
    console.error('Error creating location:', error);
    throw error;
  }
};

export const updateLocation = async (locationId, locationData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.locations}update/${locationId}`, locationData);
    return response.data;
  } catch (error) {
    console.error('Error updating location:', error);
    throw error;
  }
};

export const deleteLocation = async (locationId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.locations}delete/${locationId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting location:', error);
    throw error;
  }
};
