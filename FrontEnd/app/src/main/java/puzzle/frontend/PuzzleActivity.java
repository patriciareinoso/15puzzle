package puzzle.frontend;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PuzzleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources res = getResources();
        setContentView(R.layout.activity_puzzle);
        BoardView board = (BoardView) this.findViewById(R.id.Board);
        //TileView tile = (TileView) this.findViewById(R.id.Tile);
    }
}
