
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

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

		StringWriter stringWriter = new StringWriter() {
			{
				new Configuration() {
					{
						setTemplateLoader(new StringTemplateLoader() {
							{
								putTemplate("myTemplate", "欢迎：${name} 我在${地点}");// name, templateSource);
							}
						});
					}
				}.getTemplate("myTemplate", "utf-8").process(new HashMap() {
					{
						put("name", "javaboy2012");
						put("地点", "cantonxxxxxxx");

					}
				}, this);

			}
		};
		System.out.println(stringWriter.toString());
		
		System.out.println( new HashMap() {{
			put("key", "aaaa");
		}}.get("key") );

	}

	private static void t2() throws IOException, TemplateException {
		Configuration cfg = new Configuration() {
			{
				this.setTemplateLoader(new StringTemplateLoader() {
					{
						this.putTemplate("myTemplate", "欢迎：${name} 我在${地点}");// name, templateSource);
					}
				});
			}
		};

		Template template = cfg.getTemplate("myTemplate", "utf-8");
		Map root = new HashMap();
		root.put("name", "javaboy2012");
		root.put("地点", "cantonxxxxxxx");

		StringWriter writer = new StringWriter();

		template.process(root, writer);
		System.out.println(writer.toString());
	}

}