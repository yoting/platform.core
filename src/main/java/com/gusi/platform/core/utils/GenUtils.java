package com.gusi.platform.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.gusi.platform.core.dao.BaseDataMng;
import com.gusi.platform.core.dao.impl.BaseDataMngImpl;

public class GenUtils
{
    private BaseDataMng baseDataMng = new BaseDataMngImpl();

    public int getCurrentMaxNumber(String tableName, String field,
            String condition) throws Exception
    {

        int maxNumber = -1;
        String sql = "";
        if (tableName == null || "".equals(tableName) || field == null
                || "".equals(field))
        {
            return maxNumber;
        } else
        {
            sql = "select max(" + field + ") maxNumber from " + tableName
                    + " where 1=1";
            if (condition != null)
            {
                sql += " and " + condition;
            }
        }
        List<Object> objs = new ArrayList<Object>();
        // 这里不需要判断是否为空，执行sql以后必定有一条数据的，即使数据库中没有，那也是返回第0个元素是null，不会直接返回一个null
        Object obj = baseDataMng.getObjListBySql(sql).get(0);
        System.out.println(obj);
        // if (maxNumberObj != null) {
        // maxNumber = Integer.parseInt(maxNumberObj.toString());
        // }
        return maxNumber;
    }
}
