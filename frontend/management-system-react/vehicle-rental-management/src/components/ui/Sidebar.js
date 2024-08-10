import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { CSSTransition } from 'react-transition-group';
import { faCogs, faTachometerAlt, faUser, faChartPie, faDollarSign, faGlobe, faCarAlt, faChevronDown, faChevronUp, faBars } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import '../../assets/css/sidebar.css';

function Sidebar() {
    const [parametersMenuOpen, setParametersMenuOpen] = useState(false);
    const [peopleMenuOpen, setPeopleMenuOpen] = useState(false);
    const [hrSettingsMenuOpen, setHrSettingsMenuOpen] = useState(false);
    const [accountsMenuOpen, setAccountsMenuOpen] = useState(false);
    const [globalsMenuOpen, setGlobalsMenuOpen] = useState(false);
    const [vehiclesMenuOpen, setVehiclesMenuOpen] = useState(false);
    const [sidebarOpen, setSidebarOpen] = useState(false);

    const toggleMenu = (menuStateSetter, e) => {
        e.preventDefault();
        menuStateSetter(prevState => !prevState);
    };

    const toggleSidebar = () => {
        setSidebarOpen(prevState => !prevState);
    };

    return (
        <>
            <button className="btn btn-primary d-none mb-2 mobile-btn-sidebar" type="button" onClick={toggleSidebar}>
                <FontAwesomeIcon icon={faBars} />
            </button>
            <nav id="sidebar" className={`col-md-3 col-lg-2 d-md-block bg-light sidebar ${sidebarOpen ? 'show' : ''}`}>
                <div id="sidebarMenu" className="sidebar-sticky collapse d-md-block">
                    <ul className="nav flex-column">
                        <li className="nav-item">
                            <Link className="nav-link active" to="/">
                                <FontAwesomeIcon icon={faTachometerAlt} /> Dashboard
                            </Link>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${vehiclesMenuOpen ? '' : 'collapsed'}`} to="#" onClick={(e) => toggleMenu(setVehiclesMenuOpen, e)}>
                                <FontAwesomeIcon icon={faCarAlt} /> Vehicles
                                <FontAwesomeIcon icon={vehiclesMenuOpen ? faChevronUp : faChevronDown} className="float-right" />
                            </Link>
                            <CSSTransition in={vehiclesMenuOpen} timeout={300} classNames="menu">
                                <ul className={`collapse list-unstyled ${vehiclesMenuOpen ? 'show' : ''}`} id="vehiclesSubmenu">
                                    <li className="nav-item"><Link className="nav-link" to="/vehicles">Vehicles</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/vehicleHires">Vehicle Hires</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/vehicleMaintenances">Vehicle Maintenances</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/vehicleMovements">Vehicle Movements</Link></li>
                                </ul>
                            </CSSTransition>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${parametersMenuOpen ? '' : 'collapsed'}`} to="#" onClick={(e) => toggleMenu(setParametersMenuOpen, e)}>
                                <FontAwesomeIcon icon={faCogs} /> Parameters
                                <FontAwesomeIcon icon={parametersMenuOpen ? faChevronUp : faChevronDown} className="float-right" />
                            </Link>
                            <CSSTransition in={parametersMenuOpen} timeout={300} classNames="menu">
                                <ul className={`collapse list-unstyled ${parametersMenuOpen ? 'show' : ''}`} id="parametersSubmenu">
                                    <li className="nav-item"><Link className="nav-link" to="/vehicleMakes">Vehicle Makes</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/vehicleModels">Vehicle Models</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/vehicleTypes">Vehicle Types</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/vehicleStatuses">Vehicle Status</Link></li>
                                </ul>
                            </CSSTransition>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${peopleMenuOpen ? '' : 'collapsed'}`} to="#" onClick={(e) => toggleMenu(setPeopleMenuOpen, e)}>
                                <FontAwesomeIcon icon={faUser} /> People
                                <FontAwesomeIcon icon={peopleMenuOpen ? faChevronUp : faChevronDown} className="float-right" />
                            </Link>
                            <CSSTransition in={peopleMenuOpen} timeout={300} classNames="menu">
                                <ul className={`collapse list-unstyled ${peopleMenuOpen ? 'show' : ''}`} id="peopleSubmenu">
                                    <li className="nav-item"><Link className="nav-link" to="/employees">Employees</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/clients">Clients</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/suppliers">Suppliers</Link></li>
                                </ul>
                            </CSSTransition>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${hrSettingsMenuOpen ? '' : 'collapsed'}`} to="#" onClick={(e) => toggleMenu(setHrSettingsMenuOpen, e)}>
                                <FontAwesomeIcon icon={faChartPie} /> HR Settings
                                <FontAwesomeIcon icon={hrSettingsMenuOpen ? faChevronUp : faChevronDown} className="float-right" />
                            </Link>
                            <CSSTransition in={hrSettingsMenuOpen} timeout={300} classNames="menu">
                                <ul className={`collapse list-unstyled ${hrSettingsMenuOpen ? 'show' : ''}`} id="hrSettingsSubmenu">
                                    <li className="nav-item"><Link className="nav-link" to="/jobTitles">Job Titles</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/employeeTypes">Employee Type</Link></li>
                                </ul>
                            </CSSTransition>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${accountsMenuOpen ? '' : 'collapsed'}`} to="#" onClick={(e) => toggleMenu(setAccountsMenuOpen, e)}>
                                <FontAwesomeIcon icon={faDollarSign} /> Accounts
                                <FontAwesomeIcon icon={accountsMenuOpen ? faChevronUp : faChevronDown} className="float-right" />
                            </Link>
                            <CSSTransition in={accountsMenuOpen} timeout={300} classNames="menu">
                                <ul className={`collapse list-unstyled ${accountsMenuOpen ? 'show' : ''}`} id="accountsSubmenu">
                                    <li className="nav-item"><Link className="nav-link" to="/invoices">Invoices</Link></li>
                                </ul>
                            </CSSTransition>
                        </li>
                        <li className="nav-item">
                            <Link className={`nav-link ${globalsMenuOpen ? '' : 'collapsed'}`} to="#" onClick={(e) => toggleMenu(setGlobalsMenuOpen, e)}>
                                <FontAwesomeIcon icon={faGlobe} /> Locations
                                <FontAwesomeIcon icon={globalsMenuOpen ? faChevronUp : faChevronDown} className="float-right" />
                            </Link>
                            <CSSTransition in={globalsMenuOpen} timeout={300} classNames="menu">
                                <ul className={`collapse list-unstyled ${globalsMenuOpen ? 'show' : ''}`} id="globalsSubmenu">
                                    <li className="nav-item"><Link className="nav-link" to="/countries">Countries</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/states">States</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/locations">Locations</Link></li>
                                </ul>
                            </CSSTransition>
                        </li>
                    </ul>
                </div>
            </nav>
        </>
    );
}

export default Sidebar;
