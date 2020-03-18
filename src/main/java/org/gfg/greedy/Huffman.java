package org.gfg.greedy;

import java.util.*;
import org.gfg.math.bitalg.BitUtil;

/**
 * Implements Huffman encoding and decoding of text in UTF-8 format.
 * UTF-8 (8-bit Unicode Transformation Format) is a variable width 
 * character encoding capable of encoding all 1,112,064 valid code points 
 * in Unicode using one to four one-byte (8-bit) code units. 
 */
public class Huffman{

    /**
     * Represents a result of Huffman encoding.
     */
    public static class Result{
        /**
         * Huffman tree
         */
        public Node tree;

        /**
         * Encoded text in byte format
         */
        public byte[] encodedText;

        /**
         * Returns number of bits in this encoding
         */
        public int bitLength;
    }

    /**
     * Huffman binary tree node.
     * The Huffman binary tree for UTF-8 encoded text will have a height of at 
     * least of ceil(log(1,112,064)) = 7.
     * But it is not necessarily a balanced binary tree - all depends on character 
     * frequencies. So the actual height and therefore max code length will vary.
     * 1,112,064 gives a good upper bound.
     */
    public static class Node{
        /**
         * Node frequency
         */
        int freq;

        /**
         * Character represented by this node
         */
        Character c;

        /**
         * Node's left child
         */
        Node left;

        /**
         * Node's right child
         */
        Node right;
    }

    /**
     * Lossless compression of suppled text using Huffman's algorithm.
     * @param original original text
     * @return {@link Result}
     */
    public static Result encode(String originalText){
        Node tree = buildTree(buildFreqTable(originalText));
        Map<Character, String> charCodes = buildEncodingTable(tree);

        StringBuilder sb = new StringBuilder();
        for(char c : originalText.toCharArray()){
            sb.append(charCodes.get(c));
        }
        
        String bitString = sb.toString();
        Result result = new Result();
        result.tree = tree;
        result.encodedText = BitUtil.toByteArray(pad(bitString));
        result.bitLength = bitString.length();
        return result;
    }

    // pads bit string with zeros so that the total bit length is a multiple of 8
    private static String pad(String bitString){
        int paddingLen = 8 - (bitString.length() % 8);
        for(int i = 0; i < paddingLen; i++){
            bitString += "0";
        }
        return bitString;
    }

    private static Map<Character, String> buildEncodingTable(Node tree){
        Map<Character, String> map = new HashMap<>();
        buildEncodingTableRec(tree, "", map);
        return map;
    }

    private static void buildEncodingTableRec(Node root, String code, Map<Character, String> map){
        if(root.left == null && root.right == null && root.c != null) {
            map.put(root.c, code);
            return;
        }
        buildEncodingTableRec(root.left, code + '0', map);
        buildEncodingTableRec(root.right, code + '1', map);
    }

    private static Map<Character, Integer> buildFreqTable(String s){
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : s.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }

    private static Node buildTree(Map<Character, Integer> freq){
        PriorityQueue<Node> pq = new PriorityQueue<Node>((Node n1, Node n2) -> Integer.compare(n1.freq, n2.freq));
        for(char c : freq.keySet()){
            Node node = new Node();
            node.freq = freq.get(c);
            node.c = c;
            pq.add(node);
        }

        while(pq.size() > 1){
            Node left = pq.remove();
            Node right = pq.remove();
            
            Node node = new Node();
            node.freq = left.freq + right.freq;
            node.left = left;
            node.right = right;

            pq.add(node);
        }

        return pq.remove();
    }

    /**
     * Decodes Huffman-encoded text.
     * @param encodedText encoded text bytes
     * @param tree Huffman tree used to encode the text
     * @param bitLen total number of bits in the encoded text
     * @return decoded text
     */
    public static String decode(byte[] encodedText, Node tree, int bitLen){
        StringBuilder decoded = new StringBuilder();
        
        Node current = tree;
        // process all bytes but the last one
        for(int i = 0; i < encodedText.length - 1; i++){
            for(int j = 0; j < 8; j++){
                int bitValue = encodedText[i] & (1 << (7 - j));
                if(bitValue == 0) current = current.left;
                else current = current.right;
                if(current.c != null){
                    decoded.append(current.c);
                    current = tree;
                }
            }
        }

        // process the last byte
        byte lastByte = encodedText[encodedText.length - 1];
        for(int i = 0; i < (bitLen % 8); i++){
            int bitValue = lastByte & (1 << (7 - i));
            if(bitValue == 0) current = current.left;
            else current = current.right;
            if(current.c != null){
                decoded.append(current.c);
                current = tree;
            }
        }
        
        return decoded.toString();
    }
}