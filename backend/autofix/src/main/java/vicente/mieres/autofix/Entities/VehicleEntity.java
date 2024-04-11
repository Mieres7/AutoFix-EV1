package vicente.mieres.autofix.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "vehicle")
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long vehicleId;
    
    private String registration;
    private String model;
    private String vehicleType;
    private String manufactureYear;
    private String motorType;
    private int seats;
    private int mileage;
    private int repairs;

    private Long brand_id;


}
