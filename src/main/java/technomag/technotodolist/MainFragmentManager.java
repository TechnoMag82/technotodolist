package technomag.technotodolist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ILYA on 30.09.2016.
 */

public class MainFragmentManager {

  public static void pushFragment(FragmentActivity activity, Fragment fragment, String tag)
  {
    FragmentTransaction fTrans = activity.getSupportFragmentManager().beginTransaction();
    fTrans.replace(R.id.frActivities, fragment);
    fTrans.addToBackStack(tag);
    fTrans.commit();
  }

  public static void replaceFragment(AppCompatActivity activity, Fragment frActivity)
  {
    activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    FragmentTransaction fTrans = activity.getSupportFragmentManager().beginTransaction();
    fTrans.replace(R.id.frActivities, frActivity);
    fTrans.commit();
  }

}
