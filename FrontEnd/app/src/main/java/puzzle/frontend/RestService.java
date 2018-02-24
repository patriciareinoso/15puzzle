package puzzle.frontend;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Interface used to request rest services
 * @author Oscar Guillen
 * Created by oscar on 16/02/18.
 */

public interface RestService {
    /**
     * Service that say hello to server
     * @return hello message
     */
    @GET("MyServer/puzzle")
    Call<String> hello();

    /**
     * Service that solve the puzzle using the linear heuristic
     * @param board start puzzle to be solved
     * @return moves to get the solution
     */
    @POST("MyServer/puzzle/solve/linear")
    @Headers({ "Content-Type: application/json","Accept: application/json"})
    Call<String> solveLinear(@Body String board);

    /**
     * Service that solve the puzzle using the disorder heuristic
     * @param board start puzzle to be solved
     * @return moves to get the solution
     */
    @POST("MyServer/puzzle/solve/disorder")
    @Headers({ "Content-Type: application/json","Accept: application/json"})
    Call<String> solveDisorder(@Body String board);

    /**
     * Service that solve the puzzle using the manhattan heuristic
     * @param board start puzzle to be solved
     * @return moves to get the solution
     */
    @POST("MyServer/puzzle/solve/manhattan")
    @Headers({ "Content-Type: application/json","Accept: application/json"})
    Call<String> solveManhattan(@Body String board);

}
