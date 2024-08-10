import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8222/', 
});

axiosInstance.interceptors.request.use(
  config => {
    const token = localStorage.getItem('bearer-token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  error => Promise.reject(error)
);

export default axiosInstance;
