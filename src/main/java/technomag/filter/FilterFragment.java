package technomag.filter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import technomag.technotodolist.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment implements IFilterView {

  private SwitchCompat swNote;
  private SwitchCompat swLow;
  private SwitchCompat swMid;
  private SwitchCompat swHight;
  private SwitchCompat swByDate;
  private SwitchCompat swByPriority;
  private FilterPresenter filterPresenter;

  public FilterFragment() {
    // Required empty public constructor
  }

  private void onBackPressed()
  {
    FragmentManager fm = getActivity().getSupportFragmentManager();
    fm.popBackStack();
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ImageView imgTaskBack = (ImageView) view.findViewById(R.id.imgFilterBack);
    imgTaskBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
    swNote = (SwitchCompat) view.findViewById(R.id.swNote);
    swLow = (SwitchCompat) view.findViewById(R.id.swLow);
    swMid = (SwitchCompat) view.findViewById(R.id.swMid);
    swHight = (SwitchCompat) view.findViewById(R.id.swHight);
    swByDate = (SwitchCompat) view.findViewById(R.id.swByDate);
    swByPriority = (SwitchCompat) view.findViewById(R.id.swByPriority);
    filterPresenter = new FilterPresenter(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_filter, container, false);
    Toolbar toolBar = (Toolbar) view.findViewById(R.id.filterToolbar);
    toolBar.setTitle("");
    ((AppCompatActivity)getActivity()).setSupportActionBar(toolBar);
    setHasOptionsMenu(true);
    return view;
  }

  @Override
  public void setViews(boolean note, boolean low, boolean mid, boolean hight, boolean byDate, boolean byPriority) {
    swNote.setChecked(note);
    swLow.setChecked(low);
    swMid.setChecked(mid);
    swHight.setChecked(hight);
    swByDate.setChecked(byDate);
    swByPriority.setChecked(byPriority);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.filter, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId())
    {
      case R.id.action_accept_filter:
        filterPresenter.acceptFilter(
          swNote.isChecked(),
          swLow.isChecked(),
          swMid.isChecked(),
          swHight.isChecked(),
          swByDate.isChecked(),
          swByPriority.isChecked());
        onBackPressed();
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

}
