package agenepkg;

public class gene2 {

	//泛型使用场景: 减少重复，代码复用
	// myeclise=eclipse+web插件
	public static void main(String[] args) {
		gene2.process("aaa");
		gene2.process(111);
		gene2.process(1.1);

	}
	
	//泛型方法 。。。
	private static <ttt> void process(ttt d) {
		// TODO Auto-generated method stub
		
	}


 

}
