package vicente.mieres.autofix.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AverageTimeDTO {

    private String brandName;
    private Double averageRepairTime;
}
