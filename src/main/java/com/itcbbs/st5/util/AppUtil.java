package com.itcbbs.st5.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import com.itcbbs.st5.dao.MonthDao;
import com.itcbbs.st5.dao.OtherCoachingCombinedDao;
import com.itcbbs.st5.dao.OtherCoachingReportDao;
import com.itcbbs.st5.dao.PassengerReportDao;

public final class AppUtil {

	 // Prevent instantiation
    private AppUtil() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    public static BigDecimal nz(BigDecimal v) {
	     return v == null ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_DOWN) : v.setScale(2, RoundingMode.HALF_DOWN);
	 }

    public static BigDecimal percent(BigDecimal numerator, BigDecimal denominator) {
		 
	     if (denominator == null || denominator.compareTo(BigDecimal.ZERO) == 0) {
	         return BigDecimal.ZERO;
	     }
	     
	     return numerator
	             .multiply(BigDecimal.valueOf(100))
	             .divide(denominator, 2, RoundingMode.HALF_DOWN);
	 }
    
    // ── Safe BigDecimal division ─────────────────────────────────────────────────
    public static BigDecimal safeDivide(BigDecimal value, BigDecimal divisor) {
        if (value == null || value.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
        return value.divide(divisor, 2, RoundingMode.HALF_DOWN);
    }
    
    
    // ── Integer count division ───────────────────────────────────────────────────
    public static int divideCount(Integer count, int days, int mult_factor) {
        if (count == null || count == 0) return 0;
        return (int) Math.round((count / (double) days) * mult_factor);
    }
    
    public static String getSystemTypeDescription(String systemtype) {
   	 
   	 if(systemtype.equalsIgnoreCase("P"))
   		 return "PRS";
   	 else if (systemtype.equalsIgnoreCase("M"))
   		 return "Non PRS";
   	 else
   		 return systemtype;
    }
    
    
    
    
    public static PassengerReportDao sumRows(List<PassengerReportDao> list, String div, String systemtype) {
        return list.stream().reduce(
            new PassengerReportDao(div, systemtype,
                BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO,
                BigDecimal.ZERO, BigDecimal.ZERO),
            (acc, row) -> new PassengerReportDao(div, systemtype,
                acc.getCurrFyCount() .add(row.getCurrFyCount()),
                acc.getCurrFyAmt()   .add(row.getCurrFyAmt()),
                acc.getPrevFyCount() .add(row.getPrevFyCount()),
                acc.getPrevFyAmt()   .add(row.getPrevFyAmt()),
                acc.getBudgetUnit()  .add(row.getBudgetUnit()),
                acc.getBudgetAmt()   .add(row.getBudgetAmt())),
            (a, b) -> a  // combiner for parallel streams
        );
    }
    
    
    public static OtherCoachingReportDao otherCoachingSumRows(List<OtherCoachingReportDao> list, String div, String systemtype) {
        return list.stream().reduce(
            new OtherCoachingReportDao(div, systemtype,
                BigDecimal.ZERO, BigDecimal.ZERO,BigDecimal.ZERO),
            (acc, row) -> new OtherCoachingReportDao(div, systemtype,
                acc.getCurrFyAmt()   .add(row.getCurrFyAmt()),
                acc.getPrevFyAmt()   .add(row.getPrevFyAmt()),
                acc.getBudgetAmt()   .add(row.getBudgetAmt())),
            (a, b) -> a  // combiner for parallel streams
        );
    }
    
    
    public static List<OtherCoachingCombinedDao> zipOthc(
            List<OtherCoachingReportDao> forPeriod,
            List<OtherCoachingReportDao> uptoPeriod) {

        List<OtherCoachingCombinedDao> combined = new ArrayList<>();

        for (int i = 0; i < forPeriod.size(); i++) {
        	OtherCoachingReportDao fp = forPeriod.get(i);
        	OtherCoachingReportDao up = uptoPeriod.get(i);

            combined.add(new OtherCoachingCombinedDao(
                fp.getDivCode(),
                fp.getSytemType(),
                fp.getSytemTypeDesc(),
                // For the period
                fp.getBudgetAmt(),
                fp.getPrevFyAmt(),
                fp.getCurrFyAmt(),
                fp.getVarOverBudgetActAmt(),
                fp.getVarOverBudgetPctAmt(),
                fp.getVarOverPrevFyActAmt(),
                fp.getVarOverPrevFyPctAmt(),
                // Upto the period
                up.getBudgetAmt(),
                up.getPrevFyAmt(),
                up.getCurrFyAmt(),
                up.getVarOverBudgetActAmt(),
                up.getVarOverBudgetPctAmt(),
                up.getVarOverPrevFyActAmt(),
                up.getVarOverPrevFyPctAmt()
            ));
        }
        return combined;
    }
    
  
    public static String getNextMonth(String curMonth) {
    	
    	//Calculate Next Month
    	String nextMOnth = "-1";
    	List<String>monthList = new ArrayList<>(List.of("04","05","06","07","08","09","10","11","12","01","02","03")) ;
    	int index = monthList.indexOf(curMonth);
    	if(index != -1)
    		index ++;
    	index = index%12;
    	if(monthList.size()>index)
    		nextMOnth = monthList.get(index);
    	
    	return nextMOnth;
    	
    }
    
    public static String getNextFinancialYear(String curFY, String curMonth) {
    	
    	 String nextFy = "-1";
		 if (curFY == null || curMonth == null) {
		        throw new IllegalArgumentException("Financial year and month must not be null");
		    }

		    // Expected format: "YYYYNN" (e.g., "202526")
		    if (!curFY.matches("\\d{4}-\\d{2}")) {
		        throw new IllegalArgumentException("Invalid financial year format. Expected 'YYYY-YY', got: " + curFY);
		    }

		    if (!curMonth.matches("\\d{2}") || Integer.parseInt(curMonth) < 1 || Integer.parseInt(curMonth) > 12) {
		        throw new IllegalArgumentException("Invalid month. Expected a two-digit value between 01 and 12, got: " + curMonth);
		    }

		    // Only advance the financial year at the end of March (month "03")
		    if (!"03".equalsIgnoreCase(curMonth)) {
		    	nextFy = curFY;
		    }    		    	
		    else {
		    	
    		    int startYear = Integer.parseInt(curFY.substring(0, 4)); // e.g., 2025
    		    int endYear   = Integer.parseInt(curFY.substring(4, 6)); // e.g., 26

    		    startYear++;
    		    endYear = (endYear + 1) % 100; // Wraps around: 99 → 00
    		    nextFy =   String.format("%04d-%02d", startYear, endYear);
    		    System.out.println("nextFy = "+nextFy);

		    }
		    return nextFy;
    }
    
    public static int getDaysInMonth(String forMonth, String financialYear) {
    	
        int fyStartYear = Integer.parseInt(financialYear.substring(0, 4));
        int month       = Integer.parseInt(forMonth);
        // Indian FY: Apr–Dec → fyStartYear, Jan–Mar → fyStartYear + 1
        int calendarYear = (month >= 4) ? fyStartYear : fyStartYear + 1;
        return YearMonth.of(calendarYear, month).lengthOfMonth();
    }
    
        
   
 
 public static BigDecimal get10DaysValue(BigDecimal monthVal, int numberOfDays) {
	 
	 if(monthVal ==null || monthVal.equals(BigDecimal.ZERO) || numberOfDays <0)
		 return BigDecimal.ZERO;
	 return monthVal.divide(BigDecimal.valueOf(numberOfDays)).multiply(BigDecimal.TEN);
	 
 }
 
 public static Integer get10DaysCount(Integer monthVal, int numberOfDays) {
	 
	 if(monthVal ==null || monthVal.equals(BigDecimal.ZERO) || numberOfDays <0)
		 return 0;
	 return (monthVal/numberOfDays)*10;
	 
 }
 
 
 
 public static List<MonthDao> generateMonthDetails(List<String> monthsNumbers){
	 
	 List<MonthDao> monthDetail = new ArrayList<>();
	 
	 for (String monthnum : monthsNumbers) {
		
		MonthDao monthobj = new MonthDao(monthnum, getMonthNameForMonthNumber(monthnum));
		monthDetail.add(monthobj);
	}
	 
	 return monthDetail;
	 
 }
 
 public static String getMonthNameForMonthNumber(String monthnum) {
	 
	 String monthName = "";
	 switch (monthnum) {
	case "04": {
		
		monthName = "Apr";
		break;
	}case "05": {
		
		monthName = "May";
		break;
	}case "06": {
		
		monthName = "June";
		break;
	}case "07": {
		
		monthName = "July";
		break;
	}case "08": {
		
		monthName = "Aug";
		break;
	}case "09": {
		
		monthName = "Sep";
		break;
	}case "10": {
		
		monthName = "Oct";
		break;
	}case "11": {
		
		monthName = "Nov";
		break;
	}case "12": {
		
		monthName = "Dec";
		break;
	}case "01": {
		
		monthName = "Jan";
		break;
	}case "02": {
		
		monthName = "Feb";
		break;
	}case "03": {
		
		monthName = "Mar";
		break;
	}
	
	default:
		throw new IllegalArgumentException("Unexpected value: " + monthnum);
	}
	 
	 return monthName;
 }
 
 
}
