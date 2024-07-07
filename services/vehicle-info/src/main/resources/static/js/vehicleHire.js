/**
 * 
 */

$('document').ready(function() {	
	$('.table #editButton').on('click',function(event){		
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicleHire, status){
			$('#idEdit').val(vehicleHire.id);
			$('#ddlClientEdit').val(vehicleHire.clientid);			
			
			var dateIn = vehicleHire.dateIn.substr(0,10);
			var dateOut = vehicleHire.dateOut.substr(0,10);
			$('#dateInEdit').val(dateIn);
			$('#dateOutEdit').val(dateOut);
			
			$('#timeInEdit').val(vehicleHire.timeIn);
			$('#timeOutEdit').val(vehicleHire.timeOut);
			
			$('#ddlLocationEdit').val(vehicleHire.locationid);
			$('#priceEdit').val(vehicleHire.price);
			$('#remarksEdit').val(vehicleHire.remarks);
			$('#ddlVehicleEdit').val(vehicleHire.vehicleid);			
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicleHire, status){
			$('#idDetails').val(vehicleHire.id);
			$('#ddlClientDetails').val(vehicleHire.clientid);

			var dateIn = vehicleHire.dateIn.substr(0,10);
			var dateOut = vehicleHire.dateOut.substr(0,10);
			$('#dateInDetails').val(dateIn);
			$('#dateOutDetails').val(dateOut);

			$('#timeInDetails').val(vehicleHire.timeIn);
			$('#timeOutDetails').val(vehicleHire.timeOut);

			$('#ddlLocationDetails').val(vehicleHire.locationid);
			$('#priceDetails').val(vehicleHire.price);
			$('#remarksDetails').val(vehicleHire.remarks);
			$('#ddlVehicleDetails').val(vehicleHire.vehicleid);
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