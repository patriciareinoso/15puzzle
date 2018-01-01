package puzzle.frontend;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * View that represents the puzzle board
 */

public class BoardView extends View {

    private float mSize;
    private Paint mBoardPaint;
    private RectF mBoardBounds = new RectF();

    public static final int SIZE = 16;
    public static final int COLUMNS = 4;

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BoardView, 0, 0);

        try {
            mSize = a.getFloat(R.styleable.BoardView_size, 0.0f);
        } finally {
            a.recycle();
        }
        init();
    }

    public void setSize(float size) {
        mSize = size;
        invalidate();
        requestLayout();
    }

    public float getSize() {
        return mSize;
    }

    public void init() {
        // Set up the paint for the board
        Resources res = getResources();
        mBoardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBoardPaint.setStyle(Paint.Style.FILL);
        mBoardPaint.setColor(res.getColor(R.color.gray));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // Account for padding
        float xpad = (float) (getPaddingLeft() + getPaddingRight());
        float ypad = (float) (getPaddingTop() + getPaddingBottom());

        float ww = (float) w - xpad;
        float hh = (float) h - ypad;

        // Size of the board
        mSize = Math.min(ww, hh);
        mBoardBounds = new RectF(0.0f, 0.0f, mSize, mSize);
        mBoardBounds.offsetTo(getPaddingLeft(), getPaddingTop());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the board
        canvas.drawRect(mBoardBounds, mBoardPaint);

    }
}
