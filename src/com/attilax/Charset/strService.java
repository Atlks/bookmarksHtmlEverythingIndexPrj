package com.attilax.Charset;

 

//public class strUtil {

//	JAVA正则表达式匹配，替换，查找，切割
//	复制代码
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.attilax.collection.listBuilder;
import com.google.common.collect.ImmutableList;

//import org.apache.commons.lang.StringUtils;





//import org.apache.commons.lang3.StringUtils;




//import com.attilax.core;
//import com.attilax.collection.Func;
 
 
import com.google.common.collect.Lists;

 
public class strService   {

	public static final String slashChar = "\\/";
	public static final String backslashChar = "\\\\";
	private static final int STR_PAD_LEFT = 1;
	public static final String dotChar = "\\.";
	public static String mybatisParamTmplt = "\\#\\{.*?\\}";
	private String strOri;
	
	public strService(String s) {
		this.strOri=s;	}

	public strService() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//	o9j();
		 String txt="#{ACF01} ,,#{ACF02}";
		
		System.out.println(   strService .newx(). findV2(mybatisParamTmplt, txt).mybatsiParamsNoWarp().getRztLi());

		}

	public Object getRztLi() {
		// TODO Auto-generated method stub
		return this.rztList;
	}

	public strService mybatsiParamsNoWarp() {
		List li=Lists.newArrayList();
		for (Object object : rztList) {
			String s=object.toString().trim().substring(2, object.toString().length()-1);
			li.add(s);
		}
		 this.rztList=li;
		return this;
	}

	public static strService newx() {
		// TODO Auto-generated method stub
		return new strService();
	}

	// 1.如何从一个字符串中提取数字？
	//
	// 使用正则表达式的�?��常见问题是提取所有的数字到整数的数组。在Java中，\
	// d代表的一系列数字�?-9）�?任何时�?如果可能的话，使用预定义类将会使你的代码容易读懂，并且可以消除由畸形的字符类引入的错误详情请参阅预定义字符类
	// 的更多细节�?请注意，第一个反斜杠\的\
	// D。如果你是一个字符串中使用转义构造，你必须先反斜杠用另一个反斜杠的编译字符串。这就是为什么我们需要使用\\ D�?
	/**
	 * o43
	 */
	public static String getNum(String str) {
		List<Integer> numbers = new LinkedList<Integer>();
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(str);
		while (m.find()) {
			numbers.add(Integer.parseInt(m.group()));
		}
		return str;
	}
 
	public static String splitByLineSeparator(String str) {
		return str;
		// String.split(System.getProperty("line.separator"));
	}

	public static Object pipeidu = "pipeidu";

	/**
	 * grp(0)==RMB 205.34�?USD.,,,, m.group(1)=205.34�?
	 * 
	 * @param fontPic
	 * @param find
	 *            "qq(.*?)qq"
	 */
	public static List Mid_ListFmt(String left, String right, String s2) {

		String abcdef = "abcdef";
		String find = left + "(.*?)" + right;

		// String s = strUtilO36.getMidtrings(s2, find);

		Pattern p = Pattern.compile(find);
		Matcher m = p.matcher(s2);
		ArrayList<String> strs = new ArrayList<String>();
		while (m.find()) {
			strs.add(m.group(1));
		}

		// if (strs.size() == 0)
		// return "";
		return strs;
	}

	public static boolean isStr(Object obj) {
		if (obj instanceof String)
			return true;
		else
			return false;
	}

	public static List<String> toList(String extword) {
		List<String> li = new ArrayList<String>();
		String[] a = extword.split(",");
		for (String str : a) {
			li.add(str);
		}
		return li;
	}

	public static boolean isFind(String s, String dweiu) {
		// 孩[^,，�?\.]*?�?
		String regEx = dweiu.replaceAll("\\*", "[^,，�?\\.]{0,10}?"); // 表示a或F
		// System.out.println("\r\n"+regEx);
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(s);
		boolean rs = mat.find();
		return rs;
	}
	
	/**
	 * 	String dweiu = "app-*.xml";
			String file="app-xx.xml";
		boolean b=	isFind_ByWildcard(file,dweiu);
	 * @param s
	 * @param exp_common
	 * @return
	 */
	public static boolean isFind_ByWildcard(String s, String exp_common) {
		// 孩[^,，�?\.]*?�?
		String regEx = exp_common.replaceAll("\\*", ".*"); // 表示a或F
		// System.out.println("\r\n"+regEx);
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(s);
		boolean rs = mat.find();
		return rs;
	}

	public static List<String> toList(String[] strArr) {
		List<String> li = new ArrayList<String>();

		for (String str : strArr) {
			li.add(str);
		}
		return li;
	}

	public static String trimx(String trimedChar, String str) {
		// tonitsi.trim()
		// StringUtils.trim(str)
		String a = str;
		a = a.trim();
		while (a.startsWith(trimedChar)) {
			a = a.substring(1, a.length()).trim();
		}
		while (a.endsWith(trimedChar)) {
			a = a.substring(0, a.length() - 1).trim();
		}

		return a;
	}

	/**
	 * split by comma
	 * @param linex
	 * @return
	 */
	public static Set toSet(String linex) {
		Set<String> li = new HashSet<String>();
		String[] a = linex.split(",");
		for (String str : a) {
			li.add(str);
		}
		return li;
	}
	
	public static Set toSet(String linex,String splitor) {
		Set<String> li = new HashSet<String>();
		String[] a = linex.split(splitor);
		for (String str : a) {
			if(str.trim().length()>0)
			li.add(str.trim());
		}
		return li;
	}


