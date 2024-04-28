package vicente.mieres.autofix.Controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import vicente.mieres.autofix.Entities.VehicleRepairEntity;
import vicente.mieres.autofix.Services.VehicleRepairService;

@WebMvcTest(VehicleRepairController.class)
public class VehicleRepairControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VehicleRepairService vehicleRepairService;

    @Test
    public void saveVehicleRepair_ShouldReturnSavedVehicleRepair() throws Exception{

        VehicleRepairEntity vehicleRepair = new VehicleRepairEntity(1L, 1L, 1L);

        given(vehicleRepairService.saveVehicleRepair(Mockito.any(VehicleRepairEntity.class))).willReturn(vehicleRepair);

        VehicleRepairEntity newVehicleRepair = new VehicleRepairEntity();
        newVehicleRepair.setRepairId(1L);
        newVehicleRepair.setVehicleId(1L);
        newVehicleRepair.setVehicleRepairId(1L);

        ObjectMapper objectMapper = new ObjectMapper();
        String newVehicleRepairJson = objectMapper.writeValueAsString(newVehicleRepair);


        mockMvc.perform(post("/vehicleRepair/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newVehicleRepairJson))
                .andExpect(status().isOk());

    }

    @Test 
    public void getAll_ShouldReturnAll() throws Exception{
        VehicleRepairEntity vr = new VehicleRepairEntity();
        vr.setVehicleRepairId(1L);

        List<VehicleRepairEntity> list = new ArrayList<>();
        list.add(vr);

        given(vehicleRepairService.getVehicleRepairs()).willReturn(list);
        mockMvc.perform(get("/vehicleRepair/")).andExpect(status().isOk());


    }

}
