package agenepkg;

public class gene1 {

	//泛型使用场景: 减少重复，代码复用
	// myeclise=eclipse+web插件
	public static void main(String[] args) {
		gene1.process("aaa");
		gene1.process(111);
		gene1.process(1.1);

	}
	
	
	private static void process(double d) {
		// TODO Auto-generated method stub
		
	}


	public static void process(String args) {
		// TODO Auto-generated method stub

	}
	
	
	public static void process(Integer args) {
		// TODO Auto-generated method stub

	}

}
