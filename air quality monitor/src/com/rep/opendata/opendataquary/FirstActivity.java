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
		myhost.addTab(myhost.newTabSpec("空氣品質").setIndicator("空氣品質").setContent(new Intent(this  ,IndexActivity.class )));
		myhost.addTab(myhost.newTabSpec("其他地區").setIndicator("其他地區").setContent(new Intent(this  ,UserInterfaceActivity.class )));
		myhost.addTab(myhost.newTabSpec("紫外線").setIndicator("紫外線").setContent(new Intent(this  ,UviActivity.class)));
		myhost.addTab(myhost.newTabSpec("關於").setIndicator("關於").setContent(new Intent(this  ,AboutinfoActivity.class )));
		myhost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 0, 0, "資料更新");
		menu.add(1,1,1, "注意事項");
		//menu.add(2,2,2,"開啟GPS");
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
			dialog.setTitle("更新狀態")
			.setMessage("每小時更新一次")
			.setNegativeButton("確定", null)
			.setCancelable(false)
			.create().show();
			break;
		case 1 :
			dialog.setTitle("小幫手")
			.setMessage("1.空氣品質指數以細懸浮微粒(PM2.5)濃度為準。\n\n" +
						"2.紫外線指數 UVI，防曬準則分為低量、中量、高量、過量、危險。")
						.setNegativeButton("確定", null)
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
