package com.safertyNet.safetyNetAlerts.model;

import lombok.Data;

@Data
public class Firestation {

	private String address;
	
	private int station;

	public Firestation(String address, int station) {
		super();
		this.address = address;
		this.station = station;
	}

	@Override
	public String toString() {
		return "Firestation [address=" + address + ", station=" + station + "]";
	}
	
	

}