import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: zl52074
 * @time: 2020/3/26 21:22
 */
public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(new Date()));
//        System.out.println(formatter.format(System.currentTimeMillis()-1000*3600*24*30L));
    }
}
