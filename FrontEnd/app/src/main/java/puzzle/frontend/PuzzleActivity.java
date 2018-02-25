package puzzle.frontend;

import android.content.res.Resources;
import android.graphics.Color;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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

    /**
     * Server URL
     */
    public static String URL = "http://10.0.2.2:8080/";

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
                    Log.i("Tiles " , tiles.toString());
                }
                else {
                    Log.i("NOT MOVING ", Integer.toString((Integer) tiles.getItem(position)));
                }
            }
        });
    }

    /**
     * Called when the user taps the Ready button.
     * Execute thread that make web server request.
     * @param view parent view.
     *
     */
    public void solvePuzzle(View view) {
        Button button = (Button) findViewById(R.id.ReadyButton);
        button.setClickable(false);
        board.setEnabled(false);
        RequestSolve getSolution = new RequestSolve();
        getSolution.execute();
    }

    public class RequestSolve extends AsyncTask<String, Void, String> {

        String response = "";

        @Override
        protected String doInBackground(String... params){
            RestService service;
            Call<String> callSolve;
            try
            {

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                httpClient.addInterceptor(new Interceptor() {
                  @Override
                  public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                      Request original = chain.request();

                      Request request = original.newBuilder()
                              .header("Content-Type", "application/json")
                              .header("Accept", "application/json")
                              .method(original.method(), original.body())
                              .build();

                      return chain.proceed(request);
                  }
                });

                OkHttpClient client = httpClient
                        .connectTimeout(100000, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(100000, TimeUnit.SECONDS)
                        .build();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .client(client)
                        .build();

                service = retrofit.create(RestService.class);

                callSolve = service.solveManhattan(tiles.toString());

                System.out.println(callSolve.request().toString());

                Response<String> reply = callSolve.execute();

                if (reply.code() != 200) {
                    System.out.println("HTTP Error code = " + reply.code());
                }
                else
                    System.out.println("Solution found: " + reply.body());
                    response = reply.body();


            }
            catch (Exception e)
            {
                System.out.println("Exception: "+ e.toString());
                e.printStackTrace();
                return null;
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result){
            if(result == null)
            {
                System.out.println("Error... contact developers!");
                return;
            }
            Button button = (Button) findViewById(R.id.ReadyButton);
            button.setClickable(true);
            board.setEnabled(true);
            if (result.length() == 0)
                System.out.println("No solution!!!");
            else
            {
                applySolution(new LinkedList<String>(Arrays.asList(result.split(" "))));
            }

        }
    }

    private void applySolution(final List<String> moves) {

        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                int space = tiles.getSpacePosition();
                int otherPosition = -1;
                String move = moves.get(0);
                switch (move) {
                    case "UP":
                        otherPosition = space - 4;
                        break;
                    case "DOWN":
                        otherPosition = space + 4;
                        break;
                    case "LEFT":
                        otherPosition = space - 1;
                        break;
                    case "RIGHT":
                        otherPosition = space + 1;
                        break;
                    default:
                        break;
                }

                // Move tile and update board.
                tiles.moveTile(otherPosition);
                Log.i("DIR" , move);
                space = tiles.getSpacePosition();
                Log.i("Tiles " , tiles.toString());
                board.setAdapter(tiles);


                moves.remove(0);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (moves.size() > 0) {
                            applySolution(moves);
                        }
                    }
                }, 100);
            }
        }, 600);
    }


}
