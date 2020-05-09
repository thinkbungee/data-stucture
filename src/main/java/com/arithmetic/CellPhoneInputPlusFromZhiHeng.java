package com.arithmetic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 杜志恒写的九宫格程序
 */
public class CellPhoneInputPlusFromZhiHeng {

    public static Map<String, List<String>> strConf = new HashMap<String, List<String>>() {{
        put("1", Arrays.asList(",", "."));
        put("2", Arrays.asList("a", "b", "c"));
        put("3", Arrays.asList("d", "e", "f"));
        put("4", Arrays.asList("g", "h", "i"));
        put("5", Arrays.asList("j", "k", "l"));
        put("6", Arrays.asList("m", "n", "o"));
        put("7", Arrays.asList("p", "q", "r", "s"));
        put("8", Arrays.asList("t", "u", "v"));
        put("9", Arrays.asList("w", "x", "y", "z"));
    }};

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String testInput;
        while ((testInput = bufferedReader.readLine()) != null) {
            long startTime = System.currentTimeMillis();
            String[] ss = testInput.split("#");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ss.length; i++) {
                if (i % 2 == 0) {
                    if (ss[i] != null && ss[i].length() != 0) {
                        for (int i1 = 0; i1 < ss[i].length(); i1++) {
                            if (strConf.containsKey(String.valueOf(ss[i].charAt(i1)))) {
                                sb.append(ss[i].charAt(i1));
                            }
                        }
                    }
                } else {
                    sb.append(OOO(0, 0, null, ss[i], new StringBuilder()));
                }
            }
            System.out.println(sb.toString());
            long endTime = System.currentTimeMillis();
            System.out.println("花费时间：" + (endTime - startTime));
        }

    }

    public static String OOO(int i, int charIdx, String lastChar, String inputVal,
        StringBuilder respVal) {
        if (inputVal != null && inputVal.length() != 0 && i <= inputVal.length()) {
            String nextChar = i == inputVal.length() ? "" : String.valueOf(inputVal.charAt(i));
            if (i == 0 || (StringUtils.isNotEmpty(lastChar) && lastChar
                .equalsIgnoreCase(nextChar))) {
                OOO(++i, ++charIdx, nextChar, inputVal, respVal);
            } else {
                if (strConf.containsKey(lastChar)) {
                    List<String> ll = strConf.get(lastChar);
                    if (charIdx < ll.size()) {
                        respVal.append(ll.get(charIdx));
                    } else {
                        respVal.append(ll.get((charIdx - 1) % ll.size()));
                    }
                }
                OOO(++i, 0, nextChar, inputVal, respVal);
            }
        }
        return respVal.toString();
    }
}
