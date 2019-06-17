package org.gfg.misc.berkleetoberkley;

import static org.junit.Assert.assertThat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class PathFinderTest {

    private PathFinder pathFinder;

    public PathFinderTest() throws IOException {
        String nodeFilePath = this.getClass().getResource("/dijkstra/nhpn.nod").getFile();
        String linkFilePath = this.getClass().getResource("/dijkstra/nhpn.lnk").getFile();
        pathFinder = new PathFinder(RoadNetwork.fromFile(nodeFilePath, linkFilePath));
    }

    private String toString(double d) {
        return String.format(Locale.US, "%.4f", d);
    }

    @Test
    public void bostonMaBerkleyCa() throws PathFinderException {
        assertThat(toString(pathFinder.findShortestPath("BOSTON EAST BOST MA", "BERKELEY CA").getLength()),
                is("0.7436"));
    }

    @Test
    public void pasadenaCaCambridgeMa() throws PathFinderException {
        assertThat(toString(pathFinder.findShortestPath("PASADENA CBD  W CA", "NORTH CAMBRIDGE MA").getLength()),
                is("0.7135"));
    }

    @Test
    public void portlandMeSanFranciscoCa() throws PathFinderException {
        assertThat(toString(pathFinder.findShortestPath("PORTLAND NW ME", "SAN FRANCISCO CB CA").getLength()),
                is("0.7598"));
    }

    @Test
    public void pasadenaCaBellevueWa() throws PathFinderException, FileNotFoundException {
        ShortestPathResult sp = pathFinder.findShortestPath("PASADENA CBD  W CA", "BELLEVUE N WA");
        assertThat(toString(sp.getLength()), is("0.2727"));

        String kml = sp.toKml();
        assertThat(kml, is(not(nullValue())));
        writeToFile(kml, "PASADENA_BELLEVUE.kml");
    }

    @Test
    public void sacramentoNashville() throws PathFinderException, FileNotFoundException {
        ShortestPathResult sp = pathFinder.findShortestPath("SACRAMENTO NW CA", "NASHVILLE C-N TN");
        String kml = sp.toKml();
        assertThat(kml, is(not(nullValue())));
        writeToFile(kml, "SACRAMENTO_NASHVILLE.kml");
    }

    @Test
    public void newHavenCtCambridgeMa() throws PathFinderException {
        assertThat(toString(pathFinder.findShortestPath("NEW HAVEN NW CT", "NORTH CAMBRIDGE MA").getLength()),
                is("0.0325"));
    }

    @Test
    public void chicagoIlPortlandMe() throws PathFinderException, FileNotFoundException {
        ShortestPathResult sp = pathFinder.findShortestPath("N CHICAGO C-S IL", "PORTLAND NW ME");
        assertThat(toString(sp.getLength()), is("0.2568"));

        Node firstNode = sp.getShortestPath().get(0);
        assertThat(firstNode.getLongitude(), is(-87845383));
        assertThat(firstNode.getLatitude(), is(42322071));
        Node lastNode = sp.getShortestPath().get(sp.getShortestPath().size() - 1);
        assertThat(lastNode.getLongitude(), is(-70322357));
        assertThat(lastNode.getLatitude(), is(43701080));

        String kml = sp.toKml();
        assertThat(kml, is(not(nullValue())));
        writeToFile(kml, "CHICAGO_IL_PORTLAND_ME.kml");
    }

    @Test
    public void bellevueCambridge() throws PathFinderException {
        assertThat(toString(pathFinder.findShortestPath("BELLEVUE N WA", "NORTH CAMBRIDGE MA").getLength()),
                is("0.7100"));
    }

    @Test
    public void portlandBoston() throws PathFinderException {
        assertThat(toString(pathFinder.findShortestPath("PORTLAND C-NW OR", "BOSTON EAST BOST MA").getLength()),
                is("0.7247"));
    }

    @Test
    public void seattleMiami() throws PathFinderException, FileNotFoundException {
        ShortestPathResult sp = pathFinder.findShortestPath("SEATTLE AURORA V WA", "NORTH MIAMI BEAC FL");
        assertThat(toString(sp.getLength()), is("0.7706"));

        Node firstNode = sp.getShortestPath().get(0);
        assertThat(firstNode.getLongitude(), is(-122344559));
        assertThat(firstNode.getLatitude(), is(47779629));
        Node lastNode = sp.getShortestPath().get(sp.getShortestPath().size() - 1);
        assertThat(lastNode.getLongitude(), is(-80153976));
        assertThat(lastNode.getLatitude(), is(25925640));

        String kml = sp.toKml();
        assertThat(kml, is(not(nullValue())));
        writeToFile(kml, "SEATTLE_MIAMI.kml");
    }

    @Test
    public void laPortland() throws PathFinderException, FileNotFoundException {
        ShortestPathResult sp = pathFinder.findShortestPath("LOS ANGELES C-N CA", "PORTLAND NW ME");
        assertThat(toString(sp.getLength()), is("0.7297"));

        Node firstNode = sp.getShortestPath().get(0);
        assertThat(firstNode.getLongitude(), is(-118248871));
        assertThat(firstNode.getLatitude(), is(34102306));
        Node lastNode = sp.getShortestPath().get(sp.getShortestPath().size() - 1);
        assertThat(lastNode.getLongitude(), is(-70322357));
        assertThat(lastNode.getLatitude(), is(43701080));

        String kml = sp.toKml();
        assertThat(kml, is(not(nullValue())));
        writeToFile(kml, "LA_PORTLAND.kml");
    }

    private static void writeToFile(String str, String filePath) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("src/main/java/org/gfg/misc/berkleetoberkley/" + filePath);
        out.write(str);
        out.close();
    }
}