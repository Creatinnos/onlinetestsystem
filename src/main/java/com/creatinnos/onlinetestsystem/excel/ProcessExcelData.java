package com.creatinnos.onlinetestsystem.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.creatinnos.onlinetestsystem.DAOImpl.CandidateDao;
import com.creatinnos.onlinetestsystem.DAOImpl.CategoryDao;
import com.creatinnos.onlinetestsystem.DAOImpl.ExamDao;
import com.creatinnos.onlinetestsystem.DAOImpl.QuestionDao;
import com.creatinnos.onlinetestsystem.DAOImpl.UserDao;
import com.creatinnos.onlinetestsystem.model.Category;
import com.creatinnos.onlinetestsystem.model.NewExam;
import com.creatinnos.onlinetestsystem.model.Question;
import com.creatinnos.onlinetestsystem.model.QuestionList;
import com.creatinnos.onlinetestsystem.model.SubCategory;
import com.creatinnos.onlinetestsystem.model.Subject;
import com.creatinnos.onlinetestsystem.model.candidate.Candidates;
import com.creatinnos.onlinetestsystem.model.users.OrganizationUsers;
import com.creatinnos.onlinetestsystem.utils.Constants;

public class ProcessExcelData {

	private static final String FILE_NAME = "D:\\home\\";
	private static final HashMap<String, Sheet> sheets = new HashMap<>();

	public static void main(String[] args) {
		readExcel("OnlineTestSystem.xlsx", "1234");
	}

