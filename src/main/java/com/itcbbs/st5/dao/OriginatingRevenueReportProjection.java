package com.itcbbs.st5.dao;

import java.math.BigDecimal;

public interface OriginatingRevenueReportProjection {

	String getRevenuehead();
	String getSystemtype();
	BigDecimal getBudget();
	BigDecimal getPfytotal();
	BigDecimal getCfytotal();
}
