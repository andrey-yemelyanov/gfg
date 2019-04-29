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

    @Test
    public void addOneTest(){
        assertThat(addOne(0), is(1));
        assertThat(addOne(12), is(13));
        assertThat(addOne(-1), is(0));
        assertThat(addOne(11), is(12));
    }

    @Test
    public void multBy3Dot5(){
        assertThat(multiplyBy3Dot5(0), is(0));
        assertThat(multiplyBy3Dot5(2), is(7));
        assertThat(multiplyBy3Dot5(5), is(17));
    }

    @Test
    public void powerOfFour(){
        assertThat(isPowerOf4(0), is(false));
        assertThat(isPowerOf4(1), is(true));
        assertThat(isPowerOf4(3), is(false));
        assertThat(isPowerOf4(4), is(true));
        assertThat(isPowerOf4(8), is(false));
        assertThat(isPowerOf4(16), is(true));
        assertThat(isPowerOf4(32), is(false));
        assertThat(isPowerOf4(64), is(true));
    }
}