package aaa;

import java.util.List;

import com.google.common.collect.Lists;

public class order {

	public static void main(String[] args) {
		int[] ia=new int[]{1,7,2,99,3};
		List orderedLi=getOrderLi(ia);
		System.out.println(orderedLi);

	}

	private static List getOrderLi(int[] ia) {

        List li=Lists.newArrayList();
		for (int i : ia) {
			li.add(i);
		}
		int len=li.size();
		 List li2=Lists.newArrayList();
		 for(int i=0;i<len;i++)
		 {
			 Object max = max(li);
			li2.add(max);
			 li.remove(max);
		 }
		
		return li2;
	}

	private static Object max(List<Integer> li) {
		 int max = 0;
		 for (Integer object : li) {
			if(object>max)
				max=object;
		}
		return max;
	}

}
