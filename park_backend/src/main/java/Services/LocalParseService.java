package Services;

import java.util.Locale;

import org.springframework.stereotype.Service;
@Service
public class LocalParseService {
	 public Locale parseLocale(String locale) {
	        if (locale != null && !locale.isEmpty()) {
	            {
	                return new Locale(locale);
	            }
	        }
	        // Default English ken mafama hata locale
	        return Locale.US;
	    }

}
