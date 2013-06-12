package com.min.order;

import com.min.util.HttpUtil;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	//login, cancel button
	private Button cancelBtn, loginBtn;

	//username, password
	private EditText userEditText, pwdEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("物流管理系统-用户登录");
		setContentView(R.layout.login_system);
		
		//实例化组件,button
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		loginBtn = (Button)findViewById(R.id.loginButton);
		
		//实例化组件,editText
		userEditText = (EditText) findViewById(R.id.userEditText);
		pwdEditText = (EditText) findViewById(R.id.pwdEditText);
		
		cancelBtn.setOnClickListener(new OnClickListener(){
			public void onClick (View v) {
				finish();
			}
		});
		
		//为按钮添加单击事件监听器
		loginBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if (login()) {
					Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
					startActivity(intent);
				} else {
					showDialog("用户名或密码错误，请重新输入");
				}
			}
		});
		
	}
	
	//显示提示信息的对话框
	private void showDialog (String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	//validate方法，对用户名和密码进行非空验证
	private boolean validate () {
		String username = userEditText.getText().toString();
		if (username.equals("")) {
			showDialog("输入用户名");
			return false;
		}
		String pwd = pwdEditText.getText().toString();
		if (pwd.equals("")) {
			showDialog("输入密码！");
			return false;
		}
		return true;
	}
	
	//查询用户名和密码
	private String query(String username, String password) {
		//查询字串
		String queryString = "username" + username + "&password" + password;
		//查询url
		//String url = HttpUtil.BASE_URL + "servlet/LoginServlet?" + queryString;
		
		//查询并返回结果
		//return HttpUtil.queryStringForPost(url);
		
		return "xiem=123456";
	}

	//将用户信息保存到配置文件
	private void saveUserMsg(String msg) {
		//用户编号
		String id = "";
		//用户名称
		String name = "";
		//获得信息数组
		String[] msgs = msg.split(";");
		int idx = msgs[0].indexOf("=");
		id = msgs[0].substring(idx+1);
		idx = msgs[1].indexOf("=");
		id = msgs[1].substring(idx+1);
		//共享信息
		SharedPreferences pre = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE); //获得Preferences
		SharedPreferences.Editor editor = pre.edit(); //获得Editor
		//保存用户Id
		editor.putString("id", id);
		//保存用户名称
		editor.putString("name", name);
		//提交数据
		editor.commit();
	}
	
	//登录方法
	private boolean login() {
		//获得用户名称
		String username = userEditText.getText().toString();
		//获得密码
		String pwd = pwdEditText.getText().toString();
		//获得登录结果
		String result = query(username, pwd);
		
		if (result!=null && result.equals("0")) {
			return false;
		} else {
			saveUserMsg(result);
			return true;
		}
	}
}

