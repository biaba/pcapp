package pcapp.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


// static methods to convert Date-> String and vice versa
public class DateUtils {
	
private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    // reading date string -> converting to date
    public static Date stringToDate(String dateString) throws ParseException {
        Date theDate = formatter.parse(dateString);
        
        return theDate;        
    }
    
    // reading date -> converting to String
    public static String dateToString(Date theDate) {
        
        String result = null;
        
        if (theDate != null) {
            result = formatter.format(theDate);
        }
        
        return result;
    }

}
