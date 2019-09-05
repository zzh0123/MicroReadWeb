package com.zhenhua.microread.util;

import com.zhenhua.microread.entity.Result;

/**
 * @author zzh
 * @create 2019-08-18 22:26
 * @desc 请求结果工具类
 */
public class ResultUtil {

    public static Result getSearchResult(Object object){
        Result result = new Result();
        if (object != null){
            result.setCode(800);
            result.setMessage("查询成功！");
            result.setData(object);
        } else {
//            result.setCode(800);
            result.setMessage("查询失败！");
            result.setData(object);
        }
        return result;
    }

    public static Result getInsertResult(Object object){
        Result result = new Result();
        if (object != null){
            result.setCode(800);
            result.setMessage("插入成功！");
            result.setData(object);
        } else {
//            result.setCode(800);
            result.setMessage("插入失败！");
            result.setData(object);
        }
        return result;
    }
}
