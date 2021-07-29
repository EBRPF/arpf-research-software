package org.rrcat.arpf.ui.api;

import com.google.gson.GsonBuilder;
import org.rrcat.arpf.ui.api.service.ApiInterface;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFetch {

  public static Retrofit fetch() {
    Retrofit.Builder service = new Retrofit.Builder()
            .baseUrl(ApiInterface.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));
            return service.build();



  }
}
