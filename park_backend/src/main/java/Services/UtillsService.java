package Services;

import org.springframework.stereotype.Service;
import java.time.Duration;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
@Service
public class UtillsService {
	
	

	public String formatDurationHumanReadable(String duration, String locale) {
	    Duration parsedDuration = Duration.parse(duration);

	    long days = parsedDuration.toDaysPart();
	    long hours = parsedDuration.toHoursPart();
	    long minutes = parsedDuration.toMinutesPart();

	    StringBuilder result = new StringBuilder();

	    if (days > 0) {
	        result.append(days == 1 ?
	                days + " day, " :
	                days + " days, ");
	    }

	    if (hours > 0) {
	        result.append(hours == 1 ?
	                hours + " hour, " :
	                hours + " hours, ");
	    }

	    if (minutes > 0) {
	        result.append(minutes == 1 ?
	                minutes + " minute" :
	                minutes + " minutes");
	    }

	    return result.toString().replaceAll(", $", "");
	}
	

	

	public String formatAmount(double amount, String currencyCode) {
	        Currency currency = Currency.getInstance(currencyCode);
	        Locale locale = Locale.US; // Set default locale to en_US

	        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	        format.setCurrency(currency);

	        return format.format(amount);	
	}
	
	
}
