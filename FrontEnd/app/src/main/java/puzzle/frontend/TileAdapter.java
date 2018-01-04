package puzzle.frontend;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by patty on 1/4/18.
 */

public class TileAdapter extends BaseAdapter {
    private Context mContext;

    private Integer[] tiles = new Integer[SIZE];

    /**
     * Positions that can be moved.
     */
    private ArrayList<Integer> canMove = new ArrayList<Integer>();

    private int spacePosition;


    public static final int SIZE = 16;
    public static final int COLUMNS = 4;


    public TileAdapter(Context c) {
        mContext = c;
        for (int i = 0; i < SIZE - 1; i++){
            tiles[i] = i + 1;
        }
        tiles[SIZE-1] = 0;
        spacePosition = SIZE -1;
        canMove.add(11);
        canMove.add(14);
    }

    public int getCount() {
        return tiles.length;
    }

    public Object getItem(int position) {
        return tiles[position];
    }

    public long getItemId(int position) {
        return position % COLUMNS;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button tile;
        if (convertView == null){
            // if it is not recycled, initialized some attributes
            tile = new Button(mContext);
        }
        else {
            tile = (Button) convertView;
        }
        if (tiles[position] != 0){
            tile.setText(Integer.toString(tiles[position]));
        }
        else {
            tile.setText("");
            tile.setBackgroundColor(Color.TRANSPARENT);
        }
        tile.setClickable(false);
        tile.setFocusableInTouchMode(false);
        tile.setFocusable(false);
        return tile;
    }

    public int getSpacePosition(){
        return spacePosition;
    }

    public boolean canMove(int position){
        return canMove.contains(position);
    }

    public void moveTile (int position){
        if (canMove(position)){
            // Switch tile values
            tiles[spacePosition] = tiles[position];
            tiles[position] = 0;

            // Update the space position
            spacePosition = position;

            // Update canMove list.
            canMove.clear();
            if (spacePosition > 3){
                canMove.add(spacePosition-4);
            }
            if ((spacePosition % 4) != 0){
                canMove.add(spacePosition-1);
            }
            if (spacePosition < 12){
                canMove.add(spacePosition+4);
            }
            if (((spacePosition +1) % 4) != 0){
                canMove.add(spacePosition+1);
            }
        }
    }

}