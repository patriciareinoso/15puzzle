package puzzle.frontend;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by patty on 1/4/18.
 */

public class TileAdapter extends BaseAdapter {
    private Context mContext;

    private Integer[] tiles = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};

    public TileAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return tiles.length;
    }

    public Object getItem(int position) {
        return tiles[position];
    }

    public long getItemId(int position) {
        return position%4;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TileButton tile;
        if (convertView == null){
            // if it is not recycled, initialized some attributes
            tile = new TileButton(mContext, position);
        }
        else {
            tile = (TileButton) convertView;
        }
        tile.setValue(tiles[position]);
        return tile;
    }


}