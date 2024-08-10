import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowUp, faArrowDown } from '@fortawesome/free-solid-svg-icons';

const TableHeader = ({ columns, sortConfig, sortBy }) => {
    // first 4
    const visibleColumns = columns.slice(0, 4);

    return (
        <thead>
            <tr>
                {visibleColumns.map(column => (
                    <th key={column.key} onClick={() => sortBy(column.key)}>
                        {column.label}
                        {sortConfig.key === column.key && (
                            sortConfig.direction === 'ascending' ? 
                                <FontAwesomeIcon icon={faArrowDown} /> : 
                                <FontAwesomeIcon icon={faArrowUp} />
                        )}
                    </th>
                ))}
                <th>Action</th> 
            </tr>
        </thead>
    );
};

export default TableHeader;
