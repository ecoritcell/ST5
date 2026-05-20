CREATE DATABASE  IF NOT EXISTS `st5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `st5`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: st5
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `budget_detail`
--

DROP TABLE IF EXISTS `budget_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget_detail` (
  `detailid` bigint NOT NULL AUTO_INCREMENT,
  `bheaderid` bigint NOT NULL,
  `headcode` varchar(45) DEFAULT NULL,
  `measure` varchar(45) DEFAULT NULL,
  `month` varchar(2) DEFAULT NULL,
  `budgetval` decimal(10,2) DEFAULT NULL,
  `createdon` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedon` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`detailid`),
  KEY `budget_header_foreignkey_idx` (`bheaderid`),
  CONSTRAINT `budget_header_foreignkey` FOREIGN KEY (`bheaderid`) REFERENCES `budget_header` (`bheaderid`)
) ENGINE=InnoDB AUTO_INCREMENT=688 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `budget_header`
--

DROP TABLE IF EXISTS `budget_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `budget_header` (
  `bheaderid` bigint NOT NULL AUTO_INCREMENT,
  `financialyear` varchar(7) NOT NULL,
  `budgetno` smallint NOT NULL,
  `divid` smallint NOT NULL,
  `formonth` varchar(2) NOT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `createdon` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedon` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`bheaderid`),
  UNIQUE KEY `uniqe_annual_budget_hdr` (`financialyear`,`budgetno`,`divid`,`formonth`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `div_master`
--

DROP TABLE IF EXISTS `div_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `div_master` (
  `divid` int NOT NULL AUTO_INCREMENT,
  `divcode` varchar(45) DEFAULT NULL,
  `divname` varchar(45) DEFAULT NULL,
  `isactive` int DEFAULT '1',
  `creationdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`divid`),
  UNIQUE KEY `divid_UNIQUE` (`divid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `general_code`
--

DROP TABLE IF EXISTS `general_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `general_code` (
  `recordid` int NOT NULL AUTO_INCREMENT,
  `codetype` varchar(45) DEFAULT NULL,
  `code` varchar(45) NOT NULL,
  `shortdesc` varchar(100) DEFAULT NULL,
  `longdesc` varchar(100) DEFAULT NULL,
  `parentcode` varchar(45) DEFAULT NULL,
  `isactive` int DEFAULT '1',
  `codeorder` int DEFAULT NULL,
  `creationdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`recordid`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `signatory_detail`
--

DROP TABLE IF EXISTS `signatory_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `signatory_detail` (
  `recordid` int NOT NULL AUTO_INCREMENT,
  `railwayname` varchar(100) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `signatoryname` varchar(45) DEFAULT NULL,
  `signatorydesg` varchar(45) DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedon` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`recordid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stn_earning_detail`
--

DROP TABLE IF EXISTS `stn_earning_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stn_earning_detail` (
  `recordid` bigint NOT NULL AUTO_INCREMENT,
  `headerid` bigint NOT NULL,
  `classcode` varchar(45) NOT NULL,
  `bookedcount` int DEFAULT '0',
  `bookedamt` decimal(12,2) DEFAULT '0.00',
  `refundcount` int DEFAULT '0',
  `refundamt` decimal(12,2) DEFAULT '0.00',
  `excesscount` int DEFAULT '0',
  `excessamt` decimal(12,2) DEFAULT '0.00',
  `pmwagon` decimal(12,2) DEFAULT '0.00',
  `pmtonne` decimal(12,2) DEFAULT '0.00',
  `tpwagon` decimal(12,2) DEFAULT '0.00',
  `wagon` decimal(12,2) DEFAULT '0.00',
  `tonne` decimal(12,2) DEFAULT '0.00',
  `rate` decimal(12,2) DEFAULT '0.00',
  `amount` decimal(12,2) DEFAULT '0.00',
  `weight` decimal(12,2) DEFAULT '0.00',
  `earning` decimal(12,2) DEFAULT '0.00',
  `userid` int DEFAULT '-1',
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`recordid`),
  UNIQUE KEY `unq_stn_earning_detail` (`headerid`,`classcode`),
  KEY `stn_earning_header_id_idx` (`headerid`),
  CONSTRAINT `stn_earning_header_id` FOREIGN KEY (`headerid`) REFERENCES `stn_earning_header` (`recordid`)
) ENGINE=InnoDB AUTO_INCREMENT=4629 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stn_earning_header`
--

DROP TABLE IF EXISTS `stn_earning_header`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stn_earning_header` (
  `recordid` bigint NOT NULL AUTO_INCREMENT,
  `financialyear` varchar(7) NOT NULL,
  `formonth` varchar(2) NOT NULL,
  `period` varchar(45) NOT NULL,
  `entrytype` varchar(45) NOT NULL,
  `systemtype` varchar(45) NOT NULL,
  `headofaccounts` varchar(45) NOT NULL,
  `zoneid` int DEFAULT '1',
  `divisionid` int DEFAULT NULL,
  `stationtype` varchar(2) DEFAULT NULL,
  `stationcode` varchar(10) DEFAULT NULL,
  `valuetype` varchar(45) NOT NULL,
  `receivedfy` varchar(7) DEFAULT NULL,
  `receivedmonth` varchar(2) DEFAULT NULL,
  `receivedperiod` varchar(45) DEFAULT NULL,
  `remarks` varchar(2) DEFAULT NULL,
  `parentrecordid` bigint DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `createdon` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`recordid`),
  UNIQUE KEY `unique_stn_earning_hdr` (`financialyear`,`formonth`,`period`,`entrytype`,`systemtype`,`headofaccounts`,`divisionid`,`stationcode`,`valuetype`)
) ENGINE=InnoDB AUTO_INCREMENT=339 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `stn_master`
--

DROP TABLE IF EXISTS `stn_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stn_master` (
  `stnid` int NOT NULL AUTO_INCREMENT,
  `stncode` varchar(45) NOT NULL,
  `stnname` varchar(45) NOT NULL,
  `percentage` varchar(45) NOT NULL,
  `divid` int NOT NULL,
  `divcode` varchar(45) NOT NULL,
  `isactive` int DEFAULT '1',
  `creationdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stnid`),
  UNIQUE KEY `stnid_UNIQUE` (`stnid`),
  KEY `division_id_foreign_key_idx` (`divid`),
  CONSTRAINT `division_id_foreign_key` FOREIGN KEY (`divid`) REFERENCES `div_master` (`divid`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `active` int DEFAULT '1',
  `level` int DEFAULT '1',
  `creationdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `lastupdated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zone_master`
--

DROP TABLE IF EXISTS `zone_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zone_master` (
  `zoneid` int NOT NULL AUTO_INCREMENT,
  `zonecode` varchar(45) NOT NULL,
  `zonename` varchar(45) NOT NULL,
  `isactive` int NOT NULL DEFAULT '1',
  `lastupdated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creationdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`zoneid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'st5'
--

--
-- Dumping routines for database 'st5'
--
/*!50003 DROP FUNCTION IF EXISTS `fn_budget_val` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_budget_val`(p_budgetval DECIMAL(18,4),
    p_month_int      INT,
    p_to_month_int   INT,
    p_days_in_month  INT,
    p_i_period       VARCHAR(10),
    p_is_cumm        BOOLEAN) RETURNS decimal(18,4)
    DETERMINISTIC
BEGIN
 -- Months fully before to_month: always full value
    IF p_month_int < p_to_month_int THEN
        RETURN p_budgetval;
    END IF;

    -- For to_month: apply period split
    IF p_month_int = p_to_month_int THEN
        IF p_is_cumm THEN
            RETURN CASE p_i_period
                WHEN 'I'   THEN (p_budgetval / p_days_in_month) * 10
                WHEN 'II'  THEN (p_budgetval / p_days_in_month) * 20
                WHEN 'III' THEN p_budgetval
                ELSE p_budgetval
            END;
        ELSE
            RETURN CASE p_i_period
                WHEN 'I'   THEN (p_budgetval / p_days_in_month) * 10
                WHEN 'II'  THEN (p_budgetval / p_days_in_month) * 10
                WHEN 'III' THEN (p_budgetval / p_days_in_month) * (p_days_in_month - 20)
                ELSE p_budgetval
            END;
        END IF;
    END IF;

    RETURN p_budgetval; -- fallback
RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_days_in_month` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_days_in_month`(fy VARCHAR(7), i_month VARCHAR(7),i_month_int int) RETURNS int
    DETERMINISTIC
BEGIN

DECLARE target_year     INT;

SET target_year = CASE
                    WHEN i_month_int > 12
                    THEN CAST(SUBSTRING_INDEX(fy, '-', 1) AS UNSIGNED) + 1
                    ELSE SUBSTRING_INDEX(fy, '-', 1)
                  END;

    -- Build date as 1st of the month, then extract last day
    -- LPAD ensures month is zero-padded: 4 → '04', 13 → '13' ← wait, see note below
    RETURN DAY(LAST_DAY(
        STR_TO_DATE(CONCAT(target_year, '-',i_month, '-01'),'%Y-%m-%d')
    ));
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_getBudgetNo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_getBudgetNo`(i_fy varchar(7), i_divid int, i_formonth varchar(2)) RETURNS int
    DETERMINISTIC
BEGIN

	DECLARE o_budgetno INT DEFAULT 1;
    
    -- When Div id is -1 that means it is for zone
    IF(i_divid = -1)THEN
    
		if exists ( select 1 from budget_header where financialyear = i_fy  and formonth = i_formonth ) THEN
			SELECT budgetno into o_budgetno from budget_header where financialyear = i_fy and formonth = i_formonth ORDER BY budgetno LIMIT 1;
		elseif EXISTS (select 1 from budget_header where financialyear = i_fy and  fn_month_int(i_formonth)>= fn_month_int(formonth)) THEN
			SELECT budgetno into o_budgetno from budget_header where financialyear = i_fy and  fn_month_int(i_formonth)>= fn_month_int(formonth) ORDER BY budgetno desc LIMIT 1;
		else 
			SET o_budgetno = 1;
		end if;
	ELSE
		if exists ( select 1 from budget_header where financialyear = i_fy and divid = i_divid and formonth = i_formonth ) THEN
			SELECT budgetno into o_budgetno from budget_header where financialyear = i_fy and divid = i_divid and formonth = i_formonth ORDER BY budgetno LIMIT 1;
		elseif EXISTS (select 1 from budget_header where financialyear = i_fy and divid = i_divid and  fn_month_int(i_formonth)>= fn_month_int(formonth)) THEN
			SELECT budgetno into o_budgetno from budget_header where financialyear = i_fy and divid = i_divid and  fn_month_int(i_formonth)>= fn_month_int(formonth) ORDER BY budgetno desc LIMIT 1;
		else 
			SET o_budgetno = 1;
		end if;
    END IF;

RETURN o_budgetno;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_getYearFromFY` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_getYearFromFY`(fy varchar(7)) RETURNS int
    DETERMINISTIC
BEGIN
	return CAST(LEFT(fy, 4) AS UNSIGNED);
RETURN 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_header_priority` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_header_priority`(valuetype varchar(45), fy varchar(7), to_month varchar(2), period varchar(45), received_fy varchar(7),received_month varchar(2),received_period varchar(45)) RETURNS int
    DETERMINISTIC
BEGIN

RETURN CASE  
     WHEN valuetype = 'O' 
		  AND received_fy = fy
		  AND (
				fn_month_int(received_month) < fn_month_int(to_month)
				 OR (
						fn_month_int(received_month) = fn_month_int(to_month)
						AND fn_period_int(received_period) <= fn_period_int(period)
					)
				) THEN 1
	 WHEN valuetype = 'A' 	THEN 2  -- 'A' → second priority
	 ELSE 3        -- Unreceived 'O' → last
END;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_is_o_received` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_is_o_received`(valuetype varchar(45), fy varchar(7), to_month varchar(2), period varchar(45), received_fy varchar(7),received_month varchar(2),received_period varchar(45)) RETURNS int
    DETERMINISTIC
BEGIN
	
   return CASE 
		WHEN valuetype = 'O' 
		 AND received_fy = fy
		 AND (
				fn_month_int(received_month) < fn_month_int(to_month)
				 OR (
					 fn_month_int(received_month) = fn_month_int(to_month)
					 AND fn_period_int(received_period) <= fn_period_int(period)
					)
				)
				THEN 1 ELSE  0 
	END;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_month_int` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_month_int`(p_month VARCHAR(2)) RETURNS int
    DETERMINISTIC
BEGIN
RETURN CASE p_month
        WHEN '01' THEN 13
        WHEN '02' THEN 14
        WHEN '03' THEN 15
        ELSE CAST(p_month AS UNSIGNED)
    END;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_passenger_amt` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_passenger_amt`(
	p_bookedamt     DECIMAL(18,4),
    p_refundamt     DECIMAL(18,4),
    p_excessamt     DECIMAL(18,4),
    p_formonth_int  INT,
    p_to_month_int  INT,
    p_period        VARCHAR(10),   -- h.period (I / II / III / MON)
    p_i_period      VARCHAR(10),   -- selected period from input
    p_days_in_month INT,
    p_is_cumm       BOOLEAN
) RETURNS decimal(18,4)
    DETERMINISTIC
BEGIN

-- Net amount reused multiple times
    DECLARE net_amt DECIMAL(18,4);
    SET net_amt = p_bookedamt - p_refundamt + p_excessamt;
    -- All other cases: return full net amount
    RETURN net_amt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_passenger_amt_bkp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_passenger_amt_bkp`(
	p_bookedamt     DECIMAL(18,4),
    p_refundamt     DECIMAL(18,4),
    p_excessamt     DECIMAL(18,4),
    p_formonth_int  INT,
    p_to_month_int  INT,
    p_period        VARCHAR(10),   -- h.period (I / II / III / MON)
    p_i_period      VARCHAR(10),   -- selected period from input
    p_days_in_month INT,
    p_is_cumm       BOOLEAN
) RETURNS decimal(18,4)
    DETERMINISTIC
BEGIN

-- Net amount reused multiple times
    DECLARE net_amt DECIMAL(18,4);
    SET net_amt = p_bookedamt - p_refundamt + p_excessamt;

    -- Only MON records for the final month need partial-period adjustment
    -- All other records (I/II/III periods, or earlier months) are taken fully
    IF p_formonth_int = p_to_month_int AND p_period = 'MON' THEN
        IF p_is_cumm THEN
            RETURN CASE p_i_period
                WHEN 'I'   THEN (net_amt / p_days_in_month) * 10
                WHEN 'II'  THEN (net_amt / p_days_in_month) * 20
                WHEN 'III' THEN  net_amt
                ELSE             net_amt
            END;
        ELSE
            -- Non-cumulative: single month partial period
            RETURN CASE p_i_period
                WHEN 'I'   THEN (net_amt / p_days_in_month) * 10
                WHEN 'II'  THEN (net_amt / p_days_in_month) * 10
                WHEN 'III' THEN (net_amt / p_days_in_month) * (p_days_in_month - 20)
                ELSE             net_amt
            END;
        END IF;
    END IF;

    -- All other cases: return full net amount
    RETURN net_amt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_passenger_number` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_passenger_number`(
	p_bookedcount     DECIMAL(18,4),
    p_refundcount     DECIMAL(18,4),
    p_excesscount     DECIMAL(18,4),
    p_formonth_int  INT,
    p_to_month_int  INT,
    p_period        VARCHAR(10),   -- h.period (I / II / III / MON)
    p_i_period      VARCHAR(10),   -- selected period from input
    p_days_in_month INT,
    p_is_cumm       BOOLEAN
) RETURNS decimal(18,4)
    DETERMINISTIC
BEGIN
-- Net amount reused multiple times
    DECLARE net_number DECIMAL(18,4);
    SET net_number = p_bookedcount - p_refundcount + p_excesscount;

    -- Only when period is monhly i.e = MON, records for the final month need partial-period adjustment
    -- All other records (I/II/III periods, or earlier months) are taken fully
    IF p_formonth_int = p_to_month_int AND p_period = 'MON' THEN
        IF p_is_cumm THEN
            RETURN CASE p_i_period
                WHEN 'I'   THEN (net_number / p_days_in_month) * 10
                WHEN 'II'  THEN (net_number / p_days_in_month) * 20
                WHEN 'III' THEN  net_number
                ELSE             net_number
            END;
        ELSE
            -- Non-cumulative: single month partial period
            RETURN CASE p_i_period
                WHEN 'I'   THEN (net_number / p_days_in_month) * 10
                WHEN 'II'  THEN (net_number / p_days_in_month) * 10
                WHEN 'III' THEN (net_number / p_days_in_month) * (p_days_in_month - 20)
                ELSE             net_amt
            END;
        END IF;
    END IF;

    -- All other cases: return full net amount
    RETURN net_number;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_period_int` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_period_int`(p_period VARCHAR(5)) RETURNS int
    DETERMINISTIC
BEGIN
	RETURN CASE p_period
        WHEN 'I'   THEN 1
        WHEN 'II'  THEN 2
        WHEN 'III' THEN 3
        ELSE NULL
    END;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_valuetype_int` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fn_valuetype_int`(p_valuetype VARCHAR(45)) RETURNS int
    DETERMINISTIC
BEGIN
RETURN CASE p_valuetype
        WHEN 'O' THEN 1
        ELSE 2
    END;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `deleteHeaderAndDetail` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `deleteHeaderAndDetail`(in i_headerid long, out o_status varchar(100))
BEGIN
	DECLARE iii_period_recordid LONG DEFAULT NULL;
	SET o_status = '';
    
	IF EXISTS (SELECT 1 FROM stn_earning_header WHERE recordid = i_headerid) THEN
    
		SELECT recordid INTO iii_period_recordid FROM stn_earning_header WHERE parentrecordid = i_headerid LIMIT 1;
		DELETE FROM stn_earning_detail WHERE headerid = i_headerid;
        DELETE FROM stn_earning_header WHERE recordid = i_headerid;        
        
       
        IF iii_period_recordid IS NOT NULL THEN
			-- Delete child records first (referential integrity)
			DELETE FROM stn_earning_detail  WHERE headerid = iii_period_recordid;
			-- Then delete parent record
			DELETE FROM stn_earning_header  WHERE recordid = iii_period_recordid;
		END IF;
        
        SET o_status = 'SUCCESS';
	ELSE 
		SET o_status = 'RECORD NOT FOUND';
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `earningHeaderCount` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `earningHeaderCount`(in fy varchar(45),
in in_formonth  varchar(2), 
in in_period varchar(45), 
in in_entrytype varchar(45), in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45), out o_eh_count int)
BEGIN
SET o_eh_count = 0;
if(in_entrytype = 'SW') THEN
	select COUNT(*) into o_eh_count from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode;
ELSEIF(in_entrytype = 'DW') THEN
	select COUNT(*) into o_eh_count from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid;
ELSEIF (in_entrytype = 'ZW') THEN
	select COUNT(*) into o_eh_count from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts;
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getBudgetData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getBudgetData`(in fy varchar(7),in budget_no varchar(2),in for_month varchar(2),in div_id int )
BEGIN
	SELECT * FROM annual_budget where financialyear = fy and divisionid =div_id and budgetno = budget_no;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getBudgetHeader` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getBudgetHeader`(in i_fy varchar(45), in i_budgetno int, in i_month varchar(45), in i_div int)
BEGIN
	select * from budget_header where financialyear = i_fy and budgetno = i_budgetno and formonth =i_month and divid = i_div;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getEarningHeader` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getEarningHeader`(
in fy varchar(45),
in in_formonth  varchar(2), 
in in_period varchar(45), 
in in_entrytype varchar(45), in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45), in in_valuetype varchar(45))
BEGIN
if(in_entrytype = 'SW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode and valuetype = in_valuetype;
ELSEIF(in_entrytype = 'DW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and valuetype = in_valuetype;
ELSEIF (in_entrytype = 'ZW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and valuetype = in_valuetype;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getEarningHeaderId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getEarningHeaderId`(
in fy varchar(45),
in in_formonth  varchar(2), 
in in_period varchar(45), 
in in_entrytype varchar(45), in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45), out o_recordid bigint)
BEGIN
if(in_entrytype = 'SW') THEN
	select recordid into o_recordid from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode order by recordid limit 1;
ELSEIF(in_entrytype = 'DW') THEN
	select recordid into o_recordid from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid  order by recordid limit 1;
ELSEIF (in_entrytype = 'ZW') THEN
	select recordid into o_recordid from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts order by recordid limit 1 ;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getEarningHeaderWithoutValType` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getEarningHeaderWithoutValType`(
in fy varchar(45),
in in_formonth  varchar(2), 
in in_period varchar(45), 
in in_entrytype varchar(45), in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45))
BEGIN
if(in_entrytype = 'SW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode order by CASE valuetype WHEN 'O' THEN 1 ELSE 2 END limit 1;
ELSEIF(in_entrytype = 'DW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid order by CASE valuetype WHEN 'O' THEN 1 ELSE 2 END limit 1;
ELSEIF (in_entrytype = 'ZW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts order by CASE valuetype WHEN 'O' THEN 1 ELSE 2 END limit 1;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getEarningHeader_not_used` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getEarningHeader_not_used`(
in fy varchar(45),
in in_formonth  varchar(2), 
in in_period varchar(45), 
in in_entrytype varchar(45), in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45), in in_valuetype varchar(45))
BEGIN
if(in_entrytype = 'SW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode and 
                    valuetype IN ('A','O') ORDER BY CASE valuetype WHEN 'O' THEN 1 WHEN 'A' THEN 2 END ASC LIMIT 1;
ELSEIF(in_entrytype = 'DW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and valuetype IN ('A','O') ORDER BY CASE valuetype WHEN 'O' THEN 1 WHEN 'A' THEN 2 END ASC LIMIT 1;
ELSEIF (in_entrytype = 'ZW') THEN
	select * from stn_earning_header where financialyear = fy and formonth = in_formonth and period = in_period and 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    valuetype IN ('A','O') ORDER BY CASE valuetype WHEN 'O' THEN 1 WHEN 'A' THEN 2 END ASC LIMIT 1;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getEarningMonths` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getEarningMonths`(in in_fy varchar(7), in in_entrytype varchar(45), 
in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45))
BEGIN

IF(in_entrytype = 'SW') THEN
	select DISTINCT formonth  from stn_earning_header where financialyear = in_fy and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode ORDER BY formonth ASC ;
ELSEIF(in_entrytype = 'DW') THEN
	select DISTINCT formonth from stn_earning_header where  financialyear = in_fy and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid ORDER BY formonth ASC;
ELSEIF (in_entrytype = 'ZW') THEN
	select DISTINCT formonth  from stn_earning_header where financialyear = in_fy and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts ORDER BY formonth ASC;
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getEarningPeriod` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getEarningPeriod`(in in_fy varchar(7), in in_formonth varchar(2), in in_entrytype varchar(45), 
in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45))
BEGIN

IF(in_entrytype = 'SW') THEN
	select DISTINCT period  from stn_earning_header where financialyear = in_fy and formonth=in_formonth and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode ORDER BY period ASC ;
ELSEIF(in_entrytype = 'DW') THEN
	select DISTINCT period from stn_earning_header where  financialyear = in_fy and formonth=in_formonth and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid ORDER BY period ASC;
ELSEIF (in_entrytype = 'ZW') THEN
	select DISTINCT period  from stn_earning_header where financialyear = in_fy and formonth=in_formonth and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts ORDER BY period ASC;
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getEarningValueType` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getEarningValueType`(in in_fy varchar(7), in in_formonth varchar(2),in in_period varchar(45), in in_entrytype varchar(45), 
in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45))
BEGIN

IF(in_entrytype = 'SW') THEN
	select DISTINCT valuetype  from stn_earning_header where financialyear = in_fy and formonth=in_formonth and period=in_period and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode ORDER BY valuetype ASC ;
ELSEIF(in_entrytype = 'DW') THEN
	select DISTINCT valuetype from stn_earning_header where  financialyear = in_fy and formonth=in_formonth and period=in_period and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid ORDER BY valuetype ASC;
ELSEIF (in_entrytype = 'ZW') THEN
	select DISTINCT valuetype  from stn_earning_header where financialyear = in_fy and formonth=in_formonth and period=in_period and
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts ORDER BY valuetype ASC;
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getForMonthForBudgetnumber` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getForMonthForBudgetnumber`(in fy varchar(7),in i_division int, in budget_no varchar(2),out for_month varchar(2))
BEGIN

SELECT IFNULL(
  (SELECT distinct formonth FROM budget_header
   WHERE financialyear = fy AND divid = i_division AND budgetno = budget_no LIMIT 1),
  '-1'
) into for_month;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getLast3EarningFy` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getLast3EarningFy`(in in_entrytype varchar(45), 
in  in_systemtype varchar(45),in in_headofaccounts varchar(45), in in_divisionid int, in in_stationcode varchar(45))
BEGIN

IF(in_entrytype = 'SW') THEN
	select DISTINCT financialyear  from stn_earning_header where 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid and stationcode = in_stationcode ORDER BY financialyear DESC LIMIT 3;
ELSEIF(in_entrytype = 'DW') THEN
	select DISTINCT financialyear from stn_earning_header where  
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts and 
                    divisionid = in_divisionid ORDER BY financialyear DESC LIMIT 3;
ELSEIF (in_entrytype = 'ZW') THEN
	select DISTINCT financialyear  from stn_earning_header where 
					entrytype = in_entrytype and systemtype = in_systemtype and headofaccounts = in_headofaccounts ORDER BY financialyear DESC LIMIT 3;
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getMaxBudgetNumber` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getMaxBudgetNumber`(in fy varchar(7),in i_division int, out budget_no int)
BEGIN
	SELECT IFNULL(MAX(budgetno), 0)
    INTO budget_no
    FROM budget_header
    WHERE financialyear = fy and divid = i_division;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getStationEarning` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getStationEarning`(in fy varchar(7),in for_month varchar(2), in mon_period varchar(3),
														in entry_type varchar(2),in sys varchar(1), in headof_acct varchar(45),
														in div_code varchar(3),in stn_type varchar(2), in stn_code varchar(45), in val_type varchar(45))
BEGIN
IF(entry_type = 'SW') THEN
	SELECT * FROM stn_earning WHERE financialyear = fy and formonth=for_month and period = mon_period and entrytype =entry_type and systm =sys and headofaccount = headof_acct and 
					divcode = div_code and  stntype = stn_type and stncode = stn_code and valuetype = val_type;
elseif(entry_type = 'DW') THEN
	SELECT * FROM stn_earning WHERE financialyear = fy and formonth=for_month and period = mon_period and entrytype =entry_type and systm =sys and headofaccount = headof_acct  and 
					divcode = div_code and valuetype = val_type;
elseif(entry_type = 'ZW') THEN
	SELECT * FROM stn_earning WHERE financialyear = fy and formonth=for_month and period = mon_period and entrytype =entry_type and systm =sys and headofaccount = headof_acct and valuetype = val_type ;

END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getStationsByDivByPercentage` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getStationsByDivByPercentage`( in i_divid int, in i_stntype varchar(45))
BEGIN

SELECT stncode FROM stn_master where divid = i_divid and percentage = i_stntype AND isactive = 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getStationsWithValueType` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getStationsWithValueType`(in i_fy varchar(7), in i_formonth varchar(45),in i_period varchar(45),in i_entrytype varchar(45),
 in i_system varchar(45), in i_hoa varchar(45), in i_divid int, in i_stntype varchar(45))
BEGIN
DECLARE i_zoneid INT DEFAuLT 1;
IF(i_entrytype = "ZW") THEN
	SELECT a.zoneid as id, a.zonecode as code, b.valuetype,b.receivedfy,b.receivedmonth,b.receivedperiod,b.parentrecordid,b.remarks from
	(SELECT zoneid, zonecode FROM zone_master where isactive = 1) a
	LEFT JOIN 
	(
		SELECT 
					zoneid,
					valuetype,
					receivedfy,
					receivedmonth,
					receivedperiod,
                    parentrecordid,
					remarks
				FROM (
					SELECT 
						zoneid,
						valuetype,
						receivedfy,
						receivedmonth,
						receivedperiod,
                        parentrecordid,
						remarks,
						ROW_NUMBER() OVER (
							PARTITION BY zoneid                          -- per station
							ORDER BY CASE valuetype WHEN 'O' THEN 1 ELSE 2 END  -- O first
						) AS rn
					FROM stn_earning_header
					WHERE financialyear  = i_fy
					  AND formonth       = i_formonth
					  AND period         = i_period
					  AND entrytype      = i_entrytype
					  AND systemtype     = i_system
					  AND headofaccounts = i_hoa
					  AND zoneid = i_zoneid
				) ranked
				WHERE rn = 1   -- ✅ Pick best row per station (O over A)
	) b
	ON a.zoneid = b.zoneid;
ELSEIF (i_entrytype = "DW") THEN
	SELECT a.divid as id, a.divcode as code,b.valuetype,b.receivedfy,b.receivedmonth,b.receivedperiod,b.parentrecordid,b.remarks from
	(SELECT divid,divcode FROM div_master where  isactive = 1) a
	LEFT JOIN 
	(
		SELECT 
					divisionid,
					valuetype,
					receivedfy,
					receivedmonth,
					receivedperiod,
                    parentrecordid,
					remarks
				FROM (
					SELECT 
						divisionid,
						valuetype,
						receivedfy,
						receivedmonth,
						receivedperiod,
                        parentrecordid,
						remarks,
						ROW_NUMBER() OVER (
							PARTITION BY divisionid                          -- per station
							ORDER BY CASE valuetype WHEN 'O' THEN 1 ELSE 2 END  -- O first
						) AS rn
					FROM stn_earning_header
					WHERE financialyear  = i_fy
					  AND formonth       = i_formonth
					  AND period         = i_period
					  AND entrytype      = i_entrytype
					  AND systemtype     = i_system
					  AND headofaccounts = i_hoa
					  AND zoneid     = i_zoneid
				) ranked
				WHERE rn = 1   -- ✅ Pick best row per station (O over A)
	) b
	ON a.divid = b.divisionid;
ELSEIF (i_entrytype = "SW") THEN

	SELECT a.stnid as id, a.stncode as code,b.valuetype,b.receivedfy,b.receivedmonth,b.receivedperiod,b.parentrecordid,b.remarks from
	(SELECT stnid,stncode FROM stn_master where divid = i_divid and percentage = i_stntype AND isactive = 1) a
	LEFT JOIN 
	(
		SELECT 
					stationcode,
					valuetype,
					receivedfy,
					receivedmonth,
					receivedperiod,
                    parentrecordid,
					remarks
				FROM (
					SELECT 
						stationcode,
						valuetype,
						receivedfy,
						receivedmonth,
						receivedperiod,
                        parentrecordid,
						remarks,
						ROW_NUMBER() OVER (
							PARTITION BY stationcode                          -- per station
							ORDER BY CASE valuetype WHEN 'O' THEN 1 ELSE 2 END  -- O first
						) AS rn
					FROM stn_earning_header
					WHERE financialyear  = i_fy
					  AND formonth       = i_formonth
					  AND period         = i_period
					  AND entrytype      = i_entrytype
					  AND systemtype     = i_system
					  AND headofaccounts = i_hoa
					  AND divisionid     = i_divid
				) ranked
				WHERE rn = 1   -- ✅ Pick best row per station (O over A)
	) b
	ON a.stncode = b.stationcode;
END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getUserByUsernameAndPassword` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `getUserByUsernameAndPassword`(in uname varchar(45), in pass varchar(45))
BEGIN
	SELECT * from users WHERE username = uname and password = pass;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserOrUpdateBudgetData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `inserOrUpdateBudgetData`(in div_id int, in fy varchar(7), in budget_no varchar(2),in for_month varchar(2), 
in head_val varchar(45), in sub_head varchar(45), in month_val varchar(2), in budget_val decimal(10,2),OUT action_status VARCHAR(10))
BEGIN

	 DECLARE record_id INT DEFAULT NULL;

    -- Get record ID if exists
    SELECT recordid
    INTO record_id
    FROM annual_budget
    WHERE divisionid = div_id
      AND financialyear = fy
      AND budgetno = budget_no
      AND head = head_val
      AND subhead = sub_head
      AND `month` = month_val
    LIMIT 1;

    IF record_id IS NOT NULL THEN
        -- Update existing record
        UPDATE annual_budget SET value = budget_val WHERE recordid = record_id;
        SET action_status = "UPDATED";
    ELSE
        -- Insert new record
        INSERT INTO annual_budget
            (divisionid, financialyear, budgetno, formonth, head, subhead, `month`, value)
        VALUES
            (div_id, fy, budget_no, for_month, head_val, sub_head, month_val, budget_val);
            
		SET action_status = "INSERTED";
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserOrUpdateStnEarning` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `inserOrUpdateStnEarning`(in fy varchar(7),in for_month varchar(2), in mon_period varchar(3),
											in entry_type varchar(2),in sys varchar(1), in headof_acct varchar(45),
											in div_code varchar(3),in stn_type varchar(2), in stn_code varchar(45), in val_type varchar(45),
                                            in rcv_on varchar(45), in rem varchar(45),in earn_cat varchar(45), in earn_subcat varchar(45),
                                            in quant double, in amt double, out action_status varchar(45))
BEGIN
	DECLARE record_id INT DEFAULT NULL;

    -- Get record ID if exists
    IF(entry_type = 'SW') THEN
		SELECT recordid INTO record_id FROM stn_earning WHERE financialyear = fy and formonth=for_month and period = mon_period and entrytype =entry_type and systm =sys and headofaccount = headof_acct and 
					divcode = div_code and  stntype = stn_type and stncode = stn_code and valuetype = val_type and earningcat = earn_cat and earningsubcat = earn_subcat  LIMIT 1;
	elseif(entry_type = 'DW') THEN
		SELECT recordid INTO record_id FROM stn_earning WHERE financialyear = fy and formonth=for_month and period = mon_period and entrytype =entry_type and systm =sys and headofaccount = headof_acct  and 
					divcode = div_code and valuetype = val_type LIMIT 1;
	elseif(entry_type = 'ZW') THEN
		SELECT recordid INTO record_id FROM stn_earning WHERE financialyear = fy and formonth=for_month and period = mon_period and entrytype =entry_type and 
			systm =sys and headofaccount = headof_acct and valuetype = val_type LIMIT 1;
	END IF;
    
    IF record_id IS NOT NULL THEN
        -- Update existing record
        if(amt != -1.0 && quant != -1.0) then
			UPDATE stn_earning SET earningcat = earn_cat,earningsubcat=earn_subcat,quantity=quant,amount=amt WHERE recordid = record_id;
            SET action_status = "UPDATED";
		elseif (amt != -1.0) then
			UPDATE stn_earning SET earningcat = earn_cat,earningsubcat=earn_subcat,amount=amt WHERE recordid = record_id;
            SET action_status = "UPDATED";
		elseif (quant != -1.0) then
			UPDATE stn_earning SET earningcat = earn_cat,earningsubcat=earn_subcat,quantity=quant WHERE recordid = record_id;
            SET action_status = "UPDATED";
		else		
			SET action_status = "NOT UPDATED";
		end if;
    ELSE
        -- Insert new record
        INSERT INTO stn_earning
            (financialyear, formonth, period, entrytype,systm, headofaccount, divcode,stntype,stncode,valuetype,receivedon, 
				remarks,earningcat,earningsubcat,quantity,amount)
        VALUES
            (fy, for_month, mon_period, entry_type, sys, headof_acct,div_code,stn_type,stn_code,val_type,NULLIF(rcv_on, ''),rem,earn_cat,earn_subcat,
             CASE 
				WHEN quant = -1.0 THEN DEFAULT(quantity)
				ELSE quant
			END,
			CASE 
				WHEN amt = -1.0 THEN DEFAULT(amount)
				ELSE amt
			END);
                        
		SET action_status = "INSERTED";
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_agr` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_agr`(
	IN prev_fy     VARCHAR(7),
    IN curr_fy     VARCHAR(7),
    IN from_month  VARCHAR(2),
    IN to_month    VARCHAR(2),
    IN i_period    VARCHAR(45),
    IN is_cumm     BOOLEAN)
BEGIN

-- =============================================
-- DERIVED VARIABLES
-- =============================================
DECLARE from_month_int  INT         DEFAULT 0;
DECLARE to_month_int    INT         DEFAULT 0;
DECLARE days_in_month   INT 		DEFAULT 0;

-- =============================================
-- COMPUTE MONTH INTEGERS & DAYS IN MONTH
-- =============================================
SET from_month_int = fn_month_int(from_month);
SET to_month_int   = fn_month_int(to_month);
SET days_in_month  = fn_days_in_month(curr_fy,to_month,to_month_int);

-- ============================
-- FROM HERE MAIN QUERY STARTS
-- ===============================




END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_DivWiseTonneLoaded` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_DivWiseTonneLoaded`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(45),in to_month varchar(45),
 in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);
DECLARE full_date DATE;
DECLARE days_in_month INT;

-- ===========================
-- TO CALCULATE NUMBER OF DAYS
-- ===========================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE WHEN to_month_int > 12 THEN from_year + 1 ELSE from_year END;    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');
SET days_in_month = DAY(LAST_DAY(full_date));        


-- ============================
-- FROM HERE MAIN QUERY STARTS
-- ===============================


SELECT bd.divcode as headofaccounts, coalesce(bd.bud_total,0) AS budget, coalesce(ed.pfytotal,0) AS 'pfytotal', coalesce(ed.cfytotal,0) AS 'cfytotal' from 
-- =======================
 --  Budget (Current FY)
 -- ======================= 
(SELECT dm.divcode, coalesce(SUM(fn_budget_val(
			bd.budgetval,
            fn_month_int(bd.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) ,0) AS bud_total FROM div_master dm   
	
    LEFT JOIN (SELECT bheaderid,divid FROM budget_header WHERE financialyear = curr_fy AND budgetno = fn_getBudgetNo(curr_fy,'-1',to_month)) bh
	ON dm.divid = bh.divid
    
    LEFT JOIN  budget_detail bd
    ON bd.bheaderid = bh.bheaderid
	AND bd.measure = 'UNIT'
	AND ( fn_month_int(bd.month) between from_month_int AND to_month_int ) 
    AND bd.headcode = 'ORGG'
    
WHERE  dm.isactive = 1 GROUP BY dm.divcode ) bd 

-- =============================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- =============================================

LEFT JOIN 

    (SELECT dm.divcode,
			SUM(CASE financialyear WHEN prev_fy THEN d.tonne ELSE 0 END) AS pfytotal,
			SUM(CASE financialyear WHEN curr_fy THEN d.tonne ELSE 0 END) AS cfytotal
	FROM div_master as dm
    
	LEFT JOIN

		(SELECT recordid,financialyear,divisionid,formonth,formonth_int,period,headofaccounts
			FROM (				
					SELECT recordid,financialyear,divisionid,formonth,fn_month_int(formonth) AS formonth_int, period,headofaccounts,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
					 fn_is_o_received(valuetype,curr_fy,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
						ROW_NUMBER() OVER (
							PARTITION BY 
								financialyear,
								formonth,
								period,
								entrytype,
								systemtype,
								headofaccounts,
								divisionid,
								stationcode
							ORDER BY fn_header_priority(valuetype,curr_fy,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
						) AS rn
					FROM stn_earning_header
					WHERE financialyear IN (prev_fy, curr_fy) AND headofaccounts = 'ORGG'
				) x
				WHERE rn = 1
                AND NOT (valuetype = 'O' AND is_o_received = 0)
                AND (CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III,
							-- to_month gets only I+II
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III,
							-- to_month gets only I                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) ) 
						ELSE formonth = to_month AND period = i_period
					 END )
                ) h 
		
        ON dm.divid = h.divisionid

	LEFT JOIN stn_earning_detail d 
		ON d.headerid = h.recordid
                 
	WHERE  dm.isactive = 1 group by dm.divcode) ed
        
	ON bd.divcode = ed.divcode;
         
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_divWise_orgg` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_divWise_orgg`(
	IN prev_fy     VARCHAR(7),
    IN curr_fy     VARCHAR(7),
    IN from_month  VARCHAR(2),
    IN to_month    VARCHAR(2),
    IN i_period    VARCHAR(45),
    IN is_cumm     BOOLEAN)
BEGIN


-- =============================================
-- DERIVED VARIABLES
-- =============================================
DECLARE from_month_int  INT         DEFAULT 0;
DECLARE to_month_int    INT         DEFAULT 0;
DECLARE days_in_month   INT 		DEFAULT 0;

-- =============================================
-- COMPUTE MONTH INTEGERS & DAYS IN MONTH
-- =============================================
SET from_month_int = fn_month_int(from_month);
SET to_month_int   = fn_month_int(to_month);
SET days_in_month  = fn_days_in_month(curr_fy,to_month,to_month_int);

-- =======================
 --  Main Query Start From Here
 -- ======================= 

SELECT bd.divcode, coalesce(bd.bud_total,0) AS 'budamt',coalesce(ed.prevfy_amt,0) AS 'pfyamt', coalesce(ed.currfy_amt,0) AS 'cfyamt' from 
 
-- =======================
 --  Budget (Current FY)
 -- ======================= 
(SELECT dm.divcode, coalesce(SUM(fn_budget_val(
			d.budgetval,
            fn_month_int(d.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) ,0) AS bud_total FROM div_master dm  

	left join (SELECT bheaderid,divid FROM budget_header WHERE financialyear = curr_fy AND budgetno = fn_getBudgetNo(curr_fy,'-1',to_month) ) h
		ON  dm.divid = h.divid

	left join budget_detail d
		ON h.bheaderid = d.bheaderid
    
    AND d.headcode = 'ORGG'
    AND d.measure = 'AMT'
    AND (fn_month_int(d.month) between from_month_int AND to_month_int )
WHERE  dm.isactive = 1 GROUP BY dm.divcode) bd

-- =============================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- =============================================

left join

(SELECT dm.divcode,
	sum(case when h.financialyear = prev_fy then fn_passenger_amt(d.amount, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)  else 0 end)  'prevfy_amt',
    sum(case when h.financialyear = curr_fy then fn_passenger_amt(d.amount, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) else 0 end)  'currfy_amt'  FROM div_master dm

left join ( SELECT recordid,divisionid,financialyear,period,formonth,formonth_int FROM 
	(SELECT recordid,divisionid,financialyear,period,formonth, fn_month_int(formonth) AS formonth_int,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
					 fn_is_o_received(valuetype,curr_fy,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
		
				ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY  fn_header_priority(valuetype,curr_fy,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
                           
                    ) AS rn FROM stn_earning_header 
                    where financialyear IN(prev_fy,curr_fy) 
                    AND headofaccounts = 'ORGG'

	) X WHERE x.rn = 1
		AND NOT (valuetype = 'O' AND is_o_received = 0)
		AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) )                                  
							ELSE (formonth = to_month AND (period in (i_period)))								  
			END)
    
) h
	ON dm.divid = h.divisionid

left join stn_earning_detail d 
	ON d.headerid = h.recordid

WHERE  dm.isactive = 1 group by dm.divcode) ed
 
ON bd.divcode = ed.divcode;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_NoOfTonneLoaded` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_NoOfTonneLoaded`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(45),in to_month varchar(45),
 in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);
DECLARE full_date DATE;
DECLARE days_in_month INT;

-- ===========================
-- TO CALCULATE NUMBER OF DAYS
-- ===========================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE WHEN to_month_int > 12 THEN from_year + 1 ELSE from_year END;    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');
SET days_in_month = DAY(LAST_DAY(full_date));        


-- ============================
-- FROM HERE MAIN QUERY STARTS
-- ===============================
SELECT 
	gc.code AS revenuehead,
    sum(COALESCE(b.total, 0))  AS budget,
	sum(COALESCE(c.pfytotal, 0)) AS pfytotal,
    sum(COALESCE(c.cfytotal, 0)) AS  cfytotal
FROM general_code AS gc 

-- =======================
 --  Budget (Current FY)
 -- ======================= 
 LEFT JOIN (
	 SELECT 
			bd.headcode,        
			SUM(fn_budget_val(
				bd.budgetval,
				fn_month_int(bd.month),
				to_month_int,
				days_in_month,
				i_period,
				is_cumm
			)) AS total
		FROM budget_detail bd
		JOIN (SELECT bheaderid FROM budget_header WHERE financialyear = curr_fy AND budgetno = fn_getBudgetNo(curr_fy,'-1',to_month) ) bh -- as we want for complete zone so are passing division as -1
		
		ON bd.bheaderid = bh.bheaderid
		WHERE
			bd.measure = 'UNIT'
		  AND ( fn_month_int(bd.month) between from_month_int AND to_month_int ) AND headcode = 'ORGG'
		GROUP BY bd.headcode ) b ON gc.code = b.headcode


-- =============================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- =============================================

LEFT JOIN 

	(SELECT 
            h.headofaccounts,
            SUM(CASE financialyear WHEN prev_fy THEN d.tonne ELSE 0 END) AS pfytotal,
			SUM(CASE financialyear WHEN curr_fy THEN d.tonne ELSE 0 END) AS cfytotal
        FROM stn_earning_detail d
    
		JOIN

		(SELECT recordid,financialyear,formonth,formonth_int,period,headofaccounts
			FROM (				
					SELECT recordid,financialyear,formonth,fn_month_int(formonth) AS formonth_int, period,headofaccounts,valuetype,
					-- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
                     fn_is_o_received(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
						ROW_NUMBER() OVER (
							PARTITION BY 
								financialyear,
								formonth,
								period,
								entrytype,
								systemtype,
								headofaccounts,
								divisionid,
								stationcode
							ORDER BY   fn_header_priority(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
						) AS rn
					FROM stn_earning_header
					WHERE financialyear IN (prev_fy, curr_fy) AND headofaccounts = 'ORGG'
				) x
				WHERE rn = 1
                AND NOT (valuetype = 'O' AND is_o_received = 0)
                AND (CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III,
							-- to_month gets only I+II
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III,
							-- to_month gets only I                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) ) 
						ELSE formonth = to_month AND period = i_period
					 END )
                ) h 
			
		 ON d.headerid = h.recordid
         
		GROUP BY h.headofaccounts ) c
        
        ON  gc.code = c.headofaccounts 
        
        WHERE  gc.codetype = 'Head Of Account' AND gc.code = 'ORGG' AND gc.isactive = 1;
         
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_NoOfWagonLoaded` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_NoOfWagonLoaded`(in prev_fy varchar(7), in curr_fy varchar(7),in i_month varchar(45), in i_period varchar(45))
BEGIN

	SELECT 
            h.headofaccounts,
            SUM(CASE financialyear WHEN prev_fy THEN d.wagon ELSE 0 END) AS pfytotal,
			SUM(CASE financialyear WHEN curr_fy THEN d.wagon ELSE 0 END) AS cfytotal
        FROM stn_earning_detail d
    
		JOIN

		(SELECT recordid,financialyear,formonth,period,headofaccounts
			FROM (				
					SELECT recordid,financialyear,formonth,period,headofaccounts,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
                    fn_is_o_received(valuetype,financialyear,i_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
						ROW_NUMBER() OVER (
							PARTITION BY 
								financialyear,
								formonth,
								period,
								entrytype,
								systemtype,
								headofaccounts,
								divisionid,
								stationcode
							ORDER BY fn_header_priority(valuetype,financialyear,i_month,i_period,receivedfy,receivedmonth,receivedperiod)
						) AS rn
					FROM stn_earning_header
					WHERE financialyear IN (prev_fy, curr_fy) AND formonth = i_month AND period = i_period AND headofaccounts = 'ORGG'
				) x
				WHERE rn = 1
					AND NOT (valuetype = 'O' AND is_o_received = 0)) h 
			
		 ON d.headerid = h.recordid
         
         GROUP BY h.headofaccounts;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_originatingPassengers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_originatingPassengers`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(2),
in to_month varchar(2),in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);
DECLARE full_date DATE;
DECLARE days_in_month INT;

-- ============================
-- TO CALCULATE NUMBER OF DAYS
-- ============================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE WHEN to_month_int > 12 THEN from_year + 1 ELSE from_year END;    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');
SET days_in_month = DAY(LAST_DAY(full_date));

SELECT 
	CASE 
        WHEN a.code = 'PNSUB' THEN 'NON-SUBURBAN'
        ELSE a.code
    END  AS revenuehead,
    sum(COALESCE(b.total, 0))  AS budget,
	sum(COALESCE(c.pfytotal, 0)) AS pfytotal,
    sum(COALESCE(c.cfytotal, 0)) AS  cfytotal
FROM general_code a

/* =======================
   Budget (Current FY)
   ======================= */
LEFT JOIN (
    SELECT 
        bd.headcode,
        SUM(fn_budget_val(
			bd.budgetval,
            fn_month_int(bd.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) AS total
    FROM budget_detail bd
    JOIN (
        SELECT 
            bheaderid
        FROM budget_header
        WHERE financialyear = curr_fy
          AND budgetno     = 1
          AND formonth     = '04'        
    ) bh
        ON bd.bheaderid = bh.bheaderid
    WHERE fn_month_int(bd.month) between from_month_int AND to_month_int 
		  AND bd.measure = 'UNIT'
          AND bd.headcode = 'PNSUB'
    GROUP BY bd.headcode
) b
    ON a.code = b.headcode

-- ================================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- ================================================
LEFT JOIN (
    SELECT 
        parentcode,
        SUM(CASE financialyear WHEN prev_fy THEN TOTAL ELSE 0 END) AS pfytotal,
        SUM(CASE financialyear WHEN curr_fy THEN TOTAL ELSE 0 END) AS cfytotal
    FROM (
        SELECT 
            gc.parentcode,
            d.classcode,
            h.financialyear,
            fn_passenger_number(d.bookedcount, d.refundcount, d.excesscount,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) AS TOTAL
           
        FROM stn_earning_detail d
        JOIN (
            SELECT 
                recordid,period,financialyear,formonth,formonth_int                
            FROM (
                SELECT 
                    recordid,period,financialyear,formonth, fn_month_int(formonth) AS formonth_int,
                    ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY 
                            CASE valuetype 
                                WHEN 'O' THEN 1 
                                ELSE 2 
                            END
                    ) AS rn
                FROM stn_earning_header
                WHERE financialyear IN (prev_fy, curr_fy)
            ) x
            WHERE rn = 1
				  AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III','MON')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II','MON')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','MON')) )                                  
							ELSE (formonth = to_month AND (period in (i_period,'MON')))								  
					END)
        ) h ON d.headerid = h.recordid 
            
        LEFT JOIN general_code gc ON gc.code = d.classcode 
		where gc.parentcode = 'PNSUB'
    ) z
    GROUP BY parentcode
) c
    ON a.code = c.parentcode

WHERE a.codetype = 'Head Of Account'
  AND a.code = 'PNSUB'
  AND a.isactive = 1
  
  GROUP BY a.code;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_originatingPassengers_New` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_originatingPassengers_New`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(2),
in to_month varchar(2),in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);
DECLARE full_date DATE;
DECLARE days_in_month INT;

-- ============================
-- TO CALCULATE NUMBER OF DAYS
-- ============================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE WHEN to_month_int > 12 THEN from_year + 1 ELSE from_year END;    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');
SET days_in_month = DAY(LAST_DAY(full_date));

SELECT 
	a.code as revenuehead,
    a.systemtype,
    sum(COALESCE(b.total, 0))  AS budget,
	sum(COALESCE(c.pfytotal, 0)) AS pfytotal,
    sum(COALESCE(c.cfytotal, 0)) AS  cfytotal
FROM (
    
SELECT  hoa.code, sys.code AS systemtype,hoa.codeorder,sys.codeorder as systypeorder
FROM general_code hoa
CROSS JOIN general_code sys
WHERE hoa.codetype = 'Head Of Account' AND hoa.isactive = 1
  AND sys.codetype = 'System'          AND sys.isactive = 1
  AND hoa.code  = 'PNSUB') a

/* =======================
   Budget (Current FY)
   ======================= */
LEFT JOIN (
    SELECT 
        bd.headcode,
        'M' AS systemtype,
        SUM(fn_budget_val(
			bd.budgetval,
            fn_month_int(bd.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) AS total
    FROM budget_detail bd
    JOIN (
        SELECT 
            bheaderid
        FROM budget_header
        WHERE financialyear = curr_fy
          AND budgetno     = fn_getBudgetNo(curr_fy,-1,to_month) -- as we want for complete zone so are passing division as -1
    ) bh
        ON bd.bheaderid = bh.bheaderid
    WHERE fn_month_int(bd.month) between from_month_int AND to_month_int 
		  AND bd.measure = 'UNIT'
          AND bd.headcode = 'PNSUB'
    GROUP BY bd.headcode
) b
    ON a.code = b.headcode AND a.systemtype = b.systemtype

-- ================================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- ================================================
LEFT JOIN (
    SELECT 
        parentcode,systemtype,
        SUM(CASE financialyear WHEN prev_fy THEN TOTAL ELSE 0 END) AS pfytotal,
        SUM(CASE financialyear WHEN curr_fy THEN TOTAL ELSE 0 END) AS cfytotal
    FROM (
        SELECT 
            gc.parentcode,
            h.systemtype,
            d.classcode,
            h.financialyear,
            fn_passenger_number(d.bookedcount, d.refundcount, d.excesscount,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) AS TOTAL
           
        FROM stn_earning_detail d
        JOIN (
            SELECT 
                recordid,period,systemtype,financialyear,formonth,formonth_int                
            FROM (
                SELECT 
                    recordid,period,systemtype,financialyear,formonth, fn_month_int(formonth) AS formonth_int,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
                    fn_is_o_received(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
                    ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY fn_header_priority(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
                            
                    ) AS rn
                FROM stn_earning_header
                WHERE financialyear IN (prev_fy, curr_fy)
            ) x
            WHERE rn = 1
				  AND NOT (valuetype = 'O' AND is_o_received = 0)
				  AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) )                                  
							ELSE (formonth = to_month AND (period in (i_period)))								  
					END)
        ) h ON d.headerid = h.recordid 
            
        LEFT JOIN general_code gc ON gc.code = d.classcode 
		where gc.parentcode = 'PNSUB'
    ) z
    GROUP BY parentcode,systemtype
) c
    ON a.code = c.parentcode AND a.systemtype =c.systemtype

  
  GROUP BY a.code, a.systemtype 
  ORDER BY a.codeorder, a.systypeorder;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_originatingPassengers_New_bkp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_originatingPassengers_New_bkp`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(2),
in to_month varchar(2),in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);
DECLARE full_date DATE;
DECLARE days_in_month INT;

-- ============================
-- TO CALCULATE NUMBER OF DAYS
-- ============================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE WHEN to_month_int > 12 THEN from_year + 1 ELSE from_year END;    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');
SET days_in_month = DAY(LAST_DAY(full_date));

SELECT 
	a.code as revenuehead,
    a.systemtype,
    sum(COALESCE(b.total, 0))  AS budget,
	sum(COALESCE(c.pfytotal, 0)) AS pfytotal,
    sum(COALESCE(c.cfytotal, 0)) AS  cfytotal
FROM (
    
SELECT  hoa.code, sys.code AS systemtype,hoa.codeorder,sys.codeorder as systypeorder
FROM general_code hoa
CROSS JOIN general_code sys
WHERE hoa.codetype = 'Head Of Account' AND hoa.isactive = 1
  AND sys.codetype = 'System'          AND sys.isactive = 1
  AND hoa.code  = 'PNSUB') a

/* =======================
   Budget (Current FY)
   ======================= */
LEFT JOIN (
    SELECT 
        bd.headcode,
        'M' AS systemtype,
        SUM(fn_budget_val(
			bd.budgetval,
            fn_month_int(bd.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) AS total
    FROM budget_detail bd
    JOIN (
        SELECT 
            bheaderid
        FROM budget_header
        WHERE financialyear = curr_fy
          AND budgetno     = 1
          AND formonth     = '04'        
    ) bh
        ON bd.bheaderid = bh.bheaderid
    WHERE fn_month_int(bd.month) between from_month_int AND to_month_int 
		  AND bd.measure = 'UNIT'
          AND bd.headcode = 'PNSUB'
    GROUP BY bd.headcode
) b
    ON a.code = b.headcode AND a.systemtype = b.systemtype

-- ================================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- ================================================
LEFT JOIN (
    SELECT 
        parentcode,systemtype,
        SUM(CASE financialyear WHEN prev_fy THEN TOTAL ELSE 0 END) AS pfytotal,
        SUM(CASE financialyear WHEN curr_fy THEN TOTAL ELSE 0 END) AS cfytotal
    FROM (
        SELECT 
            gc.parentcode,
            h.systemtype,
            d.classcode,
            h.financialyear,
            fn_passenger_number(d.bookedcount, d.refundcount, d.excesscount,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) AS TOTAL
           
        FROM stn_earning_detail d
        JOIN (
            SELECT 
                recordid,period,systemtype,financialyear,formonth,formonth_int                
            FROM (
                SELECT 
                    recordid,period,systemtype,financialyear,formonth, fn_month_int(formonth) AS formonth_int,
                    ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY 
                            CASE valuetype 
                                WHEN 'O' THEN 1 
                                ELSE 2 
                            END
                    ) AS rn
                FROM stn_earning_header
                WHERE financialyear IN (prev_fy, curr_fy)
            ) x
            WHERE rn = 1
				  AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III','MON')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II','MON')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','MON')) )                                  
							ELSE (formonth = to_month AND (period in (i_period,'MON')))								  
					END)
        ) h ON d.headerid = h.recordid 
            
        LEFT JOIN general_code gc ON gc.code = d.classcode 
		where gc.parentcode = 'PNSUB'
    ) z
    GROUP BY parentcode,systemtype
) c
    ON a.code = c.parentcode AND a.systemtype =c.systemtype

  
  GROUP BY a.code, a.systemtype 
  ORDER BY a.codeorder, a.systypeorder;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_originatingRevenue` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_originatingRevenue`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(2),
in to_month varchar(2),in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);
DECLARE full_date DATE;
DECLARE days_in_month INT;

-- ===========================
-- TO CALCULATE NUMBER OF DAYS
-- ===========================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE WHEN to_month_int > 12 THEN from_year + 1 ELSE from_year END;    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');
SET days_in_month = DAY(LAST_DAY(full_date));

-- ============================
-- FROM HERE MAIN QUERY STARTS
-- ===============================
SELECT 
	CASE 
        WHEN a.code IN ('ORGG', 'OTHG') THEN 'GOODS'
        WHEN a.code = 'PNSUB' THEN 'PASSENGER'
        WHEN a.code = 'OTHC' THEN 'OTHER COACHING'
        WHEN a.code = 'SUN' THEN 'SUNDRY'
        ELSE a.code
    END AS revenuehead,
    sum(COALESCE(b.total, 0))  AS budget,
	sum(COALESCE(c.pfytotal, 0)) AS pfytotal,
    sum(COALESCE(c.cfytotal, 0)) AS  cfytotal
FROM general_code a

-- =======================
 --  Budget (Current FY)
 -- ======================= 
LEFT JOIN (
    SELECT 
        bd.headcode,        
        SUM(fn_budget_val(
			bd.budgetval,
            fn_month_int(bd.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) AS total
    FROM budget_detail bd
    JOIN (
        SELECT 
            bheaderid
        FROM budget_header
        WHERE financialyear = curr_fy
          AND budgetno     = 1
          AND formonth     = '04'
    ) bh
        ON bd.bheaderid = bh.bheaderid
    WHERE
		bd.measure = 'AMT'
      AND ( fn_month_int(bd.month) between from_month_int AND to_month_int )
    GROUP BY bd.headcode
) b ON a.code = b.headcode

-- =============================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- =============================================
LEFT JOIN (
    SELECT 
        parentcode,
        SUM(CASE financialyear WHEN prev_fy THEN TOTAL ELSE 0 END) AS pfytotal,
        SUM(CASE financialyear WHEN curr_fy THEN TOTAL ELSE 0 END) AS cfytotal
    FROM (
        SELECT 
            gc.parentcode,
            d.classcode,
            h.financialyear,
            CASE gc.parentcode
                WHEN 'ORGG'  THEN d.amount
                WHEN 'PNSUB' THEN fn_passenger_amt(d.bookedamt,d.refundamt,d.excessamt,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)								
                WHEN 'OTHC'  THEN d.earning
                WHEN 'SUN'   THEN d.earning
                WHEN 'OTHG'  THEN d.earning
                ELSE 0
            END AS TOTAL
        FROM stn_earning_detail d
        JOIN (
            SELECT 
                recordid,period,
                financialyear,formonth,formonth_int
            FROM (				
                SELECT 
                    recordid,period,
                    financialyear,formonth, fn_month_int(formonth) AS formonth_int,
                    ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY 
                            CASE valuetype 
                                WHEN 'O' THEN 1 
                                ELSE 2 
                            END
                    ) AS rn
                FROM stn_earning_header
                WHERE financialyear IN (prev_fy, curr_fy)
            ) x
            WHERE rn = 1
            AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III','MON')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II','MON')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','MON')) )                                  
							ELSE (formonth = to_month AND (period in (i_period,'MON')))								  
			END)
        ) h
		
        ON d.headerid = h.recordid
        LEFT JOIN general_code gc ON gc.code = d.classcode
			
    ) z
    GROUP BY parentcode
) c
    ON a.code = c.parentcode

WHERE a.codetype = 'Head Of Account'
  AND a.isactive = 1
  
  GROUP BY CASE 
        WHEN a.code IN ('ORGG', 'OTHG') THEN 'GOODS'
        WHEN a.code = 'PNSUB' THEN 'PASSENGER'
        WHEN a.code = 'OTHC' THEN 'OTHER COACHING'
        WHEN a.code = 'SUN' THEN 'SUNDRY'
        ELSE a.code
    END;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_originatingRevenue_New` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_originatingRevenue_New`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(2),
in to_month varchar(2),in i_period varchar(45), in is_cumm boolean)
BEGIN


-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
DECLARE days_in_month INT;

-- ===========================
-- TO CALCULATE NUMBER OF DAYS
-- ===========================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET days_in_month  = fn_days_in_month(curr_fy,to_month,to_month_int);

-- ============================
-- MAIN QUERY
-- ============================

SELECT a.code as revenuehead,a.systemtype,
	sum(COALESCE(b.total, 0))  AS budget,
	sum(COALESCE(c.pfytotal, 0)) AS pfytotal,
    sum(COALESCE(c.cfytotal, 0)) AS  cfytotal FROM (
    
SELECT  hoa.code, sys.code AS systemtype,hoa.codeorder,sys.codeorder as systypeorder
FROM general_code hoa
CROSS JOIN general_code sys
WHERE hoa.codetype = 'Head Of Account' AND hoa.isactive = 1
  AND sys.codetype = 'System'          AND sys.isactive = 1
  AND (
        ( hoa.code IN ('PNSUB','OTHC') AND sys.code IN ('M','P') )  -- Both M and P
        OR
        ( hoa.code NOT IN ('PNSUB','OTHC') AND sys.code = 'M' )     -- M only
      )) a


-- =======================
 -- Budget (Current FY)
 -- Budget is always Manual (M) entry
 -- P system type rows will show 0 budget by design
 -- ======================= 
LEFT JOIN (
    SELECT 
        bd.headcode,      
        'M' AS systemtype,
        SUM(fn_budget_val(
			bd.budgetval,
            fn_month_int(bd.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) AS total
    FROM budget_detail bd
    JOIN (
        SELECT 
            bheaderid
        FROM budget_header
        WHERE financialyear = curr_fy
          AND budgetno     = fn_getBudgetNo(curr_fy,'-1',to_month) -- as we want for complete zone so are passing division as -1
    ) bh
        ON bd.bheaderid = bh.bheaderid
    WHERE
		bd.measure = 'AMT'
      AND ( fn_month_int(bd.month) between from_month_int AND to_month_int )
    GROUP BY bd.headcode
) b ON a.code = b.headcode AND a.systemtype =b.systemtype


left join

    (SELECT 
        parentcode,systemtype,
        SUM(CASE financialyear WHEN prev_fy THEN TOTAL ELSE 0 END) AS pfytotal,
        SUM(CASE financialyear WHEN curr_fy THEN TOTAL ELSE 0 END) AS cfytotal
    FROM (
        SELECT 
            gc.parentcode,
            systemtype,           
            h.financialyear,
            CASE gc.parentcode
                WHEN 'ORGG'  THEN d.amount
                WHEN 'PNSUB' THEN fn_passenger_amt(d.bookedamt,d.refundamt,d.excessamt,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)								
                WHEN 'OTHC'  THEN d.earning
                WHEN 'SUN'   THEN d.earning
                WHEN 'OTHG'  THEN d.earning
                ELSE 0
            END AS TOTAL
        FROM stn_earning_detail d
        JOIN (
            SELECT 
                recordid,period,
                financialyear,formonth,formonth_int,systemtype
            FROM (				
                SELECT 
                    recordid,period,
                    financialyear,formonth, fn_month_int(formonth) AS formonth_int,systemtype,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
					 fn_is_o_received(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
        
                    ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY 
                           fn_header_priority(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
					) AS rn
                FROM stn_earning_header
                WHERE financialyear IN (prev_fy, curr_fy)
            ) x
            WHERE rn = 1
			AND NOT (valuetype = 'O' AND is_o_received = 0)
            AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) )                                  
							ELSE (formonth = to_month AND (period in (i_period)))	-- noy cummulative i.e only for the period							  
			END)
        ) h
		
        ON d.headerid = h.recordid
        LEFT JOIN general_code gc ON gc.code = d.classcode
			
    ) z
    GROUP BY parentcode, systemtype) c
    
    ON a.code = c.parentcode AND a.systemtype =c.systemtype
    
    GROUP BY a.code, a.systemtype   
	ORDER BY a.codeorder, a.systypeorder;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_originatingRevenue_New_bkp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_originatingRevenue_New_bkp`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(2),
in to_month varchar(2),in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
DECLARE days_in_month INT;

-- ===========================
-- TO CALCULATE NUMBER OF DAYS
-- ===========================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET days_in_month  = fn_days_in_month(curr_fy,to_month,to_month_int);

-- ============================
-- MAIN QUERY
-- ============================

SELECT a.code as revenuehead,a.systemtype,
	sum(COALESCE(b.total, 0))  AS budget,
	sum(COALESCE(c.pfytotal, 0)) AS pfytotal,
    sum(COALESCE(c.cfytotal, 0)) AS  cfytotal FROM (
    
SELECT  hoa.code, sys.code AS systemtype,hoa.codeorder,sys.codeorder as systypeorder
FROM general_code hoa
CROSS JOIN general_code sys
WHERE hoa.codetype = 'Head Of Account' AND hoa.isactive = 1
  AND sys.codetype = 'System'          AND sys.isactive = 1
  AND (
        ( hoa.code IN ('PNSUB','OTHC') AND sys.code IN ('M','P') )  -- Both M and P
        OR
        ( hoa.code NOT IN ('PNSUB','OTHC') AND sys.code = 'M' )     -- M only
      )) a


-- =======================
 -- Budget (Current FY)
 -- Budget is always Manual (M) entry
 -- P system type rows will show 0 budget by design
 -- ======================= 
LEFT JOIN (
    SELECT 
        bd.headcode,      
        'M' AS systemtype,
        SUM(fn_budget_val(
			bd.budgetval,
            fn_month_int(bd.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )) AS total
    FROM budget_detail bd
    JOIN (
        SELECT 
            bheaderid
        FROM budget_header
        WHERE financialyear = curr_fy
          AND budgetno     = fn_getBudgetNo(curr_fy,'-1',to_month) -- as we want for complete zone so are passing division as -1
    ) bh
        ON bd.bheaderid = bh.bheaderid
    WHERE
		bd.measure = 'AMT'
      AND ( fn_month_int(bd.month) between from_month_int AND to_month_int )
    GROUP BY bd.headcode
) b ON a.code = b.headcode AND a.systemtype =b.systemtype


left join

    (SELECT 
        parentcode,systemtype,
        SUM(CASE financialyear WHEN prev_fy THEN TOTAL ELSE 0 END) AS pfytotal,
        SUM(CASE financialyear WHEN curr_fy THEN TOTAL ELSE 0 END) AS cfytotal
    FROM (
        SELECT 
            gc.parentcode,
            systemtype,           
            h.financialyear,
            CASE gc.parentcode
                WHEN 'ORGG'  THEN d.amount
                WHEN 'PNSUB' THEN fn_passenger_amt(d.bookedamt,d.refundamt,d.excessamt,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)								
                WHEN 'OTHC'  THEN d.earning
                WHEN 'SUN'   THEN d.earning
                WHEN 'OTHG'  THEN d.earning
                ELSE 0
            END AS TOTAL
        FROM stn_earning_detail d
        JOIN (
            SELECT 
                recordid,period,
                financialyear,formonth,formonth_int,systemtype
            FROM (				
                SELECT 
                    recordid,period,
                    financialyear,formonth, fn_month_int(formonth) AS formonth_int,systemtype,
                    ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY 
                            CASE valuetype 
                                WHEN 'O' THEN 1 
                                ELSE 2 
                            END
                    ) AS rn
                FROM stn_earning_header
                WHERE financialyear IN (prev_fy, curr_fy)
            ) x
            WHERE rn = 1
            AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III','MON')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II','MON')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','MON')) )                                  
							ELSE (formonth = to_month AND (period in (i_period,'MON')))								  
			END)
        ) h
		
        ON d.headerid = h.recordid
        LEFT JOIN general_code gc ON gc.code = d.classcode
			
    ) z
    GROUP BY parentcode, systemtype) c
    
    ON a.code = c.parentcode AND a.systemtype =c.systemtype
    
    GROUP BY a.code, a.systemtype   
	ORDER BY a.codeorder, a.systypeorder;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_Originating_No_Rev_Div_Wise` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_Originating_No_Rev_Div_Wise`(
	IN prev_fy     VARCHAR(7),
    IN curr_fy     VARCHAR(7),
    IN from_month  VARCHAR(2),
    IN to_month    VARCHAR(2),
    IN i_period    VARCHAR(45),
    IN is_cumm     BOOLEAN )
