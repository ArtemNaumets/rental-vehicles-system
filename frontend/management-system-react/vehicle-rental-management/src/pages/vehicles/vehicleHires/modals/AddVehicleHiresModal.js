import React, { useState, useEffect } from 'react';
import * as VehicleService from '../../../../api/VehicleService'; 
import * as LocationService from '../../../../api/LocationService';
import * as CommunityService from '../../../../api/CommunityService'; 
import '../../../../assets/css/style.css';
import '../../../../assets/css/AddModal.css';

const AddVehicleHiresModal = ({ show, onClose, onAdd, fields }) => {
    const [newData, setNewData] = useState({});
    const [errorMessage, setErrorMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');
    const [highlightError, setHighlightError] = useState(false);

    const [vehicles, setVehicles] = useState([]);
    const [locations, setLocations] = useState([]);
    const [clients, setClients] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [vehiclesData, locationsData, clientsData] = await Promise.all([
                    VehicleService.getVehicles(),
                    LocationService.getLocations(),
                    CommunityService.getClients() 
                ]);
                setVehicles(vehiclesData);
                setLocations(locationsData);
                setClients(clientsData);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const handleChange = (key, value) => {
        setNewData(prevState => ({
            ...prevState,
            [key]: value
        }));
    };

    const validateInputs = () => {
        return fields.every(field => {
            if (field.key !== 'id') {
                const value = newData[field.key];
                return !(value === undefined || value === null || (typeof value === 'string' && value.trim() === ''));
            }
            return true;
        });
    };

    const addNewData = async () => {
        if (!validateInputs()) {
            setErrorMessage('Please fill in all required fields.');
            setHighlightError(true);
            setTimeout(() => setHighlightError(false), 3000);
            return;
        }

        setLoading(true);
        try {
            await VehicleService.saveVehicleHire(newData); 
            onClose();
            setNewData({});
            onAdd();
            setErrorMessage('');
            setSuccessMessage('Data added successfully.');
            setTimeout(() => setSuccessMessage(''), 3000);
        } catch (error) {
            console.error('Error adding data:', error);
            setErrorMessage('Error adding data. ' + error.message);
        } finally {
            setLoading(false);
        }
    };

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            addNewData();
        }
    };

    const renderSelect = (key, options) => {
        return (
            <select
                className="form-control"
                id={key}
                value={newData[key] || ''}
                onChange={(e) => handleChange(key, e.target.value)}
                onKeyDown={handleKeyDown}
            >
                <option value="" disabled>Select {key}</option>
                {options.map(option => (
                    <option key={option.id} value={option.id}>
                        {option.name || option.description}
                    </option>
                ))}
            </select>
        );
    };

    const renderInput = (field) => {
        const { key } = field;
        if (key.toLowerCase() === 'vehicleid') {
            return renderSelect(key, vehicles);
        } else if (key.toLowerCase() === 'clientid') {
            return renderSelect(key, clients);
        } else if (key.toLowerCase() === 'locationid') {
            return renderSelect(key, locations);
        }

        return (
            <input
                type={key.toLowerCase().includes('date') ? 'date' : 'text'}
                className={`form-control ${highlightError && (!newData[key] || (typeof newData[key] === 'string' && newData[key].trim() === '')) ? 'error-highlight' : ''}`}
                id={key}
                placeholder={field.placeholder}
                value={key.toLowerCase().includes('date') ? newData[key]?.split('T')[0] || '' : newData[key] || ''}
                onChange={(e) => handleChange(key, e.target.value)}
                onKeyDown={handleKeyDown}
            />
        );
    };

    return (
        <div className={`modal ${show ? 'show' : ''}`} tabIndex="-1" role="dialog" style={{ display: show ? 'block' : 'none' }}>
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Add New Vehicle Hire</h5>
                        <button type="button" className="close" onClick={onClose}><span>&times;</span></button>
                    </div>
                    <div className="modal-body">
                        {fields.filter(field => field.key !== 'id').map(field => (
                            <div className={`form-group ${highlightError && (!newData[field.key] || (typeof newData[field.key] === 'string' && newData[field.key].trim() === '')) ? 'error-highlight' : ''}`} key={field.key}>
                                <label htmlFor={field.key}>{field.label}</label>
                                {renderInput(field)}
                            </div>
                        ))}
                        {errorMessage && <div className={`alert alert-danger ${highlightError ? 'error-highlight' : ''}`}>{errorMessage}</div>}
                        {successMessage && <div className="alert alert-success">{successMessage}</div>}
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" onClick={onClose}>Close</button>
                        <button type="button" className="btn btn-primary" onClick={addNewData} disabled={loading}>
                            {loading ? 'Adding...' : 'Add'}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default AddVehicleHiresModal;
