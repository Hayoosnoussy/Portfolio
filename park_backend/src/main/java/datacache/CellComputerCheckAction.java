package datacache;

import data_cache.data.CellComputerDetail;
import data_cache.data.CellComputersDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import dataUtils.CorrelationId;

import java.util.*;
import java.util.stream.Collectors;

import static com.schiedtandbachmann.ParkApplication.LOGGER_NAME;
import static dataUtils.CorrelationId.CORRELATION_ID;


@Component
public class CellComputerCheckAction {
    private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ":CellComputerDetailCheckAction");
    @Value("${appConfig.baseUrl}")
    private String appConfigBaseUrl;

    @Value("${appConfig.appId}")
    private int appId;

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, CellComputerDetail> getCellComputers() {
        logger.debug("Method called: getCellComputers for appId {} returns Map<String, CellComputerDetail>", appId);
        Map<String,CellComputerDetail> map = null;
        List<CellComputerDetail> cells = getCellComputerDetailsList(appId);

        if (cells != null) {
            map = cells.parallelStream()
                    .filter(this::checkCellComputerDetail)
                    .collect(Collectors.toMap(CellComputersCache::getCellKey, item -> item));
            logger.warn("Method getCellComputers: CellComputers data from AppConfig for appId {}, count for cash: {}", appId, map.size());
        } else {
            logger.warn("Method getCellComputers: Wrong CellComputers data from AppConfig for appID {}!", appId);
        }
        logger.debug("Method finished: getCellComputers for appId {} returns Map<String, CellComputerDetail>:{}", appId, map);
        return map;
    }

    private boolean checkCellComputerDetail(CellComputerDetail cell) {
        logger.debug("Method called: checkCellComputerDetail with params - CellComputerDetail:{} returns boolean", cell);

        return cell != null
                && cell.getOperatorData() != null && cell.getOperatorData().getOperatorId() != null && cell.getOperatorData().getOperatorId() > 0
                && cell.getCellComputer() != null && cell.getCellComputer() > 0

                && (
                (cell.getIp4address() != null && !cell.getIp4address().isEmpty() &&
                 cell.getPort() != null && cell.getPort() > 0)
                 || (cell.getUrl() != null && !cell.getUrl().isEmpty()))

                && cell.getUserName() != null && !cell.getUserName().isEmpty()
                && cell.getPassword() != null && !cell.getPassword().isEmpty();
    }

    private List<CellComputerDetail> getCellComputerDetailsList(int appId) {
        logger.info("Method called: getCellComputerDetailsList for appId {} returns List<CellComputerDetail>", appId);
        final String uri = appConfigBaseUrl+"/cellComputersDetailList/"+appId;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.set("X-SampB-SelfCheckout-App", "SampB-SelfCheckout-2021");
        headers.set(CORRELATION_ID, CorrelationId.getCorrelationId());
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Map<String, String> params = new HashMap<>();

        try {
            ResponseEntity<CellComputersDetail> response = restTemplate.exchange(
                    uri, HttpMethod.GET, entity,
                    CellComputersDetail.class,
                    params);

            if (response.getStatusCode() == HttpStatus.OK) {
                CellComputersDetail result = response.getBody();

                if (result != null && result.getCellComputers() != null) {
                    logger.info("Method getCellComputerDetailsList: Exiting getCellComputerDetailsList for appId {} with data, count of items: {}", appId, result.getCellComputers().size());
                    return result.getCellComputers();
                } else {
                    logger.warn("Method getCellComputerDetailsList: Exiting getCellComputerDetailsList for appId {} with empty data", appId);
                    return new ArrayList<>();
                }
            }
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            ex.printStackTrace();
        }

        logger.warn("Method finished: getCellComputerDetailsList for appID {} returns List<CellComputerDetail>:{}", appId, "null");
        return Collections.emptyList();
    }
}
