import React, { useState, useEffect } from 'react';
import * as VehicleService from '../../../../api/VehicleService'; 
import '../../../../assets/css/style.css';
import '../../../../assets/css/AddModal.css';

import { getVehicleTypes, getVehicleMakes, getVehicleStatuses, getVehicleModels } from '../../../../api/VehicleParameterService';
import { getEmployees } from '../../../../api/CommunityService';
import { getLocations } from '../../../../api/LocationService';

const AddVehiclesModal = ({ show, onClose, onAdd, fields }) => {
    const [newData, setNewData] = useState({});
    const [errorMessage, setErrorMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');
    const [highlightError, setHighlightError] = useState(false);

    const [vehicleTypes, setVehicleTypes] = useState([]);
    const [vehicleMakes, setVehicleMakes] = useState([]);
    const [vehicleStatuses, setVehicleStatuses] = useState([]);
    const [vehicleModels, setVehicleModels] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [locations, setLocations] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [vehicleTypesData, vehicleMakesData, vehicleStatusesData, vehicleModelsData, employeesData, locationsData] = await Promise.all([
                    getVehicleTypes(),
                    getVehicleMakes(),
                    getVehicleStatuses(),
                    getVehicleModels(),
                    getEmployees(),
                    getLocations()
                ]);
                setVehicleTypes(vehicleTypesData);
                setVehicleMakes(vehicleMakesData);
                setVehicleStatuses(vehicleStatusesData);
                setVehicleModels(vehicleModelsData);
                setEmployees(employeesData);
                setLocations(locationsData);
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
            await VehicleService.saveVehicle(newData); 
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
                        {key.toLowerCase().includes('employee') ? option.lastname : (option.description || option.name)}
                    </option>
                ))}
            </select>
        );
    };

    const renderInput = (field) => {
        const { key } = field;
        if (key.toLowerCase() === 'vehicletypeid') {
            return renderSelect(key, vehicleTypes);
        } else if (key.toLowerCase() === 'vehiclemakeid') {
            return renderSelect(key, vehicleMakes);
        } else if (key.toLowerCase() === 'vehiclestatusid') {
            return renderSelect(key, vehicleStatuses);
        } else if (key.toLowerCase() === 'vehiclemodelid') {
            return renderSelect(key, vehicleModels);
        } else if (key.toLowerCase() === 'employeeid') {
            return renderSelect(key, employees);
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
                        <h5 className="modal-title">Add New Vehicle</h5>
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

export default AddVehiclesModal;
