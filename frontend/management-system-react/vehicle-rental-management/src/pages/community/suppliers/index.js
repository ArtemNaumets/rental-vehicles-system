import React, { useState, useEffect } from 'react';
import TableHeader from '../../../components/ui/TableHeader';
import TableRow from '../../../components/ui/TableRow';
import SearchBar from '../../../components/form/SearchBar';

import { getSuppliers } from '../../../api/CommunityService'; 

import { predefinedFields } from '../../../configs/fieldsConfig'; 
import { API_URLS } from '../../../configs/apiConfig';
import AddSuppliersModal from './modals/AddSuppliersModal';
import DetailsSuppliersModal from './modals/DetailsSuppliersModal';
import DeleteSuppliersModal from './modals/DeleteSuppliersModal';
import EditSuppliersModal from './modals/EditSuppliersModal';

const SuppliersPage = () => {
    const [loading, setLoading] = useState(false);
    const [showAddModal, setShowAddModal] = useState(false);
    const [showEditModal, setShowEditModal] = useState(false);
    const [showDetailsModal, setShowDetailsModal] = useState(false);
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [selectedItem, setSelectedItem] = useState({});
    const [searchTerm, setSearchTerm] = useState('');
    const [sortConfig, setSortConfig] = useState({ key: null, direction: 'ascending' });
    const [items, setItems] = useState([]);
    const [error, setError] = useState(null); 

    const fields = predefinedFields[API_URLS.suppliers] || [];

    const fetchItems = async () => {
        try {
            setLoading(true);
            setError(null); 
            const response = await getSuppliers();
            setItems(response);
        } catch (error) {
            setError('Error fetching items. ' + error.message); 
            console.error('Error fetching items:', error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        setItems([]); 
        fetchItems();
    }, []);

    const handleSearchChange = (value) => {
        setSearchTerm(value);
    };

    const sortBy = (key) => {
        let direction = 'ascending';
        if (sortConfig.key === key && sortConfig.direction === 'ascending') {
            direction = 'descending';
        }
        setSortConfig({ key, direction });
    };

    let sortedItems = [...items];
    if (sortConfig.key) {
        sortedItems.sort((a, b) => {
            if (a[sortConfig.key] < b[sortConfig.key]) {
                return sortConfig.direction === 'ascending' ? -1 : 1;
            }
            if (a[sortConfig.key] > b[sortConfig.key]) {
                return sortConfig.direction === 'ascending' ? 1 : -1;
            }
            return 0;
        });
    }

    const filteredItems = sortedItems.filter(item => {
        return Object.values(item).some(value =>
            value && value.toString().toLowerCase().includes(searchTerm.toLowerCase())
        );
    });

    const openEditModal = (item) => {
        setSelectedItem(item);
        setShowEditModal(true);
    };

    const openDetailsModal = (item) => {
        setSelectedItem(item);
        setShowDetailsModal(true);
    };

    const openDeleteModal = (item) => {
        setSelectedItem(item);
        setShowDeleteModal(true);
    };

    const closeModals = () => {
        setShowAddModal(false);
        setShowEditModal(false);
        setShowDetailsModal(false);
        setShowDeleteModal(false);
    };

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <h1 className="h2">Suppliers</h1>
                    <button className="btn btn-primary mb-3" onClick={() => setShowAddModal(true)}>Add New Supplier</button>
                    <SearchBar searchTerm={searchTerm} onSearchChange={handleSearchChange} />
                    {error && <div className="alert alert-danger p-5">{error}</div>} 
                    <table className="table table-striped table-advance table-hover">
                        <TableHeader
                            columns={fields}
                            sortConfig={sortConfig}
                            sortBy={sortBy}
                        />
                        <tbody>
                            {loading ? (
                                <tr>
                                    <td colSpan={fields.length + 1}>Loading...</td> 
                                </tr>
                            ) : filteredItems.length === 0 ? (
                                <tr>
                                    <td colSpan={fields.length + 1}><div className='alert alert-danger'>No results found.</div></td> 
                                </tr>
                            ) : (
                                filteredItems.map(item => (
                                    <TableRow
                                        key={item.id}
                                        item={item}
                                        editItem={() => openEditModal(item)}
                                        showItemDetails={() => openDetailsModal(item)}
                                        deleteItem={() => openDeleteModal(item)}
                                        fields={fields}
                                    />
                                ))
                            )}
                        </tbody>
                    </table>
                </div>
            </div>

            <AddSuppliersModal
                show={showAddModal}
                onClose={closeModals}
                onAdd={fetchItems}  
                fields={fields}
            />
            <EditSuppliersModal
                show={showEditModal}
                onClose={closeModals}
                onSave={fetchItems}  
                selectedItem={selectedItem}
                fields={fields}
            />
            <DetailsSuppliersModal
                show={showDetailsModal}
                onClose={closeModals}
                selectedItem={selectedItem}
                fields={fields}
            />
            <DeleteSuppliersModal
                show={showDeleteModal}
                onClose={closeModals}
                onDelete={fetchItems}  
                selectedItem={selectedItem}
            />
        </div>
    );
};

export default SuppliersPage;
