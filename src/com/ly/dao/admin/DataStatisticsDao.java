package com.ly.dao.admin;

import com.ly.bean.DataStatistics;
import com.ly.dao.BaseDao;

import java.sql.SQLException;

public class DataStatisticsDao extends BaseDao {


    /**
     * 查询某年某月的销售金额
     * @param year 查询年份
     * @param month 查询月份
     * @return dataStatistics 实体类
     * @throws SQLException
     */
    public DataStatistics query(String year, String month) throws SQLException {
        DataStatistics dataStatistics = new DataStatistics();

        this.sql = "select month(create_time),sum(price) from orders where year(create_time)=? and month(create_time)=? group by month(create_time)";
        this.rs = this.jdbCutil.query(this.sql, year, month);
        while (this.rs.next()){
            dataStatistics.setPrice(this.rs.getDouble(2));
        }
        dataStatistics.setYear(year);
        dataStatistics.setMonth(month);

        return dataStatistics;
    }


    /**
     * 查询某年某季度的销售金额
     * @param year 查询年份
     * @param quarterStart 查询开始季度
     * @param quarterEnd 结束
     * @return dataStatistics 实体类
     * @throws SQLException
     */
    public DataStatistics query(String year, String quarterStart, String quarterEnd) throws SQLException {
        DataStatistics dataStatistics = new DataStatistics();

        this.sql = "select month(create_time),sum(price) from orders where year(create_time)=? and month(create_time) between ? and ? group by month(create_time)";
        this.rs = this.jdbCutil.query(this.sql, year, quarterStart, quarterEnd);
        while (this.rs.next()){
            dataStatistics.setPrice(this.rs.getDouble(2));
        }
        dataStatistics.setYear(year);

        return dataStatistics;
    }


    /**
     * 查询某年销售金额
     * @param year 查询年份
     * @return dataStatistics 实体类
     * @throws SQLException
     */
    public DataStatistics query(String year) throws SQLException {
        DataStatistics dataStatistics = new DataStatistics();

        this.sql = "select sum(price) from orders where year(create_time)=? group by year(create_time)";
        this.rs = this.jdbCutil.query(this.sql, year);
        while (this.rs.next()){
            dataStatistics.setPrice(this.rs.getDouble(1));
        }
        dataStatistics.setYear(year);

        return dataStatistics;
    }



}
