package com.georg.todos.views;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.georg.todos.R;

public class CrossOut extends View implements TextWatcher{
    private Paint paintLine;
    private EditText editText;

    private boolean drawCrossout = false;

    // Constructor
    public CrossOut(Context context, EditText editText) {
        super(context);
        this.editText = editText;
        init();

    }

    public void setDrawCrossout(boolean drawCrossout){
        this.drawCrossout = drawCrossout;
        invalidate();
    }

    private void init() {
        // Initialize Paint object
        paintLine = new Paint();
        paintLine.setColor(Color.BLACK); // Set the line color
        paintLine.setStrokeWidth(6f); // Set the line width

        //adapt the line width to text changes
        editText.addTextChangedListener(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!drawCrossout){return;}
        // Draw a line from point (x1, y1) to point (x2, y2)
        String text = editText.getText().toString();
        Paint paint_text = editText.getPaint();
        float textwidth = paint_text.measureText(text);
        int stick_out = (int) getResources().getDimension(R.dimen.crossout_stickout);
        canvas.drawLine(editText.getLeft()-stick_out, (float) getHeight() /2, textwidth+editText.getLeft()+stick_out, (float) getHeight() /2, paintLine);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}
    @Override
    public void afterTextChanged(Editable s) {invalidate();}
}