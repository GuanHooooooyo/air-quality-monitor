package com.rep.opendata.opendataquary;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class UviActivity extends Activity {
	ListView lvSelect;
	String[] range = {"场","い场","玭场","狥场籔畄"};
	String n = "场";
	String m = "い场";
	String s = "玭场";
	String e = "狥场籔畄";
	String pick ;
	//List<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_uvi);
		lvSelect = (ListView)findViewById(R.id.lvSelect);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,range);
		lvSelect.setAdapter(adapter);
		lvSelect.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(arg2 ==0){	
					//Toast.makeText(getBaseContext(), n, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(UviActivity.this,UsefulActivity.class);
					intent.putExtra("Range", n);
					startActivity(intent);
				}
				else if(arg2 ==1){
					//Toast.makeText(getBaseContext(), m, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(UviActivity.this,UsefulActivity.class);
					intent.putExtra("Range", m);
					startActivity(intent);
				}
				else if(arg2 ==2){
					//Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(UviActivity.this,UsefulActivity.class);
					intent.putExtra("Range", s);
					startActivity(intent);
				}
				else if(arg2 ==3){
					//Toast.makeText(getBaseContext(), e, Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(UviActivity.this,UsefulActivity.class);
					intent.putExtra("Range", e);
					startActivity(intent);
				}
			}
		});
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.uvi, menu);
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
