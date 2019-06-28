package Interface;

import java.util.List;

import model.ImageResponse;
import model.LoginSignupResponse;
import model.Movie;
import model.PopularMovie;
import model.PopularTV;
import model.TV;
import model.TopRatedMovie;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface MovieAPI {

    //movies
    @FormUrlEncoded
    @POST("api/movies/create")
    Call<Void>addMovies(@Header( "x-access-token" ) String token, @Field( "title" ) String title, @Field( "genre" ) String genre, @Field( "synopsis" ) String synopsis, @Field( "rating" ) String rating, @Field( "date" ) String date, @Field( "image" ) String image);

    @Multipart
    @POST("api/movies/upload")
    Call<ImageResponse> uploadImage(@Header( "x-access-token" ) String token,@Part MultipartBody.Part img);

    @GET("api/movies")
    Call<List<Movie>>getAllMovie(@Header( "x-access-token" ) String token);

    //tv
    @FormUrlEncoded
    @POST("api/tv/create")
    Call<Void>addTV(@Header( "x-access-token" ) String token, @Field( "title" ) String title, @Field( "genre" ) String genre, @Field( "synopsis" ) String synopsis, @Field( "rating" ) String rating, @Field( "date" ) String date, @Field( "image" ) String image);

    @Multipart
    @POST("api/tv/upload")
    Call<ImageResponse> uploadTVImage(@Header( "x-access-token" ) String token,@Part MultipartBody.Part img);

    @GET("api/tv")
    Call<List<TV>>getAllTV(@Header( "x-access-token" ) String token);

    //popularmovies
    @FormUrlEncoded
    @POST("api/popularmovies/create")
    Call<Void>addPopularMovies(@Header( "x-access-token" ) String token, @Field( "title" ) String title, @Field( "genre" ) String genre, @Field( "synopsis" ) String synopsis, @Field( "rating" ) String rating, @Field( "date" ) String date, @Field( "image" ) String image);

    @Multipart
    @POST("api/popularmovies/upload")
    Call<ImageResponse> uploadPopularMoviesImage(@Header( "x-access-token" ) String token,@Part MultipartBody.Part img);

    @GET("api/popularmovies")
    Call<List<PopularMovie>> getPopularMovie(@Header( "x-access-token" ) String token);

    //topratedmovies
    @FormUrlEncoded
    @POST("api/topratedmovies/create")
    Call<Void>addTopRatedMovies(@Header( "x-access-token" ) String token, @Field( "title" ) String title, @Field( "genre" ) String genre, @Field( "synopsis" ) String synopsis, @Field( "rating" ) String rating, @Field( "date" ) String date, @Field( "image" ) String image);

    @Multipart
    @POST("api/topratedmovies/upload")
    Call<ImageResponse> uploadTopRatedMoviesImage(@Header( "x-access-token" ) String token,@Part MultipartBody.Part img);

    @GET("api/topratedmovies")
    Call<List<TopRatedMovie>> getTopRatedMovie(@Header( "x-access-token" ) String token);

    //populartv
    @FormUrlEncoded
    @POST("api/populartv/create")
    Call<Void>addPopularTV(@Header( "x-access-token" ) String token, @Field( "title" ) String title, @Field( "genre" ) String genre, @Field( "synopsis" ) String synopsis, @Field( "rating" ) String rating, @Field( "date" ) String date, @Field( "image" ) String image);

    @Multipart
    @POST("api/populartv/upload")
    Call<ImageResponse> uploadPopularTVImage(@Header( "x-access-token" ) String token,@Part MultipartBody.Part img);

    @GET("api/populartv")
    Call<List<PopularTV>> getPopularTV(@Header( "x-access-token" ) String token);

    //topratedtv
    @FormUrlEncoded
    @POST("api/topratedtv/create")
    Call<Void>addTopratedTV(@Header( "x-access-token" ) String token, @Field( "title" ) String title, @Field( "genre" ) String genre, @Field( "synopsis" ) String synopsis, @Field( "rating" ) String rating, @Field( "date" ) String date, @Field( "image" ) String image);

    @Multipart
    @POST("api/topratedtv/upload")
    Call<ImageResponse> uploadTopratedTVImage(@Header( "x-access-token" ) String token,@Part MultipartBody.Part img);

    @GET("api/populartv")
    Call<List<Movie>> getTopratedTV(@Header( "x-access-token" ) String token);

    //login&register
    @FormUrlEncoded
    @POST("api/account/login")
    Call<LoginSignupResponse> checkUser(@Field("username") String username , @Field("password") String password);

    @FormUrlEncoded
    @POST("api/account/signup")
    Call<Void> registerUser(@Field("full_name") String full_name , @Field("email") String email,@Field("password") String password , @Field("address") String address, @Field("contact") String contact);


}