package puzzle.frontend;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import static java.sql.DriverManager.println;

public class PuzzleActivity extends AppCompatActivity {

    GridView board;
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
                    Log.i("MOVING " , Integer.toString((Integer) tiles.getItem(position)));
                    tiles.moveTile(position);
                    board.setAdapter(tiles);
                }
                else {
                    Log.i("NOT MOVING ", Integer.toString((Integer) tiles.getItem(position)));
                }
            }
        });
    }


}
