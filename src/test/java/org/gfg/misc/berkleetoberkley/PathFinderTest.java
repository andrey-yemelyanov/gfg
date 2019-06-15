package org.gfg.misc.berkleetoberkley;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class PathFinderTest{

    private PathFinder pathFinder;

    public PathFinderTest() throws IOException {
        String nodeFilePath = this.getClass().getResource("/dijkstra/nhpn.nod").getFile();
        String linkFilePath = this.getClass().getResource("/dijkstra/nhpn.lnk").getFile();
        pathFinder = new PathFinder(RoadNetwork.fromFile(nodeFilePath, linkFilePath));
    }

    private String toString(double d){
        return String.format("%.4f", d);
    }

    @Test
    public void bostonMaBerkleyCa() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("BOSTON EAST BOST MA", "BERKELEY CA").getLength()), 
            is("0.7436"));
    }

    @Test
    public void pasadenaCaCambridgeMa() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("PASADENA CBD  W CA", "NORTH CAMBRIDGE MA").getLength()), 
            is("0.7135"));
    }

    @Test
    public void portlandMeSanFranciscoCa() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("PORTLAND NW ME", "SAN FRANCISCO CB CA").getLength()), 
            is("0.7598"));
    }

    @Test
    public void pasadenaCaBellevueWa() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("PASADENA CBD  W CA", "BELLEVUE N WA").getLength()), 
            is("0.2727"));
    }

    @Test
    public void newHavenCtCambridgeMa() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("NEW HAVEN NW CT", "NORTH CAMBRIDGE MA").getLength()), 
            is("0.0325"));
    }

    @Test
    public void chicagoIlPortlandMe() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("N CHICAGO C-S IL", "PORTLAND NW ME").getLength()), 
            is("0.2568"));
    }

    @Test
    public void bellevueCambridge() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("BELLEVUE N WA", "NORTH CAMBRIDGE MA").getLength()), 
            is("0.7100"));
    }

    @Test
    public void portlandBoston() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("PORTLAND C-NW OR", "BOSTON EAST BOST MA").getLength()), 
            is("0.7247"));
    }

    @Test
    public void seattleMiami() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("SEATTLE AURORA V WA", "NORTH MIAMI BEAC FL").getLength()), 
            is("0.7706"));
    }

    @Test
    public void laPortland() throws PathFinderException {
        assertThat(
            toString(pathFinder.findShortestPath("LOS ANGELES C-N CA", "PORTLAND NW ME").getLength()), 
            is("0.7297"));
    }
}