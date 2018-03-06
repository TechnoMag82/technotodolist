package technomag.newtask;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import technomag.technotodolist.IMainActivity;
import technomag.technotodolist.R;

public class NewTaskFragment extends Fragment implements INewTaskView {

  private Context context;
  private EditText editTask;
  private EditText editDescription;

  private TextView tvCaption;
  private TextView tvTaskPriority;
  private TextView tvTaskName;
  private MenuItem miAddTask;
  private MenuItem miEditTask;
  private MenuItem miDeleteTask;
  private Toolbar toolBar;

  private RadioButton rbtnNote;
  private RadioButton rbtnLow;
  private RadioButton rbtnMid;
  private RadioButton rbtnHight;
  
  private int iPriority = 0;
  private NewTaskPresenter newTaskPresenter;
  
  private IMainActivity mainActivity;
  private int id = -1;

  public NewTaskFragment()
  {

  }

  private void onBackPressed()
  {
    hideSoftKeyboard();
    FragmentManager fm = getActivity().getSupportFragmentManager();
    fm.popBackStack();
  }

  private void hideSoftKeyboard() {
    try {
      InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      inputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    } catch (Exception e) { }
  }

  public void taskForEdit(int id)
  {
    this.id = id;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
        try {
          mainActivity = (IMainActivity) activity;
        } catch (ClassCastException e) {

        }
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ImageView imgTaskBack = (ImageView) view.findViewById(R.id.imgTaskBack);
    imgTaskBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
    tvCaption = (TextView) view.findViewById(R.id.tvCaption);
    editTask = (EditText) view.findViewById(R.id.editTaskName);
    editDescription = (EditText) view.findViewById(R.id.editTaskText);
    editDescription.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        newTaskPresenter.setTeskDescription(editDescription.getText().toString());
      }
    });
    tvTaskPriority = (TextView) view.findViewById(R.id.tvTaskPriority);
    editTask.setActivated(true);
    editTask.requestFocus();
    editTask.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        newTaskPresenter.setTeskName(editTask.getText().toString());
      }
    });

    rbtnNote = (RadioButton) view.findViewById(R.id.rbtnNote);
    rbtnNote.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newTaskPresenter.setProgress(0);
      }
    });

    rbtnLow = (RadioButton) view.findViewById(R.id.rbtnLow);
    rbtnLow.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newTaskPresenter.setProgress(1);
      }
    });

    rbtnMid = (RadioButton) view.findViewById(R.id.rbtnMid);
    rbtnMid.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newTaskPresenter.setProgress(2);
      }
    });

    rbtnHight = (RadioButton) view.findViewById(R.id.rbtnHight);
    rbtnHight.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        newTaskPresenter.setProgress(3);
      }
    });
    newTaskPresenter = new NewTaskPresenter(this);
    if (id != -1)
      newTaskPresenter.editTastInit(Integer.toString(id));
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    View view = inflater.inflate(R.layout.activity_new_todo, container, false);
    toolBar = (Toolbar) view.findViewById(R.id.toolbarList1);
    toolBar.setTitle("");
    ((AppCompatActivity)getActivity()).setSupportActionBar(toolBar);
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public void setupTaskPriorities(int progress) {
    rbtnNote.setChecked(progress == 0);
    rbtnLow.setChecked(progress == 1);
    rbtnMid.setChecked(progress == 2);
    rbtnHight.setChecked(progress == 3);
  }

  @Override
  public void showWarning() {
    Toast.makeText(getContext(), getContext().getResources().getString(R.string.dialog_message), Toast.LENGTH_LONG).show();
  }

  @Override
  public void finishEdit(int action) {
    hideSoftKeyboard();
    mainActivity.dataUpdated();
    getActivity().getSupportFragmentManager().popBackStackImmediate();
  }

  @Override
  public void addTaskEnabled(boolean enabled) {
    if (miAddTask != null)
      miAddTask.setVisible(enabled);
  }

  @Override
  public void editTaskEnabled(boolean enabled) {
    if (miEditTask != null)
      miEditTask.setVisible(enabled);
  }

  @Override
  public void setTaskName(String taskName) {
    editTask.setText(taskName);
  }

  @Override
  public void setTaskDescription(String taskDescription) {
    editDescription.setText(taskDescription);
  }

  @Override
  public void setCaption(String caption) {
    tvCaption.setText(caption);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.new_todo, menu);
    miEditTask = menu.findItem(R.id.action_edit_task);
    miAddTask = menu.findItem(R.id.action_add_task);
    miDeleteTask = menu.findItem(R.id.action_delete_task);
    if (newTaskPresenter.getTaskAction() == NewTaskPresenter.EDIT_TASK)
      miDeleteTask.setVisible(true);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId())
    {
      case R.id.action_edit_task:
        newTaskPresenter.editTask();
        return true;
      case R.id.action_delete_task:
        newTaskPresenter.deleteTask();
        return true;
      case R.id.action_add_task:
        newTaskPresenter.addTask();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

}
