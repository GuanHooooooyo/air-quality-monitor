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
	//String[] citynames = {"������","�O�n��","�O����","��饫","�O�_��","�s�_��","�s�˿�","�Ÿq��","���L��","�O�F��","�Ὤ��"};
	
	String[] SiteN = {"�O�_��","�s�_��","��饫","��鿤","�s�˿�","�򶩥�"};
	String[] SiteM = {"���ƿ�","�]�߿�","���L��","�O����","�n�뿤"};
	String[] SiteS = {"�̪F��","�O�n��","�Ÿq��","������"};
	String[] SiteE = {"�Ὤ��","�O�F��","�y����","���","������"};
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
						UviInfo.setTitle("��������UVI")
							.setMessage("�ʴ��t�Ω|�������쵵�~�u����(UVI)�A�е��ݧ�s�C")
							.setNegativeButton("�T�w", null)
							.setCancelable(false)
							.create().show();
					}else if(pos>=0 && pos<=2){
						UviInfo.setTitle("UVI���ơG"+pos)
						.setMessage("�ζ˯żƬ��C�q�šA�i�w�ߥ~�X�C")
						.setNegativeButton("�T�w", null)
						.setCancelable(false)
						.create().show();
					}else if(pos>=3 && pos<=5){
						UviInfo.setTitle("UVI���ơG"+pos)
						.setMessage("�ζ˯żƬ����q�šA�i�w�ߥ~�X�A�y�L�`�N���ΧY�i�C")
						.setNegativeButton("�T�w", null)
						.setCancelable(false)
						.create().show();
					}else if(pos>=6 && pos<=7){
						UviInfo.setTitle("UVI���ơG"+pos)
						.setMessage("�ζ˯żƬ����q�šA�ζˮɶ���30�������A�`�N���Υ~�X�t�ơA�ɶq�ݦb���t�B�C")
						.setNegativeButton("�T�w", null)
						.setCancelable(false)
						.create().show();
					}else if(pos>=8 && pos<=10){
						UviInfo.setTitle("UVI���ơG"+pos)
						.setMessage("�ζ˯żƬ��L�q�šA�ζˮɶ���20�������A�`�N���ΰt�ơA�W��10�I��U��2�I�קK�~�X�C")
						.setNegativeButton("�T�w", null)
						.setCancelable(false)
						.create().show();
					}else{
						UviInfo.setTitle("UVI���ơG"+pos)
						.setMessage("�ζ˯żƬ��M�I�šA�ζˮɶ���15�������A�`�N���ΰt�ƻP���S�窫�A�W��10�I��U��2�I�קK�~�X�C")
						.setNegativeButton("�T�w", null)
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
		
		if(rg.equals("�_��")){
			for(String p : SiteN){
				if(p.equals(r)){
					return true;
				}
		}
	}else if(rg.equals("����")){
		for(String p : SiteM){
			if(p.equals(r)){
				return true;
			}
		}
	}else if(rg.equals("�n��")){
		for(String p : SiteS){
			if(p.equals(r)){
				return true;
			}
		}
	}else if(rg.equals("�F���P�~�q")){
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
				
				//�]�wUVI ���
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
					data.setPublishAgency("�|��������");
				}
				else if(value>=0&&value<=5){
					data.setPublishAgency("�w��");
					
					data.setUVIvalue(value);
				}else if(value>=6&&value<=7){
					data.setPublishAgency("30������");
					
					data.setUVIvalue(value);
				}else if(value>=8&&value<=10){
					data.setPublishAgency("20������");
					
					data.setUVIvalue(value);
				}
				else{
					data.setPublishAgency("15������");
					
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
				dialog = ProgressDialog.show(UsefulActivity.this, "���~�u����", "  ���Ū����...");	
				
		}

		@Override
		protected void onPostExecute(List<ParseData> result) {
			
				dialog.dismiss();
				String pubTime = uvisource.get(0).getPubTime();
				factory.setTitle("��Ƨ�s���")
				.setMessage(pubTime)
				.setNegativeButton("�T�w", null)
				.setCancelable(false)
				.create()
				.show();
				UviAdapter uviadapter = new UviAdapter();
				lvUvi.setAdapter(uviadapter);
				tvCity.setText("�a��");
				tvUvi.setText("UVI");
				tvOut.setText("�ζ˪`�N");	
			
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
