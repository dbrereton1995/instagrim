package uk.ac.dundee.computing.djb.instagrim.stores;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.Bytes;
import java.nio.ByteBuffer;
import uk.ac.dundee.computing.djb.instagrim.lib.CassandraHosts;

/**
 *
 * @author Administrator
 */
public class Pic {

    private ByteBuffer bImage = null;
    private int length;
    private String type;
    private java.util.UUID UUID = null;

    public void Pic() {

    }

    public void setUUID(java.util.UUID UUID) {
        this.UUID = UUID;
    }

    public String getSUUID() {
        return UUID.toString();
    }

    public void setPic(ByteBuffer bImage, int length, String type) {
        this.bImage = bImage;
        this.length = length;
        this.type = type;
    }

    public ByteBuffer getBuffer() {
        return bImage;
    }

    public int getLength() {
        return length;
    }

    public String getType() {
        return type;
    }

    public byte[] getBytes() {

        byte image[] = Bytes.getArray(bImage);
        return image;
    }

    /**
     * Returns the string to be used as a brief description below the image
     *
     * @return String description
     */
    public String getDescription() {
        
        Cluster cluster = CassandraHosts.getCluster();
        Session session = cluster.connect("instagrim");
        
        //CQL Statement which selects the description field from the pic table with the corresponding picid
        PreparedStatement ps = session.prepare("SELECT description FROM pics WHERE picid = ?");
        ResultSet rs = null;
        BoundStatement bs = new BoundStatement(ps);
        
        //execute using the UUID of the image
        rs = session.execute(bs.bind(this.UUID));
        
        //if the image isn't found, return an empty string
        if (rs.isExhausted()) {
            return "";
            //else select the first(only) corresponding row
        } else {
            Row row = rs.one();
            String description = row.getString("description");
            
            //if the description is null, change it to an empty string
            if (description == null) {
                description = "";
            }
            return description;
        }

    }
}
