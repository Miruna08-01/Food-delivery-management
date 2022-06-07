package DAO;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CustomComparator implements Comparator<String>
{
    TreeMap<String, Integer> map = new TreeMap<String, Integer>();

    public CustomComparator(TreeMap<String, Integer> map) {
        this.map.putAll(map);

    }

    @Override
    public int compare(String o1, String o2) {
        if(map.get(o1) >= map.get(o2)){
            return -1;
        }else{
            return 1;
        }

    }
}