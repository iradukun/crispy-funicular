package com.prwebsitebackend.utils;

import javax.servlet.http.HttpServletRequest;

public final class HttpUtils {
        private final static String IE11 = "rv:11.0";
        private final static String IE10 = "MSIE 10.0";
        private final static String IE9 = "MSIE 9.0";
        private final static String IE8 = "MSIE 8.0";
        private final static String IE7 = "MSIE 7.0";
        private final static String IE6 = "MSIE 6.0";
        private final static String FIREFOX = "Firefox";
        private final static String OPERA = "Opera";
        private final static String CHROME = "Chrome";
        private final static String SAFARI = "Safari";

        public static String getBrowserType(HttpServletRequest request) {
            String browserType = null;
            if (getBrowserType(request, IE11)) {
                browserType = "IE11";
            }
            if (getBrowserType(request, IE10)) {
                browserType = "IE10";
            }
            if (getBrowserType(request, IE9)) {
                browserType = "IE9";
            }
            if (getBrowserType(request, IE8)) {
                browserType = "IE8";
            }
            if (getBrowserType(request, IE7)) {
                browserType = "IE7";
            }
            if (getBrowserType(request, IE6)) {
                browserType = "IE6";
            }
            if (getBrowserType(request, FIREFOX)) {
                browserType = "Firefox";
            }
            if (getBrowserType(request, SAFARI)) {
                browserType = "Safari";
            }
            if (getBrowserType(request, CHROME)) {
                browserType = "Chrome";
            }
            if (getBrowserType(request, OPERA)) {
                browserType = "Opera";
            }
            if (getBrowserType(request, "Camino")) {
                browserType = "Camino";
            }
            return browserType;
        }
        private static boolean getBrowserType(HttpServletRequest request,
                                              String brosertype) {
            return request.getHeader("USER-AGENT").toLowerCase()
                    .indexOf(brosertype) > 0 ? true : false;
        }
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"
    };

    private HttpUtils() {
    }

    public static String getRequestIP(HttpServletRequest request) {
        for (String header : IP_HEADERS) {
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) {
                continue;
            }
            String[] parts = value.split("\\s*,\\s*");
            return parts[0];
        }
        return request.getRemoteAddr();
    }
}