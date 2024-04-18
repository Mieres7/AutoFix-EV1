package vicente.mieres.autofix.Services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import jakarta.transaction.Transactional;
import vicente.mieres.autofix.DTO.AverageTimeDTO;
import vicente.mieres.autofix.DTO.CostRecordDTO;
import vicente.mieres.autofix.DTO.RepairMotorCostDTO;
import vicente.mieres.autofix.DTO.RepairTypeCostDTO;
import vicente.mieres.autofix.Repositories.CostRecordRepository;

@SpringBootTest
@Transactional
public class ReportServiceTest {

    @MockBean
    private CostRecordRepository costRecordRepository;
    @MockBean
    private RepairService repairService;
    @Autowired
    private ReportService reportService;

    @Test
    public void costRecordModifier_ShouldConvertRawDataToDTOs() {
        // Given
        List<Object[]> mockData = new ArrayList<>();
        Object[] recordData = new Object[12];
        recordData[0] = 1L; // CostRecordId
        recordData[1] = "Toyota"; // BrandName
        recordData[2] = "Corolla"; // VehicleModel
        recordData[3] = "XYZ123"; // Registration
        recordData[4] = 1500.0f; // RepairCost
        recordData[5] = 100.0f; // KilometerCharge
        recordData[6] = 50.0f; // AgeCharge
        recordData[7] = 20.0f; // LateCharge
        recordData[8] = 5.0f; // RepairsDiscount
        recordData[9] = 10.0f; // AttentionDayDiscount
        recordData[10] = 5.0f; // BonusDiscount
        recordData[11] = 1600.0f; // RepairCostOG
        mockData.add(recordData);

        when(repairService.getCostRecords()).thenReturn(mockData);

        // When
        List<CostRecordDTO> result = reportService.costRecordModifier();

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getBrandName()).isEqualTo("Toyota");
        assertThat(result.get(0).getVehicleModel()).isEqualTo("Corolla");
        // Assert other fields similarly

        verify(repairService).getCostRecords();  // Optionally verify interaction
    }

    @Test
    public void repairTypeCostModifier_ShouldConvertRawDataToDTOs() {
        // Preparar los datos mock
        List<Object[]> mockData = new ArrayList<>();
        Object[] typeCostData = new Object[7];
        typeCostData[0] = "Brake Replacement"; // RepairType
        typeCostData[1] = 10L; // Sedans
        typeCostData[2] = 5L; // Hatchbacks
        typeCostData[3] = 15L; // SUVs
        typeCostData[4] = 3L; // Pickups
        typeCostData[5] = 2L; // Vans
        typeCostData[6] = 2500.0f; // TotalCost
        mockData.add(typeCostData);

        when(repairService.getRepairTypeCost()).thenReturn(mockData);

        // Ejecución del método bajo prueba
        List<RepairTypeCostDTO> result = reportService.repairTypeCostModifier();

        // Verificaciones
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getRepairType()).isEqualTo("Brake Replacement");
        assertThat(result.get(0).getSedans()).isEqualTo(10L);
        assertThat(result.get(0).getTotalCost()).isEqualTo(2500.0f);

        verify(repairService).getRepairTypeCost();
    }   
    @Test
    public void repairMotorCostModifier_ShouldConvertRawDataToDTOs() {
        // Preparar los datos mock
        List<Object[]> mockData = new ArrayList<>();
        Object[] motorCostData = new Object[6];
        motorCostData[0] = "Engine Repair"; // RepairType
        motorCostData[1] = 20L; // Gasoline
        motorCostData[2] = 10L; // Diesel
        motorCostData[3] = 5L; // Hybrid
        motorCostData[4] = 3L; // Electric
        motorCostData[5] = 3000.0f; // TotalCost
        mockData.add(motorCostData);

        when(repairService.getRepairMotorCost()).thenReturn(mockData);

        // Ejecución del método bajo prueba
        List<RepairMotorCostDTO> result = reportService.repairMotorCostModifier();

        // Verificaciones
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getRepairType()).isEqualTo("Engine Repair");
        assertThat(result.get(0).getGasoline()).isEqualTo(20L);
        assertThat(result.get(0).getTotalCost()).isEqualTo(3000.0f);

        verify(repairService).getRepairMotorCost();
    }
    @Test
    public void averageTimeModifier_ShouldConvertRawDataToDTOs() {
        // Preparar los datos mock
        List<Object[]> mockData = new ArrayList<>();
        Object[] averageTimeData = new Object[2];
        averageTimeData[0] = "Toyota"; // BrandName
        averageTimeData[1] = new BigDecimal("12.5"); // AverageRepairTime
        mockData.add(averageTimeData);

        when(repairService.getAverageRepairTime()).thenReturn(mockData);

        // Ejecución del método bajo prueba
        List<AverageTimeDTO> result = reportService.averageTimeModifier();

        // Verificaciones
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getBrandName()).isEqualTo("Toyota");
        assertThat(result.get(0).getAverageRepairTime()).isEqualTo(12.5);

        verify(repairService).getAverageRepairTime();
    }
}   
