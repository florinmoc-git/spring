package com.hospital.patientadmin.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;

@FeignClient(name="mirth", url="${mirth_address}")
public interface MirthProxy {
	
	@GetMapping(path = "/channels/statuses", produces = MediaType.APPLICATION_JSON_VALUE)
	MirthChannelsStatusesJson getChannelsAndStatuses();

}
