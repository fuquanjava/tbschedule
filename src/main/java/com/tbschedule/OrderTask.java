package com.tbschedule;


import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
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
 * tbshedule-demo 2015/10/3 14:45
 * fuquanemail@gmail.com
 */
public class OrderTask  implements IScheduleTaskDealSingle<Order> {
    Log logger =  LogFactory.getLog(OrderTask.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource(name="dataSource")
    public  void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    /**
     * 执行单个任务
     * @param order Object
     * @param ownSign 当前环境名称
     * @throws Exception
     */

    @Override
    public boolean execute(Order order, String ownSign) throws Exception {
        logger.error("#########  execute ##### start ####");
        logger.error("#########  execute ##### end ####");
        return false;
    }
    /**
     * 根据条件，查询当前调度服务器可处理的任务
     * @param taskParameter 任务的自定义参数,任务中配置的自定义参数
     * @param ownSign 当前环境名称 BASE
     * @param taskQueueNum 当前任务类型的任务队列数量,也就是任务中的任务项
     * @param taskItemList 当前调度服务器，分配到的可处理队列，如果只有一台机器，那么就会是全部.
     * @param eachFetchDataNum 每次获取数据的数量,任务中配置的数量
     * @return
     * @throws Exception
     */
    @Override
    public List<Order> selectTasks(String taskParameter,String ownSign,
                                   int taskQueueNum,
                                   List<TaskItemDefine> taskItemList,
                                   int eachFetchDataNum) throws Exception {
        logger.error("#########selectTasks#########start#########");

        logger.error("----taskParameter-->" + taskParameter);
        logger.error("----ownSign-->" + ownSign);
        logger.error("----taskQueueNum--->" + taskQueueNum);
        logger.error("----taskItemList size:-->" + taskItemList.size());
        logger.error("----eachFetchDataNum-->" + eachFetchDataNum);
        List<Order> list1 = new ArrayList<Order>();
        list1.add(new Order());
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        logger.error("#########selectTasks#########end#########");
        return list1;
    }


    /**
     * 获取任务的比较器,只有在NotSleep模式下需要用到
     * @return
     */
    @Override
    public Comparator<Order> getComparator() {
        Comparator<Order> comparator = new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                System.out.println("sort.....");
                return 0;
            }
        };
        return comparator;
    }
}
