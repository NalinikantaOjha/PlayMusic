package Model;

import java.util.List;


public class ParentItem {


    private String ParentItemTitle;
    private List<ChildItem> ChildItemList;


    public ParentItem(
            String ParentItemTitle,
            List<ChildItem> ChildItemList)
    {

        this.ParentItemTitle = ParentItemTitle;
        this.ChildItemList = ChildItemList;
    }


    public String getParentItemTitle()
    {
        return ParentItemTitle;
    }



    public List<ChildItem> getChildItemList()
    {
        return ChildItemList;
    }


}



