package com.creatinnos.onlinetestsystem.DAOImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.creatinnos.onlinetestsystem.Constant.QueryConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.model.ExamInfo;
import com.creatinnos.onlinetestsystem.model.ExamInfos;
import com.creatinnos.onlinetestsystem.utils.DateUtil;

public class ExamInfoDAO {

	static Logger log = Logger.getLogger(ExamInfoDAO.class.getName());
	
	public ExamInfos findExamInfos()
	{
		return executeFetch(QueryConstants.FETCH_ALL_EXAMINFO);
		
	}
	
	

	private ExamInfos executeFetch(String query) {
		ExamInfos  examInfos=new ExamInfos();
		try {
			List<Map<String, Object>> maps=CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if(maps!=null && maps.size()>0)
			{
				for(int i=0;i<maps.size();i++)
				{
					Map<String, Object> map=maps.get(i);
					ExamInfo examInfo=new ExamInfo();
					for(String st:map.keySet())
					{
						switch(st)
						{
						case "EXAMID":
							examInfo.setExamInfoId((Long) map.get(st));
							break;
						case "EXAMNAME":
							examInfo.setExamName(""+map.get(st));
							break;
						case "EXAMSTARTDATE":
							examInfo.setExamStartDate(""+map.get(st));
							break;
						case "EXAMENDDATE":
							examInfo.setExamEndDate(""+map.get(st));
							break;
						case "EXAMDURATION":
							examInfo.setExamDuration(""+map.get(st));
							break;
						case "PROGRESS":
							examInfo.setProgress((Integer) map.get(st));
							break;
						}
					}
					
					examInfos.addExamInfos(examInfo);
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return examInfos;
	}
	public ExamInfos findExamInforDummy()
	{
		
		ExamInfos examInfos=new ExamInfos();
		ExamInfo examInfo = new ExamInfo();
		examInfo.setExamInfoId(1L);
		examInfo.setExamName("Exam Name1");
		examInfo.setExamStartDate(DateUtil.getDate(new Date()));
		examInfo.setExamEndDate(DateUtil.getDate(new Date()));
		examInfo.setProgress(50);
		examInfo.setExamDuration("1 hour 50 mins");
		examInfos.addExamInfos(examInfo );
		
		examInfo = new ExamInfo();
		examInfo.setExamInfoId(2L);
		examInfo.setExamName("Exam Name2");
		examInfo.setExamStartDate(DateUtil.getDate(new Date()));
		examInfo.setExamEndDate(DateUtil.getDate(new Date()));
		examInfo.setProgress(20);
		examInfo.setExamDuration("2 hour");
		examInfos.addExamInfos(examInfo );
		
		examInfo = new ExamInfo();
		examInfo.setExamInfoId(3L);
		examInfo.setExamName("Exam Name3");
		examInfo.setExamStartDate(DateUtil.getDate(new Date()));
		examInfo.setExamEndDate(DateUtil.getDate(new Date()));
		examInfo.setProgress(90);
		examInfo.setExamDuration("1 hour 10 mins");
		examInfos.addExamInfos(examInfo );
		
		return examInfos;
	}
}
