var constants = {};

constants.remoteUrl1="http://ec2-52-66-177-248.ap-south-1.compute.amazonaws.com:8080";
constants.remoteUrl2="http://localhost:8080";//"http://ec2-13-127-67-247.ap-south-1.compute.amazonaws.com";
constants.projectName="onlinetestsystem";

constants.doLogin = constants.remoteUrl2 + "/" +constants.projectName + "/rest/login";
constants.doRegister = constants.remoteUrl2 + "/" +constants.projectName + "/rest/register";

constants.fetchAllCandidates = constants.remoteUrl2 + "/" +constants.projectName + "/rest/fetchAllCandidates";
constants.saveCandidates = constants.remoteUrl2 + "/" +constants.projectName + "/rest/saveCandidates";
constants.updateCandidates = constants.remoteUrl2 + "/" +constants.projectName + "/rest/updateCandidates";

constants.fetchCategory = constants.remoteUrl2 + "/" +constants.projectName + "/rest/fetchCategory";
constants.saveCategory = constants.remoteUrl2 + "/" +constants.projectName + "/rest/saveCategory";
constants.saveSubCategory = constants.remoteUrl2 + "/" +constants.projectName + "/rest/saveSubCategory";
constants.saveSubject = constants.remoteUrl2 + "/" +constants.projectName + "/rest/saveSubject";

constants.saveExam = constants.remoteUrl2 + "/" +constants.projectName + "/rest/saveExam";
constants.updateExam = constants.remoteUrl2 + "/" +constants.projectName + "/rest/updateExam";

constants.saveQuestion  = constants.remoteUrl2 + "/" +constants.projectName + "/rest/saveQuestion";
constants.fetchQuestion  = constants.remoteUrl2 + "/" +constants.projectName + "/rest/fetchQuestion";
constants.fetchExamMappedQuestion  = constants.remoteUrl2 + "/" +constants.projectName + "/rest/fetchExamMappedQuestion";
constants.updateQuestion  = constants.remoteUrl2 + "/" +constants.projectName + "/rest/updateQuestion";
constants.deleteQuestion  = constants.remoteUrl2 + "/" +constants.projectName + "/rest/deleteQuestion";
constants.deleteExamMappedQuestion  = constants.remoteUrl2 + "/" +constants.projectName + "/rest/deleteExamMappedQuestion";

constants.uploadExcel=constants.remoteUrl2 + "/" +constants.projectName + "/rest/uploadExcel";

constants.fetchExam=constants.remoteUrl2 + "/" +constants.projectName + "/rest/fetchExam";
constants.fetchOranizationExam=constants.remoteUrl2 + "/" +constants.projectName + "/rest/fetchOranizationExam";

constants.fetchAllUser = constants.remoteUrl2 + "/" +constants.projectName + "/rest/fetchAllUser";
constants.saveUser = constants.remoteUrl2 + "/" +constants.projectName + "/rest/saveUser";
constants.updateUser = constants.remoteUrl2 + "/" +constants.projectName + "/rest/updateUser";



constants.instructions = [
		"Total number of questions : 20.",
		"Time allotted : 30 minutes.",
		"Each question carry 1 mark, no negative marks.",
		"DO NOT refresh the page.",
		"All the best."
];

constants.ExamInfo =[
    {"ExamId":"EXAM0001","Category":"CT01","SubCategory":"SCT01","Subject":"SUB01","ExamName":"Mid Term Revision","ExamStartDate":"2018-05-21","ExamEndDate":"2018-05-25","ExamDurationHours":"2","ExamDurationMin":"30","ExamTime":"10:30 AM","PassMark":"40","NegativeMark":"on","Candidates":"CAN01","CandidatesType":"available","Instruction":["Total number of questions : 20.","Time allotted : 30 minutes."],"progress":"N"}
];

constants.events = [
    {Event : "RRB Practice Exam has been postponded. Exam schedule will be revealed shortly.", PostedBy: "Sam Andrew", PostedOn : "28/04/2018"},
    {Event : "80% pass percentage in TANCET Practice Exam", PostedBy: "John Smith", PostedOn : "08/04/2018"}
];

constants.questCate = ["Analytical", "Logical", "Reasoning"];

constants.category = [];

constants.subCategory = [];

constants.subject = [];

constants.candidates = [
      { candidateID : "CAN01",Category: "CT01", Candidate: "SSC:B"},
      { candidateID : "CAN02",Category: "CT02", Candidate: "HSC:A"},
      { candidateID : "CAN03",Category: "CT03", Candidate: "Engg:CSE:1"},
      { candidateID : "CAN04",Category: "CT03", Candidate: "Engg:ECE:3"},
      { candidateID : "CAN01",Category: "CT04", Candidate: "BSc:Maths:2"},
      { candidateID : "CAN01",Category: "CT04", Candidate: "BSc:Physics:1"},
];

constants.question = [
	{ ExamId :"EXAM0001", questCategory:"Analytical",  questType: "multiple", question : "Which are all true in following statements", options: ["Option 1", "Option 2", "Option 3", "Option 4"]},
	{ ExamId :"EXAM0001", questCategory:"Analytical",  questType: "multiple", question : "Which are all false in following statements", options: ["Option 1", "Option 2", "Option 3", "Option 4"]},
	{ ExamId :"EXAM0001", questCategory:"Analytical",  questType: "multiple", question : "Which are all true in following statements", options: ["Option 1", "Option 2", "Option 3", "Option 4"]},
	{ ExamId :"EXAM0001", questCategory:"Logical",  questType: "single", question : "What is the syntax for Substr", options: ["Option 1", "Option 2", "Option 3", "Option 4"]},
	{ ExamId :"EXAM0001", questCategory:"Logical",  questType: "single", question :"What is the Type of Null", options: ["Option 1", "Option 2", "Option 3", "Option 4"]},
	{ ExamId :"EXAM0001", questCategory:"Reasoning",  questType: "boolean", question : "Is Undefined is equal to Null", options: []},
	{ ExamId :"EXAM0001", questCategory:"Reasoning",  questType: "desc", question : "Explain Closures.", options: []}
	
];