package technomag.maintodolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import technomag.technotodolist.R;
import technomag.todolistdb.TodoListTable;
import technomag.utils.MyDateUtils;

/**
 * Created by technomag on 08.01.18.
 */

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public interface ITodoListAdapter {
    void onItemClick(int id);
  }

  private Context context;
  private TodoListModel model;
  private ITodoListAdapter onItemClickListener;

  public TodoListAdapter(Context context, TodoListModel model)
  {
    this.context = context;
    this.model = model;
    this.model.getAllData();
  }

  public void setOnItemClickListener(ITodoListAdapter onItemClickListener)
  {
    this.onItemClickListener = onItemClickListener;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.todo_item, null, false);
    RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(lp);
    return new ToDoHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    model.setPosition(position);
    int iPriority = Integer.parseInt(model.getColumnValue(TodoListTable.Column.TASK_PRIORITY));
    switch (iPriority)
    {
      case 0:
        ((ToDoHolder)holder).imgTaskPriority.setImageResource(R.drawable.ic_note);
        break;
      case 1:
        ((ToDoHolder)holder).imgTaskPriority.setImageResource(R.drawable.ic_low_priority);
        break;
      case 2:
        ((ToDoHolder)holder).imgTaskPriority.setImageResource(R.drawable.ic_middle_priority);
        break;
      case 3:
        ((ToDoHolder)holder).imgTaskPriority.setImageResource(R.drawable.ic_hight_priority);
        break;
    }
    int color = 0;
    switch (iPriority)
    {
      case 0:
        color = context.getResources().getColor(R.color.task_title_back_note);
        break;
      case 1:
        color = context.getResources().getColor(R.color.task_title_back_low);
        break;
      case 2:
        color = context.getResources().getColor(R.color.task_title_back_middle);
        break;
      case 3:
        color = context.getResources().getColor(R.color.task_title_back_hight);
        break;
    }
    ((ToDoHolder)holder).frTaskName.setBackgroundColor(color);
    ((ToDoHolder)holder).tvTaskName.setText(model.getColumnValue(TodoListTable.Column.TASK_NAME));
    String beginDate = model.getColumnValue(TodoListTable.Column.DATE_CREATION);
    String modifyDate = model.getColumnValue(TodoListTable.Column.DATE_MODIFY);
    ((ToDoHolder)holder).tvTaskDescription.setText(model.getColumnValue(TodoListTable.Column.TASK_DESCRIPTION));
    if (beginDate != null && beginDate.length() > 0)
      ((ToDoHolder)holder).tvDateCreation.setText(MyDateUtils.reformatDateTime(beginDate));
    if (modifyDate != null && modifyDate.length() > 0)
      ((ToDoHolder)holder).tvDateModify.setText(MyDateUtils.reformatDateTime(modifyDate));
  }

  @Override
  public int getItemCount() {
    return model.getCount();
  }

  public void updateList()
  {
    model.requery();
    notifyDataSetChanged();
  }

  public class ToDoHolder extends RecyclerView.ViewHolder {

    public TextView tvTaskName;
    public TextView tvTaskDescription;
    public ImageView imgTaskPriority;
    public TextView tvDateCreation;
    public TextView tvDateModify;
    public FrameLayout frTaskName;

    public ToDoHolder(View view) {
      super(view);
      tvTaskName = (TextView) view.findViewById(R.id.textViewTaskName);
      frTaskName = (FrameLayout) view.findViewById(R.id.frTaskName);
      tvTaskDescription = (TextView) view.findViewById(R.id.textViewTaskDescription);
      imgTaskPriority = (ImageView) view.findViewById(R.id.imageViewTodo);
      tvDateCreation = (TextView) view.findViewById(R.id.tvDateCreated);
      tvDateModify = (TextView) view.findViewById(R.id.tvDateModified);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          model.setPosition(getAdapterPosition());
          onItemClickListener.onItemClick(Integer.parseInt(model.getColumnValue(TodoListTable.Column.ID)));
        }
      });
    }
  }

}
