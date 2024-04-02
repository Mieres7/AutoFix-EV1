package vicente.mieres.autofix.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repair_discount")
public class RepairDiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long repaitDiscountId;

    private float gasolineCost;
    private float dieselCost;
    private float hybridCost;
    private float electricCost;
    
}
