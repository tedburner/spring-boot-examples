package com.kit.common.util.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.function.Function;

/**
 * @author: lingjun.jlj
 * @desc 导出csv
 */
public final class ExcelExportUtils {

    private static Logger log = LoggerFactory.getLogger(ExcelExportUtils.class);

    /**
     * 导出表格
     *
     * @param response
     * @param t        参数
     * @param <T>
     */
    public static <T> void exportCsv(HttpServletResponse response, String fileName, String title, T t, Function<T, String> function) {
        response.setContentType("application/csv;charset=gb18030");

        try {
            // 防止文件名中文乱码
            fileName = URLEncoder.encode(String.format("%s.csv", fileName), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // pass
        }
        response.setHeader("Content-Disposition", "attachment;  filename=" + fileName);

        try {
            Writer writer = response.getWriter();
            //writer.write(new String(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}));
            writer.append(title).append("\n");

            String charSequence = function.apply(t);
            writer.append(charSequence);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            log.error("export csv error, | t = " + t.toString(), e);
        }
    }

    /**
     * 处理英文逗号","
     *
     * @param str
     * @return
     */
    public static String filterComma(String str) {
        return str != null ? str.replace(",", "，") : "";
    }
}
