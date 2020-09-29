package ru.onedr.earlzzz.testrentateamdagger.mvp;



import java.util.List;

import ru.onedr.earlzzz.testrentateamdagger.pojo.Post;

public interface MainActivityContract {
    interface View {
        void showData(List<Post> data);

        void showError(String message);

        void showComplete();

        void showProgress();

        void hideProgress();

    }

    interface Presenter {
        void loadData();
    }
}
