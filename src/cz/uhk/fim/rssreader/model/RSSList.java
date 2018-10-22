package cz.uhk.fim.rssreader.model;

import java.util.ArrayList;
import java.util.List;

public class RSSList {

    private List<RssItem> itemList = new ArrayList();

    public void addItem (RssItem item){
        itemList.add(item);
    }

    public RssItem getItem(int index){
        return itemList.get(index);
    }

    public  void removeItem (int index){
        itemList.remove(index);
    }

    public void clearAll(){
        itemList.clear();
    }

    public List<RssItem> getAllItems(){
        return itemList;
    }
}
