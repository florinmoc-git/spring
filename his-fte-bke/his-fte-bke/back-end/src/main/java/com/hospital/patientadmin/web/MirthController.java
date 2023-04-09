package com.hospital.patientadmin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.patientadmin.proxy.MirthChannelsStatusesJson;
import com.hospital.patientadmin.proxy.MirthProxy;

@RestController
public class MirthController {
	
	private final MirthProxy mirthProxy;
	
	public MirthController(MirthProxy mirthProxy) {
		this.mirthProxy = mirthProxy;
	}
	
	@GetMapping("mirth-channles-statuses")
	public MirthChannelsStatusesJson getChannelsAndStatuses() {
		return mirthProxy.getChannelsAndStatuses();		
	}
}
