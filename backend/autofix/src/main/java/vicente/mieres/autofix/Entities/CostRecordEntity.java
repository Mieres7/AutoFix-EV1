package vicente.mieres.autofix.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "cost_record")
@NoArgsConstructor
@AllArgsConstructor
public class CostRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long costRecordId;

    private Long vehicleId;

    private float repairCostOG;
    private float repairCost;

    private float kilometerCharge;
    private float ageCharge;
    private float lateCharge;

    private float repairsDiscount;
    private float attentionDayDiscount;
    private float bonusDiscount;

}
