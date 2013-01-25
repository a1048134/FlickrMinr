package pae.team8.flickrminr.data;

import com.aetrion.flickr.photos.comments.Comment;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: klexx
 * Date: 24.01.13
 * Time: 14:25
 * To change this template use File | Settings | File Templates.
 */
public class CommentResults{
    private LinkedHashMap<String, Integer> _results;

    public CommentResults(LinkedHashMap<String, Integer> results){
        _results = results;
    }
    public void setResults(LinkedHashMap<String, Integer> results){
        _results = results;
    }

    public LinkedHashMap<String, Integer> getResults(){
        return _results;
    }
}
