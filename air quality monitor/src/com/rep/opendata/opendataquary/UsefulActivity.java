package com.rep.opendata.opendataquary;

import java.util.ArrayList;
import java.util.List;

import com.opdt.otUtility.ParseData;
import com.opdt.otUtility.XmlCatcher;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UsefulActivity extends Activity {
	ListView lvUvi;
	TextView tvCity,tvUvi,tvOut,txtnon;
	List<ParseData> uvisource = new ArrayList<ParseData>();
	int value;
	//String[] citynames = {"高雄市","臺南市","臺中市","桃園市","臺北市","新北市","新竹縣","嘉義縣","雲林縣","臺東縣","花蓮縣"};
	
	String[] SiteN = {"臺北市","新北市","桃園市","桃園縣","新竹縣","基隆市"};
	String[] SiteM = {"彰化縣","苗栗縣","雲林縣","臺中市","南投縣"};
	String[] SiteS = {"屏東縣","臺南市","嘉義縣","高雄市"};
	String[] SiteE = {"花蓮縣","臺東縣","宜蘭縣","澎湖縣","金門縣"};
	String uv  ;
	int parseuv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_useful);
		findView();
		
		UviTask uvi = new UviTask();
		uvi.execute();
		
		lvUvi.setOnItemClickListener(new OnItemClickListener() {
			AlertDialog.Builder UviInfo = new AlertDialog.Builder(UsefulActivity.this);
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					int pos = uvisource.get(position).getUVIvalue();
					//String poss = uvisource.get(position).getUVI();
					//parseuv = Integer.parseInt(poss);
					
					if(pos<0){
						UviInfo.setTitle("未偵測到UVI")
							.setMessage("監測系統尚未偵測到紫外線指數(UVI)，請等待更新。")
							.setNegativeButton("確定", null)
							.setCancelable(false)
							.create().show();
					}else if(pos>=0 && pos<=2){
						UviInfo.setTitle("UVI指數："+pos)
						.setMessage("曬傷級數為低量級，可安心外出。")
						.setNegativeButton("確定", null)
						.setCancelable(false)
						.create().show();
					}else if(pos>=3 && pos<=5){
						UviInfo.setTitle("UVI指數："+pos)
						.setMessage("曬傷級數為中量級，可安心外出，稍微注意防曬即可。")
						.setNegativeButton("確定", null)
						.setCancelable(false)
						.create().show();
					}else if(pos>=6 && pos<=7){
						UviInfo.setTitle("UVI指數："+pos)
						.setMessage("曬傷級數為高量級，曬傷時間為30分鐘內，注意防曬外出配備，盡量待在陰暗處。")
						.setNegativeButton("確定", null)
						.setCancelable(false)
						.create().show();
					}else if(pos>=8 && pos<=10){
						UviInfo.setTitle("UVI指數："+pos)
						.setMessage("曬傷級數為過量級，曬傷時間為20分鐘內，注意防曬配備，上午10點到下午2點避免外出。")
						.setNegativeButton("確定", null)
						.setCancelable(false)
						.create().show();
					}else{
						UviInfo.setTitle("UVI指數："+pos)
						.setMessage("曬傷級數為危險級，曬傷時間為15分鐘內，注意防曬配備與長袖衣物，上午10點到下午2點避免外出。")
						.setNegativeButton("確定", null)
						.setCancelable(false)
						.create().show();
					}
			}
		});
	}
	//------------Main-------------------------