BEGIN

-- =============================================
-- DERIVED VARIABLES
-- =============================================
DECLARE from_month_int  INT         DEFAULT 0;
DECLARE to_month_int    INT         DEFAULT 0;
DECLARE target_year     VARCHAR(4);
DECLARE full_date       DATE;
DECLARE days_in_month   INT;

-- =============================================
-- COMPUTE MONTH INTEGERS & DAYS IN MONTH
-- =============================================
SET from_month_int = fn_month_int(from_month);
SET to_month_int   = fn_month_int(to_month);
SET days_in_month = fn_days_in_month(curr_fy,to_month,to_month_int);

-- =============================================
-- MAIN QUERY
-- Division-wise Originating Passengers & Revenue
-- =============================================
SELECT
    d.divcode,
    d.systemtype,

    -- Actuals: Passenger Count
    COALESCE(act.prevfy_count, 0)  AS prevfycount,
    COALESCE(act.currfy_count, 0)  AS currfycount,

    -- Actuals: Revenue Amount
    COALESCE(act.prevfy_amt,   0)  AS prevfyamt,
    COALESCE(act.currfy_amt,   0)  AS currfyamt,

    -- Budget
    COALESCE(bud.bud_unit,     0)  AS budunit,
    COALESCE(bud.bud_amt,      0)  AS budamt

