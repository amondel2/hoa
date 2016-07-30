package com.gcl

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class MessRole implements Serializable {

	private static final long serialVersionUID = 1

	Messages mess
	Role role

	MessRole(Messages u, Role r) {
		this()
		mess = u
		role = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof MessRole)) {
			return false
		}

		other.mess?.id == mess?.id && other.role?.id == role?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (mess) builder.append(mess.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static MessRole get(long messId, long roleId) {
		criteriaFor(messId, roleId).get()
	}

	static boolean exists(long messId, long roleId) {
		criteriaFor(messId, roleId).count()
	}

	private static DetachedCriteria criteriaFor(long messId, long roleId) {
		MessRole.where {
			mess == Messages.load(messId) &&
			role == Role.load(roleId)
		}
	}

	static MessRole create(Messages mes, Role role, boolean flush = false) {
		def instance = new MessRole(Messages: mes, role: role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Messages m, Role r, boolean flush = false) {
		if (m == null || r == null) return false

		int rowCount = MessRole.where { mess == m && role == r }.deleteAll()

		if (flush) { MessRole.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(Messages m, boolean flush = false) {
		if (m == null) return

		MessRole.where { mess == m }.deleteAll()

		if (flush) { MessRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		MessRole.where { role == r }.deleteAll()

		if (flush) { MessRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, MessRole ur ->
			if (ur.mess == null || ur.mess.id == null) return
			boolean existing = false
			MessRole.withNewSession {
				existing = MessRole.exists(ur.mess.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['mess', 'role']
		version false
	}
}
