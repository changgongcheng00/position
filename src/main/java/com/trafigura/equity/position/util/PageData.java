package com.trafigura.equity.position.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PageData
 * @Description TODO
 * @Author cheng
 **/
@Data
public class PageData<T> {

    /**
     * 分页
     */
    private int current;//当前页面号
    private int rowCount;//每页行数
    private long total;//总记录数
    private int pages;//总页数
    /**
     * 返回值
     */
    private List<T> data;


    public PageData() {
        super();
        this.data = new ArrayList<>();
    }

    public static <T> PageData<T> emptyList(){
        return new PageData<>();
    }
    public static <T> PageData<T> getList(List<T> items){
        PageData pd = new PageData();
        pd.setData(items);
        pd.setTotal(items.size());
        return pd;
    }

    /**
     * Description
     * 重新封装返回值通用构造器，可以用于分页参数的获取和返回，同一个controller里多个实体的返回
     */
    public static <T extends Serializable> PageData<T> getPageData(int current, int rowCount){
        PageData<T> pageData = new PageData<>();
        pageData.setRowCount(rowCount);
        pageData.setCurrent(current);
        pageData.setTotal(0);
        //分页插件
        PageHelper.startPage(current, rowCount);
        return pageData;
    }

    public static <T extends Serializable> PageData<T> setPageData(ResponseData<T> rdata,List<T> item){
        PageData<T> pageData = rdata.getResult();
        //分页插件 查询结果处理
        PageInfo<T> pageInfo = new PageInfo<>(item);
        pageData.setPages(pageInfo.getPages());
        pageData.setTotal(pageInfo.getTotal());
        pageData.setData(item);
        return pageData;
    }

}
