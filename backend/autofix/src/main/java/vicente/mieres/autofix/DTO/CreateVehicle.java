package vicente.mieres.autofix.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateVehicle {

    private String registration;
    private String model;
    private String vehicleType;
    private String manufactureYear;
    private String motorType;
    private int seats;
    private int mileage;
    private Long brandId;
}
