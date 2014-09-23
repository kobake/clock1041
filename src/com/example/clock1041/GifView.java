package com.example.clock1041;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

public class GifView extends View {
	private Movie mMovie;
	private long mMovieStart;

	public GifView(Context context) {
		super(context);
		setFocusable(true);

		// リソースから設定
		java.io.InputStream is = this.getResources().openRawResource(R.drawable.toyoi);
		mMovie = Movie.decodeStream(is);

		// ネットから取得
		new AsyncTask<String, Integer, Movie>(){
			@Override
			protected Movie doInBackground(String... params) {
				Movie movie = null;
				String url = "http://38.media.tumblr.com/063481f87ba3058c8bb235148df090b9/tumblr_nb8zykBVPC1qze3hdo1_r1_500.gif";
				DefaultHttpClient client = new DefaultHttpClient();
				try{
					HttpGet request = new HttpGet(url);
					HttpResponse response = client.execute(request);
					if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
						byte[] data = EntityUtils.toByteArray(response.getEntity());
						movie = Movie.decodeByteArray(data, 0, data.length);
					}
				}
				catch(Exception ex){
					Log.w("test", ex.toString());
				}
				return movie;
			}

			@Override
			protected void onPostExecute(Movie result) {
				// TODO Auto-generated method stub
				// super.onPostExecute(result);
				if(result != null){
					mMovie = result;
				}
			}
		}.execute("");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xFFCCCCCC);

		long now = android.os.SystemClock.uptimeMillis();
		if (mMovieStart == 0) { // first time
			mMovieStart = now;
		}
		if (mMovie != null) {
			int dur = mMovie.duration();
			if (dur == 0) {
				dur = 1000;
			}
			int relTime = (int) ((now - mMovieStart) % dur);
			mMovie.setTime(relTime);
			float xScale = getWidth() * 1.0f / mMovie.width();
			float yScale = getHeight() * 1.0f / mMovie.height();
			if(xScale < yScale)xScale = yScale;
			if(xScale > yScale)yScale = xScale;
			canvas.scale(xScale, yScale);
			mMovie.draw(canvas,
					0,//getWidth() - mMovie.width(),
					0//getHeight() - mMovie.height()
			);
			invalidate();
		}
	}
}