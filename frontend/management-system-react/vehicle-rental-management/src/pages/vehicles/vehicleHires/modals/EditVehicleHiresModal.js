import React, { useEffect, useState } from 'react';
import * as VehicleService from '../../../../api/VehicleService'; 
import * as LocationService from '../../../../api/LocationService'; 
import * as CommunityService from '../../../../api/CommunityService'; 
import '../../../../assets/css/EditModal.css';

const EditVehicleHiresModal = ({ show, onClose, onSave, selectedItem, fields }) => {
    const [editedFields, setEditedFields] = useState({});
    const [originalFields, setOriginalFields] = useState({});
    const [hasChanges, setHasChanges] = useState(false);
    const [saving, setSaving] = useState(false);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(null);
    const [vehicles, setVehicles] = useState([]);
    const [locations, setLocations] = useState([]);
    const [clients, setClients] = useState([]);

    useEffect(() => {
        if (selectedItem) {
            const { vehicleName, locationName, clientName, vehicle, ...rest } = selectedItem;
            setEditedFields(rest);
            setOriginalFields(rest);
        }
    }, [selectedItem]);

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
            await VehicleService.updateVehicleHire(selectedItem.id, editedFields);
            setSuccess('Item updated successfully');
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
                        {option.name || option.description}
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
            case 'vehicleid':
                return renderSelect(key, vehicles);
            case 'clientid':
                return renderSelect(key, clients);
            case 'locationid':
                return renderSelect(key, locations);
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
                        <h5 className="modal-title">Edit Vehicle Hire</h5>
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
                        <button type="button" className="btn btn-secondary" onClick={onClose}>Close</button>
                        <button
                            type="button"
                            className={`btn btn-primary ${!hasChanges || saving ? 'disabled' : ''}`}
                            onClick={saveEditedFields}
                            disabled={!hasChanges || saving}
                        >
                            {saving ? 'Saving...' : 'Save'}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EditVehicleHiresModal;
