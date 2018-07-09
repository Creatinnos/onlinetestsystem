package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.DAOImpl.CandidateDao;
import com.creatinnos.onlinetestsystem.DAOImpl.CategoryDao;
import com.creatinnos.onlinetestsystem.model.Category;
import com.creatinnos.onlinetestsystem.model.CategoryList;
import com.creatinnos.onlinetestsystem.model.SubCategory;
import com.creatinnos.onlinetestsystem.model.Subject;
import com.creatinnos.onlinetestsystem.model.candidate.Candidates;

@Controller
public class CategoryService {

	@ResponseBody
	@RequestMapping(value = "/fetchCategory", method = RequestMethod.GET )

	public CategoryList loadAllCandidate(@QueryParam("organizationId") String organizationId,@PathParam("userId")String userId)
	{
		System.out.println(organizationId+"--"+userId);
		CategoryDao categoryDAO=new CategoryDao();
		return categoryDAO.fetchAllCategory(organizationId);
	}

	@ResponseBody
	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST )

	public Category saveCandidate(@RequestBody Category category)
	{
		CategoryDao categoryDAO=new CategoryDao();
		return categoryDAO.saveCategory(category);
	}


	@ResponseBody
	@RequestMapping(value = "/saveSubCategory", method = RequestMethod.POST )

	public SubCategory saveSubCandidate(@RequestBody SubCategory subCategory)
	{
		CategoryDao categoryDAO=new CategoryDao();
		return categoryDAO.saveSubCategory(subCategory);
	}


	@ResponseBody
	@RequestMapping(value = "/saveSubject", method = RequestMethod.POST )

	public Subject saveSubject(@RequestBody Subject subject)
	{
		CategoryDao categoryDAO=new CategoryDao();
		return categoryDAO.saveSubject(subject);
	}


	@ResponseBody
	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST )

	public Candidates updateCandidate(@RequestBody Candidates candidate)
	{
		CandidateDao candidateDAO=new CandidateDao();
		if(candidateDAO.updateCandidates(candidate))
		{
			System.out.println("save --> "+candidate);
			return candidate;	
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "/updateAllCategory", method = RequestMethod.POST )

	public List<Candidates> updateCandidates(@RequestBody List<Candidates> candidates)
	{
		CandidateDao candidateDAO=new CandidateDao();
		if(candidateDAO.updateCandidates(candidates))
		{
			System.out.println("save --> "+candidates);
			return candidates;	
		}
		return null;
	}
}
