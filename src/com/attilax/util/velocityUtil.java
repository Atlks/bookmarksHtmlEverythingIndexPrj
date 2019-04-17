package com.attilax.util;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class velocityUtil {

	public static String getTmpltCalcRzt(String sqlTmplt, VelocityContext context) {
		// 初始化并取得Velocity引擎
				VelocityEngine ve = new VelocityEngine();
				ve.init();

				// 取得velocity的模版内容, 模板内容来自字符传

				// 输出流
				StringWriter writer = new StringWriter();

				// 转换输出

				ve.evaluate(context, writer, "", sqlTmplt); // 关键方法

				String rzt = writer.toString();
				return rzt;
	}

}
