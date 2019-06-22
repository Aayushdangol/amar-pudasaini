package Interface;

import model.GenreResponse;
import model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {
    @GET("movie/popular")
    Call<MoviesResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("genre/movies/list")
    Call<GenreResponse> getGenres(
            @Query( "api_key" ) String apiKey,
            @Query( "language" ) String language
    );
}