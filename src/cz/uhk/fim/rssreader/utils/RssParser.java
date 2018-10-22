package cz.uhk.fim.rssreader.utils;

import cz.uhk.fim.rssreader.model.RSSList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class RssParser{

    private RSSList rssList;
    private ItemHandler itemHandler;

    public RssParser(){
        this.rssList=new RSSList();
        this.itemHandler=new ItemHandler(rssList);
    }

    private void parse(String source) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        parser.parse(new File(source),itemHandler);
    }

    public RSSList getParsedRSS(String source) throws ParserConfigurationException, SAXException, IOException {
        parse(source);
        return rssList;
    }
}
