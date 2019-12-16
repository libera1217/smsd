package spring.sts.smsd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.model.festival.FestivalDTO;
import spring.model.mapper.FestivalMapper;
import spring.utility.smsd.Utility;

@Controller
public class FestivalController {

	@Autowired
	private FestivalMapper mapper;
	
	@GetMapping("/festival/read")
	public String read(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		List<FestivalDTO> imageList = mapper.readFestival(id);
		FestivalDTO read = imageList.get(0);
		
		request.setAttribute("list", imageList);
		request.setAttribute("read", read);
		return "/festival/read";
	}
	
	@GetMapping("/festival/list")
	public String list(HttpServletRequest request) {
		
		String season = request.getParameter("season");
		String price = request.getParameter("price");
		String search_sdate = request.getParameter("sdate");
		String search_edate = request.getParameter("edate");
		String area = request.getParameter("area");
		boolean isSearch = false;
		
		Map searchMap = new HashMap();
		List<FestivalDTO> searchList = new ArrayList();
		if(price != null) {
			search_sdate = Utility.formatDate(search_sdate);
			search_edate = Utility.formatDate(search_edate);
			searchMap.put("price", price);
			searchMap.put("sdate", search_sdate);
			searchMap.put("edate", search_edate);
			searchMap.put("area", area);
			searchList = mapper.searchFestivalList(searchMap);
			isSearch = true;
		}
		
		int nMonth;
		int nYear;
		
		String sdate = "";
		String edate = "";
		
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		
		nYear = calendar.get(Calendar.YEAR);
		nMonth = calendar.get(Calendar.MONTH) + 1;
		
		nYear = Integer.parseInt(String.valueOf(nYear).substring(2));
		if(season == null || season.equals("")) {
			switch(nMonth) {
			case 1:case 2:case 11:case 12:
				season = "winter";
				break;
			case 3:case 4:case 5:
				season = "spring";
				break;
			case 6:case 7:case 8:
				season = "summer";
				break;
			case 9:case 10:
				season = "autumn";
				break;
			}
		}
		
		switch(season) {
		case "spring":
			sdate = nYear+"0301";
			edate = nYear+"0531";
			break;
		case "summer":
			sdate = nYear+"0601";
			edate = nYear+"0831";
			break;
		case "autumn":
			sdate = nYear+"0901";
			edate = nYear+"1031";
			break;
		case "winter":
			sdate = nYear+"1101";
			edate = ++nYear+"0228";
			break;
		}
		
		
		Map map = new HashMap();
		map.put("season", season);
		map.put("sdate", sdate);
		map.put("edate", edate);
		List<FestivalDTO> topList = mapper.topFestivalList(map);
		List<FestivalDTO> recentList = mapper.recentFestivalList(map);

		Iterator<FestivalDTO> topIter = topList.iterator();
		
		request.setAttribute("best", topIter.next());
		request.setAttribute("isSearch", isSearch);
		request.setAttribute("topList", topList);
		request.setAttribute("recentList", recentList);
		request.setAttribute("searchList", searchList);
		
		
		return "/festival/list";
	}
}