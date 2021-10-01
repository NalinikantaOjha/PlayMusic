package Interface;

import Model.ChildItem;
import Model.ResultsDTO;

public interface onChildClicked {
    void onChildClicked(ResultsDTO resultsDTO, int position);
    void onFolderClicked(ChildItem childItem);
    void onParentCLicked();
}
