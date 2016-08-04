CREATE TABLE SCHEDULE_TEST (
  ID bigint(15) default NULL,
  DEAL_COUNT int(11) default NULL,
  STS varchar(2) default NULL,
  OWN_SIGN varchar(50) not NULL,
  PRIMARY KEY (ID)
);


-- 动态的造几十万数据
CREATE  PROCEDURE CREATE_TEST_DATA(IN ownSign varchar(50),IN datanum INTEGER(11))
BEGIN
 declare i int DEFAULT 1;
  WHILE i <= datanum DO
    insert into SCHEDULE_TEST VALUES(i,0,'N',ownSign);
   set i = i + 1;
  END WHILE;
END;

CALL CREATE_TEST_DATA('BASE',300000);

CREATE  PROCEDURE CP_TABLE(IN tableName varchar(100),IN tableNum INTEGER(11))
  BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE v_sql VARCHAR(1000);
    WHILE i <= tableNum DO
      set v_sql= concat("CREATE TABLE ",tableName,"_",i," LIKE ",tableName);
      set @v_sql=v_sql;
      prepare stmt from @v_sql;
      EXECUTE stmt;
      deallocate prepare stmt;
      set i = i + 1;
    END WHILE;
  END;

-- 说明：创建32张表，从 gateway_trade_order_1,gateway_trade_order_2 .... gateway_trade_order_32,原来的表保留，新表无数据.
CALL CP_TABLE('gateway_trade_order',32);

