package pae.team8.flickrminr.analyzer;

import com.aetrion.flickr.photos.comments.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pae.team8.flickrminr.App;
import pae.team8.flickrminr.data.CommentResults;
import pae.team8.flickrminr.data.CsvWriter;
import pae.team8.flickrminr.data.DataSet;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class NlpAnalyzer implements Analyzer{
	Logger logger = LoggerFactory.getLogger("pae.team8.flickrminr.analyzer.NlpAnalyzer");

	public NlpAnalyzer(){}

    @Override
    public DataSet doAnalyze(DataSet data) {
        CsvWriter writer = new CsvWriter("comments.csv", true);

        LinkedHashMap<Comment, CommentResults> comments = data.getComments();

        for(Map.Entry<Comment, CommentResults> comment : comments.entrySet()){
            String text = comment.getKey().getText();
            CommentResults results= App._nlp.evalData(text);
            data.setComment(comment.getKey(),results);
            writer.writeToCSV(comment.getKey().getId(), results.getResults());
        }

        writer.close();
        return data;
    }
}