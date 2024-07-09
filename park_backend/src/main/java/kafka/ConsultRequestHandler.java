package kafka;

import org.springframework.beans.factory.annotation.Autowired;

import DBEntity.ConsultTransaction;
import repository.ConsultTransactionRepository;
import java.time.LocalDateTime;

public class ConsultRequestHandler {
	
	@Autowired
	ConsultTransactionRepository Rep;
	
	public void handleConsultRequest(ConsultRequest obj) {
	
	try {
        ConsultTransaction trans = new ConsultTransaction();
        trans.setClientId(obj.getClientId());
        trans.setTransactionId(obj.getTransactionId());
        trans.setMediumType(obj.getMediumType());
        trans.setMediumValue(obj.getMediumValue());
        trans.setLicensePlate(obj.getLicensePlate());
        trans.setType(obj.getTransactionType());
        trans.setEpan(obj.getEpan());
        trans.setOperatorId(obj.getOperator());
        trans.setCellComputerId(obj.getCellComputer());
        trans.setFacilityId(obj.getFacilityId());
        trans.setAmount(obj.getAmount());
        trans.setCurrency(obj.getCurrency());
        trans.setVatType(obj.getVatType());
        trans.setVatValue(obj.getVatValue());
        trans.setTenant(obj.getTenant());
        trans.setDuration(obj.getDuration());
        trans.setTariffTimeStart(obj.getTariffTimeStart());
        trans.setTariffTimeEnd(obj.getTariffTimeEnd());
        trans.setArticleShortText(obj.getArticleShortText());
        trans.setCreatedAt(LocalDateTime.now());
        trans.setLicensePlate(obj.getLicensePlate());
        
        Rep.save(trans);
        
	} catch (Exception exception){
		exception.getMessage();
		
	}
}

}