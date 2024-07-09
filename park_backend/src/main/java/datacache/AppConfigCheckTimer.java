package datacache;

import data_cache.data.CellComputerDetail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import dataUtils.CorrelationId;

import static com.schiedtandbachmann.ParkApplication.LOGGER_NAME;

import java.util.Map;


@Configuration
@EnableScheduling
public class AppConfigCheckTimer {
    private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ":AppConfigCheckTimer");
    @Autowired
    private CellComputerCheckAction actionCellComputer;

    @Value("${scheduler.cellComputerCheck.enable}")
    private boolean checkEnable;
    private static final int PERIODS_MIN = 1;

    //@Scheduled(cron = "0 15 * * * ?")
    //@Scheduled(cron = "${scheduler.cellComputerCheck.cron}")
    @Scheduled(fixedRate = 1000*60* PERIODS_MIN)
    public void scheduleTaskUsingCronExpression() {
        logger.debug("Method called: scheduleTaskUsingCronExpression returns void");
        long now = System.currentTimeMillis()/ 100;  // / 1000
        CorrelationId.setCorrelationId("CellComputerCheckHandling_"+now);  // in 10 ms /0.1 s
        if (!checkEnable) {
            logger.debug("Method scheduleTaskUsingCronExpression: APP_CONFIG-CHECK HANDLING cron job {} not enabled", CorrelationId.getCorrelationId());
            return;
        }
        logger.debug("Method scheduleTaskUsingCronExpression: APP_CONFIG-CHECK HANDLING cron job {} started  ..", CorrelationId.getCorrelationId());

        try {
            Map<String, CellComputerDetail> cellComputersData = actionCellComputer.getCellComputers();
            if (cellComputersData != null) {
                CellComputersCache cashData = CellComputersCache.getCellComputersCache();
                cashData.putAllNew(cellComputersData);
                logger.warn("Method scheduleTaskUsingCronExpression: CellComputers added in to CellComputerCash!");
            } else {
                logger.warn("Method scheduleTaskUsingCronExpression: No CellComputers to add in to CellComputerCash!");
            }
        } catch (Exception e) {
            logger.error("Method scheduleTaskUsingCronExpression: Exception by AppConfigCheckTimer: {}", e.getMessage());
            e.printStackTrace();
        }
        logger.debug("Method finished: scheduleTaskUsingCronExpression returns void with correlationId:{}", CorrelationId.getCorrelationId());
    }
}
