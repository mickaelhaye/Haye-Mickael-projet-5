package com.safertyNet.safetyNetAlerts.model;

import lombok.Data;

@Data
public class Firestation {

	private String address;

	private String station;

	public Firestation(String address, String station) {
		super();
		this.address = address;
		this.station = station;
	}

	@Override
	public String toString() {
		return "Firestation [address=" + address + ", station=" + station + "]";
	}

}
