package org.gfg.graph;

import java.util.*;

import org.gfg.Dictionary;
import org.gfg.graph.BellmanFord.Result;
import org.gfg.hash.HashDictionary;

/**
 * Solves currency arbitrage problem.
 * See https://www.investopedia.com/terms/c/currency-arbitrage.asp for reference
 */
public class Arbitrage{
    /**
     * Checks for arbitrage opportunities for a given set of currencies and corresponding exchange rates.
     * @param currencies list of currency codes
     * @param exchangeRates matrix of exchange rates for the supplied currencies
     * @return an arbitrage sequence if available, null otherwise
     */
    public static List<String> check(String[] currencies, double[][] exchangeRates){
        final String s = "_SOURCE_";
        // build graph
        Dictionary<String, Integer> ci = new HashDictionary<>();
        for(int i = 0; i < currencies.length; i++){
            ci.add(currencies[i], i);
        }
        Dictionary<String, List<WeightedEdge<String>>> graph = new HashDictionary<>();
        graph.add(s, new ArrayList<>());
        for(String c : currencies){
            graph.add(c, new ArrayList<>());
            graph.get(s).add(new WeightedEdge<String>(c, 0.0));
        }
        for(String c1 : currencies){
            for(String c2 : currencies){
                if(c1 != c2){
                    graph.get(c1).add(new WeightedEdge<>(c2, -Math.log(
                        exchangeRates[ci.get(c1)][ci.get(c2)])));
                }
            }
        }

        // run Bellman-Ford on the graph
        Result<String> result = BellmanFord.run(graph, s);

        return result.negativeWeightCycle();
    }
}