package vicente.mieres.autofix.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vicente.mieres.autofix.DTO.CreateRepair;
import vicente.mieres.autofix.Proyections.AverageTimeProyection;
import vicente.mieres.autofix.Proyections.RepairVehicleMotorProyection;
import vicente.mieres.autofix.Proyections.RepairVehicleTypeProyection;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Services.RepairService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/repair")
@CrossOrigin("*")
public class RepairController {
    
    @Autowired
    RepairService repairService;

    @PostMapping("/")
    public ResponseEntity<RepairEntity> saveRepair(@RequestBody CreateRepair repairData) {
        RepairEntity newRepair = repairService.saveRepair(repairData);
        return ResponseEntity.ok(newRepair);
    }

    @GetMapping("/{repairId}")
    public ResponseEntity<Float> getTotalCost(@PathVariable Long repairId) {
        float totalCost = repairService.getTotalCost(repairId);
        return ResponseEntity.ok(totalCost);
    }
    
    @GetMapping("/summary/type")
    public ResponseEntity<List<RepairVehicleTypeProyection>> getRepairTypeCost() {
        List<RepairVehicleTypeProyection> repairVehicleTypes = repairService.getRepairTypeCost();
        if (repairVehicleTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
            
        }
        return ResponseEntity.ok(repairVehicleTypes);
    }

    @GetMapping("/average")
    public ResponseEntity<List<AverageTimeProyection>> getAverageRepairCost(){
        List<AverageTimeProyection> averageTimeProyections = repairService.getAverageRepairTime();
        if (averageTimeProyections.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(averageTimeProyections);
    }

    @GetMapping("/summary/motor")
    public ResponseEntity<List<RepairVehicleMotorProyection>> getRepairMotorCost(){
        List<RepairVehicleMotorProyection> repairVehicleMotorProyections = repairService.getRepairMotorCost();
        if(repairVehicleMotorProyections.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(repairVehicleMotorProyections);
    }

    @PutMapping("/{repairId}")
    public ResponseEntity<RepairEntity> upadteRepair(@PathVariable Long repairId, @RequestBody RepairEntity repair){
        RepairEntity repairUpdated = repairService.updateRepair(repairId, repair);
        return ResponseEntity.ok(repairUpdated);
    }
}