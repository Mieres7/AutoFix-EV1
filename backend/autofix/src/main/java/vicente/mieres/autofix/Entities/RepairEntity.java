package vicente.mieres.autofix.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repair")
public class RepairEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long repairId;

    private float totalCost;
    private LocalDateTime checkInDateTime;
    private LocalDateTime checkOutDateTime;
    private LocalDateTime costumerDateTime;
    private boolean bonus;

    private Long repairTypeCostId;
    private Long kilometerChargeId;
    private Long ageChargeId;
    private Long repairDiscount;

    private Long costRecordId;


}
