package com.example.clock1041;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

//import com.example.android.apis.graphics.BitmapDecode.SampleView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.VideoView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//View videoView = (VideoView)findViewById(R.id.videoView1);
		GifView gifView = new GifView(this);
		gifView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		FrameLayout frame = (FrameLayout)findViewById(R.id.FrameLayout1);
		frame.addView(gifView, 0);
		//videoView.
		//setContentView(new GifView(this));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
