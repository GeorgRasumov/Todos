package com.georg.todos;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class CrossOut extends View {
    private Paint paint;
    private EditText editText;

    private boolean drawcrossout = true;
    public void setDrawcrossout(boolean drawcrossout) {
        this.drawcrossout = drawcrossout;
        invalidate();
    }

    // Constructor
    public CrossOut(Context context, EditText editText) {
        super(context);
        this.editText = editText;

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                invalidate();
            }

        });
        init();

    }

    // Initialize Paint object
    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLACK); // Set the line color
        paint.setStrokeWidth(6f); // Set the line width
        // Add more paint settings as needed
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!drawcrossout){return;}
        // Draw a line from point (x1, y1) to point (x2, y2)
        String text = editText.getText().toString();
        Paint paint_text = editText.getPaint();
        float textwidth = paint_text.measureText(text);
        int stick_out = (int) getResources().getDimension(R.dimen.crossout_stickout);
        canvas.drawLine(editText.getLeft()-stick_out, (float) getHeight() /2, textwidth+editText.getLeft()+stick_out, (float) getHeight() /2, paint);
    }
}