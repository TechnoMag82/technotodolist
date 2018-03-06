package technomag.maintodolist;

import android.database.Cursor;

import technomag.todolistdb.ToDoDB;

/**
 * Created by technomag on 08.01.18.
 */

public class TodoListModel {

  private Cursor cursor;

  public TodoListModel() {

  }

  public Cursor getAllData(){
    if (cursor != null && !cursor.isClosed())
      cursor.close();
    cursor = ToDoDB.getInstance().todoListTable.getAllData();
    return cursor;
  }

  public void closeData() {
    if (cursor != null && !cursor.isClosed())
      cursor.close();
  }

  public void setPosition(int position)
  {
    if (cursor != null && !cursor.isClosed())
      cursor.moveToPosition(position);
  }

  public String getColumnValue(String columnName)
  {
    if (cursor != null && !cursor.isClosed())
      return ToDoDB.getColumnValue(cursor, columnName);
    else
      return "";
  }

  public int getCount()
  {
    if (cursor != null && !cursor.isClosed())
      return cursor.getCount();
    else
      return 0;
  }

  public void requery()
  {
    if (cursor != null && !cursor.isClosed())
      cursor.requery();
  }

}
