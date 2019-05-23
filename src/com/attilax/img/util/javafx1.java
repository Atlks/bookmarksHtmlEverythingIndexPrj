package com.attilax.img.util;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class javafx1 extends Application {

	@Override
	public void start(Stage primaryStage) {
	//	Group
		Button btn = new Button();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello World!");
			}
		});

		WebView webView = new WebView();  
		WebEngine webEngine = webView.getEngine();  
		String url="http://www.baidu.com";
		webEngine.load(  url);  //加载一个网页  
	//	webEngine.executeScript(java.lang.String script)  //执行网页中的脚本语言
		
		//在网页上右键点击的时候，可以选择在新窗口打开，此时你必须自己设定。  new window open setting
		/*
		setCreatePopupHandler(Callback<PopupFeatures,WebEngine> handler)  //处理弹出窗口。  
 webEngine.setCreatePopupHandler(  
            new Callback<PopupFeatures, WebEngine>() {  
                @Override public WebEngine call(PopupFeatures config) {  
                    return newWebView.getEngine();  
                }  
             }  
 );  
 */
		
		StackPane root_StackPane = new StackPane();
		root_StackPane.getChildren().add(btn);

		Scene scene = new Scene(webView, 600	,600 );
		
		Scene scene2 = new Scene(root_StackPane, 600	,600 );

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene2);
		primaryStage.show();
	}

	public static void main(String[] args) {
		 javafx.application.Application.launch(args);
	}
}
