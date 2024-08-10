import React, { useEffect, useState } from 'react';
import InfoBox from './components/InfoBox';
import VehicleRentals from './components/VehicleRentals';
import MaintenanceSchedule from './components/MaintenanceSchedule';
import DashboardCharts from './components/DashboardCharts';
import { faCarSide, faClipboardCheck, faUsers, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';
import { getVehicles, getVehicleHires, getVehicleMaintenances } from '../../api/VehicleService';
import { getLocations } from '../../api/LocationService';
import { getEmployees, getClient } from '../../api/CommunityService';

const Dashboard = () => {
    const [countVehicles, setCountVehicles] = useState('-');
    const [countEmployees, setCountEmployees] = useState('-');
    const [countLocations, setCountLocations] = useState('-');
    const [vehicleHires, setVehicleHires] = useState([]);
    const [countVehicleHires, setCountVehicleHires] = useState('-');
    const [vehicleMaintenances, setVehicleMaintenances] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [vehiclesData, employeesData, locationsData, vehicleHiresData, vehicleMaintenancesData] = await Promise.all([
                    getVehicles().catch(error => {
                        console.error('Error fetching vehicles:', error);
                        return [];
                    }),
                    getEmployees().catch(error => {
                        console.error('Error fetching employees:', error);
                        return [];
                    }),
                    getLocations().catch(error => {
                        console.error('Error fetching locations:', error);
                        return [];
                    }),
                    getVehicleHires().catch(error => {
                        console.error('Error fetching vehicle hires:', error);
                        return [];
                    }),
                    getVehicleMaintenances().catch(error => {
                        console.error('Error fetching vehicle maintenances:', error);
                        return [];
                    }),
                ]);

                const vehiclesMap = vehiclesData.reduce((acc, vehicle) => {
                    acc[vehicle.id] = vehicle;
                    return acc;
                }, {});

                const employeesMap = employeesData.reduce((acc, employee) => {
                    acc[employee.id] = employee;
                    return acc;
                }, {});

                const enrichedVehicleHires = await Promise.all(vehicleHiresData.map(async hire => {
                    try {
                        const clientData = await getClient(hire.clientid);
                        return {
                            ...hire,
                            vehicle: vehiclesMap[hire.vehicleid]?.name || 'Unknown Vehicle',
                            client: clientData.name || 'Unknown Client',
                            employee: employeesMap[hire.employeeid]?.name || 'Unknown Employee',
                        };
                    } catch (error) {
                        console.error('Error fetching client data:', error);
                        return {
                            ...hire,
                            vehicle: vehiclesMap[hire.vehicleid]?.name || 'Unknown Vehicle',
                            client: 'Unknown Client',
                            employee: employeesMap[hire.employeeid]?.name || 'Unknown Employee',
                        };
                    }
                }));

                setCountVehicles(vehiclesData.length);
                setCountEmployees(employeesData.length);
                setCountLocations(locationsData.length);
                setVehicleHires(enrichedVehicleHires);
                setCountVehicleHires(vehicleHiresData.length);
                setVehicleMaintenances(vehicleMaintenancesData);
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    return (
        <div>
            <div className="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                <h1 className="h2">Dashboard</h1>
            </div>
            <div className="row mb-3">
                <InfoBox icon={faCarSide} value={countVehicles} label="Total Vehicles" bgClass="bg-warning" />
                <InfoBox icon={faClipboardCheck} value={countVehicleHires} label="Last hires" bgClass="bg-primary" />
                <InfoBox icon={faUsers} value={countEmployees} label="Employees" bgClass="bg-dark" />
                <InfoBox icon={faMapMarkerAlt} value={countLocations} label="Locations" bgClass="bg-success" />
            </div>
            
            <VehicleRentals rentals={vehicleHires} />
            <MaintenanceSchedule maintenances={vehicleMaintenances} />
            <DashboardCharts vehicleHires={vehicleHires} vehicleMaintenances={vehicleMaintenances} />
        </div>
    );
};

export default Dashboard;
