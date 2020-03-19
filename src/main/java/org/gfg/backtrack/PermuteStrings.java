package org.gfg.backtrack;

import java.util.*;

public class PermuteStrings{

    public static List<String> permute(String s){
        List<String> permutations = new ArrayList<>();
        permute(s.toCharArray(), 0, permutations);
        return permutations;
    }
    private static void permute(char[] s, int i, List<String> p){
        if(i == s.length){
            p.add(new String(s));
            return;
        }

        for(int j = i; j < s.length; j++){
            swap(s, i, j);
            permute(s, i + 1, p);
            swap(s, i, j);
        }
    }

    private static void swap(char[] s, int i, int j){
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}