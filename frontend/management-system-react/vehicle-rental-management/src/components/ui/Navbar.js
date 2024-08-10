import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { LogOut } from '../../configs/keyсloak';
import { keycloak } from '../../configs/keyсloak';
import 'bootstrap/dist/css/bootstrap.min.css';
import '@fortawesome/fontawesome-free/css/all.min.css';  

const Navbar = () => {
  const [isDropdownOpen, setDropdownOpen] = useState(false);

  const handleDropdownToggle = () => {
    setDropdownOpen(!isDropdownOpen);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
      <Link className="navbar-brand" to="/">
        Vehicles Rental Management System
      </Link>
      <button className="navbar-toggler" type="button" onClick={handleDropdownToggle} aria-controls="navbarNav" aria-expanded={isDropdownOpen} aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className={`collapse navbar-collapse ${isDropdownOpen ? 'show' : ''}`} id="navbarNav">
        <ul className="navbar-nav ml-auto">
          <li className="nav-item dropdown">
            <a className="nav-link dropdown-toggle" href="#" onClick={handleDropdownToggle} id="userDropdown" role="button" aria-haspopup="true" aria-expanded={isDropdownOpen}>
              <i className="fas fa-user-circle"></i> {keycloak.tokenParsed.preferred_username || 'User'}
            </a>
            <div className={`dropdown-menu dropdown-menu-right ${isDropdownOpen ? 'show' : ''}`} aria-labelledby="userDropdown">
              <Link className="dropdown-item" to="/profile">
                <i className="fas fa-user"></i> My Profile
              </Link>
              <div className="dropdown-divider"></div>
              <button className="dropdown-item text-danger" onClick={LogOut}>
                <i className="fas fa-sign-out-alt"></i> Log Out
              </button>
            </div>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
