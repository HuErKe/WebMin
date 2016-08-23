package api.com.chj.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.codehaus.jackson.map.ObjectMapper;

import api.com.chj.core.StackTraceUtil;

/**
 * Created by Administrator on 2016/1/27.
 */
public class HttpUtil {
    private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

    static ObjectMapper mapper = new ObjectMapper();


    public static Map<String, Object> httpPostJSON(String url, Map<String, Object> data) {
        Map retMap = null;
        String response = null;

        try {
            response = httpPost(url, mapper.writeValueAsString(data));
            if(response != null && !"".equals(response)) {
                retMap = (Map)mapper.readValue(response, Map.class);
                return retMap;
            } else {
                return null;
            }
        } catch (Exception var5) {
            throw new RuntimeException("httpPost:[" + url + "] [" + data + " ] [ " + response + "]:" + StackTraceUtil.getStackTrace(var5), var5);
        }
    }

    public static String httpPost(String url, String postData) {
        StringBuffer response = new StringBuffer();

        System.out.println("postData="+postData);
        
        try {
            for(int e = 0; e < 3; ++e) {
                HttpClient client = new HttpClient(connectionManager);
                PostMethod method = new PostMethod(url);
                method.addRequestHeader("Content-Type", "text/html;charset=UTF-8");
                StringBuffer rsp = new StringBuffer();
                ByteArrayInputStream bi = new ByteArrayInputStream(postData.getBytes("UTF-8"));
                InputStreamRequestEntity fr = new InputStreamRequestEntity(bi);
                method.setRequestEntity(fr);

                try {
                    client.executeMethod(method);
                    if(method.getStatusCode() == 200) {
                        BufferedReader e1 = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8"));

                        String line;
                        while((line = e1.readLine()) != null) {
                            rsp.append(line);
                        }

                        e1.close();
                    }
                } catch (IOException var15) {
                    throw new RuntimeException("httpPost:[" + url + "] :" + StackTraceUtil.getStackTrace(var15), var15);
                } finally {
                    method.releaseConnection();
                }

                System.out.println("请求次数["+(e+1)+"]");
                
                if(rsp.length() > 0) {
                    response = rsp;
                    break;
                }
            }

            return response.toString();
        } catch (Exception var17) {
            throw new RuntimeException("httpPost:[" + url + "] :" + StackTraceUtil.getStackTrace(var17), var17);
        }
    }

}
