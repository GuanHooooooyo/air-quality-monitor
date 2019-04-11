package com.rep.opendata.opendataquary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;

import com.opdt.Utility.ParseValue;
import com.opdt.Utility.XMLHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CityActivity extends Activity {
	ListView lvData;
	TextView tvPublish,tvUpdte,tv3,tv4,textView3;
	int i ;
	String s ="";
	List<ParseValue> listsource = new ArrayList<ParseValue>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		findView();
		dataTask showData = new dataTask();
		showData.execute();
		
		//�غcItemClickListener
		lvData.setOnItemClickListener(new OnItemClickListener() {
			AlertDialog.Builder factory = new AlertDialog.Builder(CityActivity.this);
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int pm25value = listsource.get(position).getPM25Value();
				String site = listsource.get(position).getSiteName();
				//�P�_PM2.5 �@�׫���
				if(pm25value>=0 && pm25value<36){
					factory.setIcon(R.drawable.safe)
					.setTitle(site+" PM2.5����:"+pm25value)
					.setMessage("���a�B�L�ɿ@�צ��w�����СA�Цw�ߥ~�X�C")
					.setNegativeButton("�T�w", null)
					.setCancelable(false);
				}else if(pm25value>35 && pm25value<54){
					factory.setIcon(R.drawable.cloud)
					.setTitle(site+" PM2.5����:"+pm25value)
					.setMessage("���a�B�L�ɿ@�צ�󴶳q���СA�i���`�~�X�A�`�N�L�Ӥ���(�����o�B�y���)�ɶq��֤�~���ʡC")
					.setNegativeButton("�T�w", null)
					.setCancelable(false);
				}else if(pm25value>53 && pm25value<71){
					factory.setIcon(R.drawable.maskp1)
					.setTitle("  "+site+" PM2.5����:"+pm25value)
					.setMessage("���a�B�L�ɿ@�צ��M�I���СA�~�X�����W�f�n�A�D���n�ɽд�֥~�X�C")
					.setNegativeButton("�T�w", null)
					.setCancelable(false);
				}else if(pm25value>70){
					factory.setIcon(R.drawable.heavy)
					.setTitle(site+" PM2.5����:"+pm25value)
					.setMessage("���a�B�L�ɿ@�׶W���A�D���n�ɽФ��n�~�X�C")
					.setNegativeButton("�T�w", null)
					.setCancelable(false);
				}else{
					factory.setIcon(R.drawable.warning)
					.setTitle(site+" �t�κ��@��")
					.setMessage("�Ӧa�Ϩt�Υ��b���@�A�е��ݤU����s�C")
					.setNegativeButton("�T�w", null)
					.setCancelable(false);
				}
				AlertDialog dialog = factory.create();
				dialog.show();
			}
		});
	}
	
	//�غc GetView ��Intent ��ƿz��X��
	private String getName(){
		String city = this.getIntent().getStringExtra("cityName");
		return city;
	}
   //�غcAsyncTask 
		private class dataTask extends AsyncTask<Void, Void, List<ParseValue>>{
				ProgressDialog pd;
				com.opdt.Utility.XMLHandler halper;
	@Override
	protected List<ParseValue> doInBackground(Void... params) {
		halper = new XMLHandler();
		halper.get();
		//InputStream is = halper.get();
		listsource.clear();
		//BufferedReader buff = XMLHandler.StreamtoReader(is);
		for(ParseValue post : halper.posts){
			//Ū����� InputStream UTF-8
			//�P�_�a�ϦW��
			if(!post.getCounty().equals(getName())){
				continue;
			}
			//�P�_PM2.5 �O�_���ŭ�
			String pm25 = post.getPM25();
			if(pm25.equals(""))
				i=-1;
			else
				i = Integer.parseInt(post.getPM25());
			if(i<0){
				post.setPM25("���@");
				post.setPM25value(-1);
				post.setIcon(R.drawable.warning);
			}
			else if(i<36){
				post.setPM25("�w��");
				post.setPM25value(i);
				post.setIcon(R.drawable.safe);
			}else if(i>35 && i<54){
				post.setPM25("���q");
				post.setPM25value(i);
				post.setIcon(R.drawable.cloud);
			}else if(i>53 && i<71){
				post.setPM25("�M�I");
				post.setPM25value(i);
				post.setIcon(R.drawable.maskp1);
			}else{
				post.setPM25("�W��");
				post.setPM25value(i);
				post.setIcon(R.drawable.heavy);
			}
			listsource.add(post);
			
		}
		return listsource;
	}

	@Override
	protected void onPreExecute() {
			pd =ProgressDialog.show(CityActivity.this, "", "   ���Ū����,�еy��...");
	}

	@Override
	protected void onPostExecute(List<ParseValue> result) {
		pd.dismiss();
		String publish = listsource.get(0).getPublishTime();
	//	BufferedReader reader = new BufferedReader(new InputStreamReader(publish));
		tvPublish.setText(publish);
		tvUpdte.setText("��s���");
		tv3.setText("�a�ϦW��");
		tv4.setText("���е���");
		textView3.setText("�Ů�~��");
		dataAdapter adapter = new dataAdapter();
		lvData.setAdapter(adapter);
	}
  }
		
	//�غc Adapter�~��BaseAdapter
		private class dataAdapter extends BaseAdapter{
				LayoutInflater inflator = LayoutInflater.from(CityActivity.this);
		@Override
		public int getCount() {
			return listsource.size();
		}

		@Override
		public Object getItem(int position) {
			return listsource.get(position).getSiteName();
		}

		@Override
		public long getItemId(int position) {		
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewTag tag = null;
			if(convertView == null){
				convertView = inflator.inflate(R.layout.txtdescr, null);
				tag = new ViewTag();
				tag.txtLocal = (TextView)convertView.findViewById(R.id.txtLocal);
				tag.txtIndetor = (TextView)convertView.findViewById(R.id.txtIndetor);
				tag.imageSug = (ImageView)convertView.findViewById(R.id.imageSug);
				convertView.setTag(tag);
			}else{
				tag = (ViewTag)convertView.getTag();
			}
			//�]�w�����C�� :�w�� ->���q ->�M�I ->�W��
			Resources getres = getResources();
			tag.txtLocal.setText(listsource.get(position).getSiteName());
			tag.txtIndetor.setText(listsource.get(position).getPM25());
				if(ItemColor(position)==-1){
					tag.txtIndetor.setTextColor(android.graphics.Color.GRAY);
			      }else if(ItemColor(position)<36){
			    	  tag.txtIndetor.setTextColor(getres.getColor(R.color.green));
			      }else if(ItemColor(position)>35 && ItemColor(position)<54){
			    	  tag.txtIndetor.setTextColor(getres.getColor(R.color.org));
			      }else if(ItemColor(position)>53 && ItemColor(position)<71){
			    	  tag.txtIndetor.setTextColor(android.graphics.Color.RED);
			      }else{
			    	  tag.txtIndetor.setTextColor(getres.getColor(R.color.purple));
			      }
			tag.imageSug.setImageResource(listsource.get(position).getIcon());
			
			return convertView;
			}		
		}
		public class ViewTag{
			public TextView txtLocal;
			public TextView txtIndetor;
			public ImageView imageSug;
		}
		public void findView(){
			lvData = (ListView)findViewById(R.id.lvData);
		
			tvPublish = (TextView)findViewById(R.id.tvPublish);
			tv3 = (TextView)findViewById(R.id.tv3);
			tv4 = (TextView)findViewById(R.id.tv4);
			tvUpdte = (TextView)findViewById(R.id.tvUpdate);
			textView3 = (TextView)findViewById(R.id.tvAct);
		}
		public int ItemColor(int pos){
			int item = listsource.get(pos).getPM25Value();
			return item;
		}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 0, 0, "�W�@��");
		menu.add(0, 1, 0, "���s��z");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()){
		case 0:
			finish();
			break;
		case 1:
			InitRefresh();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	//���s��z�A�e�������A�}��
	public void InitRefresh(){
		Intent it = getIntent();
		finish();
		startActivity(it);
	}
	
}
