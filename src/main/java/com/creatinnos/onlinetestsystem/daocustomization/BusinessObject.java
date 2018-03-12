package com.creatinnos.onlinetestsystem.daocustomization;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import com.creatinnos.onlinetestsystem.model.Question;

public class BusinessObject implements InvocationHandler {

	public Object intefaceClass;

	private BusinessObject(Class<?> intefaceClass) {

		this.intefaceClass = intefaceClass;
	}

	private BusinessObject() {

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
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return intefaceClass;// nameField.get(result);
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
				nameField.set(intefaceClass, args[0]);
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return intefaceClass;
		} else if (methodName.startsWith("is")) {
			String name = methodName.substring(methodName.indexOf("is") + 2);
			System.out.println(name);
			return intefaceClass;
		}
		return intefaceClass;
	}

	@SuppressWarnings("unchecked")
	public static <T> T create1(Class<T> boInterfaceClass) {
		BusinessObject handler = new BusinessObject(boInterfaceClass.getClass());

		T s = (T) Proxy.newProxyInstance(boInterfaceClass.getClassLoader(), new Class[] { boInterfaceClass }, handler);

		System.out.println(s.getClass().getInterfaces()[0].getCanonicalName());
		return (T) Proxy.newProxyInstance(boInterfaceClass.getClassLoader(), new Class[] { boInterfaceClass }, handler);
	}

	/*
	 * @SuppressWarnings("unchecked") public static <T> T create123(Class<T>
	 * boInterfaceClass) { Handler handler = new Handler(If.class); return
	 * (T)Proxy.newProxyInstance(If.class.getClassLoader(), new Class[] {
	 * If.class }, handler); }
	 */
	public static void save(Object object) {

	}

	public static void saveOrUpdate(Object object) {

	}

	public static String frameQuery(Object question) {
		Class<?> class1 = question.getClass();
		Method methods[] = class1.getMethods();
		Class<?> c = question.getClass().getInterfaces()[0];
		if (c.isAnnotationPresent((Class<? extends Table>) Table.class)) {
			Table ta = c.getAnnotation(Table.class);
			System.out.println(ta.tableName());
		}
		Field[] fields=class1.getFields();
		if(fields!=null)
		{
			for(int i=0;i<fields.length;i++)
			{
				Field field=fields[i];
				try {
					System.out.println(field.get(question));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return "";
	}

	public static void main(String[] args) throws Exception {
		Question question = BusinessObject.create1(Question.class);

		question.setQuestion("dd");
		question.setQuestionId("qid");
		question.setUploadDate("hjg");
		frameQuery(question);
	}

	class Query {

		private String tableName;

		private List<ColumnInfo> columnInfo;

		public String getTableName() {
			return tableName;
		}

		public void setTableName(String tableName) {
			this.tableName = tableName;
		}

		public List<ColumnInfo> getColumnInfo() {
			return columnInfo;
		}

		public void setColumnInfo(List<ColumnInfo> columnInfo) {
			this.columnInfo = columnInfo;
		}

		public void addColumnInfo(ColumnInfo columnInfo) {
			if (this.columnInfo == null) {
				this.columnInfo = new ArrayList<ColumnInfo>();
			}
			this.columnInfo.add(columnInfo);

		}

		public String toQuery(Operation operation) {
			String query = "";
			switch (operation) {
			case SELECT:
				query = "SELECT * from " + this.getTableName();
				break;
			case INSERT:
				List<ColumnInfo> columnInfos = this.getColumnInfo();
				StringBuffer column = new StringBuffer();
				StringBuffer value = new StringBuffer();
				if (columnInfos != null) {
					for (int i = 0; i < columnInfos.size(); i++) {
						ColumnInfo columnInfo = columnInfos.get(i);
						if (columnInfo != null) {
							column.append(columnInfo.getName());
							if (columnInfo.getDataType() instanceof String) {
								value.append("'" + columnInfo.getValue() + "'");
							} else if (columnInfo.getDataType() instanceof Integer) {
								value.append(columnInfo.getValue());
							}
						}
						if (i != columnInfos.size()) {
							value.append(",");
						}
					}
				}
				query = "insert into " + this.getTableName() + "(" + column.toString() + ") values(" + value.toString()
				+ ")";
				break;
			case UPDATE:
				break;
			}

			return query;
		}
	}

	enum Operation {
		INSERT, UPDATE, SELECT
	}

	class ColumnInfo {
		private String name;
		private String value;
		private Object dataType;

		public ColumnInfo(String name, String value, Object dataType) {

			this.name = name;
			this.value = value;
			this.dataType = dataType;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Object getDataType() {
			return dataType;
		}

		public void setDataType(Object dataType) {
			this.dataType = dataType;
		}

	}
}