FROM (SELECT  d.divcode, s.systemtype,s.sysorder
		FROM div_master d
		CROSS JOIN (
				SELECT 'P'   AS systemtype, 1 as sysorder UNION ALL
				SELECT 'M' AS systemtype, 2 as sysorder
		) s
	WHERE d.isactive = 1
	ORDER BY d.divcode) d

-- =============================================
-- LEFT JOIN 1: Actual Earnings (Prev FY & Curr FY)
-- =============================================

left join (

	select a.divcode, 
	h.systemtype,
	sum(case when h.financialyear = prev_fy then fn_passenger_number(c.bookedcount, c.refundcount, c.excesscount,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)  else 0 end) 'prevfy_count' , 
    sum(case when h.financialyear = prev_fy then fn_passenger_amt(c.bookedamt,c.refundamt,c.excessamt,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)  else 0 end)  'prevfy_amt',
    sum(case when h.financialyear = curr_fy then fn_passenger_number(c.bookedcount, c.refundcount, c.excesscount,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) else 0 end) 'currfy_count' , 
    sum(case when h.financialyear = curr_fy then fn_passenger_amt(c.bookedamt,c.refundamt,c.excessamt,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) else 0 end)  'currfy_amt'    

from div_master a

left join (SELECT recordid,financialyear,systemtype,divisionid,formonth,formonth_int,period
			FROM (
					SELECT recordid,financialyear,systemtype,divisionid,formonth, fn_month_int(formonth) AS formonth_int,period,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
                    fn_is_o_received(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
					ROW_NUMBER() OVER (
							PARTITION BY 
								financialyear,
								formonth,
								period,
								entrytype,
								systemtype,
								headofaccounts,
								divisionid,
								stationcode
							ORDER BY fn_header_priority(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
								
						) AS rn
					FROM stn_earning_header  
					WHERE financialyear in (prev_fy,curr_fy) AND headofaccounts = 'PNSUB'
						
				)x            
            WHERE rn = 1
            AND NOT (valuetype = 'O' AND is_o_received = 0)
            AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) )                                  
							ELSE (formonth = to_month AND (period in (i_period)))								  
			END)
		) h
            
