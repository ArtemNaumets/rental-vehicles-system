import React, { useEffect, useState } from 'react';
import * as VehicleService from '../../../../api/VehicleService'; 
import * as LocationService from '../../../../api/LocationService'; 
import * as VehicleParameterService from '../../../../api/VehicleParameterService'; 
import * as CommunityService from '../../../../api/CommunityService'; 
import '../../../../assets/css/EditModal.css';

const EditVehiclesModal = ({ show, onClose, onSave, selectedItem, fields }) => {
    const [editedFields, setEditedFields] = useState({});
    const [originalFields, setOriginalFields] = useState({});
    const [hasChanges, setHasChanges] = useState(false);
    const [saving, setSaving] = useState(false);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [vehicleMakes, setVehicleMakes] = useState([]);
    const [vehicleModels, setVehicleModels] = useState([]);
    const [vehicleTypes, setVehicleTypes] = useState([]);
    const [locations, setLocations] = useState([]);
    const [employees, setEmployees] = useState([]);
    const [vehicleStatuses, setVehicleStatuses] = useState([]);
    const [saved, setSaved] = useState(false);

    useEffect(() => {
        if (selectedItem) {
            setEditedFields(selectedItem);
            setOriginalFields(selectedItem);
            setSaved(false);
        }
    }, [selectedItem]);

    useEffect(() => {
        const fetchData = async () => {
            const requests = [
                VehicleParameterService.getVehicleMakes(),
                VehicleParameterService.getVehicleModels(),
                VehicleParameterService.getVehicleTypes(),
                LocationService.getLocations(),
                CommunityService.getEmployees(),
                VehicleParameterService.getVehicleStatuses()
            ];

            const results = await Promise.allSettled(requests);

            results.forEach((result, index) => {
                if (result.status === 'fulfilled') {
                    switch (index) {
                        case 0:
                            setVehicleMakes(result.value);
                            break;
                        case 1:
                            setVehicleModels(result.value);
                            break;
                        case 2:
                            setVehicleTypes(result.value);
                            break;
                        case 3:
                            setLocations(result.value);
                            break;
                        case 4:
                            setEmployees(result.value);
                            break;
                        case 5:
                            setVehicleStatuses(result.value);
                            break;
                        default:
                            break;
                    }
                } else {
                    console.error('Error fetching data:', result.reason);
                }
            });
        };

        fetchData();
    }, []);

    useEffect(() => {
        const isModified = Object.keys(originalFields).some(key => 
            JSON.stringify(originalFields[key]) !== JSON.stringify(editedFields[key])
        );
        setHasChanges(isModified);
    }, [editedFields, originalFields]);

    const handleFieldChange = (fieldName, value) => {
        setEditedFields(prevState => ({
            ...prevState,
            [fieldName]: value
        }));
    };

    const saveEditedFields = async () => {
        if (!hasChanges) return;

        setSaving(true);
        setError(null);
        setSuccess(null);

        try {
            const updatedFields = {
                ...editedFields,
            };

            await VehicleService.updateVehicle(selectedItem.id, updatedFields);
            setSuccess('Item updated successfully');
            setSaved(true); 
            setTimeout(() => setSuccess(null), 3000);
            onSave();
            setTimeout(() => onClose(), 1500);
        } catch (error) {
            setError('Error updating item.');
            console.error('Error updating fields:', error);
        } finally {
            setSaving(false);
        }
    };

    const handleKeyDown = (event) => {
        if (event.key === 'Enter') {
            event.preventDefault();
            saveEditedFields();
        }
    };

    const renderSelect = (key, options) => {
        return (
            <select
                className="form-control"
                id={`${key}Edit`}
                value={editedFields[key] || ''}
                onChange={(e) => handleFieldChange(key, e.target.value)}
                disabled={key === 'id'}
            >
                <option value="" disabled>Select {key}</option>
                {options.map(option => (
                    <option key={option.id} value={option.id}>
                        {key.toLowerCase().includes('employee') ? option.lastname : (option.name || option.description)}
                    </option>
                ))}
            </select>
        );
    };

    const renderInput = (field) => {
        const { key } = field;
        const fieldValue = editedFields[key] || '';
        const originalValue = originalFields[key] || '';

        switch (key.toLowerCase()) {
            case 'vehicletypeid':
                return renderSelect(key, vehicleTypes);
            case 'vehiclemakeid':
                return renderSelect(key, vehicleMakes);
            case 'vehiclemodelid':
                return renderSelect(key, vehicleModels);
            case 'locationid':
                return renderSelect(key, locations);
            case 'employeeid':
                return renderSelect(key, employees);
            case 'vehiclestatusid':
                return renderSelect(key, vehicleStatuses);
            default:
                return (
                    <input
                        type={key.toLowerCase().includes('date') ? 'date' : 'text'}
                        className={`form-control ${fieldValue !== originalValue ? 'modified' : ''}`}
                        id={`${key}Edit`}
                        value={key.toLowerCase().includes('date') ? (fieldValue ? fieldValue.split('T')[0] : '') : fieldValue}
                        onChange={(e) => key !== 'id' && handleFieldChange(key, e.target.value)}
                        disabled={key === 'id'}
                    />
                );
        }
    };

    return (
        <div className={`modal ${show ? 'show' : ''}`} tabIndex="-1" role="dialog" style={{ display: show ? 'block' : 'none' }}>
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Edit Vehicle</h5>
                        <button type="button" className="close" onClick={onClose}><span>&times;</span></button>
                    </div>
                    <div className="modal-body">
                        <form onKeyDown={handleKeyDown}>
                            {fields.map(field => (
                                <div className="form-group" key={field.key}>
                                    <label htmlFor={`${field.key}Edit`}>{field.label}:</label>
                                    {renderInput(field)}
                                </div>
                            ))}
                            {error && <div className="alert alert-danger">{error}</div>}
                            {success && <div className="alert alert-success">{success}</div>}
                        </form>
                    </div>
                    <div className="modal-footer">
                        {!saved && (
                            <>
                                <button type="button" className="btn btn-secondary" onClick={onClose}>Close</button>
                                <button
                                    type="button"
                                    className={`btn btn-primary ${!hasChanges || saving ? 'disabled' : ''}`}
                                    onClick={saveEditedFields}
                                    disabled={!hasChanges || saving}
                                >
                                    {saving ? 'Saving...' : 'Save'}
                                </button>
                            </>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EditVehiclesModal;
