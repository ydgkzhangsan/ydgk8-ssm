package com.ydgk.crowd;

import com.ydgk.ssm.util.CrowdUtil;
import org.junit.Test;

/**
 * @author kfstart
 * @descrption
 * @create 2020-06-30 14:03
 */
public class CrowUtilTest {

    @Test
    public void testMD5(){
        String s = CrowdUtil.md5("123456");
        System.out.println("s = " + s);
    }
}
