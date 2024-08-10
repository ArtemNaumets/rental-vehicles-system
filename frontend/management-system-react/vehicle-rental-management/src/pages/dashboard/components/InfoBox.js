import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

const InfoBox = ({ value, label, bgClass, icon }) => (
    <div className="col-md-3">
        <div className={`info-box ${bgClass} text-white text-center py-3 rounded`}>
            <FontAwesomeIcon icon={icon} /> 

            <h4>{value}</h4>
            <h6>{label}</h6>
        </div>
    </div>
);

export default InfoBox;
