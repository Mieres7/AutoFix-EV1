package vicente.mieres.autofix.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vicente.mieres.autofix.DTO.AverageTimeDTO;
import vicente.mieres.autofix.DTO.CostRecordDTO;
import vicente.mieres.autofix.DTO.RepairMotorCostDTO;
import vicente.mieres.autofix.DTO.RepairTypeCostDTO;
import vicente.mieres.autofix.Services.ReportService;

import java.util.List;

@RestController
@RequestMapping("/report")
@CrossOrigin("*")
public class ReportController {
    
    @Autowired
    ReportService reportService;

    @GetMapping("/cost/record")
    public ResponseEntity<List<CostRecordDTO>> getCostRecords(){
        List<CostRecordDTO> costRecordProyections = reportService.costRecordModifier();
        return ResponseEntity.ok(costRecordProyections);
    }

    @GetMapping("/summary/type")
    public ResponseEntity<List<RepairTypeCostDTO>> getRepairTypeCost() {
         List<RepairTypeCostDTO> repairVehicleTypes = reportService.repairTypeCostModifier();
         return ResponseEntity.ok(repairVehicleTypes);
    }

    @GetMapping("/average")
    public ResponseEntity<List<AverageTimeDTO>> getAverageRepairCost(){
         List<AverageTimeDTO> averageTimeProyections = reportService.averageTimeModifier();
         return ResponseEntity.ok(averageTimeProyections);
    }

    @GetMapping("/summary/motor")
    public ResponseEntity<List<RepairMotorCostDTO>> getRepairMotorCost(){
         List<RepairMotorCostDTO> repairVehicleMotorProyections = reportService.repairMotorCostModifier();
         return ResponseEntity.ok(repairVehicleMotorProyections);
    }

}
