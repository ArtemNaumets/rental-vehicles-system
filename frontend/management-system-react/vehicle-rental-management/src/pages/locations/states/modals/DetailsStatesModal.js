import React, { useEffect, useState } from 'react';
import * as LocationService from '../../../../api/LocationService'; 

const DetailsStatesModal = ({ show, onClose, selectedItem, fields }) => {
    const [countries, setCountries] = useState([]);

    useEffect(() => {
        const fetchLocations = async () => {
            try {
                const [countriesData] = await Promise.all([
                    LocationService.getCountries(), 
                ]);

                setCountries(countriesData);

            } catch (error) {
                console.error('Error fetching locations:', error);
            }
        };

        fetchLocations();
    }, []);

    const getLocationName = (id ) => {
    
        return id;
    };

    const renderFieldValue = (key, value) => {
        if (value === null || value === undefined) {
            return ''; 
        }
        if (key.toLowerCase() === 'countryid') {
            return getLocationName(value, key.toLowerCase().includes('country') ? 'country' : '');
        }
        if (typeof value === 'object') {
            return value.description || JSON.stringify(value);
        }
        return value;
    };

    return (
        <div className={`modal ${show ? 'show' : ''}`} tabIndex="-1" role="dialog" style={{ display: show ? 'block' : 'none' }}>
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Details State</h5>
                        <button type="button" className="close" onClick={onClose}><span>&times;</span></button>
                    </div>
                    <div className="modal-body">
                        {fields.map(field => (
                            <div className="form-group" key={field.key}>
                                <label htmlFor={`${field.key}Details`}>{field.label}:</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id={`${field.key}Details`}
                                    value={renderFieldValue(field.key, selectedItem[field.key])}
                                    readOnly
                                />
                            </div>
                        ))}
                    </div>
                    <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" onClick={onClose}>Close</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DetailsStatesModal;
