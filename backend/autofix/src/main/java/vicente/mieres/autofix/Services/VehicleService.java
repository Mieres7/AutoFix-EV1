package vicente.mieres.autofix.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.DTO.CreateVehicle;
import vicente.mieres.autofix.Entities.VehicleEntity;
import vicente.mieres.autofix.Repositories.VehicleRepository;

@Service
public class VehicleService {
    
    @Autowired
    VehicleRepository vehicleRepository;

    public void deleteVehicle(Long VehicleId){
        vehicleRepository.deleteById(VehicleId);
    }


    public VehicleEntity saveVehicle(CreateVehicle vehicleData) {
        VehicleEntity newVehicle = new VehicleEntity();
        
        Long brandid = (Long) vehicleData.getBrandId();
        System.out.println(vehicleData);
        newVehicle.setBrand_id(brandid);
        newVehicle.setManufactureYear(vehicleData.getManufactureYear());
        newVehicle.setMileage(vehicleData.getMileage());
        newVehicle.setModel(vehicleData.getModel());
        newVehicle.setMotorType(vehicleData.getMotorType());
        newVehicle.setRepairs(0);
        newVehicle.setSeats(vehicleData.getSeats());
        newVehicle.setVehicleType(vehicleData.getVehicleType());
    

        String registration = vehicleData.getRegistration();
        String expression = "^[A-Z]{4}\\d{2}$";
        if(registration.matches(expression)){
            if(!vehicleRepository.existsByRegistration(registration))
                newVehicle.setRegistration(registration);
        }

        return vehicleRepository.save(newVehicle);
    }

    public ArrayList<VehicleEntity> getVehicles(){
        return (ArrayList<VehicleEntity>) vehicleRepository.findAll();
    }

    public VehicleEntity getVehicle(Long VehicleId){
        return vehicleRepository.findById(VehicleId).get();
    }
    
    public boolean findByRegistration(String registration){
        return vehicleRepository.existsByRegistration(registration);
    }

}
