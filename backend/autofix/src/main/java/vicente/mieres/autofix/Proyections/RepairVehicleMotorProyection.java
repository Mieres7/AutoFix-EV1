package vicente.mieres.autofix.Proyections;

public interface RepairVehicleMotorProyection {
    String getRepairType();
    Long getGasoline();
    Long getDiesel();
    Long getHybrid();
    Long getElectric();
    Double getTotalCost();
}
