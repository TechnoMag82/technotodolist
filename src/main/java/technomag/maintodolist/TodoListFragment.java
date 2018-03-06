package technomag.maintodolist;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import technomag.filter.FilterFragment;
import technomag.newtask.NewTaskFragment;
import technomag.about.AboutFragment;
import technomag.technotodolist.MainFragmentManager;
import technomag.technotodolist.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TodoListFragment extends Fragment implements ITodoListView {

  private TodoListPresenter todoListPresenter;
  private TodoListAdapter todoListAdapter;
  private Toolbar toolBar;
  private Context context;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  private RecyclerView rcTodoList;


  public TodoListFragment() {
    // Required empty public constructor
  }

  private void initTodoList(View view)
  {
    rcTodoList = (RecyclerView) view.findViewById(R.id.rcTodoList);
    rcTodoList.setHasFixedSize(true);
    LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
    rcTodoList.setLayoutManager(mLayoutManager);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
    toolBar = (Toolbar) view.findViewById(R.id.toolbarList);
    toolBar.setTitle("");
    ((AppCompatActivity)getActivity()).setSupportActionBar(toolBar);
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initTodoList(view);
    todoListPresenter = new TodoListPresenter(this);
    todoListPresenter.viewCreated();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    todoListPresenter.viewDestroyed();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.main, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId())
    {
      case R.id.action_add_todo:
        NewTaskFragment frNewTodo = new NewTaskFragment();
        MainFragmentManager.pushFragment(getActivity(), frNewTodo, "new_todo");
        return true;
      case R.id.action_settings:
        AboutFragment frAbout = new AboutFragment();
        MainFragmentManager.pushFragment(getActivity(), frAbout, "about");
        return true;
      case R.id.action_filter:
        FilterFragment frFilter = new FilterFragment();
        MainFragmentManager.pushFragment(getActivity(), frFilter, "filter");
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void updateList() {
    todoListAdapter.updateList();
  }

  @Override
  public void loadList(TodoListModel model) {
    todoListAdapter = new TodoListAdapter(context, model);
    todoListAdapter.setOnItemClickListener(new TodoListAdapter.ITodoListAdapter() {
      @Override
      public void onItemClick(int id) {
        NewTaskFragment frNewTodo = new NewTaskFragment();
        frNewTodo.taskForEdit(id);
        MainFragmentManager.pushFragment(getActivity(), frNewTodo, "new_todo");
      }
    });
    rcTodoList.setAdapter(todoListAdapter);
  }

}
