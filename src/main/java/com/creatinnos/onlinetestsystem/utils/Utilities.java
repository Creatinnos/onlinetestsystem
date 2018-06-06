package com.creatinnos.onlinetestsystem.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {

	public static <T> void JSONtoObject(Class<T> data,String json,boolean prettyString) {

		ObjectMapper mapper = new ObjectMapper();
		try {
			T map = mapper.readValue(json, data);
			System.out.println(map);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void objectToJSON(List<?> data,boolean prettyString) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString ="";
			if(!prettyString)
			{
				jsonInString = mapper.writeValueAsString(data);
				System.out.println(jsonInString);
			}
			else
			{
				jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
				System.out.println(jsonInString);
			}
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getCurrentDateString()
	{
		Calendar calendar= Calendar.getInstance();
		return calendar.getTime().toString();
	}
	
	public static List<HashMap<String,Object>> getListOfHashMap(List<Map<String,Object>> list)
	{
		List<HashMap<String,Object>> hashMaps=new ArrayList<HashMap<String,Object>>();
		if(list != null)
		{
			for(int i=0;i<list.size();i++)
			{
				Map<String, Object> map=list.get(i);
				HashMap<String,Object> hashMap=new HashMap<>();
				for(String str:map.keySet())
				{
					hashMap.put(str, map.get(str));
				}
				hashMaps.add(hashMap);
			}
		}
		return hashMaps;
	}
}



