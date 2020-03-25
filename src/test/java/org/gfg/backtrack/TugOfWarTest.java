package org.gfg.backtrack;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class TugOfWarTest{
    @Test
    public void tugOfWarTest(){
        boolean[] solution = TugOfWar.solve(new int[]{3, 4, 5, -3, 100, 1, 89, 54, 23, 20});
        assertThat(solution, is(new boolean[]{true, false, true, true, false, false, true, true, false, false}));

        solution = TugOfWar.solve(new int[]{23, 45, -34, 12, 0, 98, -99, 4, 189, -1, 4});
        assertThat(solution, is(new boolean[]{false, true, true, true, false, true, false, false, false, true,false}));
    }
}