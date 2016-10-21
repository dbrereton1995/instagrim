/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public String getDescription() {

        Cluster cluster = CassandraHosts.getCluster();

        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("SELECT description FROM pics WHERE picid = ?");
        ResultSet rs = null;
        BoundStatement bs = new BoundStatement(ps);
        rs = session.execute(bs.bind(this.UUID));
        if (rs.isExhausted()) {

            return "";
        } else {
            Row row = rs.one();
            String description = row.getString("description");
            return description;
        }

    }
}
