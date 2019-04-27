package org.gfg.math;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import java.util.*;

public class MathTest{
    @Test
    public void factorizeTest(){
        assertThat(MathUtils.factorize(100), is(Arrays.asList(1, 2, 4, 5, 10, 20, 25, 50, 100)));
        assertThat(MathUtils.factorize(125), is(Arrays.asList(1, 5, 25, 125)));
        assertThat(MathUtils.factorize(1), is(Arrays.asList(1)));
        assertThat(MathUtils.factorize(13), is(Arrays.asList(1, 13)));
    }
}