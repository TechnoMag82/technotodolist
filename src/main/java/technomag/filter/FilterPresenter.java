package technomag.filter;

/**
 * Created by technomag on 13.02.18.
 */

public class FilterPresenter {

  private IFilterView filterView;
  private FilterModel filterModel;

  public FilterPresenter(IFilterView filterView)
  {
    this.filterView = filterView;
    filterModel = new FilterModel();
    initFilter();
  }

  public void acceptFilter(boolean note, boolean low, boolean mid, boolean hight, boolean byDate, boolean byPriority)
  {
    filterModel.acceptFilter(note, low, mid, hight, byDate, byPriority);
  }

  private void initFilter()
  {
    if (filterView != null)
    filterView.setViews(
      filterModel.isShowNoteTask(),
      filterModel.isShowLowTask(),
      filterModel.isShowMiddileTask(),
      filterModel.isShowHightTask(),
      filterModel.isSortByDate(),
      filterModel.isSortByPriority()
      );
  }

}
