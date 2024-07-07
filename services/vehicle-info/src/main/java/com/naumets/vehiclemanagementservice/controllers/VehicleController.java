package com.naumets.vehiclemanagementservice.controllers;

import java.util.Optional;


import com.naumets.vehiclemanagementservice.models.VehicleInformation;
import com.naumets.vehiclemanagementservice.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class VehicleController {
	
	@Autowired private VehicleService vehicleService;
//	@Autowired private VehicleTypeService vehicleTypeService;
//	@Autowired private VehicleMakeService vehicleMakeService;
//	@Autowired private VehicleModelService vehicleModelService;
//	@Autowired private LocationService locationService;
//	@Autowired private EmployeeService employeeService ;
//	@Autowired private VehicleStatusService vehicleStatusService;


	@GetMapping("vehicles")
	public String findAll(Model model){		
		model.addAttribute("vehicles", vehicleService.findAll());
//		model.addAttribute("vehicleTypes", vehicleTypeService.findAll());
//		model.addAttribute("vehicleModels", vehicleModelService.findAll());
//		model.addAttribute("vehicleMakes", vehicleMakeService.findAll());
//		model.addAttribute("locations", locationService.findAll());
//		model.addAttribute("employees", employeeService.findAll());
//		model.addAttribute("vehicleStatuses", vehicleStatusService.findAll());

		return "vehicle";
	}	
	
	@RequestMapping("vehicles/findById/")
	@ResponseBody
	public Optional<VehicleInformation> findById(Integer id)
	{
		return vehicleService.findById(id);
	}
	
	@PostMapping(value="vehicles/addNew")
	public String addNew(VehicleInformation vehicleInformation) {
		vehicleService.save(vehicleInformation);
		return "redirect:/vehicles";
	}	
	
	@RequestMapping(value="vehicles/update/", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleInformation vehicleInformation) {
		vehicleService.save(vehicleInformation);
		return "redirect:/vehicles";
	}
	
	@RequestMapping(value="vehicles/delete/", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		vehicleService.delete(id);
		return "redirect:/vehicles";
	}
}
