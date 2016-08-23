package api.com.chj.core;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackTraceUtil {

    public static String getStackTrace(Throwable exception) {
        StringWriter sw = null;
        PrintWriter pw = null;

        String result;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            result = sw.toString();
        } finally {
            if(pw != null) {
                pw.close();
            }

        }

        return result;
    }
}
