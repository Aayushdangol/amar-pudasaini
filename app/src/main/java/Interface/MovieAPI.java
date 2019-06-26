package Interface;

import java.util.List;
import java.util.Map;

import model.ImageResponse;
import model.Movie;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MovieAPI {

    @FormUrlEncoded
    @POST("api/movies/create")
    Call<Void>addMovies(@Header( "x-access-token" ) String token, @Field( "title" ) String title, @Field( "genre" ) String genre, @Field( "synopsis" ) String synopsis, @Field( "rating" ) String rating, @Field( "date" ) String date, @Field( "image" ) String image);

    @Multipart
    @POST("api/movies/upload")
    Call<ImageResponse> uploadImage(@Header( "x-access-token" ) String token,@Part MultipartBody.Part img);

    @GET("api/movies")
    Call<List<Movie>>getAllMovie(@Header( "x-access-token" ) String token);
}