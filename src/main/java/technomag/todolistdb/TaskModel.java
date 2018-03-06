package technomag.todolistdb;

import android.database.Cursor;

/**
 * Created by technomag on 21.01.18.
 */

public class TaskModel {

  private boolean modified = false;
  private String taskName = "";
  private String taskDescription = "";
  private int priority = 0;
  private String id = "";

  public TaskModel(String id)
  {
    if (id.length()>0) {
      Cursor cursor = ToDoDB.getInstance().todoListTable.getDataById(id);
      cursor.moveToFirst();
      this.taskName = ToDoDB.getColumnValue(cursor, TodoListTable.Column.TASK_NAME);
      this.taskDescription = ToDoDB.getColumnValue(cursor, TodoListTable.Column.TASK_DESCRIPTION);
      this.priority = Integer.parseInt(ToDoDB.getColumnValue(cursor, TodoListTable.Column.TASK_PRIORITY));
      this.id = id;
      cursor.close();
    }
  }

  public void setTaskName(String taskName)
  {
    if (this.taskName.compareTo(taskName) != 0)
    {
      modified = true;
      this.taskName = taskName;
    }
  }
  
  public void setTaskDescription(String taskDesc)
  {
    if (this.taskDescription.compareTo(taskDesc) != 0)
    {
      modified = true;
      this.taskDescription = taskDesc;
    }
  }
  
  public void setPriority(int priority)
  {
    if (this.priority != priority)
    {
      this.modified = true;
      this.priority = priority;
    }
  }
  
  public int getPriority()
  {
    return priority;
  }
  
  public String getTaskName()
  {
    return taskName;
  }
  
  public String getTaskDescription()
  {
    return taskDescription;
  }
  
  public boolean insertTask()
  {
    ToDoDB.getInstance().todoListTable.insertTask(taskName, taskDescription, priority);
    return true;
  }
  
  public boolean updateTask()
  {
    if (modified && id.length() > 0)
    {
      ToDoDB.getInstance().todoListTable.updateTask(taskName, taskDescription, priority, id);
      id = "";
      modified = false;
      return true;
    }
    else
      return false;
  }

  public boolean deleteTask()
  {
    if (id.length()>0)
      ToDoDB.getInstance().todoListTable.deleteTask(id);
    return true;
  }

  public boolean isModified() {
    return modified;
  }

}
