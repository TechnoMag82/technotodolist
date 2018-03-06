package technomag.todolistdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import technomag.technotodolist.R;
import technomag.technotodolist.TechnoTodoListApp;

public class ToDoDB {

  private DBHelper dbHelper;
  private SQLiteDatabase sqlDB;
  private Context mContext;
  private final String dbName = "TechnoTodoDB";
  private static ToDoDB instance;
  private final int DB_VERSION = 3; // old version 1

  public TodoListTable todoListTable;

  
  private ToDoDB()
  {
    mContext = TechnoTodoListApp.getAppContext();
    OpenDB();
    todoListTable = new TodoListTable(sqlDB);
  }

  public static ToDoDB getInstance()
  {
    return instance==null ? instance = new ToDoDB(): instance;
  }

  private void OpenDB()
  {
    dbHelper = new DBHelper(mContext, dbName, null, DB_VERSION);
    sqlDB = dbHelper.getWritableDatabase();
  }

  public void closeDB()
  {
    if (dbHelper!=null)
      dbHelper.close();
  }

  public static String getColumnValue(Cursor cursor, String columnName)
  {
    return cursor.getString(cursor.getColumnIndex(columnName));
  }

  class DBHelper extends SQLiteOpenHelper
  {
    public DBHelper(Context context, String name, CursorFactory factory,
        int version) {
      super(context, name, factory, version);
      
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(TodoListTable.SQL_CREATE_DATABASE);

      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
      String strDate = df.format(Calendar.getInstance().getTime());
      ContentValues cv = new ContentValues();
      cv.put(TodoListTable.Column.TASK_NAME, mContext.getResources().getString(R.string.new_task_title));
      cv.put(TodoListTable.Column.TASK_DESCRIPTION, mContext.getResources().getString(R.string.new_task_description));
      cv.put(TodoListTable.Column.TASK_PRIORITY, 0);
      cv.put(TodoListTable.Column.DATE_CREATION, strDate);
      db.insert(TodoListTable.NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      if (newVersion == 3)
      {
        db.execSQL(TodoListTable.SQL_ADD_DATE_CREATION_COLUMN);
        db.execSQL(TodoListTable.SQL_ADD_DATE_MODIFY_COLUMN);
        db.execSQL(TodoListTable.SQL_UPDATE);
      }
    }
    
  }
  
}