ON a.divid = h.divisionid

/*=======================
LEFT JOIN EARNING DETAIL
========================*/

LEFT JOIN stn_earning_detail c
ON  c.headerid = h.recordid 

where a.isactive = 1

group by  a.divcode,h.systemtype ) act ON act.divcode = d.divcode AND act.systemtype = d.systemtype

/*=======================
COMBINE BUDGET DATA
========================*/
left join (
SELECT 
    c.divcode,
	'M' AS systemtype,
    SUM(CASE WHEN b.measure = 'UNIT' THEN fn_budget_val(b.budgetval,fn_month_int(b.month),to_month_int,days_in_month,i_period,is_cumm) ELSE 0 END) AS bud_unit,
    SUM(CASE WHEN b.measure = 'AMT'  THEN fn_budget_val(b.budgetval,fn_month_int(b.month),to_month_int,days_in_month,i_period,is_cumm) ELSE 0 END) AS bud_amt

FROM div_master c

LEFT JOIN budget_header a
    ON a.divid = c.divid
   AND a.financialyear = curr_fy
   AND a.budgetno = fn_getBudgetNo(curr_fy,a.divid,to_month) 

JOIN budget_detail b
    ON a.bheaderid = b.bheaderid
   AND ( fn_month_int(b.month) between from_month_int AND to_month_int )
   AND b.headcode = 'PNSUB'

WHERE c.isactive = 1

GROUP BY  c.divcode) bud 

