package com.ly.load.admin;

import com.ly.bean.DataStatistics;
import com.ly.dao.admin.DataStatisticsDao;
import com.ly.load.BaseLoad;
import com.ly.util.Config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DataStatisticsLoad extends BaseLoad {
    public DataStatisticsLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    /**
     * 根据器请求参数查询某年某月数据
     * @return
     * @throws SQLException
     */
    public DataStatistics queryMData() throws SQLException {
        DataStatistics dataStatistics;
        String year;
        String month;

        //如果请求参数没有年月则设置当前年月
        year = request.getParameter("year");
        month = request.getParameter("month");

        if(year == null || "".equals(year)){
            year = Config.getYear();
        }

        if(month == null || "".equals(month)){
            month = Config.getMonth();
        }

        //查询数据
        DataStatisticsDao dataStatisticsDao = new DataStatisticsDao();

        dataStatistics = dataStatisticsDao.query(year, month);

        dataStatisticsDao.close();

        return dataStatistics;
    }



    /**
     * 根据器请求参数查询某年季度数据
     * @return
     * @throws SQLException
     */
    public DataStatistics queryQData() throws SQLException {
        DataStatistics dataStatistics;
        String year;
        String quarterStart;
        String quarterEnd;

        //如果请求参数没有年月则设置当前年月
        year = request.getParameter("year");
        quarterEnd = Config.getMonth();
        quarterStart = String.valueOf(Integer.parseInt(quarterEnd) - 3);
        if(year == null || "".equals(year)){
            year = Config.getYear();
        }



        //查询数据
        DataStatisticsDao dataStatisticsDao = new DataStatisticsDao();

        dataStatistics = dataStatisticsDao.query(year, quarterStart, quarterEnd);

        dataStatisticsDao.close();

        return dataStatistics;
    }


    /**
     * 根据器请求参数查询某年数据
     * @return
     * @throws SQLException
     */
    public DataStatistics queryYData() throws SQLException {
        DataStatistics yDataStatistics;
        String year;


        //如果请求参数没有年月则设置当前年月
        year = request.getParameter("year");

        if(year == null || "".equals(year)){
            year = Config.getYear();
        }



        //查询数据
        DataStatisticsDao dataStatisticsDao = new DataStatisticsDao();

        yDataStatistics = dataStatisticsDao.query(year);

        dataStatisticsDao.close();

        return yDataStatistics;
    }

}
