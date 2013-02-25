package com.bankManagementSystem.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankManagementSystem.util.BaseActivity;

/*
 �û���¼��ȥ��������                        
 */
public class UserManActivity extends BaseActivity {
	private int images[] = { R.drawable.preferential_shop,
			R.drawable.preferential_shop, R.drawable.preferential_shop };
	private String functions[] = { "�û���ѯ", "�޸�����", "�û�ת��" };
	private GridView giGridView;
	private Button btn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.uesrfunction);
		setupView();
		giGridView.setAdapter(new GridViewAdapter(this));

		giGridView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					public void onItemClick(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						Intent intent = new Intent();
						switch (position) {
						case 0:
							intent.setClass(UserManActivity.this,
									UserManQueryActivity.class);

							break;
						case 1:
							intent.setClass(UserManActivity.this,
									UserManUpdatePwdActivity.class);
							break;
						case 2:
							intent.setClass(UserManActivity.this,
									UserManTransferAccountsActivity.class);
							break;
						}
						UserManActivity.this.startActivity(intent);

					}
				});
		btn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent backIntent = new Intent();
				backIntent.setClass(UserManActivity.this, UserActivity.class);
				UserManActivity.this.startActivity(backIntent);

			}
		});

	}

	private void setupView() {
		giGridView = (GridView) findViewById(R.id.gGridView);
		btn = (Button) findViewById(R.id.btn_back);
	}

	static class ViewHolder {
		TextView tv;
		ImageView iv;

	}

	public class GridViewAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public GridViewAdapter(Context context) {
			inflater = LayoutInflater.from(context);

		}

		public int getCount() {

			return images.length;
		}

		public Object getItem(int position) {

			return images[position];
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		// ���GridView�м���͵��ü���getView()..�ж��м����ͨ��getCount������������
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.gv_item, null);
				holder = new ViewHolder();
				holder.tv = (TextView) convertView
						.findViewById(R.id.gv_item_appname);
				holder.iv = (ImageView) convertView
						.findViewById(R.id.gv_item_icon);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(functions[position]);
			holder.iv.setImageResource(images[position]);
			return convertView;
		}

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
							UserManActivity.this.exit();
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