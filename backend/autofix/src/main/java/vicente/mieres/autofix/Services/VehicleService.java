package vicente.mieres.autofix.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Repositories.VehicleRepository;

@Service
public class VehicleService {
    
    @Autowired
    VehicleRepository vehicleRepository;

    public ArrayList<VehicleEntity> getVehicles(){
        return (ArrayList<VehicleEntity>) vehicleRepository.findAll();
    }

    public VehicleEntity getVehicle(Long vehicleId){
        return vehicleRepository.findById(vehicleId).get();
    }

}
