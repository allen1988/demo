package com.bankManagementSystem.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.bankManagementSystem.util.BaseActivity;

/*
 ��ծ������                       
 */
public class FinanicalEnvironmentCalActivity extends BaseActivity {
	private Button btn;
	private TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.governmentloan);
		setupView();
		

		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent backIntent = new Intent();
				backIntent.setClass(FinanicalEnvironmentCalActivity.this,
						FinanicalCalActivity.class);
				FinanicalEnvironmentCalActivity.this.startActivity(backIntent);

			}
		});
		
		tv.setText("��ծ���������");
		btn.setText("����");

	}

	private void setupView() {
		btn = (Button) findViewById(R.id.btn_back);
		tv=(TextView)findViewById(R.id.tv_title);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.containermenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.set:
			startActivity(new Intent(Settings.ACTION_SETTINGS));

			break;

		case R.id.exit:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("��ȷ��Ҫ�˳���");
			builder.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							FinanicalEnvironmentCalActivity.this.exit();
						}
					});
			builder.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {

						}
					});
			builder.create().show();

			break;
		}
		return super.onOptionsItemSelected(item);
	}

}