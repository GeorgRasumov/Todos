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

public class CrossOut extends View implements SingleTodo.ToDoDoneChangeListener, TextWatcher{
    private Paint paintLine;
    private EditText editText;

    private boolean drawCrossout = true;

    // Constructor
    public CrossOut(Context context, EditText editText) {
        super(context);
        this.editText = editText;
        init();

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
    public void onToDoStatusChanged(boolean done) {
        this.drawCrossout = !done;
        invalidate();
    }

    @Override
    public void afterTextChanged(Editable s) {
        invalidate();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {} //Empty
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {} //Empty

}