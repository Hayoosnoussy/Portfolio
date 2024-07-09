package dataUtils;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * Created by kubanek.peter on 15. 3. 2022 for project ZRConnector.
 */
public class CorrelationId {
    public static final String CORRELATION_ID = "correlationId";

    private CorrelationId() {}

    public static String setCorrelationId(String correlationId) {
        correlationId = (correlationId != null && !correlationId.isEmpty() ? correlationId : getUUID());
        MDC.put(CORRELATION_ID, correlationId);
        return correlationId;
    }
    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String getCorrelationId() {
        return MDC.get(CORRELATION_ID);
    }
}
