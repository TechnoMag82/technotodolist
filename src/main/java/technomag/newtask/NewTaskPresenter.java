package technomag.newtask;

import technomag.technotodolist.R;
import technomag.technotodolist.TechnoTodoListApp;
import technomag.todolistdb.TaskModel;

/**
 * Created by technomag on 21.01.18.
 */

public class NewTaskPresenter {

  public static final int ADD_TASK = 12;
  public static final int EDIT_TASK = 14;

  private INewTaskView taskView;
  private int progress = 0;

  public int getTaskAction() {
    return taskAction;
  }

  private int taskAction = ADD_TASK;
  private TaskModel taskModel;

  public NewTaskPresenter(INewTaskView taskView)
  {
    this.taskView = taskView;
    this.taskModel = new TaskModel("");
    initTaskPriorities();
  }

  public void deleteTask()
  {
    taskModel.deleteTask();
    taskView.finishEdit(ADD_TASK);
  }

  public void editTastInit(String id)
  {
    taskAction = EDIT_TASK;
    this.taskModel = new TaskModel(id);
    taskView.setTaskName(taskModel.getTaskName());
    taskView.setTaskDescription(taskModel.getTaskDescription());
    progress = taskModel.getPriority();
    initTaskPriorities();
    taskView.setCaption(TechnoTodoListApp.getAppContext().getResources().getString(R.string.action_edit_task));
  }

  public void addTask()
  {
    if (validate(taskModel.getTaskName(), taskModel.getTaskDescription(), taskModel.getPriority()))
          taskModel.insertTask();
  }

  public void editTask()
  {
    if (validate(taskModel.getTaskName(), taskModel.getTaskDescription(), taskModel.getPriority()))
    {
      taskModel.updateTask();
    }
  }

  private boolean validate(final String taskName, final String taskDesc, final int taskPriority)
  {
    if (taskName.length() > 0 && taskDesc.length() > 0) {
      taskView.finishEdit(ADD_TASK);
      return true;
    }
    else
    {
      taskView.showWarning();
      return false;
    }
  }

  private void initTaskPriorities()
  {
    taskView.setupTaskPriorities(progress);
  }

  public void setProgress(int progress)
  {
    taskModel.setPriority(progress);
    buttonEnabled(taskModel.isModified());
  }

  public void setTeskName(String taskName)
  {
    taskModel.setTaskName(taskName);
    buttonEnabled(taskModel.isModified());
  }

  public void setTeskDescription(String taskDesc)
  {
    taskModel.setTaskDescription(taskDesc);
    buttonEnabled(taskModel.isModified());
  }

  private void buttonEnabled(boolean modified)
  {
    taskView.addTaskEnabled(taskAction == ADD_TASK && modified);
    taskView.editTaskEnabled(taskAction == EDIT_TASK && modified);
  }

}
