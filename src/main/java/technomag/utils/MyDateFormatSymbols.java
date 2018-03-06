package technomag.utils;

import android.content.Context;

import java.text.DateFormatSymbols;

import technomag.technotodolist.R;

/**
 * Created by ILYA on 17.10.2016.
 */

public class MyDateFormatSymbols extends DateFormatSymbols {

  private Context context;

  public MyDateFormatSymbols(Context context)
  {
    this.context = context;
  }

  @Override
  public String[] getMonths() {
    return context.getResources().getStringArray(R.array.months);
  }

}
