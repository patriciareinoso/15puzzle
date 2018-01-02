package puzzle.frontend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Class that represents a Tile in the board.
 */

public class TileButton extends android.support.v7.widget.AppCompatButton {

    /**
     * Value of the Tile.
     */
    private int value;

    /**
     * Paint associated to the background of the tile.
     */
    private Paint mTilePaint;

    /**
     * Paint associated to the text of the tile.
     */
    private Paint mTextPaint;

    /**
     * Bounds defining the coordinates of the tile.
     */
    private RectF mTileBounds = new RectF();

    public TileButton(Context context, int value){
        super(context);

        this.value = value;

        //Setting the Tile paint
        mTilePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTilePaint.setStyle(Paint.Style.FILL);
        mTilePaint.setColor(Color.LTGRAY);
        mTilePaint.setTextAlign(Paint.Align.CENTER);

        //Setting th Text Paint.
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(70f);
    }

    /**
     * Overrides the onDraw method to be able to draw the Background and the value.
     * @param canvas where the tile is going to be drawn.
     */
    @Override
    public void onDraw(Canvas canvas){
        canvas.drawRect(mTileBounds, mTilePaint);
        canvas.drawText(Integer.toString(value), 50, 50, mTextPaint);
    }

    /**
     * Sets the coordiates of the tile.
     * @param left the X coordinate of the left side of the rectangle
     * @param top The Y coordinate of the top of the rectangle
     * @param right the X coordinate of the right side of the rectangle
     * @param bottom the Y coordinate of the bottom of the rectangle
     */
    public void setBounds(float left, float top, float right, float bottom){
        mTileBounds = new RectF(left, top, right, bottom);
    }

}
