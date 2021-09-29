package Model;

import java.util.List;


public class ParentItem {


    private String ParentItemTitle;
    private List<ResultsDTO> ChildItemList;


    public ParentItem(
            String ParentItemTitle,
            List<ResultsDTO> ChildItemList)
    {

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


}



