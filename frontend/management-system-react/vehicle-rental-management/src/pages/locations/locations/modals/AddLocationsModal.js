import React, { useState, useEffect } from 'react';
import * as LocationService from '../../../../api/LocationService'; 
import '../../../../assets/css/style.css';
import '../../../../assets/css/AddModal.css';

const AddLocationsModal = ({ show, onClose, onAdd, fields }) => {
    const [newData, setNewData] = useState({});
    const [errorMessage, setErrorMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const [successMessage, setSuccessMessage] = useState('');
    const [highlightError, setHighlightError] = useState(false);
    const [countries, setCountries] = useState([]);
    const [states, setStates] = useState([]);

    useEffect(() => {
        const fetchLocations = async () => {
            try {
                const [countriesData, statesData] = await Promise.all([
                    LocationService.getCountries(), 
                    LocationService.getStates() 
                ]);
                setCountries(countriesData);
                setStates(statesData);
            } catch (error) {
                console.error('Error fetching locations:', error);
            }
        };

        fetchLocations();
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
            setErrorMessage('Please fill in all the required fields.');
            setHighlightError(true);
            setTimeout(() => setHighlightError(false), 3000);
            return;
        }

        setLoading(true);
        try {
            await LocationService.saveLocation(newData); 
            onClose();
            setNewData({});
            onAdd();
            setErrorMessage('');
            setSuccessMessage('Data added successfully.');
            setTimeout(() => setSuccessMessage(''), 3000);
        } catch (error) {
            console.error('Error adding new data:', error);
            setErrorMessage('Error adding new data. ' + error.message);
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
                <option value="" disabled>Select {key === 'countryid' ? 'country' : 'state' }</option>
                {options.map(option => (
                    <option key={option.id} value={option.id}>
                        {key.toLowerCase().includes('country') ? option.description : option.name}
                    </option>
                ))}
            </select>
        );
    };

    const renderInput = (field) => {
        const { key } = field;
        if (key.toLowerCase() === 'countryid') {
            return renderSelect(key, countries);
        }

        if (key.toLowerCase() === 'stateid') {
            return renderSelect(key, states);
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
                        <h5 className="modal-title">Add New Location</h5>
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

export default AddLocationsModal;
