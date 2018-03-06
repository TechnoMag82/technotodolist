package technomag.newtask;

/**
 * Created by technomag on 21.01.18.
 */

public interface INewTaskView {

  void setupTaskPriorities(int progress);
  void showWarning();
  void finishEdit(int action);
  void addTaskEnabled(boolean enabled);
  void editTaskEnabled(boolean enabled);
  void setTaskName(String taskName);
  void setTaskDescription(String taskDescription);
  void setCaption(String caption);

}
