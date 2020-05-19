package com.soat.controller;

import com.soat.dto.TalentAdvocateDTO;
import com.soat.entities.TalentAdvocate;
import com.soat.service.ICandidateService;
import com.soat.service.TalentAdvocateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/talentAdvocate")
public class TalentAdvocateController {

	@Autowired
	private ICandidateService candidateService;

	@Autowired
	private TalentAdvocateService talentAdvocateService;

	@GetMapping(value = "/listTalentAdvocates")
	public ResponseEntity<List<TalentAdvocateDTO>> getTalentAdvocates() {
		List<TalentAdvocate> talentAdvocates = talentAdvocateService.getTalentAdvocates();
		if (!CollectionUtils.isEmpty(talentAdvocates)) {
			//on retire tous les élts null que peut contenir cette liste
			talentAdvocates.removeAll(Collections.singleton(null));
			List<TalentAdvocateDTO> talentAdvocateDTOs = talentAdvocates.stream().map(talentAdvocate -> {
				return mapTalentAdvocateToTalentAdvocateDTO(talentAdvocate);
			}).collect(Collectors.toList());
			return new ResponseEntity<List<TalentAdvocateDTO>>(talentAdvocateDTOs, HttpStatus.OK);
		}
		return new ResponseEntity<List<TalentAdvocateDTO>>(HttpStatus.NO_CONTENT);
	}


	private TalentAdvocateDTO mapTalentAdvocateToTalentAdvocateDTO(TalentAdvocate talentAdvocate) {
		ModelMapper mapper = new ModelMapper();
		TalentAdvocateDTO talentAdvocateDTO = mapper.map(talentAdvocate, TalentAdvocateDTO.class);
		return talentAdvocateDTO;
	}

}
