package vicente.mieres.autofix.Controllers;

import org.springframework.web.bind.annotation.RestController;


import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/vehicle")
@CrossOrigin("*")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleEntity> getVehicle(@PathVariable Long vehicleId) {
        VehicleEntity vehicle = vehicleService.getVehicle(vehicleId);
        return ResponseEntity.ok(vehicle);
    }
    
    
    
}
