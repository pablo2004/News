package com.jolijun.news.Modules.Principal.Services;

import com.jolijun.news.Modules.Principal.Services.Models.Noticias;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pablo on 18/01/17.
 */

public interface PrincipalServices {

    @GET("/noticias/apiNoticias/")
    Call<Noticias> noticias(@Query("particion_id") String particion_id, @Query("api") String api);

}
