package com.ldeshpande.contactbook.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class HelperUtil {

    // All methods are static
    private HelperUtil() {
    }

    public static String convertToStackTrace(Throwable exception) {
        Writer stackTraceWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stackTraceWriter);
        exception.printStackTrace(printWriter);
        return stackTraceWriter.toString();
    }

}
