package pae.team8.flickrminr.data;

import com.aetrion.flickr.people.User;
import com.aetrion.flickr.photos.Photo;
import com.aetrion.flickr.photos.comments.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FlickrData implements DataSet{
	Logger logger = LoggerFactory.getLogger("pae.team8.flickrminr.data.FlickrData");

	private ArrayList<User> _authors;
    private LinkedHashMap<Comment, CommentResults> _comments;
	private Photo _image;
	private User _owner;
	private BufferedImage _realImage;

	public FlickrData() {
		_authors = new ArrayList<User>();
		_comments = new LinkedHashMap<Comment, CommentResults>();
	}

    @Override
	public void setAuthors(ArrayList<User> authors) {
		this._authors = authors;
	}
    @Override
	public void setComments(ArrayList<Comment> comments) {
		for(Comment comment : comments){
			if(comment != null && comment.getText().length() >= 10){
				_comments.put(comment, null);
			}
		}
	}
    public void setComment(Comment comment, CommentResults results){
        _comments.put(comment, results);
    }
    @Override
	public void setImage(Photo image) {
		_image = image;
	}
    @Override
	public void setRealImage(BufferedImage realImage) {
		_realImage = realImage;
	}
    @Override
	public void setOwner(User owner){
        _owner = _owner;
    }
    @Override
	public ArrayList<User> getAuthors(){
		return _authors;
	}
    @Override
	public LinkedHashMap<Comment, CommentResults> getComments(){
		return _comments;
	}
    @Override
	public BufferedImage getRealImage(){
		return _realImage;
	}
    @Override
	public Photo getImage(){
		return _image;
	}
    @Override
	public User getOwner(){
		return _owner;
	}
}