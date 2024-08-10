import React from 'react';
import { Bar, Line } from 'react-chartjs-2';
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, PointElement, LineElement } from 'chart.js';

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  PointElement,
  LineElement
);

const DashboardCharts = ({ vehicleHires, vehicleMaintenances }) => {

    const processHireData = (hires) => {
        const data = hires.reduce((acc, hire) => {
            const date = new Date(hire.dateOut).toLocaleDateString();
            if (!acc[date]) acc[date] = 0;
            acc[date]++;
            return acc;
        }, {});

        return {
            labels: Object.keys(data),
            datasets: [{
                label: 'Vehicle Hires',
                data: Object.values(data),
                backgroundColor: 'rgba(75, 192, 192, 0.6)',
                borderColor: 'rgba(75, 192, 192, 1)',
                borderWidth: 1,
            }],
        };
    };

    const processMaintenanceData = (maintenances) => {

        const data = maintenances.reduce((acc, maintenance) => {
            const date = new Date(maintenance.startDate).toLocaleDateString();
            if (!acc[date]) acc[date] = 0;
            acc[date]++;
            return acc;
        }, {});

        return {
            labels: Object.keys(data),
            datasets: [{
                label: 'Vehicle Maintenances',
                data: Object.values(data),
                backgroundColor: 'rgba(153, 102, 255, 0.6)',
                borderColor: 'rgba(153, 102, 255, 1)',
                borderWidth: 1,
            }],
        };
    };

    const hireChartData = processHireData(vehicleHires);
    const maintenanceChartData = processMaintenanceData(vehicleMaintenances);

    return (
        <div className="row">
            <div className="col-md-6">
                <div className="card mb-4">
                    <div className="card-header">Vehicle Hires</div>
                    <div className="card-body">
                        <Bar data={hireChartData} />
                    </div>
                </div>
            </div>
            <div className="col-md-6">
                <div className="card mb-4">
                    <div className="card-header">Vehicle Maintenances</div>
                    <div className="card-body">
                        <Line data={maintenanceChartData} />
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DashboardCharts;
