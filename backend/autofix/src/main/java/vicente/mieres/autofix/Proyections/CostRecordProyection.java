package vicente.mieres.autofix.Proyections;

public interface CostRecordProyection {
    Long getCostRecordId();
    String getBrandName();
    String getVehicleModel();
    String getRegistration();
    Double getRepairCost();
    Double getKilometerCharge();
    Double getAgeCharge();
    Double getLateCharge();
    Double getRepairsDiscount();
    Double getAttentionDayDiscount();
    Double getBonusDiscount();
    Double getRepairCostOG();
}
