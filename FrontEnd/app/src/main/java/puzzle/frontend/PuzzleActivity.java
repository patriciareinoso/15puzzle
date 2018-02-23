package puzzle.frontend;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import static android.os.SystemClock.sleep;

/**
 * Main activity of the puzzle game.
 * Handles the board as a {@link GridView} and the tiles using a {@link TileAdapter}
 */
public class PuzzleActivity extends AppCompatActivity {

    /**
     * Represents the board of the game. 4 columns are fixed.
     */
    GridView board;

    /**
     * Adapter to represent the tiles and make the connection with the backend.
     */
    TileAdapter tiles = new TileAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        setContentView(R.layout.activity_puzzle);

        board = (GridView) findViewById(R.id.Board);
        board.setAdapter(tiles);

        board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if (tiles.canMove(position)){
                    int space = tiles.getSpacePosition();

                    Log.i("MOVING " , Integer.toString((Integer) tiles.getItem(position)));
                    tiles.moveTile(position);

                    board.setAdapter(tiles);
                    //updateView(space, position);
                    tiles.getView(position, board.getChildAt(position), board);
                    tiles.getView(space, board.getChildAt(space), board);
                    tiles.addMovement(space, position);
                    Log.i("Solution " , String.valueOf(tiles.getSolution()));
                    Log.i("Tiles " , tiles.toString());
                }
                else {
                    Log.i("NOT MOVING ", Integer.toString((Integer) tiles.getItem(position)));
                }
            }
        });
    }

    /**
     * Called when the user taps the Ready button
     * TEMPORARY IMPLEMENTATION. Only a simulation for the moment.
     * @param view parent view.
     */
    public void solvePuzzle(View view) {
        Button button = (Button) findViewById(R.id.ReadyButton);
        button.setClickable(false);
        int space = tiles.getSpacePosition();
        int otherPosition = -1;

        // Translate the direction into a position.
        for (TileAdapter.Direction dir : tiles.getSolution()){
            switch (dir) {
                case UP:
                    otherPosition = space - 4;
                    break;
                case DOWN:
                    otherPosition = space + 4;
                    break;
                case LEFT:
                    otherPosition = space - 1;
                    break;
                case RIGHT:
                    otherPosition = space + 1;
                    break;
                default:
                    break;
            }

            // Move tile and update board.
            tiles.moveTile(otherPosition);
            Log.i("DIR" , String.valueOf(dir));
            space = tiles.getSpacePosition();
            sleep(1000);
            Log.i("Tiles " , tiles.toString());
            board.setAdapter(tiles);
        }
        tiles.getSolution().clear();
        button.setClickable(true);
    }


}
