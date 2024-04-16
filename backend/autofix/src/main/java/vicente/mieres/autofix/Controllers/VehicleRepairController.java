package vicente.mieres.autofix.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vicente.mieres.autofix.Entities.VehicleRepairEntity;
import vicente.mieres.autofix.Services.VehicleRepairService;


@RestController
@RequestMapping("/vehicleRepair")
@CrossOrigin("*")
public class VehicleRepairController {
    
    @Autowired
    VehicleRepairService vehicleRepairService;

    @PostMapping("/")
    public ResponseEntity<VehicleRepairEntity> saveVehicleRepair(@RequestBody VehicleRepairEntity vehicleRepair){
        VehicleRepairEntity newVehicleRepair = vehicleRepairService.saveVehicleRepair(vehicleRepair);
        return ResponseEntity.ok(newVehicleRepair);

    }

}
