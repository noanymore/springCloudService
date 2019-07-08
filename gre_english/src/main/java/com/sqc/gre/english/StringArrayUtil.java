package com.sqc.gre.english;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author < a href="sqc95111@163.com">sqc</ a>
 * @version 1.0
 * @ClassName StirngArrayUtil
 * @Description TODO
 * @date 2019/4/15 10:43
 **/
@Component
public class StringArrayUtil {

    private String[]  array;
    private int length;

    /**
     * 方法实现说明 TODO
     *
     * @param
     * @return StringUtils.concatenateStringArrays
     * @throws
     * @author < a href="sqc95111@163.com">sqc</ a>
     * @date 2019/4/15 10:54
     */
    public static String[] addArrayByTail(String[] a,String[] b){
        List resultList;
        resultList = CollectionUtils.arrayToList(a);
        for(String singleWord:b){
            resultList.add(singleWord);
        }
        String[] resultArray ;
        resultArray = (String[]) resultList.toArray();
        return resultArray;
    }

    public static String[] addSingleByTail(String[] a,String b){
        List resultList = CollectionUtils.arrayToList(a);
        resultList.add(b);
        String[] resultArray ;
        resultArray = (String[]) resultList.toArray();
        return resultArray;
    }

    /**
     * 方法实现说明 TODO
     * 打乱顺序
     * @param
     * @return
     * @throws
     * @author < a href="sqc95111@163.com">sqc</ a>
     * @date 2019/4/15 11:04
     */
    public static String[] disruptOrder(String[] a){
        Set collection = new HashSet<String>();
        for(String word:a) {
            collection.add(word);
        }
        String[] result = (String[]) collection.toArray(new String[collection.size()]);
        return result;
    }

    public static String[] devideStringToArray(String words){
        words = words.replaceAll("\r\n| |,",",");
        words = testCharactor(words);
        String[] array = words.split(",");
        return array;
    }



    private static String testCharactor(String words){
        char[] chars = words.toCharArray();
        int length = chars.length-1;
        for(int i=0;i<length;i++){
            if(chars[i]==','&&chars[i+1]==','){
                for(int index = i;index<length;index++){
                    chars[index] = chars[index+1];
                }
                length--;
            }
        }
        words = String.valueOf(chars);
        return words;
    }



}
