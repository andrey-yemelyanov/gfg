package org.gfg.math.bitalg;

import static org.junit.Assert.assertThat;
import java.util.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static org.gfg.math.bitalg.BitUtil.*;

public class BitUtilTest{
    @Test
    public void oppositeSignsTest(){
        assertThat(oppositeSigns(0, 0), is(false));
        assertThat(oppositeSigns(1, 2), is(false));
        assertThat(oppositeSigns(-1, -2), is(false));
        assertThat(oppositeSigns(0, -1), is(true));
        assertThat(oppositeSigns(-1, 0), is(true));
        assertThat(oppositeSigns(-1, 2), is(true));
        assertThat(oppositeSigns(-2, 1), is(true));
    }
}