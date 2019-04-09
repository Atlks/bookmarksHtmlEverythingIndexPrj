package aOPtool;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

public class EnterkeyMonitor {

	private static int confirmKey=1;
	private static int confirmKey2=2;
	private static int EXIT_KEY_MARK=3;

	public static void main(String[] args) throws Exception {

		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

			
			@Override
			public void eventDispatched(AWTEvent event) {
				// TODO Auto-generated method stub
				if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {
					switch (((KeyEvent) event).getKeyCode()) {
					case KeyEvent.VK_F1:

						break;

					case KeyEvent.VK_F8:  
						
						break;						
					}
				}
			}
		}, AWTEvent.KEY_EVENT_MASK);
		System.out.println("--f");
		//原来需要把dll文件放到项目com.melloware.jintellitype包下。建议同时把两个dll文件都加进去，这样，你的程序就可以同时兼容32位和64位系统，而你不需要任何额外的处理。
		//也可以吧dll放在system下 win目录下，
	//	System.load("H:\\gitWorkSpace\\bookmarksHtmlEverythingIndexPrj\\libs\\JIntellitype.dll");
		
		System.load("H:\\gitWorkSpace\\bookmarksHtmlEverythingIndexPrj\\libs\\JIntellitype64.dll");
		
		 //第一步：注册热键，第一个参数表示该热键的标识，第二个参数表示组合键，如果没有则为0，第三个参数为定义的主要热键
	       JIntellitype.getInstance().registerHotKey(confirmKey, JIntellitype.MOD_ALT, (int)KeyEvent.VK_ENTER);  
	       JIntellitype.getInstance().registerHotKey(confirmKey2, JIntellitype.MOD_ALT, (int)'C');  
	       JIntellitype.getInstance().registerHotKey(EXIT_KEY_MARK, JIntellitype.MOD_ALT, (int)'Q'); 

		HotkeyListener	hotkeyListener = new HotkeyListener(){
			public void onHotKey(int code) {
				switch(code){
				case 1:
                    //这里写想实现的功能
					System.out.println("alt+enter");
					break;
				case 2:
					System.out.println("alt+s");
					break;
				case 3:
					System.out.println("alt+q");
					break;
					
				}
			}};
		JIntellitype.getInstance().addHotKeyListener(hotkeyListener);
 
		//Thread.sleep(8000);
	}

}
