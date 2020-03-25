package org.gfg.backtrack;

import java.util.*;

public class StrMatcher{
    public static Map<Character, String> match(String str, String pattern){
        if(pattern.length() > str.length()) return null;
        Map<Character, String> map = new HashMap<>();
        if(!match(map, str, pattern, 0, 0)) return null;
        return map;
    }

    private static boolean match(Map<Character, String> m, String str, String pattern, int s, int p){
        if(p == pattern.length() && s == str.length()) return true;
        if(p == pattern.length()) return false;
        
        char subPattern = pattern.charAt(p);
        if(m.containsKey(subPattern)){
            String charSeq = m.get(subPattern);
            if(s + charSeq.length() > str.length()) return false;
            if(!str.substring(s, s + charSeq.length()).equals(charSeq)) return false;
            return match(m, str, pattern, s + charSeq.length(), p + 1);
        }

        for(int i = s; i < str.length(); i++){
            m.put(subPattern, str.substring(s, i + 1));
            if(match(m, str, pattern, i + 1, p + 1)) return true;
            m.remove(subPattern);
        }

        return false;
    }
}