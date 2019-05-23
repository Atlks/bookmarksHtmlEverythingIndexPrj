package aOPtool;

public class WinManager {
	 final User32 user32 = User32.INSTANCE;
	
	static HWND getUnitFrameWnd(){
        HWND hwnd = User32.INSTANCE.FindWindow(null, "XXX系统功能导航（客户端）");
        if (unitFrameWnd == null) {
            unitFrameWnd = hwnd;
        }
        return unitFrameWnd;
    }

}
