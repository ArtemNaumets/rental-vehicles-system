import axiosInstance from '../configs/axiosConfig';
import { API_URLS } from '../configs/apiConfig';

export const getInvoices = async () => {
  try {
    const response = await axiosInstance.get(`${API_URLS.invoices}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching invoices:', error);
    throw error;
  }
};

export const getInvoice = async (invoiceId) => {
  try {
    const response = await axiosInstance.get(`${API_URLS.invoices}findById/${invoiceId}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching invoice:', error);
    throw error;
  }
};

export const saveInvoice = async (invoiceData) => {
  try {
    const response = await axiosInstance.post(`${API_URLS.invoices}add`, invoiceData);
    return response.data;
  } catch (error) {
    console.error('Error creating invoice:', error);
    throw error;
  }
};

export const updateInvoice = async (invoiceId, invoiceData) => {
  try {
    const response = await axiosInstance.put(`${API_URLS.invoices}update/${invoiceId}`, invoiceData);
    return response.data;
  } catch (error) {
    console.error('Error updating invoice:', error);
    throw error;
  }
};

export const deleteInvoice = async (invoiceId) => {
  try {
    const response = await axiosInstance.delete(`${API_URLS.invoices}delete/${invoiceId}`);
    return response.data;
  } catch (error) {
    console.error('Error deleting invoice:', error);
    throw error;
  }
};
