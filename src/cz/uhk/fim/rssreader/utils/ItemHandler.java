package cz.uhk.fim.rssreader.utils;


import cz.uhk.fim.rssreader.model.RSSList;
import cz.uhk.fim.rssreader.model.RssItem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ItemHandler extends DefaultHandler {

    private String ITEM = "item";
    private String TITLE = "title";
    private String LINK = "link";
    private String DESCRIPTION = "description";
    private String PUB_DATE = "pubDate";
    private String AUTHOR = "dc:creator";

    private RssItem rssItem;
    private RSSList rssList;

    private boolean title;
    private boolean link;
    private boolean description;
    private boolean pubDate;
    private boolean author;

    public ItemHandler(RSSList rssList) {
        this.rssList = rssList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(ITEM)) {
            rssItem = new RssItem();
        }
        if (qName.equalsIgnoreCase(TITLE) && rssItem != null) {
            title = true;
        }
        if (qName.equalsIgnoreCase(LINK) && rssItem != null) {
            link = true;
        }
        if (qName.equalsIgnoreCase(DESCRIPTION) && rssItem != null) {
            description = true;
        }
        if (qName.equalsIgnoreCase(PUB_DATE) && rssItem != null) {
            pubDate = true;
        }
        if (qName.equalsIgnoreCase(AUTHOR) && rssItem != null) {
            author = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(ITEM)) {
            rssList.addItem(rssItem);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (title) {
            rssItem.setTitle(new String(ch, start, length));
            title = false;
        }
        if (link) {
            rssItem.setLink(new String(ch, start, length));
            link = false;
        }
        if (description) {
            rssItem.setDescription(new String(ch, start, length));
            description = false;
        }
        if (pubDate) {
            rssItem.setPubDate(new String(ch, start, length));
            pubDate = false;
        }
        if (author) {
            rssItem.setAuthor(new String(ch, start, length));
            author = false;
        }


    }
}
