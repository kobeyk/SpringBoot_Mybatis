package com.appleyk.common;


import com.appleyk.dict.FilterTypeEnum;
import com.appleyk.dict.OrderByEnum;
import com.appleyk.filter.AFilter;
import com.appleyk.filter.UserFilter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 查询过滤器构建where条件语句
 *
 * @param <T>
 * @author appleyk
 */
public class FilterHelper<T> {

    private T data;
    private FilterTypeEnum filterType;
    private Integer whereCount;
    private String whereSql;
    // where条件列表
    private List<String> whereSqlList;
    // 排序Sql
    private String orderBySql;

    public FilterHelper(T data) {
        this.data = data;
        this.whereCount = 0;
        this.whereSql = "";
        this.whereSqlList = new ArrayList<>();
    }

    public FilterTypeEnum getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterTypeEnum filterType) {
        this.filterType = filterType;
    }

    /**
     * 查询filter过滤器构建where条件语句
     *
     * @return
     */
    public String buildWhereSql() {

        if (data instanceof UserFilter) {
            UserFilter filter = (UserFilter) data;
            this.filterType = FilterTypeEnum.USER;
            return buildWhereSql(filter);
        }
        return "";
    }

    /**
     * 构建用户过滤器的条件语句
     *
     * @param filter
     * @return
     */
    private String buildWhereSql(UserFilter filter) {

        // 构建基础过滤器
        buildWhereSql(filter, whereSqlList);

        // 根据用户id列表检索
        HashSet<Integer> uids = filter.getUids();
        whereSql = buildSqlByIds(uids);
        addWhereSql(whereSql);

        // 根据用户名称列表检索
        HashSet<String> names = filter.getNames();
        whereSql = buildSqlByNames(names);
        addWhereSql(whereSql);

        return sqlListToString(whereSqlList);
    }

    /**
     * 构建基础过滤器的条件语句
     *
     * @param filter
     * @param whereSqlList
     */
    private void buildWhereSql(AFilter filter, List<String> whereSqlList) {

        // 指定排序规则
        OrderByEnum orderType = filter.getOrderType();
        boolean descOrAsc = filter.isDescOrAsc();
        orderBySql = buildSqlByOrderBy(orderType, descOrAsc);
    }

    /**
     * 添加条件语句（WhereSQLStatement）
     *
     * @param whereSql
     */
    private void addWhereSql(String whereSql) {

        if ("" != whereSql) {
            if (whereCount > 0) {
                whereSqlList.add(" and " + whereSql);
            } else {
                whereSqlList.add(whereSql);
            }
            whereCount++;
        }
    }

    /**
     * 添加条件语句（WhereSQLStatement）
     *
     * @param whereSql
     */
    private void addWhereOrSql(String whereSql) {

        if ("" != whereSql) {
            if (whereCount > 0) {
                whereSqlList.add(" OR " + whereSql);
            } else {
                whereSqlList.add(whereSql);
            }
            whereCount++;
        }
    }


    /**
     * 根据主键（唯一）id列表构建条件语句
     *
     * @param ids
     * @return
     */
    private String buildSqlByIds(HashSet<Integer> ids) {
        String whereSql = "";
        if (ids != null && ids.size() > 0) {
            whereSql = "id in (" + iSetTostirng(ids) + ")";
        }
        return whereSql;
    }

    /**
     * 根据名称列表构建条件语句
     *
     * @param names
     * @return
     */
    private String buildSqlByNames(HashSet<String> names) {
        String whereSql = "";
        if (names != null && names.size() > 0) {
            if (filterType.equals(FilterTypeEnum.USER)) {
                whereSql = "name in (" + sSetTostirng(names) + ")";
            }
        }
        return whereSql;
    }


    /**
     * @param orderByEnum
     * @param order
     * @return
     */
    private String buildSqlByOrderBy(OrderByEnum orderByEnum, boolean order) {
        String orderSql = "";
        int value = orderByEnum.getValue();
        if (value != 0) {
            String property = "";
            if (value == 1) {
                property = "id ";
            } else {
                if (filterType.equals(FilterTypeEnum.USER)) {
                    property = "name ";
                }
            }

            //true 降序 false 升序
            if (order) {
                orderSql = " ORDER BY " + property + " DESC";
            } else {
                orderSql = " ORDER BY " + property + " ASC";
            }
        }
        return orderSql;
    }


    /**
     * integer类型的set转string
     *
     * @param list
     * @return
     */
    public String iSetTostirng(HashSet<Integer> list) {
        String result = "";
        for (Integer n : list) {
            result += n + ",";
        }
        return result.substring(0, result.lastIndexOf(","));
    }

    /**
     * string类型的set转stirng
     *
     * @param list
     * @return
     */
    public String sSetTostirng(HashSet<String> list) {
        String result = "";
        for (String r : list) {
            result += "'" + r + "'" + ",";
        }
        return result.substring(0, result.lastIndexOf(","));
    }

    /**
     * 最终的sql结果在转换一下
     *
     * @param list
     * @return
     */
    public String sqlListToString(List<String> list) {
        String result = "";
        for (String r : list) {
            result += r;
        }
        return result;
    }

    public String getOrderBySql() {
        return orderBySql;
    }

    public void setOrderBySql(String orderBySql) {
        this.orderBySql = orderBySql;
    }

}
