package com.creatinnos.onlinetestsystem.daocustomization;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.creatinnos.onlinetestsystem.bo.Question;
import com.creatinnos.onlinetestsystem.model.ChoiceType;

public class BusinessObject implements InvocationHandler {
	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(BusinessObject.class.getName());

	private Object intefaceClass;
	private static JdbcTemplate con = null;

	private BusinessObject(Class<?> intefaceClass) {
		this.intefaceClass = intefaceClass;
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
				nameField = result.getClass().getField(name.toUpperCase());
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
						System.out.println(column.enumValue().getName());
						System.out.println(nameField.get(this)+"ss");
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
					nameField.set(intefaceClass, args[0]);
					break;
				case INT:
					nameField.set(intefaceClass, args[0]);
					break;
				case STRING:
					nameField.set(intefaceClass, args[0]);
					break;
				case LIST:
					nameField.set(intefaceClass, args[0].toString());
					break;
				case ENUM:
					Enum st=Enum.valueOf((Class<? extends Enum>)Class.forName(column.enumValue().getName()),args[0].toString());
					nameField.set(intefaceClass, st.name());
					//nameField.set(intefaceClass,ChoiceType.CHECKBOX.toString());
					break;
				}
				return nameField;
			} catch (NoSuchFieldException e) {
				log.error(e.getLocalizedMessage());
			} catch (SecurityException e) {
				log.error(e.getLocalizedMessage());
			} catch (Exception e) {
				log.error(e.getLocalizedMessage());
			}
			return intefaceClass;
		} else if (methodName.startsWith("is")) {
			String name = methodName.substring(methodName.indexOf("is") + 2);
			return name;
		}
		return intefaceClass;
	}

	@SuppressWarnings("unchecked")
	public static <T> T create(Class<T> boInterfaceClass) {
		BusinessObject handler = new BusinessObject(boInterfaceClass.getClass());
		return (T) Proxy.newProxyInstance(boInterfaceClass.getClassLoader(), new Class[] { boInterfaceClass }, handler);
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

	private static <T> List<Map<String, T>> fetchQuery(Class<T> class1) {
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
			return executeFetch(query);
		}
		return (new  ArrayList<Map<String, T>>());
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
	private static <T> List<T> executeFetch(String query ) {
		try {
			return (List<T>) con.queryForList(query);
		} catch (Exception exception) {
			log.error(exception);
		}
		return (new ArrayList<T>());
	}


	public static void main(String[] args) throws Exception {
		fetchQuery(Question.class);

	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> findAll(Class<T> class1) {
		return (List<T>) fetchQuery(class1);
	}

}
