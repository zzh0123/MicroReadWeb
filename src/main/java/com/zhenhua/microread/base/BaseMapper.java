package com.zhenhua.microread.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author zzh
 * @create 2019-08-18 18:32
 * @desc 自己的Mapper基类，主要不能放到mapper下
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
