package vicente.mieres.autofix.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.VehicleRepairEntity;
import vicente.mieres.autofix.Repositories.VehicleRepairRepository;

@Service
public class VehicleRepairService {

    @Autowired
    VehicleRepairRepository vehicleRepairRepository;
    
    public VehicleRepairEntity saveVehicleRepair(VehicleRepairEntity vehicleRepair){
        return vehicleRepairRepository.save(vehicleRepair);
    }

    public VehicleRepairEntity getByRepairId(Long repairId){
        return vehicleRepairRepository.findByRepairId(repairId);
    }

}
