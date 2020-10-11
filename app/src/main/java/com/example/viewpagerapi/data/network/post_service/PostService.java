package com.example.viewpagerapi.data.network.post_service;

import com.example.viewpagerapi.data.model.PostModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public class PostService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://android-3-mocker.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    PostApi service = retrofit.create(PostApi.class);



    public void getUsers(PostArrayCallback postArrayCallback) {
        Call<ArrayList<PostModel>> call = service.getUsers();
        call.enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                if (response.isSuccessful() && response != null)
                    postArrayCallback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                postArrayCallback.onFailure(t);
            }
        });
    }



    public void getPosts(PostArrayCallback arrayCallback) {

        Call<ArrayList<PostModel>> call = service.getPosts();
        call.enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                if (response.isSuccessful() && response != null)
                    arrayCallback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                arrayCallback.onFailure(t);
            }
        });

    }


    public void createPost(PostModel postModel, PostCallback postCallback) {
        Call<PostModel> call = service.createPost(postModel);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful() && response != null)
                    postCallback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                postCallback.onFailure(t);
            }
        });

    }


    public void deletePost(Integer id, PostCallback postCallback) {
        Call<PostModel> call = service.deletePost(id);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful() && response != null)
                    postCallback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                postCallback.onFailure(t);
            }
        });
    }


    public void updatePost(Integer id, PostModel postModel, PostCallback postCallback) {
        service.updatePost(id, postModel)
                .enqueue(new Callback<PostModel>() {
                    @Override
                    public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                        if (response.isSuccessful() && response != null)
                            postCallback.onSucces(response.body());
                    }

                    @Override
                    public void onFailure(Call<PostModel> call, Throwable t) {
                        postCallback.onFailure(t);
                    }
                });
    }


    public void getPost(Integer id, PostCallback postCallback) {
        Call<PostModel> call = service.getPost(id);
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                if (response.isSuccessful() && response != null)
                    postCallback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                postCallback.onFailure(t);
            }
        });
    }


    public void getUserPosts(Integer id , PostArrayCallback postArrayCallback) {
        Call<ArrayList<PostModel>> call = service.getUserPosts(id);
        call.enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                if (response.isSuccessful() && response != null)
                    postArrayCallback.onSucces(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                postArrayCallback.onFailure(t);
            }
        });
    }


    public interface PostArrayCallback {
        void onSucces(ArrayList<PostModel> postModel);

        void onFailure(Throwable e);
    }

    public interface PostCallback {
        void onSucces(PostModel postModel);

        void onFailure(Throwable e);
    }


    public interface PostApi {
        @GET("posts/")
        Call<ArrayList<PostModel>> getPosts();

        @GET("posts/{postId}")
        Call<PostModel> getPost(@Path("postId") Integer postId);

        @POST("posts/")
        Call<PostModel> createPost(@Body PostModel postModel);

        @DELETE("posts/{postId}")
        Call<PostModel> deletePost(@Path("postId") Integer postId);

        @GET("posts?users")
        Call<ArrayList<PostModel>> getUsers();

        @PUT("posts/{postId}")
        Call<PostModel> updatePost(@Path("postId") Integer postId, @Body PostModel postModel);

        @GET("posts?")
        Call<ArrayList<PostModel>> getUserPosts(@Query("user") Integer userId );


    }
}
