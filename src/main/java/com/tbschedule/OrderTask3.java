package com.tbschedule;

import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.TaskItemDefine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * tbschedule 2015/10/5 23:58
 * fuquanemail@gmail.com
 */
public class OrderTask3 implements IScheduleTaskDealMulti<Order> {
    Log logger =  LogFactory.getLog(OrderTask.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource(name="dataSource")
    public  void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean execute(Order[] tasks, String ownSign) throws Exception {
        logger.error("======== OrderTask3 execute========== start===========");
        logger.error("========= OrderTask3 execute ========= end ===========");
        return false;
    }

    @Override
    public List<Order> selectTasks(String taskParameter, String ownSign,
                                   int taskItemNum, List<TaskItemDefine> taskItemList,
                                   int eachFetchDataNum) throws Exception {
        logger.error("======OrderTask2====selectTasks========start===========");

        logger.error("----taskParameter-->" + taskParameter);
        logger.error("----ownSign-->" + ownSign);
        logger.error("----taskItemNum--->" + taskItemNum);
        logger.error("----taskItemList size:-->" + taskItemList.size());
        logger.error("----eachFetchDataNum-->" + eachFetchDataNum);
        List<Order> list1 = new ArrayList<Order>();
        list1.add(new Order());
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        logger.error("======OrderTask2====selectTasks=======end==============");
        return list1;
    }

    @Override
    public Comparator<Order> getComparator() {
        return null;
    }
}
