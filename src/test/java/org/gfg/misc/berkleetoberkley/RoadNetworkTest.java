package org.gfg.misc.berkleetoberkley;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class RoadNetworkTest{
    @Test
    public void buildRoadNetwork() throws IOException {
        String nodeFilePath = this.getClass().getResource("/dijkstra/nhpn.nod").getFile();
        String linkFilePath = this.getClass().getResource("/dijkstra/nhpn.lnk").getFile();
        RoadNetwork network = RoadNetwork.fromFile(nodeFilePath, linkFilePath);

        assertThat(network.getNodes().size(), is(90415));

        Node node = network.getNode("NYHARRISON MANHATT");
        assertThat(node, is(not(nullValue())));
        assertThat(node.getNodeId(), is(36004404));
        assertThat(node.getLongitude(), is(-73710609));
        assertThat(node.getLatitude(), is(41022255));
        assertThat(node.getState(), is("NY"));

        node = network.getNode("MOMOUND CITY W");
        assertThat(node, is(not(nullValue())));
        assertThat(node.getNodeId(), is(29000104));
        assertThat(node.getLongitude(), is(-95237350));
        assertThat(node.getLatitude(), is(40132011));
        assertThat(node.getState(), is("MO"));

        node = network.getNode("ILPRAIRIE CITY S");
        assertThat(node, is(not(nullValue())));
        assertThat(node.getNodeId(), is(17001501));
        assertThat(node.getLongitude(), is(-90464752));
        assertThat(node.getLatitude(), is(40584671));
        assertThat(node.getState(), is("IL"));

        node = network.getNode("CTNEW HAVEN YALE B");
        assertThat(node, is(not(nullValue())));
        assertThat(node.getNodeId(), is(9000789));
        assertThat(node.getLongitude(), is(-72954041));
        assertThat(node.getLatitude(), is(41310520));
        assertThat(node.getState(), is("CT"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nonExistentNode() throws IOException {
        String nodeFilePath = this.getClass().getResource("/dijkstra/nhpn.nod").getFile();
        String linkFilePath = this.getClass().getResource("/dijkstra/nhpn.lnk").getFile();
        RoadNetwork network = RoadNetwork.fromFile(nodeFilePath, linkFilePath);
        network.getNode("New York");
    }

    @Test
    public void adjacentNodes() throws IOException {
        String nodeFilePath = this.getClass().getResource("/dijkstra/nhpn.nod").getFile();
        String linkFilePath = this.getClass().getResource("/dijkstra/nhpn.lnk").getFile();
        RoadNetwork network = RoadNetwork.fromFile(nodeFilePath, linkFilePath);
        Node node = network.getNode("NYHARRISON MANHATT");
        assertThat(network.adjacentNodes(node.getNodeId()).size(), is(not(0)));
    }

    @Test
    public void nodeNameParse() throws Exception {
        assertThat(Node.toNodeName("NEW HAVEN YALE B CT"), is("CTNEW HAVEN YALE B"));
        assertThat(Node.toNodeName("ROYAL LAKES IL"), is("ILROYAL LAKES"));
        assertThat(Node.toNodeName("GREEN BROOK W NJ"), is("NJGREEN BROOK W"));
        assertThat(Node.toNodeName("CAMBRIDGE MA"), is("MACAMBRIDGE"));
    }
}