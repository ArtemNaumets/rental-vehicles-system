  import React, { useState } from 'react';
  import * as VehicleParameterService from '../../../../api/VehicleParameterService'; 
  import '../../../../assets/css/DeleteModal.css';

  const DeleteVehicleStatusesModal = ({ show, onClose, onDelete, selectedItem }) => {
      const [loading, setLoading] = useState(false);
      const [successMessage, setSuccessMessage] = useState('');
      const [errorMessage, setErrorMessage] = useState('');

      const deleteItem = async () => {
          setLoading(true);
          setErrorMessage('');
          try {
              await VehicleParameterService.deleteVehicleStatus(selectedItem.id); 
              setSuccessMessage('Item deleted successfully.');
              setTimeout(() => {
                  setSuccessMessage('');
                  onDelete(); 
                  onClose(); 
              }, 1500); 
          } catch (error) {
              setErrorMessage('Error deleting item.');
              console.error('Error deleting item:', error);
          } finally {
              setLoading(false);
          }
      };

      const handleKeyDown = (event) => {
          if (event.key === 'Enter') {
              event.preventDefault();
              if (!loading && !successMessage) {
                  deleteItem();
              }
          }
      };

      return (
          <div 
              className={`modal ${show ? 'show' : ''}`} 
              tabIndex="-1" 
              role="dialog" 
              style={{ display: show ? 'block' : 'none' }} 
              onKeyDown={handleKeyDown}
          >
              <div className="modal-dialog" role="document">
                  <div 
                      className="modal-content" 
                      tabIndex="0" 
                      onKeyDown={handleKeyDown}
                  >
                      <div className="modal-header">
                          <h5 className="modal-title">Delete Item</h5>
                          <button type="button" className="close" onClick={onClose}><span>&times;</span></button>
                      </div>
                      <div className="modal-body">
                          {loading ? (
                              <div className="spinner-container">
                                  <div className="spinner-border" role="status">
                                      <span className="sr-only">Loading...</span>
                                  </div>
                                  <p>Processing...</p>
                              </div>
                          ) : (
                              <>
                                  {successMessage ? (
                                      <div className="alert alert-success">{successMessage}</div>
                                  ) : (
                                      <>
                                          {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
                                          <p>Are you sure you want to delete the item: <strong>{selectedItem.id}</strong>?</p>
                                      </>
                                  )}
                              </>
                          )}
                      </div>
                      <div className="modal-footer">
                          {!loading && !successMessage && (
                              <>
                                  <button type="button" className="btn btn-secondary" onClick={onClose}>Cancel</button>
                                  <button type="button" className="btn btn-danger" onClick={deleteItem}>Delete</button>
                              </>
                          )}
                      </div>
                  </div>
              </div>
          </div>
      );
  };

  export default DeleteVehicleStatusesModal;
