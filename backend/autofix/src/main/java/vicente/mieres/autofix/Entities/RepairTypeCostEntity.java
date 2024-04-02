package vicente.mieres.autofix.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repair_type_cost")
public class RepairTypeCostEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long repairTypeCostId;

    private String type;
    private String description;
    // All this attributes represents the cost of repair depending on the motor type of the vehicle
    private int gasolineCost;
    private int dieselCost;
    private int hybridCost;
    private int electricCost;
}
