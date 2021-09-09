package com.test.calc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class KMPTest {

    private String str = "ABCABCDHIJK";

    private String tmpStr = "ABCD";

    @Test
    public void forceTest() {
        char[] charArr = str.toCharArray();
        char[] tmpArr = tmpStr.toCharArray();
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
    public void KMP(){

    }
}
