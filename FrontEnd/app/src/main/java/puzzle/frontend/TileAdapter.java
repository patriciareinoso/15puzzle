package puzzle.frontend;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Adapter that interacts with the graphical representation of the tiles, and the background
 * processing.
 */

public class TileAdapter extends BaseAdapter {
    /**
     * Context where the tiles are drawn.
     */
    private Context mContext;

    /**
     * Represents the list of tile values.
     */
    private Integer[] tiles = new Integer[SIZE];

    /**
     * Positions that can be moved at the current time.
     */
    private ArrayList<Integer> canMove = new ArrayList<Integer>();

    /**
     * Current position of the empty tile.
     */
    private int spacePosition;

    /**
     * Number of tiles on the board.
     */
    public static final int SIZE = 16;

    /**
     * Number of columns in the board.
     */
    public static final int COLUMNS = 4;

    /**
     * Used to simulate the solution movements.
     * TEMPORARY METHOD.
     */
    public enum Direction {
        UP, DOWN, LEFT, RIGHT;
    }

    /**
     * Solution to the puzzle. Contains the movement to do over the blank space
     * to solve the puzzle.
     * Just a simulation. TEMPORARY METHOD.
     */
    public ArrayList<Direction> solution = new ArrayList<>();

    /**
     * Constructor. Build the list of tiles and update the list of tiles that can be moved.
     * @param c context where the tiles are drawn.
     */
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

    /**
     * Mandatory method. Get the number of tiles.
     * @return 16, the number of tiles.
     */
    public int getCount() {
        return tiles.length;
    }

    /**
     * Get the Integer (value) of the tile given a position
     * @param position of the the tile value to find.
     * @return an object that is an integer.
     */
    public Object getItem(int position) {
        return tiles[position];
    }

    /**
     * Mandatory method. Get the number of the row of the position.
     * @param position to find the row number.
     * @return the row number.
     */
    public long getItemId(int position) {
        return position % COLUMNS;
    }

    /**
     * Return the value of the tile at a given position.
     * @param position to find the value at.
     * @return the value of the tile at the given position.
     */
    public int getValue(int position){
        return tiles[position];
    }

    /**
     * Get a View that displays the data at the specified position in the data set.
     * @param position the position of the item within the adapter's data set of the item whose
     *                 view we want.
     * @param convertView the old view to reuse.
     * @param parent the parent that this view will eventually be attached to.
     * @return the updated view.
     */
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
            tile.setBackgroundColor(Color.LTGRAY);
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

    /**
     * Return the current position of the blank space.
     * @return the current position of the blank space.
     */
    public int getSpacePosition(){
        return spacePosition;
    }

    /**
     * Check whether a tile in a given position can move.
     * @param position to check whether can be moved.
     * @return true if the tile can be moved, false otherwise.
     */
    public boolean canMove(int position){
        return canMove.contains(position);
    }

    /**
     * Switch the tile at the given position with the blank space.
     * @param position to be switch.
     */
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

    /**
     * TEMPORARY METHOD. Used to simulate a solution to the puzzle.
     * Everytime the user moves a tile, the movement is recorded as a direction.
     * Update the solution list. The directions are stored with respect to the blank space.
     * @param spacePosition the current position of the blank space.
     * @param otherPosition the new position of the blank space.
     */
    public void addMovement(int spacePosition, int otherPosition){
        int dif = spacePosition - otherPosition;
        Direction dir = null;
        switch (dif){
            case -4:
                dir = Direction.UP;
                break;
            case -1:
                dir = Direction.LEFT;
                break;
            case 1:
                dir = Direction.RIGHT;
                break;

            case 4:
                dir = Direction.DOWN;
                break;
            default:
                Log.i("ERROR MOVING " , Integer.toString((Integer)getItem(otherPosition)));
        }
        solution.add(0,dir);
    }

    /**
     * TEMPORARY METHOD. Returns the solution to the puzzle.
     * @return the list of directions the blank space should be moved.
     */
    public ArrayList<Direction> getSolution(){
        return solution;
    }

    /**
     * Return the current state of the board as an array of integers.
     * @return the state of the board.
     */
    public Integer[] getTiles(){
        return tiles;
    }

    /**
     * Build a string with the values of the tiles separated by space.
     * @return string representing the board.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (int i = 0; i < SIZE; i++) {
            sb.append(tiles[i]);
            sb.append("  ");
        }
        return sb.toString();
    }
}