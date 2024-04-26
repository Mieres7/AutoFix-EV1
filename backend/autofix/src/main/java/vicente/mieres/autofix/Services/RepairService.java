package vicente.mieres.autofix.Services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import vicente.mieres.autofix.DTO.CreateRepair;
import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Entities.VehicleRepairEntity;
import vicente.mieres.autofix.Repositories.RepairRepository;

@Service
public class RepairService {
    
    @Autowired
    RepairRepository repairRepository;
    @Autowired
    VehicleService vehicleService;
    @Autowired
    VehicleRepairService vehicleRepairService;
    @Autowired
    RepairValueService repairValueService;
    @Autowired
    CostRecordService costRecordService;

    public List<RepairEntity> getRepairs(){
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    public RepairEntity saveRepair(CreateRepair repairData){
        RepairEntity newRepair = new RepairEntity();

        boolean bonus = repairData.isBonus();
        System.out.println(bonus);
    
        Long vehicleId = repairData.getVehicleId();
        VehicleEntity vehicle = vehicleService.getVehicle(vehicleId);
        String manufactureYear = vehicle.getManufactureYear();
        int mileage = vehicle.getMileage();
        int repairs = vehicle.getRepairs() + 1;

        LocalDateTime checkInDateTime = LocalDateTime.now();
        int vehicleAge = checkInDateTime.getYear() - Integer.parseInt(manufactureYear);


        int repairType = repairData.getRepairType();
        Long repairTypeLong = (long) repairType;
        for(int i = 1; i <= 11; i++){
            if(repairType == i){
                newRepair.setRepairTypeCostId(repairTypeLong);
            }
        }

        if(repairs >= 0 && repairs <= 2)
            newRepair.setRepairDiscount(1L);
        else if(repairs >=3 && repairs <= 5)
            newRepair.setRepairDiscount(2L);
        else if(repairs >= 6 && repairs <= 9)
            newRepair.setRepairDiscount(3L);
        else if(repairs >= 10)
            newRepair.setRepairDiscount(4L);

        if(mileage >= 0 && mileage <= 5000)
            newRepair.setKilometerChargeId(1L);
        else if(mileage >=5001 && mileage <= 12000)
            newRepair.setKilometerChargeId(2L);
        else if(mileage >= 12001 && mileage <= 25000)
            newRepair.setKilometerChargeId(3L);
        else if(mileage >= 25001 && mileage <= 40000)
            newRepair.setKilometerChargeId(4L);
        else if(mileage > 40000)
            newRepair.setKilometerChargeId(5L);

        if(vehicleAge >= 0 && vehicleAge <= 5)
            newRepair.setAgeChargeId(1L);
    else if(vehicleAge >= 6 && vehicleAge <= 10)
            newRepair.setAgeChargeId(2L);
        else if(vehicleAge >= 11 && vehicleAge <= 15)
            newRepair.setAgeChargeId(3L);
        else if(vehicleAge >= 16)
            newRepair.setAgeChargeId(4L);

        newRepair.setBonus(bonus);
        newRepair.setCheckInDateTime(checkInDateTime);
        
        CostRecordEntity costRecord = new CostRecordEntity();
        costRecord.setVehicleId(vehicleId);
        costRecord = costRecordService.saveCostRecord(costRecord);
        Long costRecordId = costRecord.getCostRecordId();

        newRepair.setCostRecordId(costRecordId);

        newRepair = repairRepository.save(newRepair);

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity();
        Long repairId = newRepair.getRepairId();
        vehicleRepair.setRepairId(repairId); 
        vehicleRepair.setVehicleId(vehicleId);
        vehicleRepairService.saveVehicleRepair(vehicleRepair);

        return repairRepository.save(newRepair);
    }

    public RepairEntity getRepair(Long repairId){
        return repairRepository.findById(repairId).get();
    }

    public RepairEntity updateRepair(Long repairId, RepairEntity newRepair){

        RepairEntity repair = this.getRepair(repairId);

        if(newRepair.getAgeChargeId() != null)
            repair.setAgeChargeId(newRepair.getAgeChargeId());
        if(newRepair.getCheckInDateTime() != null)
            repair.setCheckInDateTime(newRepair.getCheckInDateTime());
        if(newRepair.getCheckOutDateTime() != null)
            repair.setCheckOutDateTime(newRepair.getCheckOutDateTime());
        if(newRepair.getCostumerDateTime() != null)
            repair.setCostumerDateTime(newRepair.getCostumerDateTime());
        if(newRepair.getKilometerChargeId() != null)
            repair.setKilometerChargeId(newRepair.getKilometerChargeId());
        if(newRepair.getRepairDiscount() != null)
            repair.setRepairDiscount(newRepair.getRepairDiscount());
        if(newRepair.getRepairTypeCostId() != null)
            repair.setRepairTypeCostId(newRepair.getRepairTypeCostId());
        if(newRepair.getTotalCost() > 0.0f)
            repair.setTotalCost(newRepair.getTotalCost());
        if(newRepair.getRepairId() != null)
            repair.setRepairId(newRepair.getRepairId());
        if(newRepair.isBonus())
            repair.setBonus(newRepair.isBonus());
        if (!newRepair.isBonus())
            repair.setBonus(!newRepair.isBonus());
        if(newRepair.getCostRecordId() != null)
            repair.setCostRecordId(newRepair.getCostRecordId());

        return repairRepository.save(repair);
    }


    public float getTotalCost(Long repairId){

        
        
        RepairEntity repair = this.repairRepository.findById(repairId).get();
        VehicleRepairEntity vehicleRepair = vehicleRepairService.getByRepairId(repairId);
        VehicleEntity vehicle = vehicleService.getVehicle(vehicleRepair.getVehicleId());
        
        
        String motorType = vehicle.getMotorType();
        String vehicleType = vehicle.getVehicleType();

         // costo de reparacion segun tipo de motor 
        Long typeCostId = repair.getRepairTypeCostId();
        int typeCost = repairValueService.getTypeCostValue(typeCostId, motorType);

        // dcto por # de reparaciones 
        Long repairDiscountId = repair.getRepairDiscount();
        float repairDiscount = repairValueService.getRepairDiscount(repairDiscountId, motorType);

        // cargo por edad
        // cargo por km            
        Long ageChargeId = repair.getAgeChargeId();
        Long kilometerChargeId = repair.getKilometerChargeId();
        System.out.println(ageChargeId);
        System.out.println(kilometerChargeId);
        List<Float> kilometerAgeCharge = repairValueService.getKilometerAgeCharge(kilometerChargeId, ageChargeId, vehicleType);
        
        // bonus
        Long brandId = vehicle.getBrand_id();
        int bonusDiscount = 0;
        if(repair.isBonus()){
            bonusDiscount = repairValueService.getBonusValue(brandId);
        }
        // descuento por dia de ingreso lunes y jueves creo ?¿ entre 9 y 12 am
        boolean attentionDayDiscount = false;
        LocalDateTime checkInTime = repair.getCheckInDateTime();
        if(checkInTime.getDayOfWeek().getValue() == 1 || checkInTime.getDayOfWeek().getValue() == 4){
            if(checkInTime.getHour() >= 9 && checkInTime.getHour() <= 12)
                attentionDayDiscount = true;
        }
    
        // cargo por dia de atraso
        LocalDateTime costumerDateTime = repair.getCostumerDateTime();
        LocalDateTime checkOutDateTime = repair.getCheckOutDateTime();
        Duration duration = Duration.between(costumerDateTime, checkOutDateTime); 

        Long daysBetween = duration.toDays();

        Long costRecordId = repair.getCostRecordId();

        float totalCost = repairValueService.getRepairValue(costRecordId, typeCost, repairDiscount, kilometerAgeCharge, bonusDiscount, attentionDayDiscount, daysBetween);
        repair.setTotalCost(totalCost);
        repairRepository.save(repair);

        return totalCost;
     }

    public List<Object[]> getCostRecords(){
        return repairRepository.getCostRecords();
    }

    public List<Object[]> getRepairTypeCost(){
         return repairRepository.getRepairTypeCost();
    }

    public List<Object[]> getAverageRepairTime(){
         return repairRepository.getAverageRepairTime();
    }

    public List<Object[]> getRepairMotorCost(){
         return repairRepository.getRepairMotorCost();
    }


}