ON bud.divcode = d.divcode AND bud.systemtype = d.systemtype

GROUP BY d.divcode, d.systemtype  
ORDER BY d.divcode,d.sysorder;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_OTHC_Div_Wise` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_OTHC_Div_Wise`(
	IN prev_fy     VARCHAR(7),
    IN curr_fy     VARCHAR(7),
    IN from_month  VARCHAR(2),
    IN to_month    VARCHAR(2),
    IN i_period    VARCHAR(45),
    IN is_cumm     BOOLEAN
)
BEGIN

-- =============================================
-- DERIVED VARIABLES
-- =============================================
DECLARE from_month_int  INT         DEFAULT 0;
DECLARE to_month_int    INT         DEFAULT 0;
DECLARE days_in_month   INT 		DEFAULT 0;

-- =============================================
-- COMPUTE MONTH INTEGERS & DAYS IN MONTH
-- =============================================
SET from_month_int = fn_month_int(from_month);
SET to_month_int   = fn_month_int(to_month);
SET days_in_month  = fn_days_in_month(curr_fy,to_month,to_month_int);


select d.divcode,
-- Budget data
coalesce(bud.bud_amt,0) as budamt,

-- ACtuals
coalesce(act.prevfy_amt,0) as prevfyamt,
coalesce(act.currfy_amt,0) as currfyamt



 from div_master d

