package tomcatxpkg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Lists;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.NameSpace;
import javassist.expr.NewArray;

public class bshBbeanshellTest {

	public static void main(String[] args) throws EvalError, FileNotFoundException, IOException {
		System.out.println("........");
		Interpreter i = new Interpreter(); // Construct an interpreter
		i.set("foo", 5); // Set variables
//		i.set("date", new Date() ); 
//
//	//	Date date = (Date)i.get("date");    // retrieve a variable

		// Eval a statement and get the result
		i.eval("bar = foo*10");
		// i.invokeMain(arg0, arg1);
		// Interpreter.main(null);
		System.out.println(i.get("bar"));
		System.out.println(i.eval("   new bshBbeanshellTest().meth()   "));
		String pakg = "com/attilax/json";

		System.out.println(new bshBbeanshellTest().meth());
		// Source an external script file
		//		 List li=Lists.newArrayList("aa","cc");
		//		 i.set("li",li);
		//	 	String p = "tojson.bsh";
		//	String r=	(String) i.source(p);
		//		System.out.println("---"+r);
	}

	private String meth() {

		return "meth";
	}

}
