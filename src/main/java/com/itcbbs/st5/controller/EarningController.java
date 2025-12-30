package com.itcbbs.st5.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itcbbs.st5.dao.ClassItem;
import com.itcbbs.st5.dao.StationEarningDetail;
import com.itcbbs.st5.dao.StationEarningHeader;
import com.itcbbs.st5.dao.User;
import com.itcbbs.st5.repository.EarningRepo;
import com.itcbbs.st5.services.EarningService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/earning")
public class EarningController {

	@Autowired
	EarningRepo earnRepo;

	@Autowired
	EarningService earnser;

	@GetMapping("/earning_detail")
	public String originating_period(Model model) {

		System.out.println("HIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		/* model.addAttribute("msg", "Welcome to Spring Boot with JSP!"); */
		List<ClassItem> classes = getClasses("PNSUB");
		StationEarningHeader header = new StationEarningHeader();

		List<StationEarningDetail> details = new ArrayList<>();
		for (ClassItem c : classes) {
			StationEarningDetail d = new StationEarningDetail();
			d.setClasscode(c.code);
			details.add(d);
		}

		header.setDetails(details);
		
		model.addAttribute("earningHeader", header);		
		model.addAttribute("classes", classes);
		
		/* return "originating_period"; */
		return "earning_detail";
	}

	@PostMapping("/getdetails")
	public String getEarningDetails(@RequestParam String mode, @ModelAttribute StationEarningHeader earningHeader, Model model,
			HttpSession session) {

		
		try {


			System.out.println("inside getdetails");
			System.out.println("mode = " +mode);	
			List<ClassItem> classes = getClasses(earningHeader.getHeadofaccounts());


			System.out.println("FY = " + earningHeader.getFinancialyear() + "\n" + "For month ="
					+ earningHeader.getFormonth() + "\n" + "Period = " + earningHeader.getPeriod() + "\n"
					+ "Entry type = " + earningHeader.getEntrytype() + "\n" + "System Type = "
					+ earningHeader.getSystemtype() + "\n" + "Head of Accounts = " + earningHeader.getHeadofaccounts()
					+ "\n" + "Division = " + earningHeader.getDivision() + "\n" + "Station = "
					+ earningHeader.getStationcode() + "\n" + "Value type = " + earningHeader.getValuetype());

			StationEarningHeader dbheader = null;
			if("load".equals(mode)) {
				
				dbheader = earnser.getEarningHeader(earningHeader.getFinancialyear(),
						earningHeader.getFormonth(), earningHeader.getPeriod(), earningHeader.getEntrytype(),
						earningHeader.getSystemtype(), earningHeader.getHeadofaccounts(), earningHeader.getDivision(),
						earningHeader.getStationcode(), earningHeader.getValuetype());
			}
					

			
			Map<String, StationEarningDetail> detailMap = new LinkedHashMap<>();

			// 1️ Create empty rows for ALL classes
			for (ClassItem c : classes) {
			    StationEarningDetail d = new StationEarningDetail();
			    d.setClasscode(c.code);
			    detailMap.put(c.code, d);
			}
			
			
			
			if (dbheader != null) {
				
				System.out.println("Header exist");

				// ✅ Existing data → Load full object with details
				 for (StationEarningDetail dbd : dbheader.getDetails()) {
				        detailMap.put(dbd.getClasscode(), dbd);
				  }
				 
				earningHeader = dbheader;
				
				model.addAttribute("headerId", dbheader.getRecordid());
				System.out.println("headerId = " + dbheader.getRecordid());
				System.out.println("dbheader Detail size = " + dbheader.getDetails().size());
				System.out.println("earningHeader Detail size = " + earningHeader.getDetails().size());

			} else {

				System.out.println("Header doesn't exist");

				/*
				 * List<StationEarningDetail> details = new ArrayList<>(); for (ClassItem c :
				 * classes) { StationEarningDetail d = new StationEarningDetail();
				 * d.setClasscode(c.code); details.add(d); }
				 */

				model.addAttribute("headerId", null);
			}			
			earningHeader.setDetails(new ArrayList<>(detailMap.values()));
			
			calculateAggregateValues(earningHeader);		
			
			model.addAttribute("classes", classes);
			model.addAttribute("earningHeader", earningHeader);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		/* setAllClasses(model); */
		/* return "earning_detail"; */
		
		if(earningHeader.getHeadofaccounts().equalsIgnoreCase("PNSUB")||
				earningHeader.getHeadofaccounts().equalsIgnoreCase("PSUB"))			
			return "fragments/header_footer :: PNSUB-DIV(headerId=${headerId})";
		else if(earningHeader.getHeadofaccounts().equalsIgnoreCase("ORGG"))
			return "fragments/header_footer :: ORGG-DIV(headerId=${headerId})";
		else if(earningHeader.getHeadofaccounts().equalsIgnoreCase("OTHC"))
			return "fragments/header_footer :: OTHC-DIV(headerId=${headerId})";
		else if(earningHeader.getHeadofaccounts().equalsIgnoreCase("OTHG"))
			return "fragments/header_footer :: OTHG-DIV(headerId=${headerId})";
		else if(earningHeader.getHeadofaccounts().equalsIgnoreCase("SUN"))
			return "fragments/header_footer :: SUN-DIV(headerId=${headerId})";
		else
			return "fragments/header_footer :: ERROR-DIV";
		
	}
	
	
	private void calculateAggregateValues(StationEarningHeader earningHeader) {
		
		if(earningHeader.getHeadofaccounts().equalsIgnoreCase("PNSUB") ||
				earningHeader.getHeadofaccounts().equalsIgnoreCase("PSUB")) {
			
			//Calculate aggregate values
			Map<String, StationEarningDetail> map =
				    earningHeader.getDetails().stream()
				        .collect(Collectors.toMap(
				            StationEarningDetail::getClasscode,
				            Function.identity()
				        ));

				sum("AC", map, "EC","1AC","2AC","3AC","3E","CC");
				sum("FC", map, "FCSEA","FCME","FCORD");
				sum("SLC", map, "SLSEA","SLME","SLORD");
				sum("SC", map, "SCSEA","SCME","SCORD");
				sum("TOT", map, "AC","FC","SLC","SC");
		}
		
	}
	
	private void sum(String target, Map<String, StationEarningDetail> map, String... sources) {
	    StationEarningDetail t = map.get(target);
	    if (t == null) return;

	    int bc = 0, rc = 0, ec = 0;
	    BigDecimal ba = BigDecimal.ZERO, ra = BigDecimal.ZERO, ea = BigDecimal.ZERO;

	    for (String s : sources) {
	        StationEarningDetail d = map.get(s);
	        if (d == null) continue;

	        bc += safe(d.getBookedcount());
	        rc += safe(d.getRefundcount());
	        ec += safe(d.getExcesscount());

	        ba = ba.add(safe(d.getBookedamt()));
	        ra = ra.add(safe(d.getRefundamt()));
	        ea = ea.add(safe(d.getExcessamt()));
	    }

	    t.setBookedcount(bc);
	    t.setRefundcount(rc);
	    t.setExcesscount(ec);
	    t.setBookedamt(ba);
	    t.setRefundamt(ra);
	    t.setExcessamt(ea);
	}
	
    // ===============================
    // SAFETY HELPERS
    // ===============================
    private int safe(Integer v) {
        return v == null ? 0 : v;
    }

    private BigDecimal safe(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
	

	/*
	 * @GetMapping("/getdetails") public String getEarningDetails(Model model
	 * ,HttpSession session) {
	 * 
	 * User usr = (User)session.getAttribute("loggedInUser"); int userid = -1;
	 * if(usr != null) userid = usr.getUserid();
	 * 
	 * 
	 * StationEarningHeader header = new StationEarningHeader();
	 * header.addDetail(create("EC", userid)); header.addDetail(create("1AC",
	 * userid)); header.addDetail(create("2AC", userid));
	 * header.addDetail(create("3AC", userid)); header.addDetail(create("3E",
	 * userid)); header.addDetail(create("CC", userid));
	 * header.addDetail(create("FCSEA", userid)); header.addDetail(create("FCME",
	 * userid)); header.addDetail(create("FCORD", userid));
	 * header.addDetail(create("SLSEA", userid)); header.addDetail(create("SLORD",
	 * userid)); header.addDetail(create("SCSEA", userid));
	 * header.addDetail(create("SCME", userid)); header.addDetail(create("SCORD",
	 * userid));
	 * 
	 * model.addAttribute("earningHeader", header);
	 * 
	 * return "originating_period"; }
	 */

	private StationEarningDetail create(String classCode, int userid) {
		StationEarningDetail sed = new StationEarningDetail();
		sed.setClasscode(classCode);
		sed.setBookedcount(0);
		sed.setBookedamt(BigDecimal.ZERO);

		sed.setRefundcount(0);
		sed.setRefundamt(BigDecimal.ZERO);

		sed.setExcesscount(0);
		sed.setExcessamt(BigDecimal.ZERO);

		sed.setUserid(userid);

		return sed;
	}

	@PostMapping("/process")
	public String processEarningDetails(@ModelAttribute StationEarningHeader header, @RequestParam String action,
			Model model) {

		System.out.println("inside processEarningDetails ");
		System.out.println("action = " + action);
		System.out.println("Header id = " + header.getRecordid());
		System.out.println("Detail size = " + header.getDetails().size());

		
		/*
		 * if ("go".equalsIgnoreCase(action)) {
		 * 
		 * return processGo(header, model); }
		 */

		
		  if ("save".equalsIgnoreCase(action)) {
		  
			  return processSave(header, model); 
		  }
		 

		return "redirect:earning_detail";
	}

	/*
	 * private String processGo( StationEarningHeader earningHeader,Model model) {
	 * 
	 * try {
	 * 
	 * System.out.println("inside processGo");
	 * 
	 * System.out.println("FY = " + earningHeader.getFinancialyear()+"\n"+
	 * "For month ="+earningHeader.getFormonth()+"\n"+
	 * "Period = "+earningHeader.getPeriod()+"\n"+
	 * "Entry type = "+earningHeader.getEntrytype()+"\n"+
	 * "System Type = "+earningHeader.getSystemtype()+"\n"+
	 * "Head of Accounts = "+earningHeader.getHeadofaccounts()+"\n"+
	 * "Division = "+earningHeader.getDivision()+"\n"+
	 * "Station = "+earningHeader.getStationcode()+"\n"+
	 * "Value type = "+earningHeader.getValuetype());
	 * 
	 * StationEarningHeader dbheader = earnser.getEarningHeader(
	 * earningHeader.getFinancialyear(), earningHeader.getFormonth(),
	 * earningHeader.getPeriod(), earningHeader.getEntrytype(),
	 * earningHeader.getSystemtype(), earningHeader.getHeadofaccounts(),
	 * earningHeader.getDivision(), earningHeader.getStationcode(),
	 * earningHeader.getValuetype() );
	 * 
	 * if (dbheader !=null) { // ✅ Existing data → Load full object with details
	 * System.out.println("Header exist"); earningHeader = dbheader;
	 * 
	 * 
	 * } else {
	 * 
	 * System.out.println("Header doesn't exist"); // ✅ New header → Prepare empty
	 * detail rows
	 * 
	 * List<StationEarningDetail> details = new ArrayList<>();
	 * 
	 * for (String classCode : classesMap().keySet()) { StationEarningDetail d = new
	 * StationEarningDetail(); d.setClassCode(classCode); details.add(d); }
	 * 
	 * earningHeader.setDetails(details);
	 * 
	 * }
	 * 
	 * model.addAttribute("earningHeader", earningHeader);
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace(); }
	 * 
	 * setAllClasses(model); return "earning_detail"; }
	 */

	private boolean isSaveableClass(String classCode) {

		/* System.out.println("classCode = " +classCode); */
		if (classCode.equalsIgnoreCase("AC") || classCode.equalsIgnoreCase("FC") || classCode.equalsIgnoreCase("SLC")
			|| classCode.equalsIgnoreCase("SC") || classCode.equalsIgnoreCase("TOT") || classCode.equalsIgnoreCase("OTHC_TOT")
			|| classCode.equalsIgnoreCase("OTHG_TOT") || classCode.equalsIgnoreCase("SUN_TOT") || classCode.equalsIgnoreCase("ORGG_TOT")) {

			/* System.out.println("saveable  = false"); */
			return false;
		} else {

			/* System.out.println("saveable  = true"); */
			return true;
		}
	}

	private String processSave(StationEarningHeader earningHeader, Model model) {

		System.out.println("FY = " + earningHeader.getFinancialyear() + "\n" + "For month ="
				+ earningHeader.getFormonth() + "\n" + "Period = " + earningHeader.getPeriod() + "\n" + "Entry type = "
				+ earningHeader.getEntrytype() + "\n" + "System Type = " + earningHeader.getSystemtype() + "\n"
				+ "Head of Accounts = " + earningHeader.getHeadofaccounts() + "\n" + "Division = "
				+ earningHeader.getDivision() + "\n" + "Station = " + earningHeader.getStationcode() + "\n"
				+ "Value type = " + earningHeader.getValuetype());

		try {

			boolean isNew = (earningHeader.getRecordid() == null);
			

			if (!isNew) {

				System.out.println("Old object so update");
				StationEarningHeader dbHeader = earnRepo.findById(earningHeader.getRecordid()).orElse(null);
				if (dbHeader != null) {

					dbHeader.getDetails().clear();

					for (StationEarningDetail d : earningHeader.getDetails()) {

						System.out.println("d recordid = " + d.getRecordid());

						
						  if(!isSaveableClass(d.getClasscode())) 
							  continue;
						 
						d.setBookedcount(d.getBookedcount() == null ? 0 : d.getBookedcount());
						d.setRefundcount(d.getRefundcount() == null ? 0 : d.getRefundcount());
						d.setExcesscount(d.getExcesscount() == null ? 0 : d.getExcesscount());

						d.setBookedamt(d.getBookedamt() == null ? BigDecimal.ZERO : d.getBookedamt());
						d.setRefundamt(d.getRefundamt() == null ? BigDecimal.ZERO : d.getRefundamt());
						d.setExcessamt(d.getExcessamt() == null ? BigDecimal.ZERO : d.getExcessamt());
						
						
						d.setPmwagon(d.getPmwagon() == null?BigDecimal.ZERO : d.getPmwagon());
						d.setPmtonne(d.getPmtonne()==null?BigDecimal.ZERO :d.getPmtonne());
						d.setTpwagon(d.getTpwagon()==null?BigDecimal.ZERO :d.getTpwagon());
						d.setWagon(d.getWagon()==null?BigDecimal.ZERO :d.getWagon());
						d.setTonne(d.getTonne()==null?BigDecimal.ZERO :d.getTonne());
						d.setRate(d.getRate()==null?BigDecimal.ZERO :d.getRate());
						d.setAmount(d.getAmount()==null?BigDecimal.ZERO :d.getAmount());
						d.setWeight(d.getWeight()==null?BigDecimal.ZERO :d.getWeight());
						d.setEarning(d.getEarning()==null?BigDecimal.ZERO :d.getEarning());
						
						

						d.setHeader(dbHeader);
						dbHeader.getDetails().add(d);
					}

					earnser.saveEarningHeader(dbHeader);
				}

			} else {

				System.out.println("New object so insert");

				 List<StationEarningDetail> cleaned = new ArrayList<>(); 

				for (StationEarningDetail d : earningHeader.getDetails()) {

					d.setBookedcount(d.getBookedcount() == null ? 0 : d.getBookedcount());
					d.setRefundcount(d.getRefundcount() == null ? 0 : d.getRefundcount());
					d.setExcesscount(d.getExcesscount() == null ? 0 : d.getExcesscount());

					d.setBookedamt(d.getBookedamt() == null ? BigDecimal.ZERO : d.getBookedamt());
					d.setRefundamt(d.getRefundamt() == null ? BigDecimal.ZERO : d.getRefundamt());
					d.setExcessamt(d.getExcessamt() == null ? BigDecimal.ZERO : d.getExcessamt());
					
					d.setPmwagon(d.getPmwagon() == null ? BigDecimal.ZERO : d.getPmwagon());
					d.setPmtonne(d.getPmtonne() == null ? BigDecimal.ZERO :d.getPmtonne());
					d.setTpwagon(d.getTpwagon() == null ? BigDecimal.ZERO :d.getTpwagon());
					d.setWagon(d.getWagon() == null ? BigDecimal.ZERO :d.getWagon());
					d.setTonne(d.getTonne() == null ? BigDecimal.ZERO :d.getTonne());
					d.setRate(d.getRate() == null ? BigDecimal.ZERO :d.getRate());
					d.setAmount(d.getAmount() == null ? BigDecimal.ZERO :d.getAmount());
					d.setWeight(d.getWeight() ==null ? BigDecimal.ZERO :d.getWeight());
					d.setEarning(d.getEarning() ==null ? BigDecimal.ZERO :d.getEarning());

					d.setHeader(earningHeader);

					  if(isSaveableClass(d.getClasscode())) { 
						  cleaned.add(d); 
					  }
					 
				}

				 earningHeader.setDetails(cleaned); 
				earnser.saveEarningHeader(earningHeader);
				/* earnser.save(earningHeader); */
			}

			// ✅ Reload blank page after save

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return "redirect:earning_detail";

	}

	@PostMapping("/saveEarningDetail")
	public String saveEarningDetails(@ModelAttribute StationEarningHeader header) {

		System.out.println(header.getDetails().size());

		/*
		 * for (StationEarningDetail eranDetail: header.getDetails()) {
		 * eranDetail.setHeader(header); }
		 */

		/* earnRepo.save(header); */

		return "redirect:/earning_detail";
	}

	
	
	public List<ClassItem> getClasses(String hoa) {

		List<ClassItem> classes = new ArrayList<>();
		if(hoa.equalsIgnoreCase("PNSUB") || hoa.equalsIgnoreCase("PSUB")) {
			
			classes.add(new ClassItem("EC", "Executive Class", true));
			classes.add(new ClassItem("1AC", "1st A/C", true));
			classes.add(new ClassItem("2AC", "2nd A/C", true));
			classes.add(new ClassItem("3AC", "3rd A/C", true));
			classes.add(new ClassItem("3E", "3E A/C", true));
			classes.add(new ClassItem("CC", "Chair Car", true));
			classes.add(new ClassItem("AC", "AIR CONDITION", false));
			classes.add(new ClassItem("FCSEA", "Season", true));
			classes.add(new ClassItem("FCME", "Mail & Express", true));
			classes.add(new ClassItem("FCORD", "Ordinary", true));
			classes.add(new ClassItem("FC", "FIRST CLASS", false));
			classes.add(new ClassItem("SLSEA", "Season", true));
			classes.add(new ClassItem("SLME", "Mail & Express", true));
			classes.add(new ClassItem("SLORD", "Ordinary", true));
			classes.add(new ClassItem("SLC", "SLEEPER CLASS", false));
			classes.add(new ClassItem("SCSEA", "Season", true));
			classes.add(new ClassItem("SCME", "Mail & Express", true));
			classes.add(new ClassItem("SCORD", "Ordinary", true));
			classes.add(new ClassItem("SC", "SECOND CLASS", false));
			classes.add(new ClassItem("TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("OTHC")) {
			
			// Other Coaching
			classes.add(new ClassItem("OTHC_LIMB", "Luggage including military baggage", true));
			classes.add(new ClassItem("OTHC_CHDMT", "Carriage, Horses and Dogs & Motor traffic", true));
			classes.add(new ClassItem("OTHC_PCFIT", "Penalties collected for irregular travelling", true));
			classes.add(new ClassItem("OTHC_PTPT", "Parcel to-pay traffic", true));
			classes.add(new ClassItem("OTHC_PPT", "Parcel paid traffic", true));
			classes.add(new ClassItem("OTHC_STARC", "Special trains & reserved carriages", true));
			classes.add(new ClassItem("OTHC_LLDWSCP", "Left luggage, demurrage,wharfage, storeage collected parcels", true));
			classes.add(new ClassItem("OTHC_MOCE", "Misc. other coach earnings", true));
			classes.add(new ClassItem("OTHC_UDBS", "Undercharges detected by the station at the time of delivery of parcels etc.", true));
			classes.add(new ClassItem("OTHC_OABS","Overcharges allowed by the station at the time of deleivery of parcels etc.(Less)", true));
			classes.add(new ClassItem("OTHC_TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("OTHG")) {
			
			// Other Goods 
			classes.add(new ClassItem("OTHG_DWSC", "Demurrage,Wharfage & Storages Collected", true));
			classes.add(new ClassItem("OTHG_OMGE", "Other Miscellaneous Goods Earnings", true));
			classes.add(new ClassItem("OTHG_UDBS", "Undercharges Detected by the station at the time of Delivery", true));
			classes.add(new ClassItem("OTHG_OABS", "Overcharges allowed by the station at the time of deleivery", true));
			classes.add(new ClassItem("OTHG_TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("SUN")) {
			
			// Other Goods 
			classes.add(new ClassItem("SUN_TE", "Telegraph Earning", true));
			classes.add(new ClassItem("SUN_SEBBO", "All Other Sundry Earnings Collected in Booking Office", true));
			classes.add(new ClassItem("SUN_SEBGO", "All Other Sundry Earnings Collected in Goods Office", true));
			classes.add(new ClassItem("SUN_TOT", "TOTAL", false));
			
		}else if (hoa.equalsIgnoreCase("ORGG")) {
			
			// Originating Goods 
			classes.add(new ClassItem("ORGG_OG", "Originating Goods", true));
			classes.add(new ClassItem("ORGG_TOT", "TOTAL", false));	
			
		}
		
		return classes;
	}

}