//	private boolean city(String cityname){
//		for(String c : citynames){
//			if(c.equals(cityname)){
//				return true;
//			}
//		}
//		return false;
//	}
	
	private boolean getSite(String r){
		String rg = this.getIntent().getStringExtra("Range");
		
		if(rg.equals("北部")){
			for(String p : SiteN){
				if(p.equals(r)){
					return true;
				}
		}
	}else if(rg.equals("中部")){
		for(String p : SiteM){
			if(p.equals(r)){
				return true;
			}
		}
	}else if(rg.equals("南部")){
		for(String p : SiteS){
			if(p.equals(r)){
				return true;
			}
		}
	}else if(rg.equals("東部與外島")){
		for(String p : SiteE){
			if(p.equals(r)){
				return true;
			}
		}
	}
		return false;
	}
	
	private class UviTask extends AsyncTask<Void, Void, List<ParseData>>{
			ProgressDialog dialog ;
//			AlertDialog
			AlertDialog.Builder factory = new AlertDialog.Builder(UsefulActivity.this);
			com.opdt.otUtility.XmlCatcher Uvicatcher;
		@Override
		protected List<ParseData> doInBackground(Void... params) {
			Uvicatcher = new XmlCatcher();
			Uvicatcher.get();
			uvisource.clear();
			
				
			try{
			for(ParseData data : Uvicatcher.UVIposts){
				
				//設定UVI 抓值
				//value = Integer.parseInt(data.getUVI());
				if(!getSite(data.getcounty())){
					continue;
				}

				//-------------Main-------------
//				if(!city(data.getcounty())){
//					continue;
//				}
				String UviInfo = data.getUVI();
				if(UviInfo.equals(""))
					value = -1;
				else					
					value = Integer.parseInt(data.getUVI());
					
				if(value<0){
					data.setUVI("n");
					data.setUVIvalue(-1);
					data.setPublishAgency("尚未偵測到");
				}
				else if(value>=0&&value<=5){
					data.setPublishAgency("安全");
					
					data.setUVIvalue(value);
				}else if(value>=6&&value<=7){
					data.setPublishAgency("30分鐘內");
					
					data.setUVIvalue(value);
				}else if(value>=8&&value<=10){
					data.setPublishAgency("20分鐘內");
					
					data.setUVIvalue(value);
				}
				else{
					data.setPublishAgency("15分鐘內");
					
					data.setUVIvalue(value);
					
				}

				uvisource.add(data);
				
			}
			}catch(Exception ex){
				Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
			}
			return uvisource;
		}

		@Override
		protected void onPreExecute() {
				dialog = ProgressDialog.show(UsefulActivity.this, "紫外線指數", "  資料讀取中...");	
				
		}

		@Override
		protected void onPostExecute(List<ParseData> result) {
			
				dialog.dismiss();
				String pubTime = uvisource.get(0).getPubTime();
				factory.setTitle("資料更新日期")
				.setMessage(pubTime)
				.setNegativeButton("確定", null)
				.setCancelable(false)
				.create()
				.show();
				UviAdapter uviadapter = new UviAdapter();
				lvUvi.setAdapter(uviadapter);
				tvCity.setText("地區");
				tvUvi.setText("UVI");
				tvOut.setText("曬傷注意");	
			
		}
		
	}
	private class UviAdapter extends BaseAdapter{
			LayoutInflater uviInflater = LayoutInflater.from(UsefulActivity.this);
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return uvisource.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return uvisource.get(position).getSiteName();
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			UviTag tag = null;
			if(convertView == null){
				convertView = uviInflater.inflate(R.layout.txtuvi, null);
				tag = new UviTag();
				tag.txtCty = (TextView)convertView.findViewById(R.id.txtCity);
				tag.txtUVI = (TextView)convertView.findViewById(R.id.txtUVI);
				tag.txtOut = (TextView)convertView.findViewById(R.id.txtOut);
				convertView.setTag(tag);
			}else{
				tag = (UviTag)convertView.getTag();
			}
				tag.txtCty.setText(uvisource.get(position).getSiteName());
				tag.txtUVI.setText(uvisource.get(position).getUVI());
				tag.txtOut.setText(uvisource.get(position).getPublishAgency());
			return convertView;
		}
		
	}
	public class UviTag{
		public TextView txtCty;
		public TextView txtUVI;
		public TextView txtOut;
	}
	private void findView(){
		lvUvi = (ListView)findViewById(R.id.lvUvi);
		tvCity = (TextView)findViewById(R.id.tvCity);
		tvUvi = (TextView)findViewById(R.id.tvUvi);
		tvOut = (TextView)findViewById(R.id.tvOut);
	}
	
}
