package com.soat.controller;

import com.soat.dto.CandidateDTO;
import com.soat.entities.Candidate;
import com.soat.entities.TalentAdvocate;
import com.soat.service.CandidateServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

	@Autowired
	private CandidateServiceImpl candidateService;

	@GetMapping(value = "/listCandidates")
	public Page<Candidate> getPaginatedCandidates(int page, int size) {
		return candidateService.getPaginatedCandidates(page, size);
	}

	@PostMapping("/addCandidate")
	public ResponseEntity<CandidateDTO> createNewCandidate(@RequestBody CandidateDTO candidateDTORequest) {
		//, UriComponentsBuilder uriComponentBuilder
		Candidate existingCandidate = candidateService.findCandidateByEmail(candidateDTORequest.getEmail());
		if (existingCandidate != null) {
			return new ResponseEntity<CandidateDTO>(HttpStatus.CONFLICT);
		}
		Candidate candidateRequest = mapCandidateDTOToCandidate(candidateDTORequest);
		Candidate candidate = candidateService.saveCandidate(candidateRequest);
		if (candidate != null && candidate.getIdCandidate() != null) {
			CandidateDTO candidateDTO = mapCandidateToCandidateDTO(candidate);
			return new ResponseEntity<CandidateDTO>(candidateDTO, HttpStatus.CREATED);
		}
		return new ResponseEntity<CandidateDTO>(HttpStatus.NOT_MODIFIED);

	}

	private Candidate mapCandidateDTOToCandidate(CandidateDTO candidateDTO) {
		ModelMapper mapper = new ModelMapper();
		Candidate candidate = mapper.map(candidateDTO, Candidate.class);
		TalentAdvocate candidateTalentAdvocate = new TalentAdvocate();
		setCandidateTalentAdvocateParameters(candidateDTO, candidateTalentAdvocate);
		candidate.setTalentAdvocate(candidateTalentAdvocate);
		candidate.setCreationDate(LocalDate.now());
		return candidate;
	}

	private CandidateDTO mapCandidateToCandidateDTO(Candidate candidate) {
		ModelMapper mapper = new ModelMapper();
		CandidateDTO candidateDTO = mapper.map(candidate, CandidateDTO.class);
		if (candidate.getTalentAdvocate() != null) {
			TalentAdvocate candidateTalentAdvocate = new TalentAdvocate();
			setCandidateTalentAdvocateParameters(candidateDTO, candidateTalentAdvocate);
			candidateDTO.setTalentAdvocate(candidateTalentAdvocate);
		}
		return candidateDTO;
	}

	private void setCandidateTalentAdvocateParameters(CandidateDTO candidateDTO, TalentAdvocate candidateTalentAdvocate) {
		candidateTalentAdvocate.setIdTalentAdvocate(candidateDTO.getTalentAdvocate().getIdTalentAdvocate());
		candidateTalentAdvocate.setFirstName(candidateDTO.getTalentAdvocate().getFirstName());
		candidateTalentAdvocate.setLastName(candidateDTO.getTalentAdvocate().getLastName());
		candidateTalentAdvocate.setTelephone(candidateDTO.getTalentAdvocate().getTelephone());
		candidateTalentAdvocate.setEmail(candidateDTO.getTalentAdvocate().getEmail());
	}

}
