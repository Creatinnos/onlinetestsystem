package com.creatinnos.onlinetestsystem.daocustomization;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.creatinnos.onlinetestsystem.bo.Question;
import com.creatinnos.onlinetestsystem.utils.Utilities;

public class BusinessObject implements InvocationHandler,Cloneable {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BusinessObject.class.getName());

	private static JdbcTemplate con = null;

	private BusinessObject(Class<?> intefaceClass) {
		con = CreateConnection.getConnection();
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = proxy;
		String methodName = method.getName();
		if (methodName.startsWith("get")) {
			String name = methodName.substring(methodName.indexOf("get") + 3);
			Field nameField = null;
			try {
				nameField = result.getClass().getInterfaces()[0].getField(name.toUpperCase());
				Column column = nameField.getAnnotation(Column.class);
				switch (column.columnType()) {
				case BIGINT:
					return nameField.get(this);
				case INT:
					return nameField.get(this);
				case STRING:
					return nameField.get(this);
				case LIST:
					String s1=(String)nameField.get(this);
					String replace = s1.replace("[","").replace("]","");
					List<String> myList = new ArrayList<String>(Arrays.asList(replace.split(",")));

					return myList;
				case ENUM:
					if(nameField.get(this)!=null  && !nameField.get(this).equals("choiceType"))
					{
						return Enum.valueOf((Class<? extends Enum>)Class.forName(column.enumValue().getName()),(String) nameField.get(this));
					}
				}
			} catch (NoSuchFieldException e) {
				log.error(e.getLocalizedMessage());
			} catch (SecurityException e) {
				log.error(e.getLocalizedMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return nameField.get(this);
		} else if (methodName.startsWith("set")) {
			String name = methodName.substring(methodName.indexOf("set") + 3);
			try {
				Field nameField = result.getClass().getField(name.toUpperCase());
				nameField.setAccessible(true);
				int modifiers = nameField.getModifiers();
				Field modifierField = nameField.getClass().getDeclaredField("modifiers");
				modifiers = modifiers & ~Modifier.FINAL;
				modifierField.setAccessible(true);
				modifierField.setInt(nameField, modifiers);
				Column column = nameField.getAnnotation(Column.class);
				switch (column.columnType()) {
				case BIGINT:
					nameField.set(result, args[0]);
					break;
				case INT:
					nameField.set(result, args[0]);
					break;
				case STRING:
					nameField.set(result, args[0]);
					break;
				case LIST:
					nameField.set(result, args[0].toString());
					break;
				case ENUM:
					Enum st=Enum.valueOf((Class<? extends Enum>)Class.forName(column.enumValue().getName()),args[0].toString());
					nameField.set(result, st.name());
					//nameField.set(intefaceClass,ChoiceType.CHECKBOX.toString());
					break;
				}
				modifierField = nameField.getClass().getDeclaredField("modifiers");
				modifierField.setAccessible(true);
				modifierField.setInt(nameField, Modifier.SYNCHRONIZED);
				return nameField;
			} catch (NoSuchFieldException e) {
				log.error(e.getLocalizedMessage());
			} catch (SecurityException e) {
				log.error(e.getLocalizedMessage());
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
			}
			return result;
		} else if (methodName.startsWith("is")) {
			String name = methodName.substring(methodName.indexOf("is") + 2);
			return name;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T create(Class<T> boInterfaceClass) {
		BusinessObject handler = new BusinessObject(boInterfaceClass.getClass());
		return (T) Proxy.newProxyInstance(boInterfaceClass.getClassLoader(), new Class[] { boInterfaceClass }, handler);
	}

	@SuppressWarnings("unchecked")
	public <T> T createClone(Class<T> boInterfaceClass) {
		return (T) Proxy.newProxyInstance(boInterfaceClass.getClassLoader(), new Class[] { boInterfaceClass }, new BusinessObject(boInterfaceClass));
	}


	public static void save(Object object) {
		insertQuery(object);
	}

	public static void saveOrUpdate(Object object) {
		updateQuery(object);
	}

	private static String insertQuery(Object question) {
		Class<?> class1 = question.getClass();
		Field[] fields = class1.getFields();
		String insertingFields = "", insertingFieldsValues = "", tableName = "";
		Class<?> c = question.getClass().getInterfaces()[0];
		Table table = c.getAnnotation(Table.class);
		tableName = table.tableName();
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				try {
					if (field != null && field.getName() != null && field.get(question) != null) {
						String fieldValue = (String) field.get(question);
						if (!fieldValue.toLowerCase().equals(field.getName().toLowerCase())) {
							Column column = field.getAnnotation(Column.class);
							insertingFields = insertingFields + column.columnName() + ",";

							switch (column.columnType()) {
							case BIGINT:
								insertingFieldsValues = insertingFieldsValues + fieldValue + ",";
								break;
							case INT:
								insertingFieldsValues = insertingFieldsValues + fieldValue + ",";
								break;
							case STRING:
								insertingFieldsValues = insertingFieldsValues + "'" + fieldValue + "',";
								break;
							case ENUM:
								insertingFieldsValues = insertingFieldsValues + "'" + fieldValue + "',";
								break;
							case LIST:
								insertingFieldsValues = insertingFieldsValues + "'" + fieldValue + "',";
								break;
							default:
								break;
							}
						}
					}
				} catch (IllegalArgumentException e) {
					log.error(e.getLocalizedMessage());
				} catch (IllegalAccessException e) {
					log.error(e.getLocalizedMessage());
				}
			}
			System.out.println(insertingFields);
			System.out.println(insertingFieldsValues);
			String query = "INSERT INTO " + tableName.toUpperCase() + " ("
					+ insertingFields.substring(0, insertingFields.length() - 1).toUpperCase() + ") VALUES ("
					+ insertingFieldsValues.substring(0, insertingFieldsValues.length() - 1) + ")";
			if (log.isDebugEnabled()) {
				log.debug(query);
			}
			executeInsert(query);
		}
		return "";
	}

	private static String updateQuery(Object question) {
		Class<?> class1 = question.getClass();
		Field[] fields = class1.getFields();
		String update = "";
		String tableName = "";
		Class<?> c = question.getClass().getInterfaces()[0];
		Table table = c.getAnnotation(Table.class);
		tableName = table.tableName();
		String primaryKeyField = "";
		String primaryKeyValue = "";
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				try {
					if (field != null && field.getName() != null && field.get(question) != null) {
						String fieldValue = (String) field.get(question);
						if (!fieldValue.toLowerCase().equals(field.getName().toLowerCase())) {
							Column column = field.getAnnotation(Column.class);
							if (column.isPrimaryKey()) {
								primaryKeyField = column.columnName();
								primaryKeyValue = fieldValue;
							} else {
								switch (column.columnType()) {
								case BIGINT:
									update = update + column.columnName() + "=" + fieldValue + ",";
									break;
								case INT:
									update = update + column.columnName() + "=" + fieldValue + ",";
									break;
								case STRING:
									update = update + column.columnName() + "='" + fieldValue + "',";
									break;
								}
							}
						}
					}
				} catch (IllegalArgumentException e) {
					log.error(e.getLocalizedMessage());
				} catch (IllegalAccessException e) {
					log.error(e.getLocalizedMessage());
				}
			}
			String query = "UPDATE TABLE " + tableName.toUpperCase() + " SET "
					+ update.substring(0, update.length() - 1) + " WHERE " + primaryKeyField + " = " + primaryKeyValue;
			if (log.isDebugEnabled()) {
				log.debug(query);
			}

			if (primaryKeyValue != null && !primaryKeyValue.equals("")) {
				executeUpdate(query);
			} else {
				insertQuery(question);
			}

		}
		return "";
	}

	private static String deleteQuery(Object question) {
		Class<?> class1 = question.getClass();
		Field[] fields = class1.getFields();
		String tableName = "";
		Class<?> c = question.getClass().getInterfaces()[0];
		Table table = c.getAnnotation(Table.class);
		tableName = table.tableName();
		String primaryKeyField = "";
		String primaryKeyValue = "";
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				try {
					if (field != null && field.getName() != null && field.get(question) != null) {
						String fieldValue = (String) field.get(question);
						if (!fieldValue.toLowerCase().equals(field.getName().toLowerCase())) {
							Column column = field.getAnnotation(Column.class);
							if (column.isPrimaryKey()) {
								primaryKeyField = column.columnName();
								primaryKeyValue = fieldValue;
								break;
							}
						}
					}
				} catch (IllegalArgumentException e) {
					log.error(e.getLocalizedMessage());
				} catch (IllegalAccessException e) {
					log.error(e.getLocalizedMessage());
				}
			}
			String query = "DELET FROM " + tableName.toUpperCase() + " WHERE " + primaryKeyField + " = "
					+ primaryKeyValue;
			if (log.isDebugEnabled()) {
				log.debug(query);
			}
			executeDelete(query);
		}
		return "";
	}

	private static <T> List<String> fetchQuery(Class<T> class1) {
		if(con==null)
		{
			con = CreateConnection.getConnection();
		}
		//Class<?> class1 = object.getClass();
		Field[] fields = class1.getFields();
		String insertingFields = "", insertingFieldsValues = "", tableName = "";
		Table table = class1.getAnnotation(Table.class);
		if(table == null)
		{
			try {
				throw new Exception(""+class1+" Doest have table annoutation");
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
				return null;
			}
		}

		tableName = table.tableName();
		if (fields != null) {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				try {
					if (field != null && field.getName() != null && field.get(class1) != null) {
						String fieldValue = (String) field.get(class1);
						if (!fieldValue.toLowerCase().equals(field.getName().toLowerCase())) {
							Column column = field.getAnnotation(Column.class);
							insertingFields = insertingFields + column.columnName() + ",";

							switch (column.columnType()) {
							case BIGINT:
								insertingFieldsValues = insertingFieldsValues + fieldValue + ",";
								break;
							case INT:
								insertingFieldsValues = insertingFieldsValues + fieldValue + ",";
								break;
							case STRING:
								insertingFieldsValues = insertingFieldsValues + "'" + fieldValue + "',";
								break;
							case ENUM:
								insertingFieldsValues = insertingFieldsValues + "'" + fieldValue + "',";
								break;
							case LIST:
								insertingFieldsValues = insertingFieldsValues + "'" + fieldValue + "',";
								break;
							default:
								break;
							}
						}
					}
				} catch (IllegalArgumentException e) {
					log.error(e.getLocalizedMessage());
				} catch (IllegalAccessException e) {
					log.error(e.getLocalizedMessage());
				}
			}
			System.out.println(insertingFields);
			System.out.println(insertingFieldsValues);
			String query = "SELECT * FROM " + tableName.toUpperCase();
			if (log.isDebugEnabled()) {
				log.debug(query);
			}
			return executeFetch(class1,query);
		}
		return null;//(new  HashMap<String, T>());
	}

	private static <T> List<String> fetchQuery(Class<T> class1,HashMap<String, Object> map) {
		if(con==null)
		{
			con = CreateConnection.getConnection();
		}
		Table table = class1.getAnnotation(Table.class);
		if(table == null)
		{
			try {
				throw new Exception(""+class1+" Doest have table annoutation");
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
				return null;
			}
		}
		String query ="";
		String where="";
		if(map!=null && map.size()>0)
		{
			where = " where ";
			for(String keys:map.keySet())
			{	
				where = where+keys+"='"+map.get(keys)+"' and ";	
			}
			where =where.trim();
			where =where.substring(0, where.length()-3);
		}

		if(where.equals(""))
		{
			query = "SELECT * FROM " + table.tableName().toUpperCase();	
		}
		else
		{
			query = "SELECT * FROM " + table.tableName().toUpperCase() +" "+where ;
		}




		if (log.isDebugEnabled()) {
			log.debug(query);
		}
		return executeFetch(class1,query);
	}

	private static int executeInsert(String query) {
		int result = 0;
		try {
			con.update(query);
		} catch (Exception exception) {
			log.error(exception);
		}
		return result;
	}

	private static int executeUpdate(String query) {
		int result = 0;
		try {
			con.update(query);
		} catch (Exception exception) {
			log.error(exception);
		}
		return result;
	}

	private static int executeDelete(String query) {
		int result = 0;
		try {
			con.update(query);
		} catch (Exception exception) {
			log.error(exception);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	private static <T> List<String> executeFetch(Class<T> classs,String query ) {
		try {
			List<Map<String, Object>> maps=con.queryForList(query);
			return op(classs,maps);
		} catch (Exception exception) {
			log.error(exception);
		}
		return null;//(new HashMap<String, T>());
	}


	public static <T> String op2(T object) throws ClassNotFoundException
	{
		HashMap<String, String> methodNames=new HashMap<>();
		Method methods[]=object.getClass().getInterfaces()[0].getMethods();
		int i=0;
		for(Method  method:methods )
		{
			if(method.getName().startsWith("get")){
				methodNames.put(i+"",method.getName().substring(3));
				i++;
			}
		}
		String str="{";
		try
		{
			for(i=0;i<methodNames.size();i++)
			{			
				Object temp=InvokeSetterGetter.invokeGetter(object, methodNames.get(i+""));
				if(methodNames.get(i+"").equalsIgnoreCase("choice"))
				{
					str=str+"\""+methodNames.get(i+"")+"\" : "+temp+",";
				}
				else
				{	
					str=str+"\""+methodNames.get(i+"")+"\" : \""+temp+"\",";
				}
			}
			//Utilities.JSONtoObject(object.getClass().getInterfaces()[0], str, true);
			str=str.substring(0, str.length()-1)+"}";
			System.out.println(str);
		}
		catch(Exception e)
		{
			//log.error(e);
		}

		return str;
	}

	public static <T> List<String>  op(Class<T> classs,List<Map<String,Object>> maps) throws ClassNotFoundException
	{
		List<String> list=new ArrayList<>();

		Method[] methods=classs.getMethods();
		HashMap<String, String> methodNames=new HashMap<>();
		for(Method method:methods)
		{
			methodNames.put(method.getName().substring(3).toUpperCase(),method.getName().substring(3));
		}


		for(int i=0;i<maps.size();i++)
		{			

			@SuppressWarnings("unchecked")
			T t=(T) Proxy.newProxyInstance(classs.getClassLoader(), new Class[] { classs }, new BusinessObject(classs));
			for(String st:maps.get(i).keySet())
			{
				try
				{
					Field nameField = t.getClass().getField(st);
					nameField.setAccessible(true);
					int modifiers = nameField.getModifiers();
					Field modifierField = nameField.getClass().getDeclaredField("modifiers");
					modifiers = modifiers & ~Modifier.FINAL;
					modifierField.setAccessible(true);
					modifierField.setInt(nameField, modifiers);
					nameField.set(t, maps.get(i).get(st)+"");
				}
				catch(Exception e)
				{
					//log.error(e);
				}
			}
			;
			list.add(op2(t));;
		}
		System.out.println(list);
		return list;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> find(Class<T> class1,HashMap<String, Object> map) {
		return (List<T>) fetchQuery(class1,map);
	}

	@SuppressWarnings("unchecked")
	public static <T> List <String> findAll(Class<T> class1) {
		return  fetchQuery(class1);
	}

	public static void main(String[] args) {
		ArrayList<Question>  list=new ArrayList<>();

		Question question=(Question) Proxy.newProxyInstance(Question.class.getClassLoader(), new Class[] { Question.class }, new BusinessObject(null));
		question.setAnswer("dfsfdfgdf");
		//list.add(question);

		Question question1=(Question) Proxy.newProxyInstance(Question.class.getClassLoader(), new Class[] { Question.class }, new BusinessObject(null));
		question1.setAnswer("dfsdf");

		System.out.println(""+(question == question1)+"");
		System.out.println(question.getAnswer());
		System.out.println(question1.getAnswer());
	}

}
