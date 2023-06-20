package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormat {
    private static SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

    public static Date parseDate1(String strDate){
        try{
            return simpleDateFormat1.parse(strDate);
        } catch(ParseException e){
            System.out.println("Nhập lại đúng theo định dạng (dd/MM/yyyy)");
            return null;
        }
    }
    public static String converDateToString1(Date date){
        return simpleDateFormat1.format(date);
    }

    public static Date parseDate2(String strDate){
        try{
            return simpleDateFormat2.parse(strDate);
        } catch (ParseException e){
            System.out.println("Nhập lại đúng theo định dạng (dd-MM-yyyy hh:mm:ss)");
            return null;
        }
    }
    public static String converDateToString2(Date date){
        return simpleDateFormat2.format(date);
    }
}
