package technomag.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import technomag.technotodolist.R;
import technomag.technotodolist.TechnoTodoListApp;

/**
 * Created by technomag on 24.04.17.
 */

public class MyDateUtils {

  public static String reformatDate(final String strDate)
  {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date date = null;
    try {
      date = format.parse(strDate);
      SimpleDateFormat sdf1 = new SimpleDateFormat("d MMMM", new MyDateFormatSymbols(TechnoTodoListApp.getAppContext()));
      return sdf1.format(date);
    } catch (ParseException e) {
      return strDate;
    }
  }

  public static String reformatDateTime(final String strDate)
  {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
    Date date = null;
    try {
      date = format.parse(strDate);
      SimpleDateFormat sdf1 = new SimpleDateFormat("d MMMM yyyy, HH:mm", new MyDateFormatSymbols(TechnoTodoListApp.getAppContext()));
      return sdf1.format(date);
    } catch (ParseException e) {
      return strDate;
    }
  }

  public static String getCurrentDate()
  {
    Calendar c = Calendar.getInstance();
    String months[] = TechnoTodoListApp.getAppContext().getResources().getStringArray(R.array.months);
    SimpleDateFormat sdf = new SimpleDateFormat(
      "d MMMM, HH:mm", new MyDateFormatSymbols(TechnoTodoListApp.getAppContext()));
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(c.getTime());
  }

}
