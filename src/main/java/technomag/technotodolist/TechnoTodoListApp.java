package technomag.technotodolist;

import android.app.Application;
import android.content.Context;

/**
 * Created by technomag on 08.01.18.
 */

public class TechnoTodoListApp extends Application {

  private static Context context;

  public void onCreate(){
    super.onCreate();
    TechnoTodoListApp.context = getApplicationContext();
  }

  public static Context getAppContext() {
    return TechnoTodoListApp.context;
  }

}
