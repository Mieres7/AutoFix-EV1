package vicente.mieres.autofix.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.DTO.AverageTimeDTO;
import vicente.mieres.autofix.DTO.CostRecordDTO;
import vicente.mieres.autofix.DTO.RepairMotorCostDTO;
import vicente.mieres.autofix.DTO.RepairTypeCostDTO;

@Service
public class ReportService {

    @Autowired
    RepairService repairService;
    
    public List<CostRecordDTO> costRecordModifier(){

        List<Object[]> costRecordWrong = repairService.getCostRecords();

        List<CostRecordDTO> costRecords = new ArrayList<>();
        for(Object[] costRecord : costRecordWrong){
            CostRecordDTO cr = new CostRecordDTO();
            cr.setCostRecordId((Long) costRecord[0]);
            cr.setBrandName((String) costRecord[1]);
            cr.setVehicleModel((String) costRecord[2]);
            cr.setRegistration((String)costRecord[3]);
            cr.setRepairCost((float)costRecord[4]);
            cr.setKilometerCharge((float) costRecord[5]);
            cr.setAgeCharge((float)costRecord[6]);
            cr.setLateCharge((float)costRecord[7]);
            cr.setAttentionDayDiscount((float)costRecord[8]);
            cr.setBonusDiscount((float)costRecord[9]);
            cr.setRepairCostOG((float)costRecord[10]);
            costRecords.add(cr);
        }
        return costRecords;
    }

    public List<RepairTypeCostDTO> repairTypeCostModifier(){
        List<Object[]> typeCostWrong = repairService.getRepairTypeCost();
        List<RepairTypeCostDTO> typeCostDTOs = new ArrayList<>();
        for(Object[] typeCost : typeCostWrong){
            RepairTypeCostDTO tc = new RepairTypeCostDTO();
            tc.setRepairType((String) typeCost[0]);
            tc.setSedans((Long)typeCost[1]);
            tc.setHatchbacks((Long)typeCost[2]);
            tc.setSuvs((Long)typeCost[3]);
            tc.setPickups((Long)typeCost[4]);
            tc.setVans((Long)typeCost[5]);
            tc.setTotalCost((float)typeCost[6]);
            typeCostDTOs.add(tc);
        }
        return typeCostDTOs;
    }

    public List<RepairMotorCostDTO> repairMotorCostModifier(){
        List<Object[]> motorCostWrong = repairService.getRepairMotorCost();
        List<RepairMotorCostDTO> motorCostDTOs = new ArrayList<>();
        for(Object[] motorCost : motorCostWrong){
            RepairMotorCostDTO mc = new RepairMotorCostDTO();
            mc.setRepairType((String)motorCost[0]);
            mc.setGasoline((Long)motorCost[1]);
            mc.setDiesel((Long)motorCost[2]);
            mc.setHybrid((Long)motorCost[3]);
            mc.setElectric((Long)motorCost[4]);
            mc.setTotalCost((float)motorCost[5]);
            motorCostDTOs.add(mc);
        }
        return motorCostDTOs;
    }

    public List<AverageTimeDTO> averageTimeModifier(){
        List<Object[]> averageTimeWrong = repairService.getAverageRepairTime();
        List<AverageTimeDTO> averageTimeDTOs = new ArrayList<>();
        for(Object[] averageTime : averageTimeWrong){
            AverageTimeDTO at = new AverageTimeDTO();
            at.setBrandName((String)averageTime[0]);
            at.setAverageRepairTime(((BigDecimal)averageTime[1]).doubleValue());
            averageTimeDTOs.add(at);
        }
        return averageTimeDTOs;
    }
}
