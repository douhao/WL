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
		setTitle("��������ϵͳ-�û���¼");
		setContentView(R.layout.login_system);
		
		//ʵ�������,button
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		loginBtn = (Button)findViewById(R.id.loginButton);
		
		//ʵ�������,editText
		userEditText = (EditText) findViewById(R.id.userEditText);
		pwdEditText = (EditText) findViewById(R.id.pwdEditText);
		
		cancelBtn.setOnClickListener(new OnClickListener(){
			public void onClick (View v) {
				finish();
			}
		});
		
		//Ϊ��ť��ӵ����¼�������
		loginBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				if (login()) {
					Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
					startActivity(intent);
				} else {
					showDialog("�û����������������������");
				}
			}
		});
		
	}
	
	//��ʾ��ʾ��Ϣ�ĶԻ���
	private void showDialog (String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg).setCancelable(false).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	//validate���������û�����������зǿ���֤
	private boolean validate () {
		String username = userEditText.getText().toString();
		if (username.equals("")) {
			showDialog("�����û���");
			return false;
		}
		String pwd = pwdEditText.getText().toString();
		if (pwd.equals("")) {
			showDialog("�������룡");
			return false;
		}
		return true;
	}
	
	//��ѯ�û���������
	private String query(String username, String password) {
		//��ѯ�ִ�
		String queryString = "username" + username + "&password" + password;
		//��ѯurl
		//String url = HttpUtil.BASE_URL + "servlet/LoginServlet?" + queryString;
		
		//��ѯ�����ؽ��
		//return HttpUtil.queryStringForPost(url);
		
		return "xiem=123456";
	}

	//���û���Ϣ���浽�����ļ�
	private void saveUserMsg(String msg) {
		//�û����
		String id = "";
		//�û�����
		String name = "";
		//�����Ϣ����
		String[] msgs = msg.split(";");
		int idx = msgs[0].indexOf("=");
		id = msgs[0].substring(idx+1);
		idx = msgs[1].indexOf("=");
		id = msgs[1].substring(idx+1);
		//������Ϣ
		SharedPreferences pre = getSharedPreferences("user_msg", MODE_WORLD_WRITEABLE); //���Preferences
		SharedPreferences.Editor editor = pre.edit(); //���Editor
		//�����û�Id
		editor.putString("id", id);
		//�����û�����
		editor.putString("name", name);
		//�ύ����
		editor.commit();
	}
	
	//��¼����
	private boolean login() {
		//����û�����
		String username = userEditText.getText().toString();
		//�������
		String pwd = pwdEditText.getText().toString();
		//��õ�¼���
		String result = query(username, pwd);
		
		if (result!=null && result.equals("0")) {
			return false;
		} else {
			saveUserMsg(result);
			return true;
		}
	}
}

