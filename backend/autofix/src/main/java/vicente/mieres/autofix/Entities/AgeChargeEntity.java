package vicente.mieres.autofix.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "age_charge")
public class AgeChargeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long AgeChargeId;

    private float sedanCharge;
    private float hatchbackCharge;
    private float suvCharge;
    private float pickupCharge;
    private float vanCharge;
}
