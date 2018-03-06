package technomag.todolistdb;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import technomag.filter.FilterModel;

/**
 * Created by technomag on 08.01.18.
 */

public final class TodoListTable {

  public final static String NAME = "todoList";

  public static final class Column {
    public final static String ID = "_id";
    public final static String TASK_NAME = "taskName";
    public final static String TASK_DESCRIPTION = "taskDescription";
    public final static String TASK_PRIORITY = "taskPriority";
    public final static String DATE_CREATION = "dateCreation";
    public final static String DATE_MODIFY = "dateModify";
  }

  public final static String SQL_CREATE_DATABASE =
    "create table " + NAME + "( " +
      Column.ID + " integer primary key autoincrement, " +
      Column.TASK_NAME + " text, " +
      Column.TASK_DESCRIPTION + " text, " +
      Column.DATE_CREATION + " text, " +
      Column.DATE_MODIFY + " text, " +
      Column.TASK_PRIORITY +" integer );";

  public final static String SQL_ADD_DATE_CREATION_COLUMN = "ALTER TABLE " + NAME + " ADD " + Column.DATE_CREATION + " text;";
  public final static String SQL_ADD_DATE_MODIFY_COLUMN = "ALTER TABLE " + NAME + " ADD " + Column.DATE_MODIFY + " text;";
  public final static String SQL_UPDATE = "UPDATE " + NAME + " SET " + Column.DATE_CREATION + "=(datetime('now','localtime'));";
  
  private SQLiteDatabase sqlDB;

  public TodoListTable(SQLiteDatabase sqlDB)
  {
    this.sqlDB = sqlDB;
  }

  private String buildQuery()
  {
    FilterModel filterModel = new FilterModel();
    if (filterModel.isShowNoteTask() == false &&
      filterModel.isShowLowTask() == false &&
      filterModel.isShowMiddileTask() == false &&
      filterModel.isShowHightTask() == false &&
      filterModel.isSortByDate() == false &&
      filterModel.isSortByPriority() == false
      )
    return null;
    StringBuilder sb = new StringBuilder();
    if (filterModel.isShowNoteTask() ||
      filterModel.isShowLowTask() ||
      filterModel.isShowMiddileTask() ||
      filterModel.isShowHightTask())
    {
      sb.append(" WHERE " + Column.TASK_PRIORITY + " IN (");
      if (filterModel.isShowNoteTask())
        sb.append("0,");
      if (filterModel.isShowLowTask())
        sb.append("1,");
      if (filterModel.isShowMiddileTask())
        sb.append("2,");
      if (filterModel.isShowHightTask())
        sb.append("3,");
      sb.append(")");
      int index = sb.lastIndexOf(",");
      sb.deleteCharAt(index);
    }
    if (filterModel.isSortByDate() || filterModel.isSortByPriority())
    {
      sb.append(" ORDER BY ");
      if (filterModel.isSortByDate())
        sb.append(Column.DATE_CREATION + ",");
      if (filterModel.isSortByPriority())
        sb.append(Column.TASK_PRIORITY + ",");
      int index = sb.lastIndexOf(",");
      sb.deleteCharAt(index);
    }
    return sb.toString();
  }

  public Cursor getAllData()
  {
    String query = buildQuery();
    if (query != null )
      return sqlDB.rawQuery("SELECT * FROM " + NAME + query, null);
    else
      return sqlDB.rawQuery("SELECT * FROM " + NAME, null);
  }

  public void deleteTask(String id)
  {
    sqlDB.delete(NAME, Column.ID + "=" + id , null);
  }

  public void insertTask(String taskName, String taskDescription, int taskPriority)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    String strDate = df.format(Calendar.getInstance().getTime());
    ContentValues cv = new ContentValues();
    cv.put(Column.TASK_NAME, taskName);
    cv.put(Column.TASK_DESCRIPTION, taskDescription);
    cv.put(Column.TASK_PRIORITY, taskPriority);
    cv.put(Column.DATE_CREATION, strDate);
    sqlDB.insert(NAME, null, cv);
  }

  public void updateTask(String taskName, String taskDescription, int taskPriority, String id)
  {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    String strDate = df.format(Calendar.getInstance().getTime());
    ContentValues cv = new ContentValues();
    cv.put(Column.TASK_NAME, taskName);
    cv.put(Column.TASK_DESCRIPTION, taskDescription);
    cv.put(Column.TASK_PRIORITY, taskPriority);
    cv.put(Column.DATE_MODIFY, strDate);
    sqlDB.update(NAME, cv, Column.ID + "=?", new String[]{id});
  }

  public Cursor getDataById(String id) {
    return sqlDB.rawQuery("SELECT * FROM " + NAME + " WHERE " + Column.ID  + "=" + id, null );
  }

}
