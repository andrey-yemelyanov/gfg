package org.gfg.graph;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class ArbitrageTest{
    @Test
    public void arbitrageAvailable(){
        List<String> arbitrage = Arbitrage.check(
            new String[]{"USD", "EUR", "GBP", "CHF", "CAD"}, 
            new double[][]{
                {1,     0.741, 0.657, 1.061, 1.005},
                {1.349, 1,     0.888, 1.433, 1.366},
                {1.521, 1.126, 1,     1.614, 1.538},
                {0.942, 0.698, 0.619, 1,     0.953},
                {0.995, 0.732, 0.650, 1.049, 1}
            });

        assertThat(arbitrage, is(Arrays.asList("EUR", "CAD", "USD", "EUR")));
    }
}