import React, { useEffect, useState } from 'react';
import * as CommunityService from '../../../../api/CommunityService'; 

const DetailsClientsModal = ({ show, onClose, selectedItem, fields }) => {
    const [clients, setClients] = useState([]);

    useEffect(() => {
        const fetchClients = async () => {
            try {
                const [clientsData] = await Promise.all([
                    CommunityService.getClients(), 
                ]);

                setClients(clientsData);

            } catch (error) {
                console.error('Error fetching clients:', error);
            }
        };

        fetchClients();
    }, []);

    const getClientName = (id, type) => {
        if (type === 'client') {

            const client = clients.find(item => item.id == id);

            return client ? client.name : '';
        } 
        return '';
    };

    const renderFieldValue = (key, value) => {
        if (value === null || value === undefined) {
            return ''; 
        }
        if (key.toLowerCase() === 'clientid') {
            return getClientName(value, key.toLowerCase().includes('client') ? 'client' : '');
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
                        <h5 className="modal-title">Details Client</h5>
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

export default DetailsClientsModal;
