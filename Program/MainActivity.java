package com.my.newproject13;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import java.text.DecimalFormat;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;

public class MainActivity extends Activity {
	
	private LinearLayout linear5;
	private LinearLayout linear6;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear7;
	private TextView Nama_Tugas;
	private TextView textview2;
	private TextView x;
	private TextView textview3;
	private TextView y;
	private TextView textview4;
	private TextView z;
	private Button button1;
	
	private SensorManager Accelerometer_nilai;
	private SensorEventListener _Accelerometer_nilai_sensor_listener;
	private SharedPreferences Data;
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		linear8 = findViewById(R.id.linear8);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear7 = findViewById(R.id.linear7);
		Nama_Tugas = findViewById(R.id.Nama_Tugas);
		textview2 = findViewById(R.id.textview2);
		x = findViewById(R.id.x);
		textview3 = findViewById(R.id.textview3);
		y = findViewById(R.id.y);
		textview4 = findViewById(R.id.textview4);
		z = findViewById(R.id.z);
		button1 = findViewById(R.id.button1);
		Accelerometer_nilai = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		
		Data = getSharedPreferences("Data", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Accelerometer_nilai.unregisterListener(_Accelerometer_nilai_sensor_listener);
				Data.edit().putString("data1", x.getText().toString()).commit();
				Data.edit().putString("data2", y.getText().toString()).commit();
				Data.edit().putString("data3", z.getText().toString()).commit();
				intent.setClass(getApplicationContext(), ViewActivity.class);
				startActivity(intent);
			}
		});
		
		_Accelerometer_nilai_sensor_listener = new SensorEventListener() {
			@Override
			public void onSensorChanged(SensorEvent _param1) {
				float[] _rotationMatrix = new float[16];
				SensorManager.getRotationMatrixFromVector(_rotationMatrix, _param1.values);
				float[] _remappedRotationMatrix = new float[16];
				SensorManager.remapCoordinateSystem(_rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, _remappedRotationMatrix);
				float[] _orientations = new float[3];
				SensorManager.getOrientation(_remappedRotationMatrix, _orientations);
				for (int _i = 0; _i < 3; _i++) {
					_orientations[_i] = (float)(Math.toDegrees(_orientations[_i]));
				}
				final double _x = _orientations[0];
				final double _y = _orientations[1];
				final double _z = _orientations[2];
				Nama_Tugas.setTranslationX((float)(_x));
				Nama_Tugas.setTranslationY((float)(_y));
				Nama_Tugas.setTranslationX((float)(_z));
				x.setText(String.valueOf(_x));
				y.setText(String.valueOf(_y));
				z.setText(String.valueOf(_z));
			}
			
			@Override
			public void onAccuracyChanged(Sensor _param1, int _param2) {
				
			}
		};
		Accelerometer_nilai.registerListener(_Accelerometer_nilai_sensor_listener, Accelerometer_nilai.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	private void initializeLogic() {
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}
