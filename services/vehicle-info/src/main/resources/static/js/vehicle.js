/**
 * 
 */

$('document').ready(function() {	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicle, status){
			var acDate = vehicle.acquisitionDate.substr(0,10);
			$('#txtAcquisitionDateEdit').val(acDate);
			$('#txtDescriptionEdit').val(vehicle.description);
			$('#ddlEmployeeEdit').val(vehicle.employeeid);
			$('#txtFuelCapacityEdit').val(vehicle.fuelCapacity);
			$('#txtIdEdit').val(vehicle.id);
			$('#ddlLocationEdit').val(vehicle.locationid);
			$('#txtNameEdit').val(vehicle.name);
			$('#txtNetWeightEdit').val(vehicle.netWeight);
			$('#txtPowerEdit').val(vehicle.power);
			var regDate = vehicle.registrationDate.substr(0,10);
			$('#txtRegistrationDateEdit').val(regDate);
			$('#txtRemarksEdit').val(vehicle.remarks);
			$('#ddlVehicleMakeEdit').val(vehicle.vehiclemakeid);		
			$('#ddlVehicleModelEdit').val(vehicle.vehiclemodelid);			
			$('#txtVehicleNumberEdit').val(vehicle.vehicleNumber);			
			$('#ddlVehicleStatusEdit').val(vehicle.vehiclestatusid);			
			$('#ddlVehicleTypeEdit').val(vehicle.vehicletypeid);	
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicle, status){
			var acDate = vehicle.acquisitionDate.substr(0,10);
			$('#txtAcquisitionDateDetails').val(acDate);
			$('#txtDescriptionDetails').val(vehicle.description);
			$('#ddlEmployeeDetails').val(vehicle.employeeid);
			$('#txtFuelCapacityDetails').val(vehicle.fuelCapacity);
			$('#txtIdDetails').val(vehicle.id);
			$('#ddlLocationDetails').val(vehicle.locationid);
			$('#txtNameDetails').val(vehicle.name);
			$('#txtNetWeightDetails').val(vehicle.netWeight);
			$('#txtPowerDetails').val(vehicle.power);
			var regDate = vehicle.registrationDate.substr(0,10);
			$('#txtRegistrationDateDetails').val(regDate);
			$('#txtRemarksDetails').val(vehicle.remarks);
			$('#ddlVehicleMakeDetails').val(vehicle.vehiclemakeid);
			$('#ddlVehicleModelDetails').val(vehicle.vehiclemodelid);
			$('#txtVehicleNumberDetails').val(vehicle.vehicleNumber);
			$('#ddlVehicleStatusDetails').val(vehicle.vehiclestatusid);
			$('#ddlVehicleTypeDetails').val(vehicle.vehicletypeid);
		});			
		$('#detailsModal').modal();		
	});	
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});	
	

});