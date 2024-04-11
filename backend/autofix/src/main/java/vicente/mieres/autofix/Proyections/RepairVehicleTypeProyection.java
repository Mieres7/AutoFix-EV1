package vicente.mieres.autofix.Proyections;

public interface RepairVehicleTypeProyection {
    String getRepairType();
    Long getSedans();
    Long getHatchbacks();
    Long getSuvs();
    Long getPickups();
    Long getVans();
    Double getTotalCost();
}
