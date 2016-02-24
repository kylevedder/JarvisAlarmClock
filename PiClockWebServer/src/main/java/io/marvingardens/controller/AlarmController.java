package io.marvingardens.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.marvingardens.Alarm;
import io.marvingardens.AlarmManager;
import io.marvingardens.AppMain;

@RestController
public class AlarmController {

	AlarmManager m = new AlarmManager(AppMain.scriptPath);

	@RequestMapping(value = "/api/alarm/list", method = RequestMethod.GET, produces = "application/json")
	public List<Alarm> listAlarms() {
		List<Alarm> list = m.getAllScheduled();
		System.out.println(list);
		return list;
	}

	@RequestMapping(value = "/api/alarm/add", method = RequestMethod.POST, consumes = "application/json")
	public void addAlarm(@RequestBody Alarm alarm) {
		System.out.println(alarm);
		m.schedule(alarm);
	}

	@RequestMapping(value = "/api/alarm/delete", method = RequestMethod.POST, consumes = "application/json")
	public void deleteAlarm(@RequestBody Alarm alarm) {
		System.out.println(alarm);
		m.deleteAlarm(alarm);
	}
}
