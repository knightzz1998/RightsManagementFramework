package cn.knightzz.utils;

import cn.hutool.core.util.RandomUtil;

/**
 * @author knightzz98
 * @title: UuidUtils
 * @projectName SpringSecurityChapter
 * @description: 用于生成id的工具类
 * @date 2021/8/24 11:22
 */
public class UuidUtils {

    /**
     * 随机生辰指定位数的UID
     *
     * @param length
     * @return
     */
    public static String getUUID(int length) {
        return RandomUtil.randomNumbers(length);
    }
}
