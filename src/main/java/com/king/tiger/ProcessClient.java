package com.king.tiger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 **/
public class ProcessClient {
    public static final Logger logger = Logger.getLogger(ProcessClient.class);
    
    private static Map<String, Object> root = new HashMap<String, Object>();

    /**
     * 调用FreeMarkertUtil.java FreeMarkertUtil.processTemplate("body.ftl", root,
     * out); 来生成html文件
     * 
     * @param out
     */
    public static void processBody(Writer out) {
        FreeMarkertUtil.processTemplate("details.ftl", root, out);
    }
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
        // 生成html的位置
        String dirPath = "E:/king/tiger";
        // 文件名字
        String indexFileName = "king_tiger.html";
        // 删除原来的文件
        delOldHtml(dirPath, indexFileName);
        // 防止浏览器缓存，用于重新生成新的html
        String filePath = dirPath + "/" + indexFileName;
        Writer out = new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8");
        Map<String, Object> mv = new HashMap<String, Object>();
        mv.put("name", "king");
        mv.put("age", "18");
        FreeMarkertUtil.processTemplate("details.ftl", mv , out);
        logger.info(String.format("生成文件：%s",filePath));
    }
    
    /**
     * 删除原来的html文件
     * 
     * @param htmlDir
     * @param htmlName
     */
    private static void delOldHtml(String htmlDir, String htmlName) {
        File path = new File(htmlDir);
        if(!path.exists()){
            path.mkdirs();
        }
        String[] indexfileList = path.list(new DirectoryFilter(htmlName));
        if (indexfileList != null && indexfileList.length >= 0) {
            for (String f : indexfileList) {
                File delf = new File(htmlDir + "/" + f);
                delf.delete();
            }
        }
    }
}