//	public static int chwsyeNum(String str, String typeKeywords) {
//		// String[] a=str.split("\\|");
//		String[] a = strService.splitx(str, "\\|");
//
//		int n = 0;
//		Set set = listUtil.toSet(typeKeywords);
//		for (String word : a) {
//
//			// if(typeKeywords.contains(word))
//			if (set.contains(word)) {
//				n++;
//				// System.out.println("-------------chwsyeNum:"+word);
//			}
//		}
//		return n;
//	}

	// todux reduce SUM cheusyeo
//	public static int getFiltCharExistNum(String filte_chars, final String s) {
//		String[] a = strService.SplitByNone(filte_chars.trim());
//		int sum = (Integer) com.attilax.collection.listUtil.reduce(a,
//				new Func() {
//
//					@Override
//					public Object invoke(Object... o) {
//						// ／／sum is last value
//						Integer sum = (Integer) o[0];
//						if (o[0] == null)
//							sum = 0;
//						String sub = (String) o[1];
//						String allStr = s;
//						int thisChar_count = strService.count(s, sub);
//					//	core.log(" --count char:time   "+sub+"--"+thisChar_count);
//
//						return sum + thisChar_count;
//					}
//				});
//		return sum;
//	}
	/**
	 * o4e
	 * @param regEx
	 * @param str
	 */
	@SuppressWarnings("all")
	public static List find(String regEx, String str) {
		List li=new ArrayList<String>();
		Pattern p = Pattern.compile(regEx);
		Matcher mMatcher1 = p.matcher(str);
		boolean findRzt_boolean = mMatcher1.find();
		System.out.println("findRzt_boolean:"+findRzt_boolean);
//		System.out.println(m.groupCount());
//		for (int i = 1; i <= m.groupCount(); i++) {
//			System.out.println(m.group(i));
		
//		}
		if(findRzt_boolean)
		{
			   String A=mMatcher1.group();
			   System.out.println("mMatcher1.group:"+A);
			   li.add(A);
		}
		 while (mMatcher1.find()){
			 
			   String A=mMatcher1.group();
			   System.out.println("mMatcher1.group sec:"+A);
			   li.add(A);
		 }
		 return li;
	}

	private List rztList=Lists.newArrayList();
	private ImmutableList<Character> listObj;
	
	
	public   strService findV2(String regEx, String str) {
		List li=new ArrayList<String>();
		Pattern p = Pattern.compile(regEx);
		Matcher mMatcher1 = p.matcher(str);
		boolean findRzt_boolean = mMatcher1.find();
		System.out.println("findRzt_boolean:"+findRzt_boolean);
//		System.out.println(m.groupCount());
//		for (int i = 1; i <= m.groupCount(); i++) {
//			System.out.println(m.group(i));
		
//		}
		if(findRzt_boolean)
		{
			   String A=mMatcher1.group();
			   System.out.println("mMatcher1.group:"+A);
			   li.add(A);
		}
		 while (mMatcher1.find()){
			 
			   String A=mMatcher1.group();
			   System.out.println("mMatcher1.group sec:"+A);
			   li.add(A);
		 }
		 this.rztList=li;
		 return this;
	}

	/**
	 * fmt 位置黑色�?
	 * 
	 * @param string
	 * @return
	 */
