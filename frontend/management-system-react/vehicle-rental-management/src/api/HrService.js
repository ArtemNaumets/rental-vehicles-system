import axiosInstance from '../configs/axiosConfig';
import { API_URLS } from '../configs/apiConfig';

export const getJobTitles = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.jobTitles}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching job titles:', error);
    throw error;
  }
};

export const getJobTitle = async (jobTitleId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.jobTitles}findById/${jobTitleId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching job title:', error);
    throw error;
  }
};

export const saveJobTitle = async (jobTitleData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.jobTitles}add`, jobTitleData);
    return response.data;
  } catch (error) {
    console.error('Error creating job title:', error);
    throw error;
  }
};

export const updateJobTitle = async (jobTitleId, jobTitleData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.jobTitles}update/${jobTitleId}`, jobTitleData);
    return response.data;
  } catch (error) {
    console.error('Error updating job title:', error);
    throw error;
  }
};

export const deleteJobTitle = async (jobTitleId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.jobTitles}delete/${jobTitleId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting job title:', error);
    throw error;
  }
};

export const getEmployeeTypes = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.employeeTypes}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching employee types:', error);
    throw error;
  }
};

export const getEmployeeType = async (employeeTypeId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.employeeTypes}findById/${employeeTypeId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching employee type:', error);
    throw error;
  }
};

export const saveEmployeeType = async (employeeTypeData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.employeeTypes}add`, employeeTypeData);
    return response.data;
  } catch (error) {
    console.error('Error creating employee type:', error);
    throw error;
  }
};

export const updateEmployeeType = async (employeeTypeId, employeeTypeData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.employeeTypes}update/${employeeTypeId}`, employeeTypeData);
    return response.data;
  } catch (error) {
    console.error('Error updating employee type:', error);
    throw error;
  }
};

export const deleteEmployeeType = async (employeeTypeId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.employeeTypes}delete/${employeeTypeId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting employee type:', error);
    throw error;
  }
};
