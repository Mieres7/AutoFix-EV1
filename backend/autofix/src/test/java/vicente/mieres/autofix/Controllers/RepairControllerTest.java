package vicente.mieres.autofix.Controllers;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import vicente.mieres.autofix.DTO.CreateRepair;
import vicente.mieres.autofix.Entities.RepairEntity;
import vicente.mieres.autofix.Services.RepairService;


import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RepairController.class)
public class RepairControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RepairService repairService;

    @Test
    public void saveRepair_ShouldReturnSavedRepair() throws Exception{


        LocalDateTime saveDateTime = LocalDateTime.of(2024,4,12,12,0);
        RepairEntity savedRepair = new RepairEntity(1L, 0, saveDateTime, null, null, true, 1L, 2L, 1L, 1L, 2L);
        
        given(repairService.saveRepair(Mockito.any(CreateRepair.class))).willReturn(savedRepair);

        CreateRepair createRepair = new CreateRepair();
        createRepair.setBonus(true);
        createRepair.setRepairType(1);
        createRepair.setVehicleId(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String createRepairJson = objectMapper.writeValueAsString(createRepair);

        mockMvc.perform(post("/repair/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createRepairJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bonus", is(true)));
    }

    @Test 
    public void getTotalCost_shouldReturnTotalCost() throws Exception{
        float totalCost = (float) 155414.0;

        given(repairService.getTotalCost(1L)).willReturn(totalCost);

        mockMvc.perform(get("/repair/{id}", 1L))
                .andExpect(status().isOk());
                
    }

    @Test
    public void updateRepair_ShouldReturnUpdatedRepair() throws Exception{

        LocalDateTime saveDateTime = LocalDateTime.of(2024,4,12,12,0);

        RepairEntity updateRepair = new RepairEntity(1L, 0, saveDateTime, null, null, true, 1L, 2L, 1L, 1L, 2L);
    
        given(repairService.updateRepair(eq(1L), Mockito.any(RepairEntity.class))).willReturn(updateRepair);

        String repairJson = """
                {
                    "repairId": 1,
                    "totalCost": 0.0,
                    "checkInDateTime": "2024-04-12T12:00:00",
                    "checkOutDateTime": "2024-04-12T12:00:00",
                    "costumerDateTime": "2024-04-12T12:00:00",
                    "bonus": true,
                    "repairTypeCostId": 1,
                    "kilometerChargeId": 2,
                    "ageChargeId": 1,
                    "repairDiscount": 1,
                    "costRecord": 2
                }
                """;

        mockMvc.perform(put("/repair/1" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(repairJson))
                .andExpect(status().isOk());
        
    }
    

}