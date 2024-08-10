import React from 'react';

const MaintenanceSchedule = ({ maintenances }) => {
    const formatDate = (dateStr) => {
        // Convert the date string to a Date object and format it
        const date = new Date(dateStr);
        return date.toLocaleDateString('en-US', {
            year: 'numeric',
            month: 'short',
            day: 'numeric',
        });
    };

    maintenances = maintenances.slice(-10);

    return (
        <div className="card mb-4 shadow">
            <div className="card-header bg-danger text-white">
                <h4 className="my-0">Upcoming Maintenance Schedule</h4>
            </div>
            <div className="card-body">
                {maintenances.length > 0 ? (
                    <table className="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Remarks</th>
                                <th>Price</th>
                                <th>Vehicle</th>
                                <th>Start Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            {maintenances.map((maintenance, index) => (
                                <tr key={index}>
                                    <td>{maintenance.remarks}</td>
                                    <td>${maintenance.price}</td>
                                    <td>{maintenance.vehicle.name}</td> 
                                    <td>{formatDate(maintenance.startDate)}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                ) : (
                    <p>No upcoming maintenance scheduled.</p>
                )}
            </div>
        </div>
    );
};

export default MaintenanceSchedule;
