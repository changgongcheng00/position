package com.trafigura.equity.position.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ResponseData
 * @Description TODO
 * @Author cheng
 **/
@Data
public class ResponseData<T> {

    /**
     * 返回码
     */
    private int status;

    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回值
     */
    private PageData<T> result;

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "success";

    public ResponseData() {
        super();
        this.status = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
        this.result = PageData.emptyList();
    }

    /**
     * Description
     * 成功响应 常用于增删改方法
     */
    public static <T> ResponseData<T> success(){
        return new ResponseData<>();
    }

    /**
     * Description
     * 成功响应 返回查询结果
     */
    public static <T> ResponseData<T> success(List<T> items){
        ResponseData<T> rData = new ResponseData<>();
        rData.setResult(PageData.getList(items));
        return rData;
    }
    public static <T> ResponseData<T> success(T one){
        ResponseData<T> rData = new ResponseData<>();
        if(one != null){
            rData.getResult().getData().add(one);
            rData.getResult().setTotal(1);
        }
        return rData;
    }

    /**
     * Description
     * 失败响应
     */
    public static <T> ResponseData<T> error(int code, String msg){
        ResponseData<T> rData = success();
        rData.setStatus(code);
        rData.setMessage(msg);
        return rData;
    }

    /**
     * Description
     * 重新封装返回值通用构造器，可以用于分页参数的获取和返回，同一个controller里多个实体的返回
     */
    public static <T extends Serializable> ResponseData<T> getPageData(int current, int rowCount){
        ResponseData<T> rData = new ResponseData<>();
        rData.setResult(PageData.getPageData(current,rowCount));
        return rData;
    }

    public static <T extends Serializable> ResponseData<T> setPageData(ResponseData<T> rdata,List<T> item){
        rdata.setResult(PageData.setPageData(rdata,item));
        return rdata;
    }

}
