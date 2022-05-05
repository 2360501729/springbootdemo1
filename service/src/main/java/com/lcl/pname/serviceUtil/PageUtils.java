package com.lcl.pname.serviceUtil;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lcl.pname.appcontext.AppConstant;
import com.lcl.pname.entity.Teacher;

import java.util.Map;

/**
 * 页面工具
 * @author lcl
 */
public class PageUtils {

    /**
     * 获取含有当前页和页面大小的 IPage 对象
     * @param map 含有当前页和页面数据多少的的对象
     * */
    public static <T> IPage<T> getPageI(Map<String, Object> map) {
        Long currentPage = Long.valueOf(map.remove("currentPage").toString()) ;
        Long pageSize = Long.valueOf(map.remove("pageSize").toString()) ;
        if (map.size() == 0 || ObjectUtils.isNull(currentPage) || ObjectUtils.isNull(pageSize)) {
            currentPage = AppConstant.CURRENT_PAGE;
            pageSize = AppConstant.PAGE_SIZE;
        }
        return new Page<T>(currentPage, pageSize);
    }

    public static boolean getMapSize(Map<String, Object> map) {
        return map.size() < 1;
    }

}
