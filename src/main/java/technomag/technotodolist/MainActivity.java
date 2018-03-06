package technomag.technotodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import technomag.maintodolist.TodoListFragment;

public class MainActivity extends AppCompatActivity implements IMainActivity {

  private TodoListFragment frDelivery;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    frDelivery = new TodoListFragment();
    MainFragmentManager.replaceFragment(this, frDelivery);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

  }
  
  @Override
  public void dataUpdated()
  {
    
  }
  
}
