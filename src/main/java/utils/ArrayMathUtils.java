package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayMathUtils {

    public static Integer getNextGreaterNumber(Integer n, ArrayList<Integer> arr) {
        List<List<Integer>> list = Collections.singletonList(arr);
        list.forEach(Collections::sort);
        arr.sort(Integer::compareTo);
        int i = arr.indexOf(n);
        if(arr.size() == (i+1)){
            return arr.get(i);
        }
        else {
            return arr.get(i + 1);
        }
    }
}
