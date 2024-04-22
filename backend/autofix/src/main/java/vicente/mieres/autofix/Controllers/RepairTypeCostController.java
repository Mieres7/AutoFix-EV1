package vicente.mieres.autofix.Controllers;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vicente.mieres.autofix.Entities.RepairTypeCostEntity;
import vicente.mieres.autofix.Services.RepairTypeCostService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/repair/type")
@CrossOrigin("*")
public class RepairTypeCostController {
    
    @Autowired
    RepairTypeCostService repairTypeCostService;

    @GetMapping("/")
    public ResponseEntity<List<RepairTypeCostEntity>> getRepairTypeCosts() {
        List<RepairTypeCostEntity> rtcList = repairTypeCostService.getRepairTypeCosts();
        return ResponseEntity.ok(rtcList);
    }
    


}
