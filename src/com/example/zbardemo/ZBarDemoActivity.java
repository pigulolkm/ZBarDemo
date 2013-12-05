package com.example.zbardemo;

import com.example.zbardemo.CameraTestActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class ZBarDemoActivity extends Activity {
	
	private static final int scan_REQUEST = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zbar_demo);
		
		init();
	}
	
	public void init() {
		
	}
	
	public void scanCode(View v) {
		if(isCameraAvailable())
		{
			Intent intent = new Intent();
			intent.setClass(ZBarDemoActivity.this, CameraTestActivity.class);
			startActivityForResult(intent, scan_REQUEST);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode) {
			case scan_REQUEST:
				if(resultCode == RESULT_OK)
				{
					Toast.makeText(this, "Scan Result = " + data.getStringExtra("SCAN_RESULT") +"\n Count: "+ data.getStringExtra("count"), Toast.LENGTH_LONG).show();
                } 
				else if(resultCode == RESULT_CANCELED && data != null) 
				{
                    /*String error = data.getStringExtra("ERROR_INFO");
                    if(!TextUtils.isEmpty(error)) {
                        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                    }*/
                }
                break;
		}
	}
	
	public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.zbar_demo, menu);
		return true;
	}

}
