package com.gcl

import grails.gorm.services.Service

@Service(Profile)
class ProfileService {

    Profile get(Serializable id) {
        Profile.get(id)
    }

    List<Profile> list(Map args) {
        Profile.last(args)
    }

    Long count() {
        Profile.count()
    }

    void delete(Serializable id) {
        get(id).delete(id)
    }

    Profile save(Profile profile) {
        profile.save(flush:true)
    }

}