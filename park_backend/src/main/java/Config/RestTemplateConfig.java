package Config;

import data_cache.data.CellComputerDetail;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.GeneralSecurityException;

/**
 * Created by kubanek.peter on 25. 4. 2022 for project ZRConnector.
 */
@Configuration
public class RestTemplateConfig {
    @Bean(name = "AppConfigRestTemplate")
    public static RestTemplate getRestTemplate() throws GeneralSecurityException {
        return new RestTemplate(getRequestFactory());
    }

    public RestTemplate getRestTemplateForTesting() throws GeneralSecurityException {
        return new RestTemplate(getRequestFactory());
    }

    private static HttpComponentsClientHttpRequestFactory getRequestFactory() throws GeneralSecurityException {
        // SSL
        TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
                NoopHostnameVerifier.INSTANCE);

        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("https", sslsf)
                        .register("http", new PlainConnectionSocketFactory())
                        .build();

        BasicHttpClientConnectionManager connectionManager =
                new BasicHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
                .setConnectionManager(connectionManager).build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        // timeouts
        requestFactory.setConnectTimeout(1000); //3000
        requestFactory.setReadTimeout(10000);
        return requestFactory;
    }

    public static final String HTTPS_VALUE = "https://";
    public static final String HTTP_VALUE = "http://";
    public static String getBaseUrl(CellComputerDetail cell) {
        String destination;

        if (cell.getUrl() == null || cell.getUrl().isEmpty()) {
            destination = cell.getIp4address() + ":" + cell.getPort();
            destination = HTTPS_VALUE + destination + "/";
        } else {
            destination = cell.getUrl();
            if ( !( destination.contains(HTTPS_VALUE) || destination.contains(HTTP_VALUE) )) {
                destination = HTTPS_VALUE + destination;
            }
            if ( !destination.endsWith("/")) {
                destination = destination +  "/";
            }
        }

        return destination;
    }
}