left join (

select a.divcode, 
    sum(case when h.financialyear = prev_fy then fn_passenger_amt(c.earning, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)  else 0 end)  'prevfy_amt',
    sum(case when h.financialyear = curr_fy then fn_passenger_amt(c.earning, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) else 0 end)  'currfy_amt'    

from div_master a

left join (SELECT recordid,financialyear,divisionid,formonth,period,formonth_int FROM 
			
			(SELECT recordid,
				financialyear,divisionid,formonth,period, fn_month_int(formonth) AS formonth_int,
                ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY 
                            CASE valuetype 
                                WHEN 'O' THEN 1 
                                ELSE 2 
                            END
                    ) AS rn
                FROM stn_earning_header  
				WHERE financialyear in (prev_fy,curr_fy) AND headofaccounts = "OTHC"
			)x
            
            WHERE rn = 1
            AND (CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III','MON')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II','MON')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III','MON'))
											OR
										(formonth_int = to_month_int AND period IN ('I','MON')) )                                  
							ELSE (formonth = to_month AND (period in (i_period,'MON')))								  
				END)
            ) h
            
ON a.divid = h.divisionid

/*=======================
LEFT JOIN EARNING DETAIL
========================*/

LEFT JOIN stn_earning_detail c
ON  c.headerid = h.recordid 

