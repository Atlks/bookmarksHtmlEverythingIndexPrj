package aOPtool;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class ServletOutputStreamImp extends ServletOutputStream {

	private PrintStream out;

	public ServletOutputStreamImp(PrintStream out) {
		this.out=out;
	}

	@Override
	public boolean isReady() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setWriteListener(WriteListener arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(int b) throws IOException {
		out.write(b);

	}

}
