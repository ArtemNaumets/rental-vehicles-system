import { API_URLS } from './apiConfig';

export const predefinedFields = {
    [API_URLS.vehicleMakes]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'description', label: 'Description', placeholder: 'Description' },
        { key: 'details', label: 'Details', placeholder: 'Details' },
    ],
    [API_URLS.vehicleModels]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'description', label: 'Description', placeholder: 'Description' },
        { key: 'details', label: 'Details', placeholder: 'Details' },
        
    ],
    [API_URLS.vehicleTypes]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'description', label: 'Description', placeholder: 'Description' },
        { key: 'details', label: 'Details', placeholder: 'Details' },
    ],
    [API_URLS.vehicleStatuses]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'description', label: 'Description', placeholder: 'Description' },
        { key: 'details', label: 'Details', placeholder: 'Details' },
    ],
    [API_URLS.employees]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'firstname', label: 'Firstname', placeholder: 'Enter Firstname' },
        { key: 'lastname', label: 'Lastname', placeholder: 'Enter Lastname' },
        { key: 'gender', label: 'Gender', placeholder: 'Enter Gender' },
        { key: 'othername', label: 'Othername', placeholder: 'Enter Othername' },
        { key: 'socialSecurityNumber', label: 'Social Security Number', placeholder: 'Enter Social Security Number' },
        { key: 'maritalStatus', label: 'Marital Status', placeholder: 'Enter Marital Status' },
        { key: 'countryid', label: 'Country', placeholder: 'Enter Country' },
        { key: 'stateid', label: 'State', placeholder: 'Enter State' },
        { key: 'dateOfBirth', label: 'Date of Birth', placeholder: 'Enter Date of Birth' },
        { key: 'city', label: 'City', placeholder: 'Enter City' },
        { key: 'address', label: 'Address', placeholder: 'Enter Address' },
        { key: 'phone', label: 'Phone', placeholder: 'Enter Phone' },
        { key: 'email', label: 'Email', placeholder: 'Enter Email' },
        { key: 'employeeTypeId', label: 'Employee Type', placeholder: 'Enter Employee Type' },
        { key: 'username', label: 'Username', placeholder: 'Enter Username' },
        { key: 'jobTitleId', label: 'Job Title', placeholder: 'Enter Job Title' },
        { key: 'hireDate', label: 'Hire Date', placeholder: 'Enter Hire Date' },

    ],
    [API_URLS.clients]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'name', label: 'Name', placeholder: 'Enter Name' },
        { key: 'address', label: 'Address', placeholder: 'Enter Address' },
        { key: 'city', label: 'City', placeholder: 'Enter City' },
        { key: 'countryid', label: 'Country', placeholder: 'Enter country' },
        { key: 'details', label: 'Details', placeholder: 'Enter Details' },
        { key: 'email', label: 'Email', placeholder: 'Enter Email' },
        { key: 'phone', label: 'Phone', placeholder: 'Enter Phone' },
        { key: 'stateid', label: 'State', placeholder: 'Enter State' },
        { key: 'website', label: 'Website', placeholder: 'Enter Website' },
    ],
    [API_URLS.suppliers]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'name', label: 'Name', placeholder: 'Enter Name' },
        { key: 'address', label: 'Address', placeholder: 'Enter Address' },
        { key: 'city', label: 'City', placeholder: 'Enter City' },
        { key: 'mobile', label: 'Mobile', placeholder: 'Enter Mobile' },
        { key: 'website', label: 'Website', placeholder: 'Enter Website' },
        { key: 'email', label: 'Email', placeholder: 'Enter Email' },
        { key: 'countryid', label: 'Country', placeholder: 'Enter Country' },
        { key: 'stateid', label: 'State', placeholder: 'Enter State' },
        { key: 'details', label: 'Details', placeholder: 'Enter Details' },
    ],
    [API_URLS.jobTitles]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'description', label: 'Description', placeholder: 'Enter Description' },
        { key: 'details', label: 'Details', placeholder: 'Enter Details' },
    ],
    [API_URLS.employeeTypes]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'description', label: 'Description', placeholder: 'Enter Description' },
        { key: 'details', label: 'Details', placeholder: 'Enter Details' },
    ],
    [API_URLS.invoices]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'invoiceDate', label: 'Invoice Date', placeholder: 'Enter Invoice Date' },
        { key: 'clientid', label: 'Client', placeholder: 'Enter Client' },
        { key: 'remarks', label: 'Remarks', placeholder: 'Enter Remarks' },
    ],
    [API_URLS.countries]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'code', label: 'Code', placeholder: 'Enter Code' },
        { key: 'description', label: 'Description', placeholder: 'Enter Description' },
    ],
    [API_URLS.states]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'name', label: 'Name', placeholder: 'Enter Name' },
        { key: 'code', label: 'Code', placeholder: 'Enter Code' },
        { key: 'countryid', label: 'Country', placeholder: 'Enter Country' },
        { key: 'details', label: 'Details', placeholder: 'Enter Details' },
    ],
    [API_URLS.locations]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'description', label: 'Description', placeholder: 'Enter Description' },
        { key: 'details', label: 'Details', placeholder: 'Enter Details' },
        { key: 'countryid', label: 'Country', placeholder: 'Enter Country' },
        { key: 'stateid', label: 'State', placeholder: 'Enter State' },
        { key: 'city', label: 'City', placeholder: 'Enter City' },
        { key: 'address', label: 'Address', placeholder: 'Enter Address' },

    ],

    //vehicles
    [API_URLS.vehicles]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'name', label: 'Name', placeholder: 'Enter Name' },
        { key: 'vehicleNumber', label: 'Vehicle Number', placeholder: 'Enter Vehicle Number' },
        { key: 'description', label: 'Description', placeholder: 'Enter Description' },
        { key: 'registrationDate', label: 'Registration Date', placeholder: 'Enter Registration Date' },
        { key: 'acquisitionDate', label: 'Acquisition Date', placeholder: 'Enter Acquisition Date' },
        { key: 'vehicletypeid', label: 'Vehicle Type ', placeholder: 'Enter Vehicle Type ' },
        { key: 'vehiclemakeid', label: 'Vehicle Make ', placeholder: 'Enter Vehicle Make ' },
        { key: 'power', label: 'Power', placeholder: 'Enter Power' },
        { key: 'fuelCapacity', label: 'Fuel Capacity', placeholder: 'Enter Fuel Capacity' },
        { key: 'vehiclestatusid', label: 'Vehicle Status', placeholder: 'Enter Vehicle Status' },
        { key: 'netWeight', label: 'Net Weight', placeholder: 'Enter Net Weight' },
        { key: 'employeeid', label: 'Employee', placeholder: 'Enter Employee' },
        { key: 'vehiclemodelid', label: 'Vehicle Model', placeholder: 'Enter Vehicle Model' },
        { key: 'locationid', label: 'Location', placeholder: 'Enter Location' },
        { key: 'remarks', label: 'Remarks', placeholder: 'Enter Remarks' }
    ],
    [API_URLS.vehicleMovements]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'vehicleid', label: 'Vehicle', placeholder: 'Enter Vehicle' },
        { key: 'locationid1', label: 'Location 1', placeholder: 'Enter Location 1' },
        { key: 'date1', label: 'Date 1', placeholder: 'Enter Date 1' },
        { key: 'locationid2', label: 'Location 2', placeholder: 'Enter Location 2' },
        { key: 'date2', label: 'Date 2', placeholder: 'Enter Date 2' },
        { key: 'remarks', label: 'Remarks', placeholder: 'Enter Remarks' }
        

    ],
    [API_URLS.vehicleMaintenances]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'vehicleid', label: 'Vehicle', placeholder: 'Enter Vehicle' },
        { key: 'startDate', label: 'Start Date', placeholder: 'Enter Start Date' },
        { key: 'supplierid', label: 'Supplier', placeholder: 'Enter Supplier' },
        { key: 'endDate', label: 'End Date', placeholder: 'Enter End Date' },
        { key: 'price', label: 'Price', placeholder: 'Enter Price' },
        { key: 'remarks', label: 'Remarks', placeholder: 'Enter Remarks' }        

    ],
    [API_URLS.vehicleHires]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'vehicleid', label: 'Vehicle', placeholder: 'Enter Vehicle' },
        { key: 'dateOut', label: 'Date Out', placeholder: 'Enter Date Out' },
        { key: 'clientid', label: 'Client', placeholder: 'Enter Client' },
        { key: 'locationid', label: 'Location', placeholder: 'Enter Location' },
        { key: 'timeOut', label: 'Time Out', placeholder: 'Enter Time Out' },
        { key: 'dateIn', label: 'Date In', placeholder: 'Enter Date In' },
        { key: 'timeIn', label: 'Time In', placeholder: 'Enter Time In' },
        { key: 'price', label: 'Price', placeholder: 'Enter Price' },
        { key: 'remarks', label: 'Remarks', placeholder: 'Enter Remarks' }
    ],
    [API_URLS.profile]: [
        { key: 'id', label: 'ID', placeholder: 'Enter ID' },
        { key: 'name', label: 'Name', placeholder: 'Enter Name' },
        { key: 'email', label: 'Email', placeholder: 'Enter Email' },
    ]
};
