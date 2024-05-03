package vicente.mieres.autofix.Controllers;

import org.springframework.web.bind.annotation.RestController;

import vicente.mieres.autofix.DTO.CreateVehicle;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Services.VehicleService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/vehicle")
@CrossOrigin("*")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;
    
    @GetMapping("/")
    public ResponseEntity<List<VehicleEntity>> getVehicles() {
        List<VehicleEntity> vehicles = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicles);
    }
    
    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleEntity> getVehicle(@PathVariable Long vehicleId) {
        VehicleEntity vehicle = vehicleService.getVehicle(vehicleId);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/")
    public ResponseEntity<VehicleEntity> saveVehicle(@RequestBody CreateVehicle newVehicle) {
        VehicleEntity vehicle = vehicleService.saveVehicle(newVehicle);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long vehicleId){
         vehicleService.deleteVehicle(vehicleId);
         return ResponseEntity.ok().build();
    }

    
}