//	public static String getexistChar(String path) {
//		List li = filex.read2list(path);
//		final Set set=new HashSet();
//		String sum = listUtil.reduceO4(li, new Func() {
//
//			@Override
//			public Object invoke(Object... o) {
//				String last = (String) o[0];
//				String cur = (String) o[1];
//				if (cur.trim().length() == 0)
//					return last;
//				else {
//					String now = cur.trim().split(",")[0].trim();
//					if(set.contains(now))
//						return last;
//					else
//					{set.add(now);
//					return last + now;
//					}
//				}
//
//			}
//		});
//		return sum;
//	}

	

//	private static void o9j() {
//		System.out.println( getexistChar("D:\\Users\\attilax\\Desktop\\cnChar2atian.txt"));
//		
//		final String filte_char = "()[];,、的（）［］；，";
//		// String[] ao4 = strUtil.SplitByNone(filte_char.trim());
//
//		System.out.println(getFiltCharExistNum(filte_char, "aaabb（）ccbcc"));
//
//		String s = "	005	孩子教育	儿子 女儿 孩子, 宝宝 小孩 子女";
//		String[] a = splitx(s, "\t");
//		// System.out.println(a);
//		String dweiu = " �?�?;
//		// System.out.println(isFind(s, dweiu));
//
//		// System.out.println(getListSqlFmt(
//		// "啊挨癌岸凹傲扒拔把掰百拜稗瓣邦绑蚌胞 彼笔闭裨扁便遍镖憋别濒冰兵柄播帛泊不埠擦猜财舱藏操糙嘈槽草册测恻层曾蹭叉茶查拆搀禅蝉尝�?潮扯撤趁呈乘池尺冲仇处揣踹喘疮窗床创吹捶锤瓷雌此刺葱从凑粗促窜催脆存寸搓达�?蛋裆岛到得蹬等瞪堤敌嫡电刁凋吊跌叠丁钉定丢动栋洞兜毒端短断堆吨蹲跺恩而二贰发伐帆烦返方防仿纺放飞非菲肥肺废费氛坟焚粉风封烽蜂佛否扶负覆该钙盖杆港杠高搁格给根耕梗更弓汞拱供苟沽瓜刮挂乖拐关馆光广逛瑰鬼刽贵滚国涵行航耗合鹤黑嘿很红吼琥花划化�?��坏换慌黄晃悔烩混即佳家颊假尖茧姜讲奖嚼觉解斤金浸颈窘救就举剧锯捐卷决绝掘军君菌俊�?��砍槛看扛铐靠嗑磕瞌咳渴克课啃孔抠夸垮跨块快宽款狂矿框亏傀捆困阔赖澜涝勒了垒肋里脸恋良凉俩亮辆量聊列劣临铃领流拢卵乱抡论旅铝绿略马买卖瞒蔓茫猫冒枚酶美闷们猛眯迷免面苗秒妙灭泯名鸣冥谋某亩那纳捺蝻囊攮挠脑嫩尼倪霓鲵拟你腻溺拈蔫黏撵碾念娘酿鸟捏啮镍孽您宁狞拧牛纽拗农弄奴努暖虐挪女欧殴偶啪排牌潘判盼畔旁�?��胚抨嘭朋坯披匹癖偏篇漂瞟瞥撇拼贫品聘平凭坡泼颇叵破朴谱沏气迄砌掐恰扦牵黔呛跷锹桥俏翘撬鞘妾�?惬秦擒噙青轻擎请磬穷琼求泅球裘祛蛆渠去圈权蜷犬券缺瘸阙群燃染饶绕惹人纫扔日熔揉乳褥软蕊润若弱仨洒撒卅腮散搔骚缫扫臊色涩沙筛膻扇擅伤晌尚勺舍社申沈渗笙圣施湿石识恃舐释嗜噬螫售倏赎熟漱刷�?摔甩闩栓涮双霜谁顺舜司四寺肆松宋颂艘苏俗粟虽隋随穗孙损缩索锁她塌塔沓榻苔太滩瘫坛塘涛梯题蹄体屉替天甜舔挑帖铁听停佟童捅�?凸图湍�?褪屯砣跎妥椭拓剜挽往旺伪委闻翁毋捂习峡辖霞下吓衔险相香湘镶萧邪携谢锌星醒凶胸雄熊修徐许婿宣悬选癣削薛穴雪熏旬训压哑烟腌演雁秧羊邀姚掖爷�?忆荫音殷引应涌用友有幼于余御员�?愿月粤晕�?��郓恽栽宰咱暂葬枣澡灶择贼怎增赠轧闸�?栈张涨掌赵折褶浙枕争蒸郑指志质窒痣置轴绉朱住筑抓拽转篆庄装幢追椎准捉浊啄子紫字邹钻嘴�?���?
//		// ));
//		String string = "�?�?�?你大;哥�?";
//		System.out.println(delPunctualion(string));
//	}

	private static String getListSqlFmt(String string) {
		String s = "";
		char[] a = string.toCharArray();
		for (char c : a) {
			String s2 = "'" + String.valueOf(c) + "'";
			s = s + "," + s2;

		}
		s = strService.trimx(",", s);
		return s;
	}

	/**
	 * StringTokenizer 是出于兼容�?的原因�?被保留的遗留�?虽然在新代码中并不鼓励使用它)。建议所有寻求此功能的人使用 String �?
	 * split 方法
	 * 
	 * @param str
	 * @param splitor
	 * @return
	 */
	public static String[] splitx(String str, String splitor) {

		String s = new String(
				"The=Java=platform=is=the=ideal=platform=for=network=computing");
		s = str;
		StringTokenizer st = new StringTokenizer(s, splitor);
		int len = st.countTokens();
		String[] arr = new String[len];
		// System.out.println( "Token Total: " + st.countTokens() );
		int n = 0;
		while (st.hasMoreElements()) {
			// System.out.println( st.nextToken() );

			arr[n] = st.nextToken();
			n++;
		}
		return arr;

	}

	public static int chwsyeNum(String str, String typeKeywords,
			Map eventListOnlyKeyword) {
		// String[] a=str.split("\\|");
		String[] a = strService.splitx(str, "\\|");

		int n = 0;
		Set set = (Set) eventListOnlyKeyword.get(typeKeywords);
		for (String word : a) {

			// if(typeKeywords.contains(word))
			if (set.contains(word)) {
				n++;
				// System.out.println("-------------chwsyeNum:"+word);
			}
		}
		return n;
	}

	public static int chwsyeNum(String str, String typeKeywords,
			Map eventListOnlyKeyword, Map fileLineMap) {
		// String[] a=strUtil.splitx(str, "\\|");
		String[] a = (String[]) fileLineMap.get(str);
		int n = 0;
		Set set = (Set) eventListOnlyKeyword.get(typeKeywords);
		for (String word : a) {

			// if(typeKeywords.contains(word))
			if (set.contains(word)) {
				n++;
				// System.out.println("-------------chwsyeNum:"+word);
			}
		}
		return n;
	}

	public static String remove65279(String typeShortformat) {
		char[] c = typeShortformat.toCharArray();
		char c1 = c[0];
		int c1num = c1;
		if (c1num == 65279) {
			char[] ca2 = new char[c.length - 1];
			for (int i = 0; i < ca2.length; i++) {
				ca2[i] = c[i + 1];
			}
			return new String(ca2);
		} else
			return typeShortformat;
	}

	public static List<String> splitByLen(String w1, int len) {

		List<String> list = new ArrayList<String>();

		String str = w1;
		while (str.length() > len) {
			list.add(str.substring(0, len));
			str = str.substring(len);
		}

		if (str.length() > 0) {
			list.add(str);
		}
		return list;

	}

	public static List<String> toList(String strx, String splitor) {
		List<String> li = new ArrayList<String>();
		if (strx == null || strx.trim().length() == 0)
			return li;
		String[] a = strx.split(splitor);
		for (String str : a) {
			li.add(str);
		}
		return li;
	}

	public static String right(String moodx, int i) {
		return moodx.substring(moodx.length() - i);

	}

	public static String left(String moodx, int i) {
		return moodx.substring(0, i);

	}

