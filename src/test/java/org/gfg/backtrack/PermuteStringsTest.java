package org.gfg.backtrack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class PermuteStringsTest{
    @Test
    public void permuteString(){
        final String s = "hold";
        List<String> p = PermuteStrings.permute(s);
        System.out.println(p);
        assertThat(p.size(), is(24));
    }
}