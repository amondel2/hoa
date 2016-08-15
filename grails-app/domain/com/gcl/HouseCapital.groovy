package com.gcl
import groovy.transform.ToString
import grails.gorm.DetachedCriteria;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class HouseCapital implements Serializable {

	private static final long serialVersionUID = 1
	House house
	CapitalContrib cap
	Boolean paid = false
	
	HouseCapital(House h, CapitalContrib c) {
		this()
		house = h
		cap = c
	}
	
	@Override
	boolean equals(other) {
		if (!(other instanceof HouseCapital)) {
			return false
		}

		other.cap?.id == cap?.id && other.house?.id == house?.id
	}
	
	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (cap) builder.append(cap.id)
		if (house) builder.append(house.id)
		builder.toHashCode()
	}

	static HouseCapital get(long houseId, long capId) {
		criteriaFor(houseId, capId).get()
	}

	static boolean exists(long houseId, long capId) {
		criteriaFor(houseId, capId).count()
	}

	private static DetachedCriteria criteriaFor(long houseId, long capId) {
		HouseCapital.where {
			house == House.load(houseId) &&
			cap == CapitalContrib.load(capId)
		}
	}

	static HouseCapital create(House house, CapitalContrib cap, boolean flush = false) {
		def instance = new HouseCapital(house: house, cap: cap, paid:false)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(House h, CapitalContrib c, boolean flush = false) {
		if (h == null || c == null) return false

		int rowCount = HouseCapital.where { house == h && cap == c }.deleteAll()

		if (flush) { HouseCapital.withSession { it.flush() } }

		rowCount
	}
	
	
	static void removeAll(House h, boolean flush = false) {
		if (h == null) return

		HouseCapital.where { house == h }.deleteAll()

		if (flush) { HouseCapital.withSession { it.flush() } }
	}

	static void removeAll(CapitalContrib m, boolean flush = false) {
		if (m == null) return

		HouseCapital.where { cap == m }.deleteAll()

		if (flush) { HouseCapital.withSession { it.flush() } }
	}

	static constraints = {
		cap validator: { CapitalContrib m, HouseMonth hm ->
			if (hm.house == null || hm.house.id == null) return
			boolean existing = false
			CapitalContrib.withNewSession {
				existing = CapitalContrib.exists(hm.house.id, m.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['cap', 'house']
		version false
	}

}
