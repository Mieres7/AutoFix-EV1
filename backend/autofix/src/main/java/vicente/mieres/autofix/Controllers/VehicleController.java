package vicente.mieres.autofix.Controllers;

import org.springframework.web.bind.annotation.RestController;

import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/vehicle")
@CrossOrigin("*")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/")
    public ResponseEntity<VehicleEntity> getVehicle(@RequestParam Long vehicleId) {
        VehicleEntity vehicle = vehicleService.getVehicle(vehicleId);
        return ResponseEntity.ok(vehicle);
    }
    
    
    
}
