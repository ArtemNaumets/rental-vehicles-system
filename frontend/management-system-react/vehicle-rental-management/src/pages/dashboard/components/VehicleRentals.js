import React from 'react';

const VehicleRentals = ({ rentals }) => {
    const formatDate = (dateStr) => {
        // Convert the date string to a Date object and format it
        const date = new Date(dateStr);
        return date.toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
        });
    };

    const recentRentals = rentals.slice(-10);

    return (
        <div className="card mb-4 shadow">
            <div className="card-header bg-primary text-white">
                <h4 className="my-0">Recent Vehicle Rentals</h4>
            </div>
            <div className="card-body">
                {recentRentals.length > 0 ? (
                    <table className="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Vehicle</th>
                                <th>Client</th>
                                <th>Rental Date</th>
                                <th>Return Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            {recentRentals.map((hire, index) => (
                                <tr key={index}>
                                    <td>{hire.vehicle}</td>
                                    <td>{hire.client}</td>
                                    <td>{formatDate(hire.dateOut)}</td>
                                    <td>{formatDate(hire.dateIn)}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>No recent vehicle rentals.</p>
                )}
            </div>
        </div>
    );
};

export default VehicleRentals;
