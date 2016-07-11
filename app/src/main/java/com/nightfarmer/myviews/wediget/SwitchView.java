package com.nightfarmer.myviews.wediget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nightfarmer.myviews.R;

/**
 * Created by zhangfan on 16-7-11.
 */
public class SwitchView extends View {

    public SwitchView(Context context) {
        super(context);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setImageResource(R.drawable.switch_btn_slip, R.drawable.switch_bkg_switch, R.drawable.switch_bkg_switch);
    }

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnCheckedStateChangedListener listener;
    private boolean checked;

    private Bitmap backGroundOn;
    private Bitmap backGroundOff;
    private Bitmap switchButton;

    private float viewWidth;
    private float buttonWidth;
    private float buttonX = 0;
    private boolean pressed;

    public void setOnCheckedStateChangedListener(OnCheckedStateChangedListener listener) {
        this.listener = listener;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        if (this.checked == checked) return;
        this.checked = checked;
        invalidate();
        if (listener != null) {
            listener.onChange(this, checked);
        }
    }

    public void setImageResource(int switchButton, int backGroundOn, int backGroundOff) {
        this.switchButton = BitmapFactory.decodeResource(getResources(), switchButton);
        this.backGroundOn = BitmapFactory.decodeResource(getResources(), backGroundOn);
        this.backGroundOff = BitmapFactory.decodeResource(getResources(), backGroundOff);

        viewWidth = this.backGroundOn.getWidth();
        buttonWidth = this.switchButton.getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap backGround;
        if (!pressed) {
            if (checked) {
                buttonX = viewWidth - buttonWidth;
            } else {
                buttonX = 0;
            }
        }
        if (buttonX<viewWidth / 2){
            backGround = backGroundOff;
        }else {
            backGround = backGroundOn;
        }

        canvas.drawBitmap(backGround, new Matrix(), new Paint());
        canvas.drawBitmap(switchButton, buttonX, 0, new Paint());

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                pressed = true;
                break;

            case MotionEvent.ACTION_MOVE:
                pressed = true;
                buttonX = event.getX() - buttonWidth / 2;
                if (buttonX < 0) buttonX = 0;
                if (buttonX > viewWidth - buttonWidth) buttonX = viewWidth - buttonWidth;
                break;

            case MotionEvent.ACTION_UP:
                pressed = false;
                checked = event.getX() > viewWidth / 2;
                if (listener != null) {
                    listener.onChange(this, checked);
                }
                break;

            default:
                pressed = false;
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(backGroundOn.getWidth(), backGroundOn.getHeight());
    }

    public interface OnCheckedStateChangedListener {
        void onChange(SwitchView switchView, boolean checked);
    }
}
