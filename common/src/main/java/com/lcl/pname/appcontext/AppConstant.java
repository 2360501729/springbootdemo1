package com.lcl.pname.appcontext;

/**
 * 应用常量配置接口
 */
public interface AppConstant {
    /**
     * 日期格式化的默认格式---------------------------------------
     */
    String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    String TIME_ZONE = "GMT+8";
    /*---------------------------------------------------------*/

    /*---------------------------分页常量--------------------------*/
    /**
     * 默认的当前页
     */
    Long CURRENT_PAGE = 1L;
    /**
     * 默认的页面条数
     */
    Long PAGE_SIZE = 2L;
    /**
     * 当前页的标识名字常量,建议和 PageVo 的当前页名字一样
     */
    String CURRENT_PAGE_NAME = "currentPage";
    /**
     * 每页多少条数据的名字,建议和 PageVo 的页面大小名字一样
     */
    String PAGE_SIZE_NAME = "pageSize";
    /*-----------------------------------------------------*/
}
