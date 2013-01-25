package pae.team8.flickrminr.nlp;

import pae.team8.flickrminr.data.CommentResults;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: klexx
 * Date: 23.01.13
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
public interface Evaluator {
    public CommentResults evalData(String text);
}
