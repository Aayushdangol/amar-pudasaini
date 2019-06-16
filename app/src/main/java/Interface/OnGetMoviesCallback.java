package Interface;

import java.util.List;

import model.Movie;

public interface OnGetMoviesCallback {

    void onSuccess(List<Movie>movies);
    void onError();
}
