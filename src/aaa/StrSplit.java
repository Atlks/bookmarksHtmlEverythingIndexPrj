package aaa;

public class StrSplit {

	public static void main(String[] args) {
		String s="TOM:89|JERY:90|TONY:78";
		String[] a=s.split("\\|");
		for (String i : a) {
			System.out.println( i.split(":")[0]+"-->"+  i.split(":")[1] );
		}

	}

}
