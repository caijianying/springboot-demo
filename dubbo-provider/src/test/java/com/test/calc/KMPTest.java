package com.test.calc;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class KMPTest {
    //String mainStr = "ABABABABCA";//主串
    //String modeStr = "ABABABCA";//模式串

    String mainStr = "BBC ABCDAB ABCDABCDABDE";//主串
    String modeStr = "ABCDABD";//模式串

    @org.junit.jupiter.api.Test
    public void forceTest() {
        char[] charArr = mainStr.toCharArray();
        char[] tmpArr = modeStr.toCharArray();
        int indexI = 0;
        int indexJ = 0;

        int start = 0;
        int matchCount = tmpArr.length;
        while (start <= charArr.length) {
            if (charArr[indexI] == tmpArr[indexJ]) {
                indexI++;
                indexJ++;
                matchCount--;
            } else {
                log.error("{} and " + "{} not match!", charArr[indexI], tmpArr[indexJ]);
                start++;
                indexI = start;
                indexJ = 0;
                matchCount = tmpArr.length;
            }

            if (matchCount == 0) {
                log.info("匹配成功,起始位置为{}", start);
                break;
            }
        }
    }

    @Test
    public void testKMP() {
        //        int[] next = getNext(modeStr);
        //        log.info(JSON.toJSONString(next));

        int[] next = getNextArr(modeStr);
        ////int[] next = getNext2(modeStr);
        ////int[] next = getNext(modeStr);
        log.info(JSON.toJSONString(next));

        final char[] charArr = mainStr.toCharArray();
        final char[] modeCharArr = modeStr.toCharArray();
        final int charLen = charArr.length;
        final int modeCharLen = modeCharArr.length;
        int match = 0;
        for (int i = 0; i < charLen - modeCharLen; ) {
            int offset = 1;
            for (int j = 0, startIndex = i; j < modeCharLen; j++) {
                int indexToBeMoved = next[j];
                log.info("{}与{}比较", modeCharArr[j], charArr[startIndex]);
                if (charArr[startIndex] == modeCharArr[j]) {
                    match++;
                    startIndex++;
                    if (match == modeCharLen) {
                        //计算最终串的初始下标
                        int initIndex = (--startIndex) - (modeCharLen - 1);
                        log.info("找到下标【" + initIndex + "】，值为【" + charArr[initIndex] + "】");
                    }
                } else if (indexToBeMoved == -1) {
                    startIndex++;
                    match = 0;
                    break;
                } else if (indexToBeMoved >= 0) {
                    offset = (modeCharLen - indexToBeMoved - 1);
                    match = 0;
                    break;
                }
            }
            i += offset;
        }

    }

    public static int[] getNextArr(String ps) {

        char[] p = ps.toCharArray();

        int[] next = new int[p.length];

        next[0] = -1;

        int j = 0;

        int k = -1;

        while (j < p.length - 1) {

            if (k == -1 || p[j] == p[k]) {

                next[++j] = ++k;

            } else {

                k = next[k];

            }

        }

        return next;

    }

    public static int[] getNext2(String ps) {

        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        int q, k;//q:模版字符串下标；k:最大前后缀长度
        int m = p.length;//模版字符串长度
        next[0] = 0;
        for (q = 1, k = 0; q < m; ++q)//for循环，从第二个字符开始，依次计算每一个字符对应的next值
        {
            while (k > 0 && p[q] != p[k])//递归的求出P[0]···P[q]的最大的相同的前后缀长度k
            { k = next[k - 1]; }
            if (p[q] == p[k])//如果相等，那么最大相同前后缀长度加1
            {
                k++;
            }
            next[q] = k;
        }
        return next;
    }

    /**
     * 计算最长前后缀
     *
     * @param modeStr
     * @return
     */
    private int[] getNext(String modeStr) {
        final int[] result = new int[modeStr.length()];
        result[0] = -1;
        result[1] = 0;

        final char[] modeChars = modeStr.toCharArray();
        for (int i = 1; i < modeChars.length - 2; i++) {
            char[] part = new char[i + 1];
            for (int j = 0; j < part.length; j++) {
                part[j] = modeChars[j];
            }
            result[i + 1] = calcuMax(part);
        }
        log.info(JSON.toJSONString(result));
        return result;
    }

    /**
     * 计算最长前后缀
     *
     * @return
     */
    private Integer calcuMax(char[] charArr) {
        String[] prefixStr = new String[charArr.length - 1];
        for (int i = 1; i < prefixStr.length + 1; i++) {
            char[] chars = new char[i];
            for (int j = 0; j < i; j++) {
                chars[j] = charArr[j];
            }
            prefixStr[i - 1] = new String(chars);
        }
        log.info(JSON.toJSONString(charArr) + "的前缀：" + JSON.toJSONString(prefixStr));
        String[] suffixStr = new String[charArr.length - 1];

        char[] suffix = new char[charArr.length - 1];
        for (int i = 0; i < suffixStr.length; i++) {
            suffix[i] = charArr[i + 1];
        }

        for (int i = suffix.length - 1, maxCount = 1; i >= 0; i--, maxCount++) {
            char[] chars = new char[maxCount];
            for (int j = 0, k = i; j <= maxCount - 1; j++, k++) {
                chars[j] = suffix[k];
            }
            suffixStr[i] = new String(chars);
        }
        log.info(JSON.toJSONString(charArr) + "的后缀：" + JSON.toJSONString(suffixStr));

        //比较前后缀相同情况下最大的长度
        return compare(prefixStr, suffixStr);
    }

    private Integer compare(String[] prefix, String[] suffix) {
        int matchCount = 0;
        for (int i = 0; i < prefix.length; i++) {
            int prefixLen = prefix[i].length();
            for (int j = 0; j < suffix.length; j++) {
                if (prefix[i].equals(suffix[j]) && prefixLen > matchCount) {
                    matchCount = prefixLen;
                }
            }
        }
        return matchCount;
    }

}
