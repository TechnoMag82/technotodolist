package technomag.maintodolist;

import android.database.Cursor;

/**
 * Created by technomag on 08.01.18.
 */

public interface ITodoListView {

  void updateList();

  void loadList(TodoListModel model);

}
