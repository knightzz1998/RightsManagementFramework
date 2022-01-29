package cn.knightzz.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author knightzz98
 * @title: PassWordEncoderUtils
 * @projectName SpringSecurityChapter
 * @description:
 * @date 2021/8/24 11:54
 */
public class PassWordEncoderUtils {

    /**
     * 对字符串进行加密
     * @param seq 待加密的字符串
     * @return 加密结果
     */
    public static String encode(String seq){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(seq);
    }
}
