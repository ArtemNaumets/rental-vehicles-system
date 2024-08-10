import React from 'react';

const ProfileField = ({ label, value }) => {
    return (
        <div className="form-group row mb-3">
            <label className="col-sm-4 col-form-label font-weight-bold">{label}:</label>
            <div className="col-sm-8">
                <p className="form-control-plaintext">{value || 'N/A'}</p>
            </div>
        </div>
    );
};

export default ProfileField;
