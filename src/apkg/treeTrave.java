package apkg;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import bsh.This;

class Node {
	public String name;
	public List<Node> list = Lists.newArrayList();
}

public class treeTrave {

	public static void main(String[] args) {

		Node nodeRoot = new Node() {
			{
				this.name = "a";
				this.list.add(new Node() {
					{
						this.name = "b";
						this.list.add(new Node() {
							{
								this.name = "b1";
							}
						});
						this.list.add(new Node() {
							{
								this.name = "b2";
							}
						});
					}
				});

				this.list.add(new Node() {
					{
						this.name = "c";

					}
				});
			}
		};
		System.out.println(JSON.toJSONString(nodeRoot, true));
		
		foreachTag(nodeRoot);

	}

	// 递归好处：代码更简洁清晰，可读性更好
	private static void foreachTag(Node node1) {
		
		// termnal node
				System.out.println(node1.name);
		
		List<Node> list = node1.list;
		if (list.size() > 0) { // multiChildNodes
			for (Node node : list) {
				foreachTag(node);
			}
			
		}

		

	}

}
