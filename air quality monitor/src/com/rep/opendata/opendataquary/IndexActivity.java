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
		//�غconclick ������ CityActivity
		imageNTP.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "�s�_��");
				startActivity(intent);
			}
		});
		imageTP.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "�O�_��");
				startActivity(intent);
				
			}
		});
		imageTC.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "�O����");
				startActivity(intent);
				
			}
		});
		imageTN.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(IndexActivity.this,CityActivity.class);
		intent.putExtra("cityName", "�O�n��");
		startActivity(intent);
		
	}
		});
		imageKH.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "������");
				startActivity(intent);
				
			}
		});
		imageTU.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this,CityActivity.class);
				intent.putExtra("cityName", "��饫");
				startActivity(intent);
			}
		});
	}
	//���s�ѼƳ]�w
	private void findView(){
		imageTP = (ImageView)findViewById(R.id.imgtp);
		imageNTP = (ImageView)findViewById(R.id.imgntp);
		imageTU = (ImageView)findViewById(R.id.imgtu);
		imageTC = (ImageView)findViewById(R.id.imgtc);
		imageTN = (ImageView)findViewById(R.id.imgtn);
		imageKH = (ImageView)findViewById(R.id.imgkh);
	}
	//  �bonCreate()��k������ findView();
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
