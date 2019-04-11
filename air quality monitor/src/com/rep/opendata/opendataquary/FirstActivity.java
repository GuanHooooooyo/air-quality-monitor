package com.rep.opendata.opendataquary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class FirstActivity extends TabActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_first);
		TabHost myhost = getTabHost();
		myhost.addTab(myhost.newTabSpec("�Ů�~��").setIndicator("�Ů�~��").setContent(new Intent(this  ,IndexActivity.class )));
		myhost.addTab(myhost.newTabSpec("��L�a��").setIndicator("��L�a��").setContent(new Intent(this  ,UserInterfaceActivity.class )));
		myhost.addTab(myhost.newTabSpec("���~�u").setIndicator("���~�u").setContent(new Intent(this  ,UviActivity.class)));
		myhost.addTab(myhost.newTabSpec("����").setIndicator("����").setContent(new Intent(this  ,AboutinfoActivity.class )));
		myhost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 0, 0, "��Ƨ�s");
		menu.add(1,1,1, "�`�N�ƶ�");
		//menu.add(2,2,2,"�}��GPS");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		AlertDialog.Builder dialog = new AlertDialog.Builder(FirstActivity.this);
		switch(item.getItemId()){
		case 0:
			//AlertDialog.Builder dialog = new AlertDialog.Builder(FirstActivity.this);
			dialog.setTitle("��s���A")
			.setMessage("�C�p�ɧ�s�@��")
			.setNegativeButton("�T�w", null)
			.setCancelable(false)
			.create().show();
			break;
		case 1 :
			dialog.setTitle("�p����")
			.setMessage("1.�Ů�~����ƥH���a�B�L��(PM2.5)�@�׬��ǡC\n\n" +
						"2.���~�u���� UVI�A���ηǫh�����C�q�B���q�B���q�B�L�q�B�M�I�C")
						.setNegativeButton("�T�w", null)
						.setCancelable(false).create().show();
			break;
//		case 2:
//			Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//			startActivity(intent);
//			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
