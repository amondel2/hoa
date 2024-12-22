package com.gcl

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Transactional
class ProfileService {

    Profile get(Serializable id) {
        Profile.get(id)
    }

    List<Profile> list(Map args) {
        Profile.list(args)
    }

    Long count() {
        Profile.count()
    }

    void delete(Serializable id) {
        get(id).delete(id)
    }

    Profile save(Profile profile) {
        profile.save(flush:true,failonError:true)
    }

}