import React, { useEffect, useState } from 'react';
import * as VehicleParameterService from '../../../../api/VehicleParameterService';
import * as LocationService from '../../../../api/LocationService';
import * as CommunityService from '../../../../api/CommunityService';
import * as VehicleService from '../../../../api/VehicleService';

const DetailsVehiclesModal = ({ show, onClose, selectedItem, fields }) => {
    const [vehicleMakes, setVehicleMakes] = useState([]);
    const [vehicleModels, setVehicleModels] = useState([]);
    const [vehicleTypes, setVehicleTypes] = useState([]);
    const [locations, setLocations] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [vehicleStatuses, setVehicleStatuses] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [vehicleMakesData, vehicleModelsData, vehicleTypesData, locationsData, employeesData, vehicleStatusesData] = await Promise.all([
                    VehicleParameterService.getVehicleMakes(),
                    VehicleParameterService.getVehicleModels(),
                    VehicleParameterService.getVehicleTypes(),
                    LocationService.getLocations(),
                    CommunityService.getEmployees(),
                    VehicleParameterService.getVehicleStatuses()
                ]);

                setVehicleMakes(vehicleMakesData);
                setVehicleModels(vehicleModelsData);
                setVehicleTypes(vehicleTypesData);
                setLocations(locationsData);
                setEmployees(employeesData);
                setVehicleStatuses(vehicleStatusesData);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const getFieldName = (id, type) => {
        switch (type) {
            case 'VehicleMake':
                return vehicleMakes.find(item => item.id === id)?.description || '';
            case 'VehicleModel':
                return vehicleModels.find(item => item.id === id)?.description || '';
            case 'VehicleType':
                return vehicleTypes.find(item => item.id === id)?.description || '';
            case 'Location':
                return locations.find(item => item.id === id)?.description || '';
            case 'Employee':
                return employees.find(item => item.id === id)?.lastname || ''; 
            case 'VehicleStatus':
                return vehicleStatuses.find(item => item.id === id)?.description || '';
            default:
                return '';
        }
    };

    const renderFieldValue = (key, value) => {
        if (value === null || value === undefined) {
            return ''; 
        }
        if (key.toLowerCase().includes('makeid')) {
            return getFieldName(value, 'VehicleMake');
        }
        if (key.toLowerCase().includes('modelid')) {
            return getFieldName(value, 'VehicleModel');
        }
        if (key.toLowerCase().includes('typeid')) {
            return getFieldName(value, 'VehicleType');
        }
        if (key.toLowerCase().includes('locationid')) {
            return getFieldName(value, 'Location');
        }
        if (key.toLowerCase().includes('employeeid')) {
            return getFieldName(value, 'Employee');
        }
        if (key.toLowerCase().includes('statusid')) {
            return getFieldName(value, 'VehicleStatus');
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
                        <h5 className="modal-title">Details Vehicle</h5>
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

export default DetailsVehiclesModal;
