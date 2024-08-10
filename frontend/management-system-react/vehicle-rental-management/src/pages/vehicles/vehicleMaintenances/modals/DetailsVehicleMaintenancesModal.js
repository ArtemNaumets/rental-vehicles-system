import React, { useEffect, useState } from 'react';
import * as CommunityService from '../../../../api/CommunityService';
import * as VehicleService from '../../../../api/VehicleService';

const DetailsVehicleMaintenanceModal = ({ show, onClose, selectedItem, fields }) => {
    const [vehicles, setVehicles] = useState([]);
    const [suppliers, setSuppliers] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [vehiclesData, suppliersData] = await Promise.all([
                    VehicleService.getVehicles(),
                    CommunityService.getSuppliers()
                ]);

                setVehicles(vehiclesData);
                setSuppliers(suppliersData);
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
            case 'Supplier':
                return suppliers.find(item => item.id === id)?.name || '';
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
        if (key.toLowerCase().includes('supplierid')) {
            return getFieldName(value, 'Supplier');
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
                        <h5 className="modal-title">Details Vehicle Maintenance</h5>
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

export default DetailsVehicleMaintenanceModal;
