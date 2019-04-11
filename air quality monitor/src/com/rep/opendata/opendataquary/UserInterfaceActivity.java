package com.rep.opendata.opendataquary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.Inflater;

import com.opdt.Utility.ParseValue;
import com.opdt.Utility.XMLHandler;
import com.rep.opendata.opendataquary.CityActivity.ViewTag;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class UserInterfaceActivity extends Activity {
	Spinner sp01;
	ImageView iv01 ;
	String county;
	String[] Citynames = {"臺北市","新北市","桃園市","臺中市","臺南市","高雄市"};
	
	List<ParseValue> PMsource = new ArrayList<ParseValue>();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_interface);
		sp01 = (Spinner)findViewById(R.id.sp01);
		iv01 = (ImageView)findViewById(R.id.imageGoto);
		Bordtask bt = new Bordtask();
		bt.execute();
		sp01.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				county = PMsource.get(arg2).getCounty();
//				Toast.makeText(getBaseContext(),
//						"I choose"+county+"to my final way",Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub		
			}
			
		});
		iv01.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(UserInterfaceActivity.this,CityActivity.class);
				intent.putExtra("cityName", county);
				startActivity(intent);
			}
		});
		//--------code runnable methods---------------------
		
	}

	private boolean city(String cityname){
		for(String c : Citynames){
			if(c.equals(cityname)){
				return true;
			}
		}
		return false;
	}
	
	
	public class Bordtask extends AsyncTask<Void, Void, List<ParseValue>>{
			ProgressDialog pd ;
			com.opdt.Utility.XMLHandler halper;
			
		@Override
		protected List<ParseValue> doInBackground(Void... params) {
			halper = new XMLHandler();
			halper.get();
			PMsource.clear();
			boolean found = false;
	
			for(ParseValue post : halper.posts){
				if(city(post.getCounty())){
					continue;
				}
				if(post.getCounty().length()<3){
					continue;
				}
				for(ParseValue post2 : halper.posts)
					if(post2.getCounty().equals(post.getCounty())){
						found = true;
						PMsource.remove(post2);
						
					}
				if(found)
				PMsource.add(post);
						
			}
			return PMsource;
		}
		@Override
		protected void onPreExecute() {
				pd = ProgressDialog.show(UserInterfaceActivity.this, "", "   資料讀取中...");
			
		}
		@Override
		protected void onPostExecute(List<ParseValue> result) {
				pd.dismiss();		
				IFAdapter adapter = new IFAdapter();
				sp01.setAdapter(adapter);
//			
		}
	}
	


	//--------------County Adapter-----------------------
	
	public class IFAdapter extends BaseAdapter{
			LayoutInflater inflater = LayoutInflater.from(UserInterfaceActivity.this);
			
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return PMsource.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			
			return PMsource.get(position).getCounty();
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@SuppressLint("ResourceAsColor")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Resources getres = getResources();
				VewTag tag = null;
				if(convertView ==null){
					convertView = inflater.inflate(R.layout.txtspineruse,null);
					tag = new VewTag();
					tag.selectCity = (TextView)convertView.findViewById(R.id.selectCity);
					convertView.setTag(tag);
				}else{
					tag = (VewTag)convertView.getTag();
				}
				tag.selectCity.setText(PMsource.get(position).getCounty());
				//tag.selectCity.setTextColor(getres.getColor(R.color.org));	
			return convertView;
		}
		
	}
	//--------SiteNames Adapter------------------------
	
	public class VewTag{
		public TextView selectCity;
	}

}
