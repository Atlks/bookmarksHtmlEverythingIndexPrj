import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.ant.Groovy;

import com.google.common.collect.Maps;

///bookmarksHtmlEverythingIndexPrj/src/groovyDemo.java
public class groovyDemo {

	public void methDync() {
		System.out.println("--meth dync");
	}

	public static void main(String[] args) throws ScriptException, IOException {

		Groovy groovy = new Groovy();

		Compilable engine; // 获取groovy编译类

		// org.codehaus.groovy.jsr223.GroovyScriptEngineImpl
		// apache-groovy-sdk-2.5.6\groovy-2.5.6\lib\groovy-jsr223-2.5.6.jar
//		((Compilable) new ScriptEngineManager().getEngineByName("groovy"))
//				.compile(FileUtils.readFileToString(new File("H:\\gitWorkSpace\\groovyPrj\\src\\groovyScr1.groovy")))
//				.eval();
		// 这个地方需要使用缓存，达到编译一次，多次执行。
		Object object=new ScriptEngineManager().getEngineByName("groovy")
				.eval(new FileReader("H:\\gitWorkSpace\\groovyPrj\\src\\groovyScr1.groovy"));

      System.out.println(object);
		// return script.eval();

		// System.out.println(execute("new groovyDemo().methDync();return 1",
		// Maps.newConcurrentMap()));

	}

	public static Object execute(String scriptText, Map<?, ?> context) throws ScriptException {

		Compilable engine; // 获取groovy编译类
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engineByName = manager.getEngineByName("groovy");
		engine = (Compilable) engineByName;
		// org.codehaus.groovy.jsr223.GroovyScriptEngineImpl
		// apache-groovy-sdk-2.5.6\groovy-2.5.6\lib\groovy-jsr223-2.5.6.jar
		CompiledScript script = engine.compile(scriptText);
		// 这个地方需要使用缓存，达到编译一次，多次执行。

		ScriptContext scriptContext = new SimpleScriptContext();
//			
		return script.eval();

	}

//		Set<?> its = context.entrySet(); // 上下文参数
//	for (Object o : its) {
//		// @SuppressWarnings("rawtypes")
//		// Entry entry = (Entry)o;
////		scriptContext.setAttribute(	LangUtil.getString(entry.getKey()),	 
////				entry.getValue(), ScriptContext.ENGINE_SCOPE); //copy到groovy执行环境上下文	
//	}
//	return script.eval(scriptContext); // 执行groovy脚本}
	// ScriptContext defaultCtx = ((ScriptEngine) engine).getContext();
}
