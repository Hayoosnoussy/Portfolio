package Services;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.MessageSource;

@Service
public class ConvertBarcodeToEpanService {

    private MessageSource messageSource;

    @Autowired
    public ConvertBarcodeToEpanService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    
    public Map<String, Object> epanGeneratedByBarcode(String barcode, String parking, String verificatorField) {
        String[] splitParking = parking.split("@");

        if (splitParking.length == 0) {
            return Map.of("code", 400, "message", messageSource.getMessage("invalid_epan", null, null));
        }

        String operator = splitParking[0];
        String resultEpan = barcodeToEpan(barcode, operator);

        if (!resultEpan.isEmpty()) {
            String lastDigits = resultEpan.substring(resultEpan.lastIndexOf('x') + 1).replaceAll("\\?", "");
            if (verificatorField.equals(lastDigits)) {
                return Map.of("epan", resultEpan);
            } else {
                return Map.of("code", 400, "message", messageSource.getMessage("invalid_epan", null, null));
            }
        }

        return Map.of("code", 400, "message", messageSource.getMessage("error", null, null));
    }


    private String barcodeToEpan(String barcode, String operator) {
        try {
            String prefix = "02";
            String dummyString = getScrambleKey(operator);
            barcode = decodeBarcode(barcode, dummyString);

            String cell, year, days, seconds, type;

            if (barcode.length() == 18) {
                cell = barcode.substring(3, 7);
                year = barcode.substring(7, 8);
                days = barcode.substring(8, 11);
                seconds = barcode.substring(11, 16);
                type = "0" + barcode.substring(16, 17);
            } else if (barcode.length() == 22) {
                cell = barcode.substring(8, 12);
                year = barcode.substring(12, 13);
                days = barcode.substring(13, 16);
                seconds = barcode.substring(16, 21);
                type = "0" + barcode.substring(0, 1);
            } else {
                throw new Exception("Invalid barcode length");
            }

            return prefix + operator + cell + type + "xxx" + year + days + seconds + "0??";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private String getScrambleKey(String operatorNumber) {
        return getScrambleKeyLogic(operatorNumber, 10);
    }

    private String getScrambleKeyLogic(String operatorNumber, int length) {
        StringBuilder str1 = new StringBuilder();
        StringBuilder str2 = new StringBuilder();

        try {
            str2.append(operatorNumber).append(operatorNumber).append("0000000000");

            for (int i = 0; i < length; i++) {
                str1.append(i);
            }

            char[] arrayOfByte1 = str1.toString().toCharArray();
            char[] arrayOfByte2 = str2.toString().toCharArray();

            for (int j = 0; j < length; j++) {
                char k = arrayOfByte1[j];
                arrayOfByte1[j] = arrayOfByte1[arrayOfByte2[j] - '0'];
                arrayOfByte1[arrayOfByte2[j] - '0'] = k;
            }

            str1 = new StringBuilder(String.valueOf(arrayOfByte1));
        } catch (Exception e) {
            return e.getMessage();
        }

        return str1.toString();
    }

    private String decodeBarcode(String e1, String e2) {
        try {
            char[] arrayOfByte1 = e2.toCharArray();
            char[] arrayOfByte2 = e1.toCharArray();
            char[] arrayOfByte3 = e1.toCharArray();
            int i = 0;
            int j = 0;

            for (int k = 0; k < e1.length(); k++) {
                arrayOfByte3[k] = arrayOfByte2[k];

                i = e2.indexOf(arrayOfByte3[k]);
                if (j > i) {
                    arrayOfByte3[k] = (char) (10 + i - j + 48);
                } else {
                    arrayOfByte3[k] = (char) (i - j + 48);
                }
                j = i;
            }

            for (int k = 0; k < arrayOfByte3.length; k++) {
                arrayOfByte2[k] = arrayOfByte3[arrayOfByte3.length - 1 - k];
            }
            e1 = new String(arrayOfByte2);
        } catch (Exception e) {
            return e.getMessage();
        }

        return e1;
    }

}

