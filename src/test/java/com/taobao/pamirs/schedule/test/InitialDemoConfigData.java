package com.taobao.pamirs.schedule.test;

import com.taobao.pamirs.schedule.strategy.ScheduleStrategy;
import com.taobao.pamirs.schedule.strategy.TBScheduleManagerFactory;
import com.taobao.pamirs.schedule.taskmanager.ScheduleTaskType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.unitils.UnitilsJUnit4;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByName;


@SpringApplicationContext({ "schedule.xml" })
public class InitialDemoConfigData extends UnitilsJUnit4 {
	protected static transient Log log = LogFactory
			.getLog(InitialDemoConfigData.class);
	@SpringBeanByName
    TBScheduleManagerFactory scheduleManagerFactory;

	public void setScheduleManagerFactory(
			TBScheduleManagerFactory tbScheduleManagerFactory) {
		this.scheduleManagerFactory = tbScheduleManagerFactory;
	}

	@Test
	public void initialConfigData() throws Exception {
		String baseTaskTypeName = "DemoTask";
		while(this.scheduleManagerFactory.isZookeeperInitialSucess() == false){
			Thread.sleep(1000);
		}
		scheduleManagerFactory.stopServer(null);
		
		try {
			this.scheduleManagerFactory.getScheduleDataManager()
					.deleteTaskType(baseTaskTypeName);
		} catch (Exception e) {

		}
		// �����������DemoTask�Ļ�����Ϣ
		ScheduleTaskType baseTaskType = new ScheduleTaskType();
		baseTaskType.setBaseTaskType(baseTaskTypeName);
		baseTaskType.setDealBeanName("demoTaskBean");
		baseTaskType.setHeartBeatRate(2000);
		baseTaskType.setJudgeDeadInterval(10000);
		baseTaskType.setTaskParameter("AREA=����,YEAR>30");
		baseTaskType.setTaskItems(ScheduleTaskType.splitTaskItem(
				"0:{TYPE=A,KIND=1},1:{TYPE=A,KIND=2},2:{TYPE=A,KIND=3},3:{TYPE=A,KIND=4}," +
				"4:{TYPE=A,KIND=5},5:{TYPE=A,KIND=6},6:{TYPE=A,KIND=7},7:{TYPE=A,KIND=8}," +
				"8:{TYPE=A,KIND=9},9:{TYPE=A,KIND=10}"));
		this.scheduleManagerFactory.getScheduleDataManager()
				.createBaseTaskType(baseTaskType);
		log.info("������������ɹ�:" + baseTaskType.toString());

		// ��������DemoTask�ĵ��Ȳ���
		String taskName = baseTaskTypeName;
		try {
			this.scheduleManagerFactory.getScheduleStrategyManager()
					.deleteMachineStrategy(taskName,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ScheduleStrategy strategy = new ScheduleStrategy();
		//strategy.setTaskType(taskName);
		strategy.setNumOfSingleServer(1);
		strategy.setAssignNum(10);
		strategy.setIPList("127.0.0.1".split(","));
		this.scheduleManagerFactory.getScheduleStrategyManager()
				.createScheduleStrategy(strategy);
		log.info("�������Ȳ��Գɹ�:" + strategy.toString());

	}
}