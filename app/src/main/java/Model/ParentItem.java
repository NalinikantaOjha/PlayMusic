package Model;

import java.util.List;


public class ParentItem {


    private String ParentItemTitle;
    private List<ResultsDTO> ChildItemList;
    private List<ResultsDTO>child2list;
    private List<ChildItem>childItems;


    public ParentItem(
            String ParentItemTitle,
            List<ResultsDTO> ChildItemList,List<ResultsDTO>child2list, List<ChildItem>childItems)
    {
this.child2list=child2list;
this.childItems=childItems;
        this.ParentItemTitle = ParentItemTitle;
        this.ChildItemList = ChildItemList;
    }


    public String getParentItemTitle()
    {
        return ParentItemTitle;
    }



    public List<ResultsDTO> getChildItemList()
    {
        return ChildItemList;
    }

    public List<ResultsDTO> getChild2list() {
        return child2list;
    }

    public List<ChildItem> getChildItems() {
        return childItems;
    }
}