	public static void readExcel(String fileName, String organizationId) {

		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME + fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Iterator<Sheet> sheetIterator = workbook.sheetIterator();
			while (sheetIterator.hasNext()) {
				Sheet sheet = sheetIterator.next();
				if (sheet != null && sheet.getSheetName() != null) {
					sheets.put(sheet.getSheetName().toLowerCase(), sheet);
				}
			}
			processCategory(sheets.get(Constants.CATEGORY), organizationId);
			processExam(sheets.get(Constants.EXAM), organizationId);
			processQuestion(sheets.get(Constants.QUESTION), organizationId);
			processCandidate(sheets.get(Constants.CANDIDATE), organizationId);
			processUser(sheets.get(Constants.USER), organizationId);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void processUser(Sheet userSheet, String organizationId) {
		if (userSheet == null) {
			return;
		}
		Iterator<Row> iterator = userSheet.iterator();
		iterator.next();
		UserDao userDAO = new UserDao();

		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			OrganizationUsers user = new OrganizationUsers();
			user.setOrganizationId(organizationId);
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				switch (currentCell.getColumnIndex()) {
				case 0:
					user.setName((String) getCellData(currentCell));
					break;
				case 1:
					user.setUserName((String) getCellData(currentCell));
					break;
				case 2:
					user.setPassword((String) getCellData(currentCell));
					break;
				case 3:
					Double phone = (Double) getCellData(currentCell);
					user.setPhone(phone.longValue() + "");
					break;
				case 4:
					user.setEmail((String) getCellData(currentCell));
					break;
				case 5:
					user.setRole((String) getCellData(currentCell));
					break;
				}
			}

			userDAO.saveUser(user);
		}
	}

	private static void processCandidate(Sheet candidateSheet, String organizationId) {
		if (candidateSheet == null) {
			return;
		}
		Iterator<Row> iterator = candidateSheet.iterator();
		iterator.next();
		CandidateDao candidateDAO = new CandidateDao();

		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			Candidates candidate = new Candidates();
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				switch (currentCell.getColumnIndex()) {
				case 0:
					candidate.setName((String) getCellData(currentCell));
					break;
				case 1:
					String email = (String) getCellData(currentCell);
					if (email != null) {
						candidate.setEmail(email);
					} else {
						System.out.println("Email is mandatory");
					}
					break;
				case 2:
					Double phone = (Double) getCellData(currentCell);
					candidate.setPhone(phone.longValue() + "");
					break;
				case 3:
					candidate.setFatherName((String) getCellData(currentCell));
					break;
				case 4:
					candidate.setMotherName((String) getCellData(currentCell));
					break;
				case 5:
					candidate.setDob(formateDate(((Date) getCellData(currentCell)).getTime()));
					break;
				case 6:
					candidate.setGender((String) getCellData(currentCell));
					break;
				case 7:
					candidate.setAddress((String) getCellData(currentCell));
					break;
				}
			}
			candidateDAO.saveCandidates(candidate);
		}
	}

	private static void processQuestion(Sheet questionSheet, String organizationId) {

		if (questionSheet == null) {
			return;
		}
		Iterator<Row> iterator = questionSheet.iterator();
		iterator.next();
		QuestionDao questionDao = new QuestionDao();
		QuestionList questionss = new QuestionList();
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();
			Question question2 = new Question();
			while (cellIterator.hasNext()) {
				Cell currentCell = cellIterator.next();
				switch (currentCell.getColumnIndex()) {
				case 0:
					question2.setCategory((String) getCellData(currentCell));
					break;
				case 1:
					question2.setQuestion((String) getCellData(currentCell));
					break;
				case 2:
					String optionType = (String) getCellData(currentCell);
					if (ChoiceType.Single_Selection.getValue().equals(optionType)
							|| ChoiceType.MultiSelect.getValue().equals(optionType)
							|| ChoiceType.True_False.getValue().equals(optionType)
							|| ChoiceType.Descriptive.getValue().equals(optionType)) {
						question2.setOptionType(optionType);
					} else {
						System.out.println("Invalid Option Type");
					}
					break;
				case 3:
					question2.setOption1((String) getCellData(currentCell));
					break;
				case 4:
					question2.setOption2((String) getCellData(currentCell));
					break;
				case 5:
					question2.setOption3((String) getCellData(currentCell));
					break;
				case 6:
					question2.setOption4((String) getCellData(currentCell));
					break;
				case 7:
					String correctOption = (String) getCellData(currentCell);
					String tempCorrectOption = correctOption;
					tempCorrectOption = tempCorrectOption.replaceAll(";", "");
					tempCorrectOption = tempCorrectOption.replaceAll("Option1", "");
					tempCorrectOption = tempCorrectOption.replaceAll("Option2", "");
					tempCorrectOption = tempCorrectOption.replaceAll("Option3", "");
					tempCorrectOption = tempCorrectOption.replaceAll("Option4", "");
					if (tempCorrectOption.equals("")) {
						question2.setCorrectOption(correctOption);
					} else {
						System.out.println("InvalidOption");
					}
					break;
				}
			}
			questionss.addQuestion(question2);
		}
		questionDao.saveQuestion(questionss);
	}

	private static void processCategory(Sheet categorySheet, String organizationId) {
		if (categorySheet == null) {
			return;
		}
		Iterator<Row> iterator = categorySheet.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			CategoryDao categoryDAO = new CategoryDao();
			String categoryId = null;
			String subCategoryId = null;
			while (cellIterator.hasNext()) {

				Cell currentCell = cellIterator.next();
				switch (currentCell.getColumnIndex()) {
				case 0:
					String categoryName = (String) getCellData(currentCell);
					if (categoryName != null && !categoryName.trim().equals("")) {
						Category category = new Category();
						category.setCategoryName((String) getCellData(currentCell));
						category.setOrganizationId(organizationId);
						category = categoryDAO.saveCategory(category);
						categoryId = category.getCategoryId();
					}
					break;
				case 1:
					String subCategoryName = (String) getCellData(currentCell);
					if (categoryId != null && subCategoryName != null && !subCategoryName.trim().equals("")) {
						SubCategory subCategory = new SubCategory();
						subCategory.setCategoryId(categoryId);
						subCategory.setOrganizationId(organizationId);
						subCategory.setSubCategoryName(subCategoryName);
						subCategory = categoryDAO.saveSubCategory(subCategory);
						subCategoryId = subCategory.getSubCategoryId();
					}
					break;
				case 2:
					String subjectName = (String) getCellData(currentCell);
					if (categoryId != null && subCategoryId != null && subjectName != null
							&& !subjectName.trim().equals("")) {
						Subject subject = new Subject();
						subject.setCategoryId(categoryId);
						subject.setSubCategoryId(subCategoryId);
						subject.setOrganizationId(organizationId);
						subject.setSubject(subjectName);
						subject = categoryDAO.saveSubject(subject);
					}
					break;
				}
			}
		}
	}

	private static void processExam(Sheet examSheet, String organizationId) {
		if (examSheet == null) {
			return;
		}
		Iterator<Row> iterator = examSheet.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			NewExam newExam = new NewExam();
			newExam.setOrganizationId(organizationId);
			Row currentRow = iterator.next();
			Iterator<Cell> cellIterator = currentRow.iterator();

			CategoryDao categoryDAO = new CategoryDao();
			String categoryId = null;
			String subCategoryId = null;
			while (cellIterator.hasNext()) {

				Cell currentCell = cellIterator.next();
				switch (currentCell.getColumnIndex()) {
				case 0:
					newExam.setExamName((String) getCellData(currentCell));
					break;
				case 1:
					Category category = new Category();
					category.setCategoryName((String) getCellData(currentCell));
					category.setOrganizationId(organizationId);
					category = categoryDAO.saveCategory(category);
					categoryId = category.getCategoryId();
					newExam.setCategory(categoryId);
					break;
				case 2:
					SubCategory subCategory = new SubCategory();
					subCategory.setCategoryId(categoryId);
					subCategory.setOrganizationId(organizationId);
					subCategory.setSubCategoryName((String) getCellData(currentCell));
					subCategory = categoryDAO.saveSubCategory(subCategory);
					subCategoryId = subCategory.getSubCategoryId();
					newExam.setSubCategory(subCategoryId);
					break;
				case 3:
					Subject subject = new Subject();
					subject.setCategoryId(categoryId);
					subject.setSubCategoryId(subCategoryId);
					subject.setOrganizationId(organizationId);
					subject.setSubject((String) getCellData(currentCell));
					subject = categoryDAO.saveSubject(subject);
					newExam.setSubject(subject.getSubjectId());
					break;
				case 4:
					newExam.setExamStartDate(formateDate(((Date) getCellData(currentCell)).getTime()));
					break;
				case 5:
					newExam.setExamEndDate(formateDate(((Date) getCellData(currentCell)).getTime()));
					break;
				case 6:
					newExam.setExamDuration(formateHourMin(((Date) getCellData(currentCell)).getTime()));
					break;
				case 7:
					newExam.setExamTime(formateTime(((Date) getCellData(currentCell)).getTime()));
					break;
				case 8:
					newExam.setPassMark((Double) getCellData(currentCell));
					break;
				case 9:
					String str = (String) getCellData(currentCell);
					if (str != null) {
						if (str.equals("Yes")) {
							newExam.setNegativeMarkApplicable(true);
						} else if (str.equals("No")) {
							newExam.setNegativeMarkApplicable((false));
						}
					}
					break;
				case 10:
					newExam.setInstruction((String) getCellData(currentCell));
					break;
				}
			}
			ExamDao examDao = new ExamDao();
			examDao.saveNewExam(newExam);
		}
	}

	private static Object getCellData(Cell currentCell) {
		// getCellTypeEnum shown as deprecated for version 3.15
		// getCellTypeEnum ill be renamed to getCellType starting from version
		// 4.0
		if (currentCell.getCellTypeEnum() == CellType.STRING) {
			// System.out.print(currentCell.getStringCellValue() + "--");
			return currentCell.getStringCellValue();
		} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(currentCell)) {
				// System.out.print(currentCell.getDateCellValue() + "--");
				return currentCell.getDateCellValue();
			} else {
				// System.out.print(currentCell.getNumericCellValue() + "--");
				return currentCell.getNumericCellValue();
			}
		} else if (currentCell.getCellTypeEnum() == CellType.BOOLEAN) {
			// System.out.print(currentCell.getStringCellValue() + "--");
			return currentCell.getBooleanCellValue() + "";
		}
		return null;
	}

	public static String formateDate(Long date) {
		
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date);
			return dbFormat.format(calendar.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String formateTime(Long date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date);
			return dateFormat.format(calendar.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String formateHourMin(Long date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(date);
			return dateFormat.format(calendar.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	enum ChoiceType {
		Single_Selection("Single Selection"), MultiSelect("Multiple Selection"), True_False("True/False"), Descriptive(
				"Descriptive");
		private String value;

		private ChoiceType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
