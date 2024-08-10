import React, { useState, useEffect } from 'react';
import { keycloak } from '../../configs/keyсloak';
import ProfileField from './ProfileField';
import 'bootstrap/dist/css/bootstrap.min.css';
import { LogOut } from '../../configs/keyсloak';

const Profile = () => {
    const [user, setUser] = useState({
        username: '',
        firstname: '',
        lastname: '',
        email: '',
    });
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (keycloak?.tokenParsed) {
            setUser({
                username: keycloak.tokenParsed.preferred_username || '',
                firstname: keycloak.tokenParsed.given_name || '',
                lastname: keycloak.tokenParsed.family_name || '',
                email: keycloak.tokenParsed.email || '',
            });
            setLoading(false);
        } else {
            setError('User is not authenticated');
            setLoading(false);
        }
    }, []);

    if (loading) {
        return <div className="text-center mt-5">Loading...</div>;
    }

    if (error) {
        return <div className="text-center mt-5 text-danger">{error}</div>;
    }

    return (
        <main role="main" className="container mt-5">
            <div className="card">
                <div className="card-header">
                    <h2 className="mb-0">User Profile</h2>
                </div>
                <div className="card-body">
                    <div className="row">
                        <div className="col-md-6">
                            <ProfileField label="Username" value={user.username} />
                            <ProfileField label="First Name" value={user.firstname} />
                            <ProfileField label="Last Name" value={user.lastname} />
                            <ProfileField label="Email" value={user.email} />
                        </div>
                    </div>
                </div>
                <div className="card-footer text-end">
                    <button className="btn btn-primary" onClick={LogOut}>Log out</button>
                </div>
            </div>
        </main>
    );
};

export default Profile;