where a.isactive = 1

group by  a.divcode ) act ON act.divcode = d.divcode

/*=======================
COMBINE BUDGET DATA
========================*/
left join (
SELECT 
    c.divcode,
    SUM(CASE WHEN b.measure = 'AMT'  THEN fn_budget_val(b.budgetval,fn_month_int(b.month),to_month_int,days_in_month,i_period,is_cumm) ELSE 0 END) AS bud_amt

FROM div_master c

LEFT JOIN budget_header a
    ON a.divid = c.divid
   AND a.financialyear = curr_fy
   AND a.budgetno = 1
   AND a.formonth = '04'

LEFT JOIN budget_detail b
    ON a.bheaderid = b.bheaderid
   AND (fn_month_int(b.month) between from_month_int AND to_month_int )
   AND b.headcode = 'OTHC'

WHERE c.isactive = 1

GROUP BY  c.divcode) bud ON bud.divcode = d.divcode

where d.isactive = 1 order by divcode;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_othc_div_wise_new` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_othc_div_wise_new`(
	IN prev_fy     VARCHAR(7),
    IN curr_fy     VARCHAR(7),
    IN from_month  VARCHAR(2),
    IN to_month    VARCHAR(2),
    IN i_period    VARCHAR(45),
    IN is_cumm     BOOLEAN
)
BEGIN

-- =============================================
-- DERIVED VARIABLES
-- =============================================
DECLARE from_month_int  INT         DEFAULT 0;
DECLARE to_month_int    INT         DEFAULT 0;
DECLARE days_in_month   INT 		DEFAULT 0;

-- =============================================
-- COMPUTE MONTH INTEGERS & DAYS IN MONTH
-- =============================================
SET from_month_int = fn_month_int(from_month);
SET to_month_int   = fn_month_int(to_month);
SET days_in_month  = fn_days_in_month(curr_fy,to_month,to_month_int);


select 
d.divcode,
d.systemtype,
-- Budget data
coalesce(bud.bud_amt,0) as budamt,

-- ACtuals
coalesce(act.prevfy_amt,0) as prevfyamt,
coalesce(act.currfy_amt,0) as currfyamt

 from (SELECT  d.divcode, s.systemtype,s.sysorder
		FROM div_master d
		CROSS JOIN (
				SELECT 'P'   AS systemtype, 1 as sysorder UNION ALL
				SELECT 'M' AS systemtype, 2 as sysorder
		) s
	WHERE d.isactive = 1
	ORDER BY d.divcode) d

left join (

select a.divcode, 
	h.systemtype,
    sum(case when h.financialyear = prev_fy then fn_passenger_amt(c.earning, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm)  else 0 end)  'prevfy_amt',
    sum(case when h.financialyear = curr_fy then fn_passenger_amt(c.earning, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) else 0 end)  'currfy_amt'    

from div_master a

left join (SELECT recordid,financialyear,systemtype,divisionid,formonth,period,formonth_int FROM 
			
			(SELECT recordid,financialyear,divisionid,systemtype,formonth,period, fn_month_int(formonth) AS formonth_int,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
				fn_is_o_received(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
                ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY fn_header_priority(valuetype,financialyear,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
                            
                    ) AS rn
                FROM stn_earning_header  
				WHERE financialyear in (prev_fy,curr_fy) AND headofaccounts = "OTHC"
			)x
            
            WHERE rn = 1
            AND NOT (valuetype = 'O' AND is_o_received = 0)
            AND (CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) )                                  
							ELSE (formonth = to_month AND (period in (i_period)))								  
				END)
            ) h
            
ON a.divid = h.divisionid

/*=======================
LEFT JOIN EARNING DETAIL
========================*/

LEFT JOIN stn_earning_detail c
ON  c.headerid = h.recordid 

where a.isactive = 1

group by  a.divcode,h.systemtype ) act ON act.divcode = d.divcode AND act.systemtype = d.systemtype

/*=======================
COMBINE BUDGET DATA
========================*/
left join (
SELECT 
    c.divcode,
	'M' AS systemtype,
    SUM(CASE WHEN b.measure = 'AMT'  THEN fn_budget_val(b.budgetval,fn_month_int(b.month),to_month_int,days_in_month,i_period,is_cumm) ELSE 0 END) AS bud_amt

FROM div_master c

LEFT JOIN budget_header a
    ON a.divid = c.divid
   AND a.financialyear = curr_fy
   AND a.budgetno = fn_getBudgetNo(curr_fy,a.divid,to_month) 

LEFT JOIN budget_detail b
    ON a.bheaderid = b.bheaderid
   AND (fn_month_int(b.month) between from_month_int AND to_month_int )
   AND b.headcode = 'OTHC'

WHERE c.isactive = 1

GROUP BY  c.divcode) bud 

