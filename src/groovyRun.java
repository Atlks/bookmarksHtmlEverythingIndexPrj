import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class groovyRun {

	public static void main(String[] args) throws FileNotFoundException, ScriptException {
	
		String fileName = "H:\\gitWorkSpace\\groovyPrj\\src\\groovyScr1.groovy";
		fileName=args[0].trim();
		Object object=new ScriptEngineManager().getEngineByName("groovy")
				.eval(new FileReader(fileName));

      System.out.println(object);

	}

}
