package com.rep.opendata.opendataquary;

import java.net.URI;
import java.net.URISyntaxException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AboutinfoActivity extends Activity {
	ListView lvAbout;
	TextView tvAbout;
	String[] item = {"細懸浮微粒監測","紫外線監測","檢查更新","相關程式","關於我們"};
	public static final String URL = "https://play.google.com/store/apps/details?id=com.mizSoftware.pm25&hl=zh_TW";
	public static final String PM25 = "http://taqm.epa.gov.tw/taqm/zh-tw/default.aspx";
	public static final String UVI ="http://taqm.epa.gov.tw/taqm/zh-tw/UvMap.aspx";
	public static final String ME = "http://opendata.epa.gov.tw/DevelopZone/DataList/?PageIndex=4";
	Uri uri;
	Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutinfo);
		lvAbout = (ListView)findViewById(R.id.lvAbout);
		tvAbout = (TextView)findViewById(R.id.tvAbout);
		//超連結方法 Spannable
		SpannableString ss = new SpannableString("目前版本：1.0.0\n參考資訊：OpenData.epa\n環境品質小幫手關心您");
		//建立超連結  起始字元16,結束字元28 -> Opendata.epa
		ss.setSpan(new URLSpan(ME), 16, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//顯示文字
		tvAbout.setText(ss);
		//做跳轉頁面
		tvAbout.setMovementMethod(LinkMovementMethod.getInstance());
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_list_item_1,item);
		lvAbout.setAdapter(adapter);
		lvAbout.setOnItemClickListener(new OnItemClickListener() {
				AlertDialog.Builder dialog = new AlertDialog.Builder(AboutinfoActivity.this);
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					switch(position){
					case 0:
						//URL
						uri = Uri.parse(PM25);
						intent = new Intent(Intent.ACTION_VIEW,uri);
						startActivity(intent);
						break;
					case 1:
						//URL
						uri = Uri.parse(UVI);
						intent = new Intent(Intent.ACTION_VIEW,uri);
						startActivity(intent);
						break;
					case 2:
						dialog.setTitle("更新資訊")
						.setMessage("此版本為最新版本，尚無更新資訊。")
						.setNegativeButton("確定", null)
						.setCancelable(false)
						.create()
						.show();
						break;
					case 3:
						uri = Uri.parse(URL);
						intent = new Intent(Intent.ACTION_VIEW,uri);
						startActivity(intent);
						break;
					case 4:
						dialog.setTitle("關於我們")
						.setMessage("透過行動裝置讓人們了解各地區環境品質的好壞與建議事項" +
									"\t輔英科技大學資管系\n環境品質小幫手團隊\n關心您的健康")
						.setNegativeButton("確定", null)
						.setCancelable(false)
						.create()
						.show();
						break;
					}
			}
		});
	}

	
	//actionBar Menu item
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.aboutinfo, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
