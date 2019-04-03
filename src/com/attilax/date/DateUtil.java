package com.attilax.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.attilax.util.Listutil;
import com.google.common.collect.Lists;

@SuppressWarnings("all")

/*
 * Java获取当前时间的上一个月和下一个月,第一天和最后一天,任意时间的第一天和最后一天,任意时间上一个月和下一个月 - CSDN博客.html
 * v3 s328
 * 
 * */
public class DateUtil {
	
	
	
	public static void main(String[] args) {
		
		
		createPartByWeekInYearMonthRange("2018-01","2018-03","","");
		
	}
	
	
	/*   2017-01-----2018-5
	 * CREATE TABLE public.vaj1V2_p_befor2018 PARTITION OF public.vaj1V2
    FOR VALUES FROM ('2000-01-01 00:00:00') TO ('2018-01-01 00:00:00');
	 * 
	 * */
	private static void createPartByWeekInYearMonthRange(String month_start, String month_end, String partTabNamePrefix, String tab) {
		

		List<String> li=getWeekrangleInInYearMonthRange(month_start,month_end);
		
		System.out.println("--");
		
//	  for(int y=	start.getYear();y<end.getYear())
//	  {
//		  
//	  }
	}
	
	
	  public static List<String> getWeekrangleInInYearMonthRange(String month_start, String month_end) {
		  Date start=  ( stringToDate (month_start));	Date end= stringToDate(month_end);
			int firstYear=getyear (start );  int lastYear=getyear(end );
			List<String> firstYear_date=get_curYearMonths(start,month_end);		
			List<String> lastYear_date=get_lastYear_jan2curmonth_fmtYearMonths(end);
			List<String>  span_years=get_span_years( start, end );
			List<String>  span_years_months=get_SomeYearMonths( span_years);
			List<String>  li=Lists.newArrayList();
			li.addAll(  firstYear_date );li.addAll(  span_years_months );li.addAll(  lastYear_date );
			li=Listutil.deduli(li);
		return getWeekrangleInInYearMonthRange_byYmList(li);
	}


	private static List<String> get_lastYear_jan2curmonth_fmtYearMonths(Date end) {
		List<String> st=Lists.newArrayList();
		int firstYear=getyear(end);
		int month=end.getMonth()+1;
		for(int i=1;i<=month;i++)
		{
			st.add(  firstYear+"-"+DateUtil.pad0(i)   );
		}
		return st;
 
	}


	public static List<String> getWeekrangleInInYearMonthRange_byYmList(List<String> li_ym) {
		List<String>  li=Lists.newArrayList();
		for (String ym : li_ym) {
			li.add(ym +"-01,"+	ym+ "-07");
			li.add(ym +"-07,"+	ym+ "-14");
			li.add(ym +"-14,"+	ym+ "-21");
			li.add(ym +"-21,"+	ym+ "-28");
			
			String firstDayOfNextMonth = getFirstday_nextmonth(ym);
			li.add(ym +"-28,"+	 firstDayOfNextMonth);
		}
		return li;
	}


	public static String getFirstday_nextmonth(String ym) {
		Date dt=stringToDate(ym);
		int mon=dt.getMonth()+1;
		String firstDayOfNextMonth = getFirstDayOfNextMonth(dt);
		return firstDayOfNextMonth;
	}
	
	 /** 
     *  
     * 描述:获取下一个月的第一天. 
     *  
     * @return 
     */  
    public static String getFirstDayOfNextMonth(Date dt) {  
      //  SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");  
        Calendar calendar = Calendar.getInstance(); calendar.setTime(dt);
      //  Calendar calendar = Calendar.getInstance();  
        calendar.add(Calendar.MONTH, 1);  
    //    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
        
      //  c.get(Calendar.YEAR)
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");  
        return  dft.format(calendar.getTime());  
    }  


	public static String getPMerBillNo() {
	        return getDate("yyyyMMddHHmmss") + getRandom();
	    }

	    public static final String DATE_FORMAT1 = "yyyy-MM-dd";
	    public static final String DATE_FORMAT_yyyyMM = "yyyy-MM";
	    public static String getDate(String pattern) {
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        String date = sdf.format(new Date());
	        return date;
	    }

	    public static Date stringToDate(String date, String pattern) throws ParseException {
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        return sdf.parse(date);
	    }
	    
	    public static Date stringToDate(String date )   {
	        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_yyyyMM);
	        try {
				return sdf.parse(date);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
	    }

	    public static String dateToString(Date date, String pattern) {
	        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	        return sdf.format(date);
	    }

	    public static int getRandom() {
	        Random random = new Random();
	        int ints = random.nextInt(999999999);
	        if (ints < 100000000) {
	            ints += 100000000;
	        }
	        return ints;
	    }

	private static List<String> get_SomeYearMonths(List<String> span_years) {
		List<String> st=Lists.newArrayList();
		for (String y : span_years) {
			for(int i=1;i<=12;i++)
			{
				st.add(  y+"-"+DateUtil.pad0(i)   );
			}
		}
	 
	
		return st;
	}


	private static List<String> get_span_years(Date start, Date end) {
		List<String> st=Lists.newArrayList();
		int firstYear=getyear(start);
		int last=getyear(end);
		for(int i=firstYear+1;i<last;i++)
		{
			st.add(String.valueOf(i));
		}
		
		return st;
	}


	public static String getdate_monthstart(int year, int month_start) {
		 String month_start_str = null;
		if(month_start<10)
			 month_start_str="0"+String.valueOf(month_start);
		else
			month_start_str=String.valueOf(month_start);
		return String.valueOf(year)+"-"+month_start_str+"-01";
	}

	public static String pad0(int month) {
		if(month<10)
			return "0"+String.valueOf( month);
		return String.valueOf( month);
	}
	
	
private static List<String> get_curYearMonths(Date start, String month_end) {
		
	
	Date dt_end=stringToDate(month_end); 
	
	
	List<String> st=Lists.newArrayList();
		int firstYear=getyear(start);
		int month=start.getMonth()+1;
		for(int i=month;i<=12;i++)
		{
			String ym = firstYear+"-"+DateUtil.pad0(i);
			Date dt=stringToDate(ym);
			 if(dt.getTime()<dt_end.getTime())
			
				st.add(  ym   );
			 
		}
		return st;
	}


private static int getyear(Date start) {
	 Calendar c = Calendar.getInstance(); c.setTime(start);return  c.get(Calendar.YEAR);
	 
}


public static List<String> getMonthrangleInInYearMonthRange(String month_start, String month_end) {
	  Date start=  ( stringToDate (month_start));	Date end= stringToDate(month_end);
		int firstYear=getyear (start );  int lastYear=getyear(end );
		List<String> firstYear_date=get_curYearMonths(start,month_end);		
		List<String> lastYear_date=get_lastYear_jan2curmonth_fmtYearMonths(end);
		List<String>  span_years=get_span_years( start, end );
		List<String>  span_years_months=get_SomeYearMonths( span_years);
		List<String>  li=Lists.newArrayList();
		li.addAll(  firstYear_date );li.addAll(  span_years_months );li.addAll(  lastYear_date );
		li=Listutil.deduli(li);
	return li;
}

}
