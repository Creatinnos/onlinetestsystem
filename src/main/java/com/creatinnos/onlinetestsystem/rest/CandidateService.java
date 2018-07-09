package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creatinnos.onlinetestsystem.DAOImpl.CandidateDao;
import com.creatinnos.onlinetestsystem.model.candidate.Candidates;

@Controller
public class CandidateService {

	@ResponseBody
	@RequestMapping(value = "/fetchAllCandidates", method = RequestMethod.GET )

	public List<Candidates> loadAllCandidate()
	{
		CandidateDao candidateDAO=new CandidateDao();
		return candidateDAO.fetchAllCandidates();
	}
	

	@ResponseBody
	@RequestMapping(value = "/saveCandidates", method = RequestMethod.POST )

	public Candidates saveCandidate(@RequestBody Candidates candidate)
	{
		CandidateDao candidateDAO=new CandidateDao();
		return candidateDAO.saveCandidates(candidate);
	}


	@ResponseBody
	@RequestMapping(value = "/updateCandidates", method = RequestMethod.POST )

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
	@RequestMapping(value = "/updateAllCandidates", method = RequestMethod.POST )

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
