package com.example.clock1041;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.view.View;

public class GifView extends View {
	private Movie mMovie;
	private long mMovieStart;

	public GifView(Context context) {
		super(context);
		setFocusable(true);

		java.io.InputStream is = this.getResources().openRawResource(R.drawable.toyoi);
		mMovie = Movie.decodeStream(is);
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