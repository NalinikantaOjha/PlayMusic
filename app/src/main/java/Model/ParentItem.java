package Model;

import java.util.List;


public class ParentItem {
    public static final int TEXT_TYPE = 0;
    public static final int TEXT_TYPE_TWO = 1;
    public static final int TEXT_TYPE_THREE = 2;
    public  static  final int TEXT_TYPE_FOUR=3;
    public  static  final int TEXT_TYPE_FIVE=4;

    private final int Type;
    private String ParentItemTitle;
    private List<ResultsDTO> ChildItemList;
    private List<ResultsDTO>child2list;
    private List<ChildItem>childItems;


    public ParentItem(int Type,
            String ParentItemTitle,
            List<ResultsDTO> ChildItemList,List<ResultsDTO>child2list, List<ChildItem>childItems)
    {
this.child2list=child2list;
this.childItems=childItems;
this.Type=Type;
        this.ParentItemTitle = ParentItemTitle;
        this.ChildItemList = ChildItemList;
    }

    public int getType() {
        return Type;
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



