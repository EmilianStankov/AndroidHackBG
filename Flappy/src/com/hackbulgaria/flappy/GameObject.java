package com.hackbulgaria.flappy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class GameObject {
	private Bitmap bitmap;
	private int posX;
	private int posY;
	GameObject(Bitmap b, int x, int y) {
		bitmap = b;
		posX = x;
		posY = y;
	}
	public void draw(Canvas canvas, Paint p) {
		canvas.drawBitmap(bitmap, posX, posY, p);
	}
	public void update() {
		posY -= 3;
	}
	public Point getPosition() {
		return new Point(posX, posY);
	}
	public int getWidth() {
		return bitmap.getWidth();
	}
	public int getHeight() {
		return bitmap.getHeight();
	}
	public void onClick() {
	}
}
