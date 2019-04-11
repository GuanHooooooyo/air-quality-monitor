package com.rep.opendata.opendataquary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class IndexActivity extends Activity {
	ImageView imageTP,imageNTP,imageTU,imageTC,imageTN,imageKH;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		findView();
		//篶onclick ち传 CityActivity
		imageNTP.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "穝カ");
				startActivity(intent);
			}
		});
		imageTP.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "籓カ");
				startActivity(intent);
				
			}
		});
		imageTC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "籓いカ");
				startActivity(intent);
				
			}
		});
		imageTN.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(IndexActivity.this,CityActivity.class);
		intent.putExtra("cityName", "籓玭カ");
		startActivity(intent);
		
	}
		});
		imageKH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "蔼动カ");
				startActivity(intent);
				
			}
		});
		imageTU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "堕カ");
				startActivity(intent);
			}
		});
	}
	//秙把计砞﹚
	private void findView(){
		imageTP = (ImageView)findViewById(R.id.imgtp);
		imageNTP = (ImageView)findViewById(R.id.imgntp);
		imageTU = (ImageView)findViewById(R.id.imgtu);
		imageTC = (ImageView)findViewById(R.id.imgtc);
		imageTN = (ImageView)findViewById(R.id.imgtn);
		imageKH = (ImageView)findViewById(R.id.imgkh);
	}
	//  onCreate()よ猭ず磅︽ findView();
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
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
