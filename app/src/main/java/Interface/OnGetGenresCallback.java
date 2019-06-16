package Interface;

import java.util.List;

import model.Genre;

public interface OnGetGenresCallback {

    void onSuccess(List<Genre>genres);
    void onError();
}