ON bud.divcode = d.divcode AND bud.systemtype = d.systemtype
GROUP BY d.divcode, d.systemtype  
ORDER BY d.divcode,d.sysorder;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_test` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_test`(in prev_fy varchar(7), in curr_fy varchar(7),in from_month varchar(2),
in to_month varchar(2),in i_period varchar(45), in is_cumm boolean)
BEGIN

-- =====================
-- DERIVED VARIABLES
-- =====================
declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);
DECLARE full_date DATE;
DECLARE days_in_month INT;

-- ===========================
-- TO CALCULATE NUMBER OF DAYS
-- ===========================
SET from_month_int = fn_month_int(from_month);                        
SET to_month_int = fn_month_int(to_month);                         
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE WHEN to_month_int > 12 THEN from_year + 1 ELSE from_year END;    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');
SET days_in_month = DAY(LAST_DAY(full_date));        


-- ============================
-- FROM HERE MAIN QUERY STARTS
-- ===============================


SELECT dm.divcode,
			SUM(CASE financialyear WHEN prev_fy THEN d.tonne ELSE 0 END) AS pfytotal,
			SUM(CASE financialyear WHEN curr_fy THEN d.tonne ELSE 0 END) AS cfytotal
	FROM div_master as dm
    
	LEFT JOIN

		(SELECT recordid,financialyear,divisionid,formonth,formonth_int,period,headofaccounts
			FROM (				
					SELECT recordid,financialyear,divisionid,formonth,fn_month_int(formonth) AS formonth_int, period,headofaccounts,
						ROW_NUMBER() OVER (
							PARTITION BY 
								financialyear,
								formonth,
								period,
								entrytype,
								systemtype,
								headofaccounts,
								divisionid,
								stationcode
							ORDER BY fn_valuetype_int(valuetype)
						) AS rn
					FROM stn_earning_header
					WHERE financialyear IN (prev_fy, curr_fy) AND headofaccounts = 'ORGG'
				) x
				WHERE rn = 1
                AND (CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III,
							-- to_month gets only I+II
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III,
							-- to_month gets only I                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) ) 
						ELSE formonth = to_month AND period = i_period
					 END )
                ) h 
		
        ON dm.divid = h.divisionid

	LEFT JOIN stn_earning_detail d 
		ON d.headerid = h.recordid
                 
	WHERE  dm.isactive = 1 group by dm.divcode;



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `report_zoneWise_othg` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `report_zoneWise_othg`(
	IN prev_fy     VARCHAR(7),
    IN curr_fy     VARCHAR(7),
    IN from_month  VARCHAR(2),
    IN to_month    VARCHAR(2),
    IN i_period    VARCHAR(45),
    IN is_cumm     BOOLEAN)
BEGIN

-- =============================================
-- DERIVED VARIABLES
-- =============================================
DECLARE from_month_int  INT         DEFAULT 0;
DECLARE to_month_int    INT         DEFAULT 0;
DECLARE days_in_month   INT 		DEFAULT 0;

-- =============================================
-- COMPUTE MONTH INTEGERS & DAYS IN MONTH
-- =============================================
SET from_month_int = fn_month_int(from_month);
SET to_month_int   = fn_month_int(to_month);
SET days_in_month  = fn_days_in_month(curr_fy,to_month,to_month_int);

-- =======================
 --  Main Query Start From Here
 -- ======================= 
SELECT CASE 
        WHEN bd.headcode = 'OTHG' THEN 'OTHER GOODS'        
        ELSE bd.headcode
		END  AS 'divcode',bd.bud_total as 'budamt',ed.prevfy_amt as 'pfyamt',ed.currfy_amt as 'cfyamt' from 

-- =======================
 --  Budget (Current FY)
 -- ======================= 
( SELECT d.headcode, coalesce(SUM(fn_budget_val(
			d.budgetval,
            fn_month_int(d.month),
			to_month_int,
			days_in_month,
			i_period,
			is_cumm
        )),0) bud_total from 

 (SELECT bheaderid,divid FROM budget_header WHERE financialyear = curr_fy AND budgetno = fn_getBudgetNo(curr_fy,'-1',to_month)) h

left join budget_detail d
	ON h.bheaderid = d.bheaderid
    AND d.headcode = 'OTHG'
    AND d.measure = 'AMT'
    AND (fn_month_int(d.month) between from_month_int AND to_month_int )
GROUP BY d.headcode) bd

left join

-- =============================================
-- LEFT JOIN 2: Actual Earnings (Prev FY & Curr FY)
-- =============================================

(SELECT h.headofaccounts, 
	sum(case when h.financialyear = prev_fy then fn_passenger_amt(d.earning, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) else 0 end)  'prevfy_amt',
    sum(case when h.financialyear = curr_fy then fn_passenger_amt(d.earning, 0, 0,h.formonth_int,to_month_int,h.period,i_period,days_in_month,is_cumm) else 0 end)  'currfy_amt'  from 
	( SELECT recordid,headofaccounts,financialyear,period,formonth,formonth_int FROM 
		(SELECT recordid,headofaccounts,financialyear,period,formonth, fn_month_int(formonth) AS formonth_int,valuetype,
                     -- Flag: is THIS 'O' record received?
					-- Compare receivedfy against the row's OWN financialyear
					 fn_is_o_received(valuetype,curr_fy,to_month,i_period,receivedfy,receivedmonth,receivedperiod) AS is_o_received,
        
				ROW_NUMBER() OVER (
                        PARTITION BY 
                            financialyear,
                            formonth,
                            period,
                            entrytype,
                            systemtype,
                            headofaccounts,
                            divisionid,
                            stationcode
                        ORDER BY  fn_header_priority(valuetype,curr_fy,to_month,i_period,receivedfy,receivedmonth,receivedperiod)
                           
                    ) AS rn FROM stn_earning_header 
                    where financialyear IN(prev_fy,curr_fy) 
						  AND headofaccounts = 'OTHG'                    

	) X WHERE x.rn = 1
		AND NOT (valuetype = 'O' AND is_o_received = 0)
		AND ( CASE
							-- Period III cumulative: all months 04→to_month, all periods
							WHEN is_cumm AND i_period = 'III' THEN	formonth_int  between from_month_int AND to_month_int AND period IN ('I','II','III')
                            
                            -- Period II cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+II+MON
							WHEN is_cumm AND i_period = 'II' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I','II')) )

							-- Period I cumulative: months before to_month get I+II+III+MON,
							-- to_month gets only I+MON                                                
							WHEN is_cumm AND i_period = 'I' THEN
									formonth_int  between from_month_int AND to_month_int
                                    AND ( (formonth_int < to_month_int AND period IN ('I','II','III'))
											OR
										(formonth_int = to_month_int AND period IN ('I')) )                                  
							ELSE (formonth = to_month AND (period in (i_period)))								  
			END)
) h

left join stn_earning_detail d 
	ON d.headerid = h.recordid

group by h.headofaccounts) ed
 
ON bd.headcode = ed.headofaccounts;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `test_new_procedure` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `test_new_procedure`()
BEGIN

declare i_period varchar(45) DEFAULT 'I';
declare from_month varchar(45) DEFAULT '04';
declare to_month varchar(45) DEFAULT '02';
declare prev_fy varchar(45) DEFAULT '2024-25';
declare curr_fy varchar(45) DEFAULT '2023-24';
DECLARE full_date DATE;
DECLARE days_in_month INT;

declare from_month_int int DEFAULT 0;
declare to_month_int int DEFAULT 0;
declare from_year varchar(4);

SET to_month_int = CASE to_month 
						WHEN '01' THEN 13
                        WHEN '02' THEN 14
                        WHEN '03' THEN 15 
                        ELSE CAST(to_month AS UNSIGNED) END;
                        
SET from_year = SUBSTRING_INDEX(curr_fy, '-', 1);
SET from_year = CASE 
    WHEN to_month_int > 12 THEN cast( from_year as unsigned) + 1
    ELSE from_year END;
    
SET full_date = STR_TO_DATE(CONCAT(from_year, '-', to_month, '-01'), '%Y-%m-%d');

SET days_in_month = DAY(LAST_DAY(full_date));

select from_year,days_in_month;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `toggle_value_type` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `toggle_value_type`(in i_recordid long, out o_result varchar(50))
BEGIN

DECLARE no_record INT DEFAULT 0;
DECLARE current_valuetype VARCHAR(5) DEFAULT '';
DECLARE current_rfy VARCHAR(45) DEFAULT '';
DECLARE current_rmonth VARCHAR(45) DEFAULT '';
DECLARE current_rperiod VARCHAR(45) DEFAULT '';
DECLARE current_remark VARCHAR(45) DEFAULT '';

DECLARE new_valuetype      VARCHAR(5)  DEFAULT NULL;
DECLARE new_rfy VARCHAR(45) DEFAULT NULL;
DECLARE new_rmonth VARCHAR(45) DEFAULT NULL;
DECLARE new_rperiod VARCHAR(45) DEFAULT NULL;
DECLARE new_remark VARCHAR(45) DEFAULT NULL;

DECLARE iii_period_recordid LONG DEFAULT null;

    -- ✅ Step 1: Check record exists
    SELECT COUNT(*) INTO no_record 
    FROM stn_earning_header 
    WHERE recordid = i_recordid;

    IF (no_record = 1) THEN

		-- We need to update valuetype of III period when MONTHLY valuetype changed
		
        -- ✅ Step 2: Fetch current valuetype
        SELECT valuetype,financialyear,formonth,period,remarks,(SELECT recordid FROM stn_earning_header WHERE parentrecordid = i_recordid LIMIT 1) 
        INTO   current_valuetype,current_rfy,current_rmonth,current_rperiod,current_remark, iii_period_recordid
        FROM stn_earning_header 
        WHERE recordid = i_recordid;

        -- ✅ Step 3: Toggle valuetype A → O  or  O → A
        -- When A -> O SET receivedfy = financialyear like wise
        -- When O -> A SET receivedfy = NULL like wise
        IF (current_valuetype = 'A') THEN
			SET new_valuetype = 'O';   
			SET new_rfy = current_rfy;
			SET new_rmonth = current_rmonth;
			SET new_rperiod = IF(current_rperiod = 'MON', 'III', current_rperiod);
            SET new_remark = current_remark;
			SET o_result        = 'UPDATED_TO_O';
		 ELSEIF current_valuetype = 'O' THEN
            SET new_valuetype = 'A';
            SET new_rfy  = null;
            SET new_rmonth  = null;
            SET new_rperiod  = null;
            set new_remark = null;
            SET o_result        = 'UPDATED_TO_A';
        ELSE
            SET o_result = 'INVALID_VALUETYPE';          
        END IF;
        
        IF new_valuetype IS NOT NULL THEN

            -- Update main header
            UPDATE stn_earning_header
            SET    valuetype = new_valuetype,receivedfy =new_rfy,receivedmonth = new_rmonth,receivedperiod=new_rperiod,remarks = new_remark 
            WHERE  recordid  = i_recordid;

            -- Update III period value type. This is for monthly value type
            IF iii_period_recordid IS NOT NULL THEN
                UPDATE stn_earning_header
                SET    valuetype = new_valuetype, receivedfy =new_rfy,receivedmonth = new_rmonth,receivedperiod=new_rperiod,remarks = new_remark 
                WHERE  recordid  = iii_period_recordid;
            END IF;

        END IF;

    ELSEIF (no_record = 0) THEN
        SET o_result = 'NOT_FOUND';
    ELSE
        SET o_result = 'DUPLICATE_FOUND';

    END IF;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-20 12:55:06
