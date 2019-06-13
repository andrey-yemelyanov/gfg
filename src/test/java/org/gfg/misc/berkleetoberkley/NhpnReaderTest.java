package org.gfg.misc.berkleetoberkley;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import java.util.List;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class NhpnReaderTest{
    @Test
    public void readNodes() throws IOException {
        String file = this.getClass().getResource("/dijkstra/nhpn.nod").getFile();
        List<Node> nodes = NhpnReader.readNodes(file);
        assertThat(nodes.size(), is(90415));
        
        Node node = nodes.get(42348);
        assertThat(node.getNodeId(), is(28000897));
        assertThat(node.getLongitude(), is(-90254463));
        assertThat(node.getLatitude(), is(32304264));
        assertThat(node.getState(), is("MS"));
        assertThat(node.getDescription(), is("JACKSON VAN WINK"));

        node = nodes.get(26192);
        assertThat(node.getNodeId(), is(18001302));
        assertThat(node.getLongitude(), is(-87414131));
        assertThat(node.getLatitude(), is(39472687));
        assertThat(node.getState(), is("IN"));
        assertThat(node.getDescription(), is("TERRE HAUTE C-N"));

        node = nodes.get(14032);
        assertThat(node.getNodeId(), is(9000748));
        assertThat(node.getLongitude(), is(-72979561));
        assertThat(node.getLatitude(), is(41336983));
        assertThat(node.getState(), is("CT"));
        assertThat(node.getDescription(), is("NEW HAVEN NW"));

        node = nodes.get(65385);
        assertThat(node.getNodeId(), is(42000986));
        assertThat(node.getLongitude(), is(-78992416));
        assertThat(node.getLatitude(), is(40944500));
        assertThat(node.getState(), is("PA"));
        assertThat(node.getDescription(), is("PUNXSUTAWNEY W"));
    }

    @Test
    public void readLinks() throws IOException {
        String file = this.getClass().getResource("/dijkstra/nhpn.lnk").getFile();
        List<Link> links = NhpnReader.readLinks(file);
        assertThat(links.size(), is(125302));

        Link link = links.get(101040);
        assertThat(link.getaNode(), is(47001065));
        assertThat(link.getbNode(), is(47001071));
        assertThat(link.getDescription(), is("PELLISIPPI PKWY"));

        link = links.get(89146);
        assertThat(link.getaNode(), is(41530945));
        assertThat(link.getbNode(), is(41000024));
        assertThat(link.getDescription(), is("LEWIS AND CLARK BRIDGE"));

        link = links.get(84287);
        assertThat(link.getaNode(), is(39000147));
        assertThat(link.getbNode(), is(39000148));
        assertThat(link.getDescription(), is("JAMES W SHOCKNESSY OHIO TPKE"));
    }
}