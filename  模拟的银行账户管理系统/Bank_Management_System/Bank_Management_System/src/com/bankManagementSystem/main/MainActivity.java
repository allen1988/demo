package com.bankManagementSystem.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bankManagementSystem.model.Session;
import com.bankManagementSystem.util.BaseActivity;
import com.bankManagementSystem.util.DBhelper;

public class MainActivity extends BaseActivity implements OnClickListener {
	/** Called when the activity is first created. */
	private EditText et_account;
	private EditText et_pwd;
	private Button btn_login;
	private TextView tv_register;
	private TextView tv_contact;
	private String[] permission = { "����Ա", "�û�" };
	private int position;
	private String account;
	private String password;
	public static int REQUEST = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		initUI();
		et_account.setOnClickListener(this);
		et_pwd.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		String text1 = getString(R.string.selhelp_register);
		String text2 = getString(R.string.concact_us);
		// ���ı�ת����SpannableString����
		SpannableString spannableString1 = new SpannableString(text1);
		SpannableString spannableString2 = new SpannableString(text2);
		// ��tv_register�е������ı����ó�ClickableSpan���󣬲�ʵ����onClick����
		spannableString1.setSpan(new ClickableSpan() {

			@Override
			public void onClick(View widget) {
				Intent intent = new Intent(MainActivity.this,
						RegisterTipActivity.class);
				MainActivity.this.startActivityForResult(intent, REQUEST);

			}
		}, 0, text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// ʹ��SpannableString������������TextView�ؼ�������
		tv_register.setText(spannableString1);
		// �ڵ�������ʱ������Ҫִ�еĶ���������������MovementMethod����
		tv_register.setMovementMethod(LinkMovementMethod.getInstance());

	}

	// ��ʼ��UI�ؼ�..
	private void initUI() {
		et_account = (EditText) findViewById(R.id.et_account);
		et_pwd = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		tv_register = (TextView) findViewById(R.id.tv_register);
		tv_contact = (TextView) findViewById(R.id.tv_contact);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder dBuilder = new AlertDialog.Builder(this);
			dBuilder.setTitle("�˳�����");
			dBuilder.setMessage("ȷ��Ҫ�˳���");
			dBuilder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							MainActivity.this.finish();

						}
					});
			dBuilder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

						}
					});
			dBuilder.create().show();
		}

		return super.onKeyDown(keyCode, event);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.et_account:
			AlertDialog dialog;
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("�û�����").setSingleChoiceItems(permission, 0,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							position = which;
							Toast.makeText(MainActivity.this,
									"��ѡ���Ȩ����" + permission[position],
									Toast.LENGTH_SHORT).show();
							dialog.dismiss();

						}
					});
			dialog = builder.create();
			dialog.show();
			break;
		case R.id.et_password:

			break;
		case R.id.btn_login:
			account = et_account.getText().toString().trim();
			password = et_pwd.getText().toString().trim();
			Session.setName(account);
			Session.setPassword(password);
			Session.setId(DBhelper.getInstance(this).getUser(account, password)
					.get_id());
			boolean isExist = false;
			if (account.equals("")) {
				Toast.makeText(MainActivity.this, "�˺Ų���Ϊ��!!", Toast.LENGTH_LONG)
						.show();
				return;
			} else if (password.equals("")) {
				Toast.makeText(MainActivity.this, "���벻��Ϊ��!!", Toast.LENGTH_LONG)
						.show();
				return;
			} else {
				// ��½���û�����
				if (position == 1) {
					isExist = DBhelper.getInstance(MainActivity.this)
							.checkUser(account, password);
					if (isExist) {
						Intent userIntent = new Intent();
						userIntent.setClass(this, UserActivity.class);
						startActivity(userIntent);

					} else {
						Toast.makeText(MainActivity.this, "�û�������",
								Toast.LENGTH_SHORT).show();
					}
				}
				// ��½�ǹ���Ա����
				else {
					isExist = DBhelper.getInstance(MainActivity.this)
							.checkAdmin(account, password);
					if (isExist) {
						Intent intent = new Intent(MainActivity.this,
								RegisterTipActivity.class);
						MainActivity.this.startActivity(intent);
					} else {
						Toast.makeText(MainActivity.this, "����Ա������",
								Toast.LENGTH_SHORT).show();
					}

				}
			}
			break;

		}
	}
}