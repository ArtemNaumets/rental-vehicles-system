import React from 'react';

const TableRow = ({ item, editItem, showItemDetails, deleteItem, fields }) => {
    const visibleFields = fields.slice(0, 4).map(field => field.key);

    const shouldDisplayField = (key) => {
        return visibleFields.includes(key);
    };

    const formatDateString = (dateString) => {
        const date = new Date(dateString);
        if (isNaN(date)) {
            return dateString; 
        }
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    };

    const renderCellValue = (key, value) => {
        if (value === null || value === undefined) {
            return '';
        }
        if (typeof value === 'object') {
            return value.description || JSON.stringify(value);
        }
        if (key.toLowerCase().includes('date')) {
            return formatDateString(value);
        }
        return value;
    };

    return (
        <tr>
            {Object.keys(item).map(key => (
                shouldDisplayField(key) ? (
                    <td key={key}>
                        {renderCellValue(key, item[key])}
                    </td>
                ) : null
            ))}
            <td>
                <button className="btn btn-info mr-1" onClick={() => editItem(item)}>Edit</button>
                <button className="btn btn-success mr-1" onClick={() => showItemDetails(item)}>Details</button>
                <button className="btn btn-danger" onClick={() => deleteItem(item)}>Delete</button>
            </td>
        </tr>
    );
};

export default TableRow;
