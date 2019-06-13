package org.gfg.misc.berkleetoberkley;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Reads data from National Highway Planning Network database.
 */
public class NhpnReader{

    /**
     * Reads link list from list file.
     * @param filePath link file path
     * @return list of link objects
     * @throws IOException
     */
    public static List<Link> readLinks(String filePath) throws IOException{
        return readLines(filePath).stream()
                                  .map(NhpnReader::toLink)
                                  .collect(Collectors.toList());
    }

    /**
     * Reads node list from node file.
     * @param filePath node file path
     * @return list of node objects
     * @throws IOException
     */
    public static List<Node> readNodes(String filePath) throws IOException {
        return readLines(filePath).stream()
                                  .map(NhpnReader::toNode)
                                  .collect(Collectors.toList());
    }

    private static List<String> readLines(String filePath) throws IOException {
        BufferedReader fileReader = null;
        try{
            List<String> lines = new ArrayList<>();
            fileReader = new BufferedReader(new FileReader(filePath));
            String line = null;
            while((line = fileReader.readLine()) != null){
                lines.add(line);
            }
            return lines;
        }finally{
            if(fileReader != null){
                fileReader.close();
            }
        }
    }

    private static Node toNode(String line){
        return new Node(
            Integer.parseInt(line.substring(23, 33).trim()), 
            Integer.parseInt(line.substring(33, 43).trim()), 
            Integer.parseInt(line.substring(43, 53).trim()), 
            Integer.parseInt(line.substring(88, 90).trim()), 
            line.substring(53, 88).trim());
    }

    private static Link toLink(String line){
        return new Link(
            Integer.parseInt(line.substring(33, 43).trim()),
            Integer.parseInt(line.substring(43, 53).trim()), 
            line.substring(53, 88).trim());
    }
}