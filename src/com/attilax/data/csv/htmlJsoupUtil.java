package com.attilax.data.csv;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

import com.attilax.io.FileUtilsAti;

 

public class htmlJsoupUtil {

	public static void main(String[] args) {

//		String f = "C:\\Users\\attilax\\Documents\\nethtmlcoll\\���������ԭ��(��10��)��(���������˹��...)�����_����_�����Ķ��� - ����ͼ��.html";
//		// f="C:\\Users\\attilax\\Documents\\ati doc index ext v3\\fav like txt
//		// coll\\note ever\\005 note\\Untitled Note [60].html";
//		String html = FileUtilsAti.readFileToStringAutoDetectEncode(f);
//		html2txtV2(html);
//		// System.out.println(text);
//		System.out.println("66--");

	}

	static String html2txtByFile(String f) {
		// org.apache.commons.io.FileUtils.readFileToString(file, encoding)
		String t = FileUtilsAti.readFileToStringAutoDetectEncode(f);
		String text = Jsoup.parse(t).text();
		return text;
	}

	public static String html2txt(String t) {
		// org.apache.commons.io.FileUtils.readFileToString(file, encoding)

		Document Document1 = Jsoup.parse(t);
		List<Node> li = Document1.childNodes();

		String text = Document1.text();
		return text;
	}

	static String html2txtV2(String t) {
		// org.apache.commons.io.FileUtils.readFileToString(file, encoding)

		Document Document1 = Jsoup.parse(t);
		List<Node> li = Document1.childNodes();
		 
		for (Node node : li) {
			traveNode(node);

		}

		String text = Document1.text();
		return text;
	}

	private static void traveNode(Node nodex) {

		List<Node> li = nodex.childNodes();
		if (nodex.childNodes().size() == 0)
			System.out.println(nodex.toString());
		else {
			for (Node node : li) {
				System.out.println(node.toString());
				traveNode(node);

			}
		}

	}
	
	
	
	 public String text(Node node) {

	        final StringBuilder htmltxt = new StringBuilder();
	        new NodeTraversor(new NodeVisitor() {
	            public void head(Node node, int depth) {
	                if (node instanceof TextNode) {
	                    TextNode textNode = (TextNode) node;
	                  //  appendNormalisedText(accum, textNode);
	                } else if (node instanceof Element) {
	                    Element element = (Element) node;
//	                    if (htmltxt.length() > 0 &&
//	                        (element.isBlock() || element.tag.getName().equals("br")) &&
//	                        !TextNode.lastCharIsWhitespace(htmltxt))
//	                        htmltxt.append(" ");
	                }
	            }

	            public void tail(Node node, int depth) {
	            }
	        }).traverse(node);
	        return htmltxt.toString().trim();
	 }

}
