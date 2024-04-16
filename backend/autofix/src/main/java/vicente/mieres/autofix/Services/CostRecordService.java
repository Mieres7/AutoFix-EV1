package vicente.mieres.autofix.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vicente.mieres.autofix.Entities.CostRecordEntity;
import vicente.mieres.autofix.Repositories.CostRecordRepository;

@Service
public class CostRecordService {
    @Autowired
    CostRecordRepository costRecordRepository;

    public CostRecordEntity saveCostRecord(CostRecordEntity costRecord){
        return costRecordRepository.save(costRecord);
    }

    public CostRecordEntity getCostRecord(Long costRecordId){
        return costRecordRepository.findById(costRecordId).get();
    }

    public ArrayList<CostRecordEntity> getCostRecords(){
        return (ArrayList<CostRecordEntity>) costRecordRepository.findAll();
    }

    public CostRecordEntity setCostRecord(Long costRecordId, float repairValueOG,float repairValue, float kilometerCharge, float ageCharge, float daysBetweenValue, float repairDiscount, float attentionDayDiscount, float bonusDiscount){
        CostRecordEntity costRecord = this.getCostRecord(costRecordId);

        costRecord.setRepairCostOG(repairValueOG);
        costRecord.setAgeCharge(ageCharge);
        costRecord.setAttentionDayDiscount(attentionDayDiscount);
        costRecord.setBonusDiscount(bonusDiscount);
        costRecord.setKilometerCharge(kilometerCharge);
        costRecord.setLateCharge(daysBetweenValue);
        costRecord.setRepairCost(repairValue);
        costRecord.setRepairsDiscount(repairDiscount);

        return costRecordRepository.save(costRecord);
    }



}

