package com.creatinnos.onlinetestsystem.DAOImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.creatinnos.onlinetestsystem.Constant.QueryConstants;
import com.creatinnos.onlinetestsystem.daocustomization.CreateConnection;
import com.creatinnos.onlinetestsystem.model.Event;
import com.creatinnos.onlinetestsystem.model.Events;
import com.creatinnos.onlinetestsystem.utils.DateUtil;

public class EventsDao {

	static Logger log = Logger.getLogger(EventsDao.class.getName());

	public Events findEvents() {
		return executeFetch(QueryConstants.FETCH_ALL_EVENTINFO);

	}

	private Events executeFetch(String query) {
		Events events = new Events();
		try {
			List<Map<String, Object>> maps = CreateConnection.getConnection().queryForList(query);
			System.out.println(maps);
			if (maps != null && maps.size() > 0) {
				for (int i = 0; i < maps.size(); i++) {
					Map<String, Object> map = maps.get(i);
					Event event = new Event();
					for (String st : map.keySet()) {
						switch (st) {
						case "EVENTSINFO":
							event.setEvent("" + map.get(st));
							break;
						case "USERNAME":
							event.setPostedBy("" + map.get(st));
							break;
						case "CREATEDDATE":
							event.setPostedOnDate("" + map.get(st));
							break;

						}
					}

					events.addEvents(event);
				}
			}
		} catch (Exception exception) {
			log.error(exception);
		}
		return events;
	}

	public Events findEventsDummy() {
		Events events = new Events();
		Event event = new Event();
		event.setEvent("event1");
		event.setPostedBy("postedBy1");
		event.setPostedOnDate(DateUtil.getDate(new Date()));
		events.addEvents(event);

		event = new Event();
		event.setEvent("event2");
		event.setPostedBy("postedBy2");
		event.setPostedOnDate(DateUtil.getDate(new Date()));
		events.addEvents(event);

		event = new Event();
		event.setEvent("event3");
		event.setPostedBy("postedBy3");
		event.setPostedOnDate(DateUtil.getDate(new Date()));
		events.addEvents(event);
		return events;
	}
}
