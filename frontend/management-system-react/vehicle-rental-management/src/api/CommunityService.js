import axiosInstance from '../configs/axiosConfig';
import { API_URLS } from '../configs/apiConfig';

export const getClients = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.clients}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching clients:', error);
    throw error;
  }
};

export const getClient = async (clientId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.clients}findById/${clientId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching client:', error);
    throw error;
  }
};

export const saveClient = async (clientData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.clients}add`, clientData);
    return response.data;
  } catch (error) {
    console.error('Error creating client:', error);
    throw error;
  }
};

export const updateClient = async (clientId, clientData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.clients}update/${clientId}`, clientData);
    return response.data;
  } catch (error) {
    console.error('Error updating client:', error);
    throw error;
  }
};

export const deleteClient = async (clientId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.clients}delete/${clientId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting client:', error);
    throw error;
  }
};

export const getEmployees = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.employees}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching employees:', error);
    throw error;
  }
};

export const getEmployee = async (employeeId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.employees}findById/${employeeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching employee:', error);
    throw error;
  }
};

export const saveEmployee = async (employeeData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.employees}add`, employeeData);
    return response.data;
  } catch (error) {
    console.error('Error creating employee:', error);
    throw error;
  }
};

export const updateEmployee = async (employeeId, employeeData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.employees}update/${employeeId}`, employeeData);
    return response.data;
  } catch (error) {
    console.error('Error updating employee:', error);
    throw error;
  }
};

export const deleteEmployee = async (employeeId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.employees}delete/${employeeId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting employee:', error);
    throw error;
  }
};

export const getSuppliers = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.suppliers}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching suppliers:', error);
    throw error;
  }
};

export const getSupplier = async (supplierId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.suppliers}findById/${supplierId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching supplier:', error);
    throw error;
  }
};

export const saveSupplier = async (supplierData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.suppliers}add`, supplierData);
    return response.data;
  } catch (error) {
    console.error('Error creating supplier:', error);
    throw error;
  }
};

export const updateSupplier = async (supplierId, supplierData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.suppliers}update/${supplierId}`, supplierData);
    return response.data;
  } catch (error) {
    console.error('Error updating supplier:', error);
    throw error;
  }
};

export const deleteSupplier = async (supplierId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.suppliers}delete/${supplierId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting supplier:', error);
    throw error;
  }
};
