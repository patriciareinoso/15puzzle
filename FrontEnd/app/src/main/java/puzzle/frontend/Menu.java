package puzzle.frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    /** Called when the user taps the Play button */
    public void playGame(View view) {
        Intent intent = new Intent(this, PuzzleActivity.class);
        startActivity(intent);
    }

    /** Called when the user taps the Help button */
    public void displayInstructions(View view) {
        Intent intent = new Intent(this, DisplayInstructionsActivity.class);
        startActivity(intent);
    }
}
