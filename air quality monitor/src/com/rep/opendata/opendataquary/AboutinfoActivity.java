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
	String[] item = {"���a�B�L�ɺʴ�","���~�u�ʴ�","�ˬd��s","�����{��","����ڭ�"};
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
		//�W�s����k Spannable
		SpannableString ss = new SpannableString("�ثe�����G1.0.0\n�ѦҸ�T�GOpenData.epa\n���ҫ~��p�������߱z");
		//�إ߶W�s��  �_�l�r��16,�����r��28 -> Opendata.epa
		ss.setSpan(new URLSpan(ME), 16, 28, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		//��ܤ�r
		tvAbout.setText(ss);
		//�����୶��
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
						dialog.setTitle("��s��T")
						.setMessage("���������̷s�����A�|�L��s��T�C")
						.setNegativeButton("�T�w", null)
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
						dialog.setTitle("����ڭ�")
						.setMessage("�z�L��ʸ˸m���H�̤F�ѦU�a�����ҫ~�誺�n�a�P��ĳ�ƶ�" +
									"\t���^��ޤj�Ǹ�ިt\n���ҫ~��p����ζ�\n���߱z�����d")
						.setNegativeButton("�T�w", null)
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
