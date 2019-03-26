


import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreemarker {

    /**
     * @param args
     * @throws IOException 
     * @throws TemplateException 
     */
    public static void main(String[] args) throws IOException, TemplateException {

        
        Configuration cfg = new Configuration();
        StringTemplateLoader StringTemplateLoader1 = new StringTemplateLoader();
        String templateContent="欢迎：${name} 我在${地点}";
        StringTemplateLoader1.putTemplate("myTemplate",templateContent);
        
        cfg.setTemplateLoader(StringTemplateLoader1);
        
        
            Template template = cfg.getTemplate("myTemplate","utf-8");
            Map root = new HashMap();  
            root.put("name", "javaboy2012");  root.put("地点", "cantonxxxxxxx");
            
            StringWriter writer = new StringWriter();  
           
                template.process(root, writer);
                System.out.println(writer.toString());  
           





    }

}