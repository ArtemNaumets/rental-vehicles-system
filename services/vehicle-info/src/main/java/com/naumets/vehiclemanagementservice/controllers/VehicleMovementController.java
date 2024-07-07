package com.naumets.vehiclemanagementservice.controllers.vehicle;

import java.util.Optional;

import com.naumets.vehiclemanagementservice.services.locations.LocationService;
import com.naumets.vehiclemanagementservice.services.vehicle.VehicleMovementService;
import com.naumets.vehiclemanagementservice.services.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naumets.vehiclemanagementservice.models.vehicle.VehicleMovement;

@Controller
public class VehicleMovementController {
	
	@Autowired private VehicleMovementService vehicleMovementService;
	@Autowired private LocationService locationService;
	@Autowired private VehicleService vehicleService;
	
	@GetMapping("vehicleMovements")
	public String findAll(Model model){		
		model.addAttribute("vehicleMovements", vehicleMovementService.findAll());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());

		return "movement";
	}	
	
	@RequestMapping("vehicleMovements/findById/")
	@ResponseBody
	public Optional<VehicleMovement> findById(Integer id)
	{
		return vehicleMovementService.findById(id);
	}
	
	@PostMapping(value="vehicleMovements/addNew")
	public String addNew(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/vehicleMovements";
	}	
	
	@RequestMapping(value="vehicleMovements/update/", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleMovement vehicleMovement) {
		vehicleMovementService.save(vehicleMovement);
		return "redirect:/vehicleMovements";
	}
	
	@RequestMapping(value="vehicleMovements/delete/", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleMovementService.delete(id);
		return "redirect:/vehicleMovements";
	}


}
