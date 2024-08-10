import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/ui/Navbar';
import Sidebar from './components/ui/Sidebar';
import Dashboard from './pages/dashboard';
import Loader from './components/ui/Loader';
import './assets/css/style.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import '@fortawesome/fontawesome-free/';
import 'animate.css/animate.min.css';
import Profile from './pages/profile';
import ClientsPage from './pages/community/clients';
import EmployeesPage from './pages/community/employees';
import SuppliersPage from './pages/community/suppliers';
import EmployeeTypesPage from './pages/hrConfigurations/employeeTypes';
import JobTitlesPage from './pages/hrConfigurations/jobTitles';
import InvoicesPage from './pages/finances/invoices';
import VehicleMakesPage from './pages/vehicleParameters/vehicleMakes';
import VehicleModelsPage from './pages/vehicleParameters/vehicleModels';
import VehicleStatusesPage from './pages/vehicleParameters/vehicleStatuses';
import VehicleTypesPage from './pages/vehicleParameters/vehicleTypes';
import CountriesPage from './pages/locations/countries';
import StatesPage from './pages/locations/states';
import LocationsPage from './pages/locations/locations';
import VehiclesPage from './pages/vehicles/vehicles';
import VehicleMaintenancesPage from './pages/vehicles/vehicleMaintenances';
import VehicleMovementsPage from './pages/vehicles/vehicleMovements';
import VehicleHiresPage from './pages/vehicles/vehicleHires';

function App() {
    const [loaderVisible, setLoaderVisible] = useState(true);
    const [appVisible, setAppVisible] = useState(false);

    useEffect(() => {
        let randomNumber = Math.floor(Math.random() * (1200 - 500 + 1)) + 500;
        setTimeout(() => {
            setLoaderVisible(false);
            setAppVisible(true);
        }, randomNumber);
    }, []);

    return (
        <Router>
            <div className="App">
                {loaderVisible && <Loader />}

                {appVisible && (
                    <div id="app" className="fade-in"> 
                        <Navbar />
                        <div className="container-fluid">
                            <div className="row">
                                <Sidebar />
                                <main role="main" className="mt-5 col-md-9 ml-sm-auto col-lg-10 px-md-4">
                                <Routes>
                                 <Route exact path="/" element={<Dashboard />} />

                                 <Route path="/vehicleMakes" element={<VehicleMakesPage/>} />
                                 <Route path="/vehicleModels" element={<VehicleModelsPage/>} />
                                 <Route path="/vehicleStatuses" element={<VehicleStatusesPage/>} />
                                 <Route path="/vehicleTypes" element={<VehicleTypesPage/>} /> 

                                <Route path="/employees" element={<EmployeesPage/>} /> 
                                <Route path="/clients" element={<ClientsPage/>} />
                                <Route path="/suppliers" element={<SuppliersPage/>} />

                                <Route path="/jobTitles" element={<JobTitlesPage/>} />
                                <Route path="/employeeTypes" element={<EmployeeTypesPage/>} />

                                <Route path="/invoices" element={<InvoicesPage/>} />

                                <Route path="/countries" element={<CountriesPage/>} />
                                <Route path="/states" element={<StatesPage/>} />
                                <Route path="/locations" element={<LocationsPage/>} />

                                <Route path="/vehicles" element={<VehiclesPage/>} />
                                <Route path="/vehicleHires" element={<VehicleHiresPage/>} />
                                <Route path="/vehicleMaintenances" element={<VehicleMaintenancesPage />} />
                                <Route path="/vehicleMovements" element={<VehicleMovementsPage />} />
                                
                                <Route path='/profile' element={<Profile/>}/> 

                                </Routes>
                                </main>
                            </div>
                        </div>
                    </div>
                )}
            </div>
        </Router>
    );
}

export default App;
