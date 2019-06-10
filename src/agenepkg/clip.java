package agenepkg;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("all")
public class clip {

	public static void main(String[] args) throws UnsupportedFlavorException, IOException {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		Transferable Transferable1_clipboardContent = clipboard.getContents(null);
		// 获取文本中的Transferable对象

		if (Transferable1_clipboardContent == null)
			return;

		if (!Transferable1_clipboardContent.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
			return;

		List<File> list = (List<File>) (Transferable1_clipboardContent.getTransferData(DataFlavor.javaFileListFlavor));
		for (File file : list) {
			System.out.println(file);
		}

	}

}
