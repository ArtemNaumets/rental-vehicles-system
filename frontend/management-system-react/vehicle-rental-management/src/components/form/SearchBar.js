import React from 'react';

const SearchBar = ({ searchTerm, onSearchChange }) => {
    return (
        <div className="col-md-12 mb-3">
            <input
                type="text"
                className="form-control"
                placeholder="Search..."
                value={searchTerm}
                onChange={(e) => onSearchChange(e.target.value)}
            />
        </div>
    );
};

export default SearchBar;
