package pae.team8.flickrminr.flickr;

import pae.team8.flickrminr.data.DataSet;
import pae.team8.flickrminr.exceptions.MinrException;

/**
 * Created with IntelliJ IDEA.
 * User: klexx
 * Date: 23.01.13
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public interface Connector {
    public boolean Connect();
    public DataSet fetchData(String id) throws MinrException;
}
