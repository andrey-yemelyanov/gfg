package org.gfg.backtrack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class StrMatcherTest{
    @Test
    public void matchTest(){
        Map<Character, String> match = StrMatcher.match("GraphTreesGraph", "aba");
        assertThat(match, is(not(nullValue())));
        assertThat(match.size(), is(2));
        assertThat(match.get('a'), is("Graph"));
        assertThat(match.get('b'), is("Trees"));

        match = StrMatcher.match("GraphGraphGraph", "aaa");
        assertThat(match, is(not(nullValue())));
        assertThat(match.size(), is(1));
        assertThat(match.get('a'), is("Graph"));

        match = StrMatcher.match("GeeksforGeeks", "GfG");
        assertThat(match, is(not(nullValue())));
        assertThat(match.size(), is(2));
        assertThat(match.get('G'), is("Geeks"));
        assertThat(match.get('f'), is("for"));

        match = StrMatcher.match("GeeksforGeeks", "GG");
        assertThat(match, is(nullValue()));

        match = StrMatcher.match("GeeksforGeeks", "GeeksforGeeks");
        assertThat(match, is(not(nullValue())));
        assertThat(match.size(), is(7));
        assertThat(match.get('G'), is("G"));
        assertThat(match.get('e'), is("e"));
        assertThat(match.get('k'), is("k"));
        assertThat(match.get('s'), is("s"));
        assertThat(match.get('f'), is("f"));
        assertThat(match.get('o'), is("o"));
        assertThat(match.get('r'), is("r"));
    }
}