package com.gcl.fin

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class HouseMonth implements Serializable {

	private static final long serialVersionUID = 1

	House house
	DueMonths months
	Boolean paid = false

	HouseMonth(House h, DueMonths m) {
		this()
		house = h
		months = m
		paid = false
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof HouseMonth)) {
			return false
		}

		other.months?.id == months?.id && other.house?.id == house?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (months) builder.append(months.id)
		if (house) builder.append(house.id)
		builder.toHashCode()
	}

	static HouseMonth get(long houseId, long monthsId) {
		criteriaFor(houseId, monthsId).get()
	}

	static boolean exists(long houseId, long monthsId) {
		criteriaFor(houseId, monthsId).count()
	}

	private static DetachedCriteria criteriaFor(long houseId, long monthsId) {
		HouseMonth.where {
			house == House.load(houseId) &&
			months == DueMonths.load(monthsId)
		}
	}

	static HouseMonth create(House house, DueMonths month, boolean flush = false) {
		def instance = new HouseMonth(house,month)
		instance.save(flush: flush, insert: true, failOnError:true)
		instance
	}

	static boolean remove(House h, DueMonths m, boolean flush = false) {
		if (h == null || m == null) return false

		int rowCount = HouseMonth.where { house == h && months == m }.deleteAll()

		if (flush) { HouseMonth.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(House h, boolean flush = false) {
		if (h == null) return

		HouseMonth.where { house == h }.deleteAll()

		if (flush) { HouseMonth.withSession { it.flush() } }
	}

	static void removeAll(DueMonths m, boolean flush = false) {
		if (m == null) return

		HouseMonth.where { months == m }.deleteAll()

		if (flush) { HouseMonth.withSession { it.flush() } }
	}

	static constraints = {
	}

	static mapping = {
		id composite: ['months', 'house']
		version false
	}
}