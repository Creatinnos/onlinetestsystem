package com.creatinnos.onlinetestsystem.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.creatinnos.onlinetestsystem.DAOImpl.CandidateDAO;
import com.creatinnos.onlinetestsystem.model.candidate.Candidates;

@Path("/")
public class CandidateService {

	@GET
	@Path("/fetchAllCandidates")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidates> loadAllCandidate()
	{
		CandidateDAO candidateDAO=new CandidateDAO();
		return candidateDAO.fetchAllCandidates();
	}
	
	@POST
	@Path("/saveCandidates")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Candidates saveCandidate(Candidates candidate)
	{
		CandidateDAO candidateDAO=new CandidateDAO();
		return candidateDAO.saveCandidates(candidate);
	}

	@POST
	@Path("/updateCandidates")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Candidates updateCandidate(Candidates candidate)
	{
		CandidateDAO candidateDAO=new CandidateDAO();
		if(candidateDAO.updateCandidates(candidate))
		{
			System.out.println("save --> "+candidate);
			return candidate;	
		}
		return null;
	}
	@POST
	@Path("/updateAllCandidates")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidates> updateCandidates(List<Candidates> candidates)
	{
		CandidateDAO candidateDAO=new CandidateDAO();
		if(candidateDAO.updateCandidates(candidates))
		{
			System.out.println("save --> "+candidates);
			return candidates;	
		}
		return null;
	}
}
