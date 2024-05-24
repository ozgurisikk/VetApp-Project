package com.vetApp.VetApp.core.utilities;

import com.vetApp.VetApp.core.result.Result;
import com.vetApp.VetApp.core.result.ResultData;

import static com.vetApp.VetApp.core.utilities.Msg.*;

public class ResultHelper {
    public static <T> ResultData<T> created(T data){
        return new ResultData<>(true, CREATED, "201", data);
    }
    public static <T> ResultData<T> success(T data){
        return new ResultData<>(true, OK, "200", data);
    }
    public static Result successVaccine(String msg){
        return new Result(true, msg, "200");
    }
    public static Result ok(){
        return new Result(true, OK, "200");
    }
    public static Result notFoundError(String msg){
        return new Result(false, msg, "404");
    }
    public static Result conflictError(String msg) {
        return new Result(false, msg, "409");
    }
    public static Result minuteError(String msg){
        return new Result(false, msg, "400");
    }
}
