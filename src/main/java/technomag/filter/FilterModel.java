package technomag.filter;

import android.content.Context;
import android.content.SharedPreferences;

import technomag.technotodolist.TechnoTodoListApp;

/**
 * Created by technomag on 13.02.18.
 */

public class FilterModel {

  private boolean showNoteTask;
  private boolean showLowTask;
  private boolean showMiddileTask;
  private boolean showHightTask;
  private boolean sortByDate;
  private boolean sortByPriority;

  public FilterModel()
  {
    loadFilter();
  }

  public void acceptFilter(boolean note, boolean low, boolean mid, boolean hight, boolean byDate, boolean byPriority)
  {
    showNoteTask = note;
    showLowTask = low;
    showMiddileTask = mid;
    showHightTask = hight;
    sortByDate = byDate;
    sortByPriority = byPriority;
    saveFilter();
  }

  private void loadFilter()
  {
    SharedPreferences localePreferences = TechnoTodoListApp.getAppContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
    showNoteTask = localePreferences.getBoolean("note", true);
    showLowTask = localePreferences.getBoolean("low", true);
    showMiddileTask = localePreferences.getBoolean("mid", true);
    showHightTask = localePreferences.getBoolean("hight", true);
    sortByDate = localePreferences.getBoolean("by_date", true);
    sortByPriority = localePreferences.getBoolean("by_priority", true);
  }

  private void saveFilter()
  {
    SharedPreferences localePreferences = TechnoTodoListApp.getAppContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = localePreferences.edit();
    editor.putBoolean("note", showNoteTask);
    editor.putBoolean("low", showLowTask);
    editor.putBoolean("mid", showMiddileTask);
    editor.putBoolean("hight", showHightTask);
    editor.putBoolean("by_date", sortByDate);
    editor.putBoolean("by_priority", sortByPriority);
    editor.commit();
  }

  public boolean isShowNoteTask() {
    return showNoteTask;
  }

  public boolean isShowLowTask() {
    return showLowTask;
  }

  public boolean isShowMiddileTask() {
    return showMiddileTask;
  }

  public boolean isShowHightTask() {
    return showHightTask;
  }

  public boolean isSortByDate() {
    return sortByDate;
  }

  public boolean isSortByPriority() {
    return sortByPriority;
  }

}
