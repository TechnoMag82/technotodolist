package technomag.maintodolist;

/**
 * Created by technomag on 08.01.18.
 */

public class TodoListPresenter {

  private ITodoListView todoListView;
  private TodoListModel todoListModel;


  public TodoListPresenter(ITodoListView todoView)
  {
    this.todoListView = todoView;
    todoListModel = new TodoListModel();
  }

  public void viewDestroyed()
  {
    todoListModel.closeData();
  }


  public void viewCreated()
  {
    todoListView.loadList(todoListModel);
  }

}
