import React, { useEffect, useState } from 'react';
import * as LocationService from '../../../../api/LocationService'; 
import * as HrService from '../../../../api/HrService'; 

const DetailsEmployeesModal = ({ show, onClose, selectedItem, fields }) => {
    const [countries, setCountries] = useState([]);
    const [states, setStates] = useState([]);
    const [employeeTypes, setEmployeeTypes] = useState([]);
    const [jobTitles, setJobTitles] = useState([]);

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

        const fetchHrConfigurations = async () => {
            try {
                const [employeeTypesData, jobTitlesData] = await Promise.all([
                    HrService.getEmployeeTypes(), 
                    HrService.getJobTitles() 
                ]);

                setEmployeeTypes(employeeTypesData);
                setJobTitles(jobTitlesData);
            } catch (error) {
                console.error('Error fetching HR configurations:', error);
            }
        };

        fetchLocations();
        fetchHrConfigurations();
    }, []);

    const getLocationName = (id, type) => {
        if (type === 'country') {
            const country = countries.find(c => c.id ==id);
            return country ? country.description : 'Unknown Country';
        } else if (type === 'state') {
            const state = states.find(s => s.id == id);
            return state ? state.name : 'Unknown State';
        }
        return '';
    };

    const getHrValue = (id, type) => {
        if (type === 'employeeType') {
            const employeeType = employeeTypes.find(e => e.id == id);

            return employeeType ? employeeType.description : 'Unknown Employee Type';
        } else if (type === 'jobTitle') {
            const jobTitle = jobTitles.find(j => j.id == id);
            return jobTitle ? jobTitle.description : 'Unknown Job Title';
        }
        return '';
    };

    const renderFieldValue = (key, value) => {
        if (value === null || value === undefined) {
            return ''; 
        }
        if (key.toLowerCase() === 'countryid' || key.toLowerCase() === 'stateid') {
            return getLocationName(value, key.toLowerCase().includes('country') ? 'country' : 'state');
        }
        if (key.toLowerCase() === 'employeetypeid' || key.toLowerCase() === 'jobtitleid') {
            return getHrValue(value, key.toLowerCase().includes('employeetype') ? 'employeeType' : 'jobTitle');
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
                        <h5 className="modal-title">Employee Details</h5>
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

export default DetailsEmployeesModal;
