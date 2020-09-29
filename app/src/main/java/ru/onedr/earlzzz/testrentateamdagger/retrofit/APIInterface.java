package ru.onedr.earlzzz.testrentateamdagger.retrofit;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("/api/users")
    public Observable<JsonObject> getAllPosts();
}
