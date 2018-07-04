package com.chaoliu1995.english.util;


import lombok.Builder;
import lombok.ToString;
import okhttp3.*;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: ChaoLiu
 * @Description:
 * @Date: 2018/7/4 11:41
 */
public class HttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    
    public final static String GET = "GET";
    public final static String POST = "POST";
    public final static String PUT = "PUT";
    public final static String DELETE = "DELETE";
    public final static String PATCH = "PATCH";

    private final static String UTF8 = "UTF-8";

    private final static String DEFAULT_CHARSET = UTF8;
    private final static String DEFAULT_METHOD = GET;
    private final static String DEFAULT_MEDIA_TYPE = MediaType.APPLICATION_JSON_UTF8_VALUE;
    private static boolean DEFAULT_LOG = false;

    private final static OkHttpClient client = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(20, 5, TimeUnit.MINUTES))
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS).build();

    /**
     * GET请求
     * @param url
     * URL地址
     * @return
     */
    public static String get(String url){
        return execute(OkHttp.builder().url(url).build());
    }
    /**
     * GET请求
     * @param url
     * URL地址
     * @return
     */
    public static String get(String url, String charset){
        return execute(OkHttp.builder().url(url).responseCharset(charset).build());
    }

    /**
     * 带查询参数的GET查询
     * @param url
     * URL地址
     * @param queryMap
     * 查询参数
     * @return
     */
    public static String get(String url,  Map<String, String> queryMap){
        return execute(OkHttp.builder().url(url).queryMap(queryMap).build());
    }

    /**
     * 带查询参数的GET查询
     * @param url
     * URL地址
     * @param queryMap
     * 查询参数
     * @return
     */
    public static String get(String url,  Map<String, String> queryMap, String charset){
        return execute(OkHttp.builder().url(url).queryMap(queryMap).responseCharset(charset).build());
    }

    /**
     * POST
     * application/json
     * @param url
     * @param json
     * @return
     */
    public static String postJson(String url, String json){
        return execute(OkHttp.builder().url(url).method(POST).data(json).mediaType(MediaType.APPLICATION_JSON_VALUE).build());
    }

    /**
     * POST
     * application/x-www-form-urlencoded
     * @param url
     * @param formMap
     * @return
     */
    public static String postForm(String url, Map<String, String> formMap){
        String data = "";
        if(MapUtils.isNotEmpty(formMap)){
            data = formMap.entrySet().stream().map(entry->String.format("%s=%s", entry.getKey(), entry.getValue())).collect(Collectors
                    .joining("&"));
        }
        return execute(OkHttp.builder().url(url).method(POST).data(data).mediaType(MediaType.APPLICATION_JSON_VALUE).build());
    }

    private static String post(String url, String data, String mediaType, String charset){
        return execute(OkHttp.builder().url(url).method(POST).data(data).mediaType(mediaType).responseCharset(charset).build());
    }


    /**
     * 通用执行方法
     */
    private static String execute(OkHttp okHttp){
        if(StringUtils.isEmpty(okHttp.requestCharset)){
            okHttp.requestCharset = DEFAULT_CHARSET;
        }
        if(StringUtils.isEmpty(okHttp.responseCharset)){
            okHttp.responseCharset = DEFAULT_CHARSET;
        }
        if(StringUtils.isEmpty(okHttp.method)){
            okHttp.method = DEFAULT_METHOD;
        }
        if(StringUtils.isEmpty(okHttp.mediaType)){
            okHttp.mediaType = DEFAULT_MEDIA_TYPE;
        }
        if(okHttp.requestLog){//记录请求日志
            logger.info(okHttp.toString());
        }

        String url =okHttp.url;

        Request.Builder builder = new Request.Builder();

        if(MapUtils.isNotEmpty(okHttp.queryMap)){
            String queryParams = okHttp.queryMap.entrySet().stream()
                    .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining("&"));
            url = String.format("%s%s%s", url, url.contains("?")?"&":"?", queryParams);
        }
        builder.url(url);

        if(MapUtils.isNotEmpty(okHttp.headerMap)){
            okHttp.headerMap.forEach(builder::addHeader);
        }

        String method = okHttp.method.toUpperCase();
        String mediaType = String.format("%s;charset=%s", okHttp.mediaType, okHttp.requestCharset);

        if(StringUtils.equals(method, GET)){
            builder.get();
        }else if(ArrayUtils.contains(new String[]{POST, PUT, DELETE, PATCH}, method)){
            RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse(mediaType), okHttp.data);
            builder.method(method, requestBody);
        }else{
            return Consts.EMPTY_STRING;
        }

        String result = "";
        try {
            Response response = client.newCall(builder.build()).execute();
            byte[] bytes = response.body().bytes();
            result = new String(bytes, okHttp.responseCharset);
            if (okHttp.responseLog){//记录返回日志
                logger.info(String.format("Got response->%s",result));
            }
        }catch (Exception e){
            logger.error(okHttp.toString(), e);
        }
        return result;
    }

    @Builder
    @ToString(exclude = {"requestCharset", "responseCharset", "requestLog", "responseLog" })
    static class OkHttp{
        private String url;
        private String method = DEFAULT_METHOD;
        private String data;
        private String mediaType = DEFAULT_MEDIA_TYPE;
        private Map<String, String> queryMap;
        private Map<String, String> headerMap;
        private String requestCharset = DEFAULT_CHARSET;
        private String responseCharset = DEFAULT_CHARSET;
        private boolean requestLog = DEFAULT_LOG;
        private boolean responseLog = DEFAULT_LOG;
    }
}
