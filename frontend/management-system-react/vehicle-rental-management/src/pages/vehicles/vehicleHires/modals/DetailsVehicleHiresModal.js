import React, { useEffect, useState } from 'react';
import * as CommunityService from '../../../../api/CommunityService';
import * as VehicleService from '../../../../api/VehicleService';
import * as LocationService from '../../../../api/LocationService'; 

const DetailsVehicleHiresModal = ({ show, onClose, selectedItem, fields }) => {
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

    const getFieldName = (id, type) => {
        switch (type) {
            case 'Vehicle':
                return vehicles.find(item => item.id === id)?.name || '';
            case 'Location':
                return locations.find(item => item.id === id)?.description || '';
            case 'Client':
                return clients.find(item => item.id === id)?.name || '';
            default:
                return '';
        }
    };

    const renderFieldValue = (key, value) => {
        if (value === null || value === undefined) {
            return '';
        }
        if (key.toLowerCase().includes('vehicleid')) {
            return getFieldName(value, 'Vehicle');
        }
        if (key.toLowerCase().includes('locationid')) {
            return getFieldName(value, 'Location');
        }
        if (key.toLowerCase().includes('clientid')) {
            return getFieldName(value, 'Client');
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
                        <h5 className="modal-title">Details Vehicle Movement</h5>
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

export default DetailsVehicleHiresModal;
