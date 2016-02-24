package io.marvingardens;

import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Alarm {

	private String name;
	private String zip;
	private int hour;
	private int min;
	private List<Integer> days;

	public Alarm() {
	}

	public Alarm(String name, String zip, int hour, int min, List<Integer> days) {
		this.name = name;
		this.zip = zip;
		setHour(hour);
		setMin(min);
		this.days = new LinkedList<>(days);
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getDays() {
		return days;
	}

	public void setDays(List<Integer> days) {
		this.days = days;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		if (hour < 0) {
			throw new IllegalArgumentException();
		}
		this.hour = hour % 24;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		if (min < 0) {
			throw new IllegalArgumentException();
		}
		this.min = min % 60;
		this.setHour(getHour() + min / 60);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((days == null) ? 0 : days.hashCode());
		result = prime * result + hour;
		result = prime * result + min;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarm other = (Alarm) obj;
		if (days == null) {
			if (other.days != null)
				return false;
		} else if (!days.equals(other.days))
			return false;
		if (hour != other.hour)
			return false;
		if (min != other.min)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",");
		for (int day : days) {
			sj.add(String.valueOf(day));
		}
		return String.format("%s, %s, %2d:%2d Days: %s", name, zip, hour, min, sj.toString());
	}

}
