package org.rrcat.arpf.ui.api;

import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFetch {
  Retrofit  service= new Retrofit.Builder()
          .baseUrl("https://")
          .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
          .build();
}
