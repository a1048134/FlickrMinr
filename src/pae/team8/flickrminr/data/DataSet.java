package pae.team8.flickrminr.data;

import com.aetrion.flickr.people.User;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.comments.Comment;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: klexx
 * Date: 23.01.13
 * Time: 22:52
 * To change this template use File | Settings | File Templates.
 */
public interface DataSet {

    public void setAuthors(ArrayList<User> authors);
    public void setComments(ArrayList<Comment> comments);
    public void setComment(Comment comment, CommentResults results);
    public void setImage(Photo image);
    public void setRealImage(BufferedImage realImage);
    public void setOwner(User owner);

    public ArrayList<User> getAuthors();
    public LinkedHashMap<Comment, CommentResults> getComments();
    public BufferedImage getRealImage();
    public Photo getImage();
    public User getOwner();
}
