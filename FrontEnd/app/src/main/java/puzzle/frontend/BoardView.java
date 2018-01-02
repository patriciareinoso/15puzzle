package puzzle.frontend;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.service.quicksettings.Tile;
import android.util.AttributeSet;
import android.view.View;

/**
 * View that represents the puzzle board
 */

public class BoardView extends View {

    private float mSize;
    private float mTileSize;
    private Paint mBoardPaint;
    private RectF mBoardBounds = new RectF();
    public Context mContext;
    TileButton newTile;

    public static final int SIZE = 16;
    public static final int COLUMNS = 4;

    /**
     * Class constructor taking a context and an attribute set. This constructor is used by the
     * layout engine to construct a {@link BoardView} from a set of XML attributes.
     * @param context of the layout.
     * @param attrs set of attributes
     *
     */
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BoardView, 0, 0);

        try {
            mSize = a.getFloat(R.styleable.BoardView_size, 0.0f);
        } finally {
            a.recycle();
        }
        mContext = context;
        init();
    }

    /**
     * Set the dimension of the Board.
     * @param size of the board.
     */
    public void setSize(float size) {
        mSize = size;
        invalidate();
        requestLayout();
    }

    /**
     * Return the dimension of the Board.
     * @return the dimension of the Board.
     */
    public float getSize() {
        return mSize;
    }

    /**
     * Initialize the Board.
     */
    public void init() {
        // Set up the paint for the board
        Resources res = getResources();
        mBoardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBoardPaint.setStyle(Paint.Style.FILL);
        mBoardPaint.setColor(res.getColor(R.color.boardColor));
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

        // Size of the tiles
        mTileSize = mSize / 4;
        newTile = new TileButton(mContext,10);
        newTile.setBounds(0.0f, 0.0f, mTileSize, mTileSize);

    }

    /**
     * Draws the board and the tiles.
     * @param canvas where the board is drawn.
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mBoardBounds, mBoardPaint);
        newTile.draw(canvas);
    }
}
