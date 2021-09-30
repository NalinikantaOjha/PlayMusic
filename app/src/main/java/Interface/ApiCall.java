package Interface;

import java.util.List;

import Model.ResponseDTO;
import Model.ResultsDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {
    @GET("search")
    Call<ResponseDTO> getPosts(@Query("term") String term);
}
