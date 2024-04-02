package vicente.mieres.autofix.Entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "bonus_discount")
@NoArgsConstructor
@AllArgsConstructor
public class BonusDiscountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long bonusDiscountId;


    private int amount;
    private boolean available;
    private int discount;
    private String period;

    private Long brandId;



}