//	public static String delPunctualion(String string) {
//		string = securyInt.getString(string);
//		// 先去掉标�?再合并空�?
//		Pattern p = Pattern.compile("[(.|,|\"|\\?|!|:|;|'|。|，|”|？|！|：|；|�?]");// 这边增加�?��的符�?
//		// ,
//		// 例如要加�?��
//		// '则变成[(.|,|\"|\\?|!|:|')],如果是特殊符号要加转�?
//		// \
//		Matcher m = p.matcher(string);// 这为要整理的字符�?
//		String first = m.replaceAll("");
//		System.out.println(first + "*******");
//		p = Pattern.compile("   {2,}");
//		m = p.matcher(first);
//		String second = m.replaceAll("");
//		System.out.println(second);// second就是你要的字符串�?
//
//		return second;
//	}

	public static void testReg() {

	}

	private static void strMatch() {
		String phone = "13539770000";
		// �?��phone是否是合格的手机�?标准:1�?��，第二位�?,5,8，后9位为任意数字)
		System.out.println(phone + ":" + phone.matches("1[358][0-9]{9,9}")); // true

		String str = "abcd12345efghijklmn";
		// �?��str中间是否包含12345
		System.out.println(str + ":" + str.matches("\\w+12345\\w+")); // true
		System.out.println(str + ":" + str.matches("\\w+123456\\w+")); // false
	}

	private static void strSplit() {
		String str = "asfasf.sdfsaf.sdfsdfas.asdfasfdasfd.wrqwrwqer.asfsafasf.safgfdgdsg";
		String[] strs = str.split("\\.");
		for (String s : strs) {
			System.out.println(s);
		}
	}

	private static void getStrings() {
		String str = "rrwerqq84461376qqasfdasdfrrwerqq84461377qqasfdasdaa654645aafrrwerqq84461378qqasfdaa654646aaasdfrrwerqq84461379qqasfdasdfrrwerqq84461376qqasfdasdf";
		Pattern p = Pattern.compile("qq(.*?)qq");
		Matcher m = p.matcher(str);
		ArrayList<String> strs = new ArrayList<String>();
		while (m.find()) {
			strs.add(m.group(1));
		}
		for (String s : strs) {
			System.out.println(s);
		}
	}

	/**
	 * 
	 * @param str
	 * @param find
	 *            "qq(.*?)qq"
	 */
	public static String getMidtrings(String str, String find) {
		// String str =
		// "rrwerqq84461376qqasfdasdfrrwerqq84461377qqasfdasdaa654645aafrrwerqq84461378qqasfdaa654646aaasdfrrwerqq84461379qqasfdasdfrrwerqq84461376qqasfdasdf"
		// ;
		// "qq(.*?)qq"
		Pattern p = Pattern.compile(find);
		Matcher m = p.matcher(str);
		ArrayList<String> strs = new ArrayList<String>();
		while (m.find()) {
			strs.add(m.group(1));
		}
		// for (String s : strs){
		// System.out.println(s);
		// // }
		if (strs.size() == 0)
			return "";
		return strs.get(0);
	}

	public static String getMidtrings(String s2, String left, String right) {
		// String s2="   周期 Statement cycle 2013/11/15 - 2013/12/14  ";
		// s2=doc.text();

		String abcdef = "abcdef";
		String fd = left + "(.*?)" + right;

		String s = strService.getMidtrings(s2, fd);
		return s;
	}

	private static void replace() {
		String str = "asfas5fsaf5s4fs6af.sdaf.asf.wqre.qwr.fdsf.asf.asf.asf";
		// 将字符串中的.替换成_，因�?是特殊字符，�?��要用\.表达，又因为\是特殊字符，�?��要用\\.来表�?
		str = str.replaceAll("\\.", "_");
		System.out.println(str);
	}

	public static boolean isContainCnchar(String s3) {
		if (s3.getBytes().length == s3.length())
			return false;
		else
			return true;
	}

	public static String[] SplitByBackslash(String add) {
		return add.split("\\\\");

	}

	/**
	 * o4d
	 * 
	 * @param add
	 * @return
	 */
