package com.mall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Yunkang Wu
 */
@Slf4j
public class CookieUtil {

    private final static String COOKIE_DOMAIN = ".mall.com";
    private final static String COOKIE_NAME = "mall_login_token";

    /**
     * 读取cookie
     *
     * @param request
     * @return
     */
    public static String readLoginToken(HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                log.info("read cookeName:{} cookeValue:{}", ck.getName(), ck.getValue());
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    log.info("read success cookeName:{} cookeValue:{}", ck.getName(), ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 写入cookie
     *
     * @param response
     * @param token
     */
    public static void writeLoginToken(HttpServletResponse response, String token) {
        Cookie ck = new Cookie(COOKIE_NAME, token);
        ck.setDomain(COOKIE_DOMAIN);
        ck.setPath("/");//代表在根目录

        //如果这个maxage不设置的话，cookie不会写入硬盘，写在内存，只在当前页面有效
        ck.setMaxAge(60 * 60 * 24 * 365);
        log.info("write cookeName:{} cookeValue:{}", ck.getName(), ck.getValue());
        response.addCookie(ck);
    }

    /**
     * 登出时，返回有效期为0的cookie给浏览器，浏览器会把该cookie删除
     *
     * @param request
     * @param response
     */
    public static void delLoginToken(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);//设置为0，代表删除此cookie
                    log.info("del cookeName:{} cookeValue:{}", ck.getName(), ck.getValue());
                    response.addCookie(ck);
                    return;//命中时，直接结束循环
                }
            }
        }
    }
}
