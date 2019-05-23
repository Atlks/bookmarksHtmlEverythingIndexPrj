package com.attilax.img.clr;

import com.attilax.ex.FileNotExistEx;
import com.attilax.ex.FmtEx;

public interface GabFileReconger {

	public boolean isGabFile(Object object) throws FileNotExistEx, FmtEx;

}