//	public static String[] SplitByNone(String Str) {
//		String[] a = Str.split("|");
//		String[] a2 = com.attilax.collection.listUtil.filterO4(a, new Func() {
//
//			@Override
//			public Object invoke(Object... o) {
//				String word = (String) o[0];
//				if (word.length() == 0)
//					return true;
//				return false;
//			}
//		});
//		return a2;
//
//	}

	public static String replaceByStar(String add) {

		return add.replaceAll("\\*", "").trim();
	}

	// public static int count(String s, String str) {
	// // TODO Auto-generated method stub
	// return 0;
	// }

	public static int count(String text, String sub) {
		int count = 0, start = 0;
		while ((start = text.indexOf(sub, start)) >= 0) {
			start += sub.length();
			count++;
		}
		return count;
	}

	/**
	 * o4d  deduliDuliChar
	 * @param string
	 * @return
	 */
//	public static String deduli(String Str) {
//		String r="";
//		String[] a=SplitByNone(Str);
//		Set set=new HashSet();
//		for(String word:a)
//		{
//			if(set.contains(word))
//				continue;
//			else
//			{
//				set.add(word);
//				r=r+word;
//			}
//			
//		}
//		return  r;
//	}
	/**
	 * 
	@author attilax 老哇的爪�?
		@since  o7e j46m$
	
	 * @param item
	 * @param dic
	 * @return
	 */
	public static String replaceBatchO7(String item, String dic_splitByComma,String replacemt) {
		String[] sa=dic_splitByComma.split(",");
	 for (int i = 0; i < sa.length; i++) {
		String s = sa[i];
		item=item.replaceAll(s, replacemt);
	}
	 return item;
	
	}
	@Deprecated
	public static String replaceBatch(String item, String dic) {
		return dic;  //o77
	//	List li=filex.read2list_filtEmptyNstartSpace(dic);
//		String r=com.attilax.collection.listUtil.<List,String>reduceO4d(li, item,new Funcx<Object, String>(){
//
//			@Override
//			public String invoke(Object... o) {
//				String last=o[0].toString();
//				String cur=o[1].toString();
//				String[] a=cur.split(",");
//				last=last.replaceAll(a[0].trim(), a[1].trim());
//				core.log("a0:"+a[0].trim()+"   a1:"+a[1].trim());
//				core.log(" -- last:"+last+"  cur:"+cur+"  curProcessed:"+last);
//				return last;
//			}});
//		return r;
	}

	public static byte[] toByteArr(String string) {
	//	string.getBytes(charsetName)
		return null;
	}
	public static byte[] toByteArr(String string,String enc) {
	
		try {
			return 	string.getBytes(enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		
		}
		//return null;
	}

	/**
	 * @param string
	 * @param i
	 * @param string2
	 * @return
	 * @author  attilax 老哇的爪�?
	 *@since  2014-6-23 下午4:30:51$
	 */
	public static String pad(String string, int i, String padChar) {
		// attilax 老哇的爪�? 下午4:30:51   2014-6-23 
		int padNum=i-string.length();
		return null;
	}
	
	
	/**
	 * 
	 * @param s
	 * @param len
	 * @return
	 */
	public static String  str_pad0(String s,int len)
	{
		return  str_pad(s,len,"0",STR_PAD_LEFT );
	}
	
	public static String  str_pad(String s,int len,String charx,int mode_leftOrRight)
	{
		if(mode_leftOrRight==STR_PAD_LEFT)
				return 	StringUtils.leftPad(s, len, charx);
		return charx;
		 
	}


	/**
	@author attilax 老哇的爪�?
		@since  o7e k1z$
	
	 * @param string
	 * @return
	 */
	public static String[] SplitByDot(String s) {
		// attilax 老哇的爪�? k1z   o7e 
		return s.split("\\.");
	 
		 
		
	}

	/**
	@author attilax 老哇的爪�?
		@since  o9p m_43_0   
	
	 * @param name
	 * @return
	 */
	public static String UpHeadStr(String str) {
		  if(str!=null && str!=""){
		         str  = str.substring(0,1).toUpperCase()+str.substring(1);
		     }
		     return str;
		
	}
	
	 public static String do17(String str) {
	     if(str!=null && str!=""){
	         str  = str.substring(0,1).toUpperCase()+str.substring(1);
	     }
	     return str;
	 }

	/**
	@author attilax 老哇的爪�?
		@since  o08 k_7_50   
	
	 * @param string
	 * @param strFmt
	 * @return
	 */
	public static String concat(String s1, String s2) {
		// attilax 老哇的爪�? k_7_50   o08 
		if(s1==null)
			s1="";
		if(s2==null)s2="";
		return s1+s2;
		
	}

	/**
	 * def is empty
	 * @param object
	 * @return
	 */
	public static boolean isNotEmpty(Object object) {
		 if(object==null)
			 return false;
		 if(object.toString().length()>0)
			 return true;
		return false;
	}


		/**safe val ,,def val is empty
		@author attilax 老哇的爪�?
		@since   oa7 a_z_0
		 
		 */
	public static String val(Object object) {
		try {
			return  (String)object;
		} catch (Exception e) {
			return "";
		}
		
	}
	public static String toStr(Object object) {
		 try {
			return (String)object;
		} catch (Exception e) {
			try {
				return (object.toString());
			} catch (Exception e2) {
				return "..";
			}
			
		}
		//return "";
	}

		/**
		@author attilax 老哇的爪�?
		@since   oar d_3_39
		 
		 */
	public static String replaceAllMultiSpace(String k2) {
		 for(int i=0;i<15;i++)
		 {
			 if(k2.contains("  "))
				 k2=	 k2.replaceAll("  ", " ");
			 else
				 return k2;
		 }
		return k2;
	}

			/**
			@author attilax 老哇的爪�?
			@since   ob1 c_4_e
			 
			 */
		public static Map toMap(String s) {
			String[] a=s.split(",");
			Map mp=new HashMap();
			for (String exp : a) {
				String[] a2=exp.split("=");
				try {
					mp.put(a2[0].trim(), a2[1].trim());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			return mp;
		}

				/**
				@author attilax 老哇的爪�?
				@since   ob3 m_46_j
				 
				 */
			public static boolean isEmpty(Object object) {
				 
				return !isNotEmpty(object);
			}

				public static List<String> preg_match_all(String reg, String sql2) {
					// TODO Auto-generated method stub
					return find(reg,sql2);
				}
       
				public static strService $(String s) {
					
					return new strService(s);
				}

				public strService replace(String repstr, String repstr_Final) {
					String tmps = strOri.replaceAll(repstr, repstr_Final);
					this.strOri=tmps;
					return this;
				}

				public listBuilder toList() {
				//	char[] charArr=this.strOri.toCharArray();
				
					listBuilder lb=new listBuilder(this.strOri);
					return lb;
				}

				public String build() {
					// TODO Auto-generated method stub
					return this.strOri;
				}

				public static Set<String> toSetNoSplitor(String f) {
					   String[] sa=f.split("");
					   Set<String> staffsSet = new HashSet<>(Arrays.asList(sa));
					   staffsSet.remove("");
					return staffsSet;
				}
				
				public static List<String> toListNoSplitor(String s) {
				
					List<String> li_sa=Lists.newArrayList();
					String[] sa = s.split("");
					for (String ch : sa) {
						if(ch.equals(""))
							continue;
						li_sa.add(ch);
					}
				//	li_sa.remove("");
					return li_sa;
				}

				 
}
