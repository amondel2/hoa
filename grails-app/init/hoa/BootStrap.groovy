package hoa

import com.gcl.Role
import com.gcl.User
import com.gcl.UserRole
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class BootStrap {
    GrailsApplication grailsApplication
    def init = { servletContext ->



        Role adminRole = createRole('ROLE_ADMIN')
        Role userRole = createRole('ROLE_USER')
        Role bm = createRole('ROLE_BOARDMEMBER')
        createUser()
        User testUser = User.where { userName: grailsApplication.config.getProperty("defaults.adminUser")}.find()
        createUr(testUser, adminRole)
        createUr(testUser, userRole)
        createUr(testUser, bm)
    }

    @Transactional
    Role createRole(String roleName) {
       Role r = Role.findOrCreateByAuthority(roleName)
       r.save(flush: true)
       r
    }


    @Transactional
    def createUser() {
        Integer testUserCnt = User.countByUsername(grailsApplication.config.getProperty("defaults.adminUser"))
        if(testUserCnt == 0) {
            User tmp = new User(username: grailsApplication.config.getProperty("defaults.adminUser"),
                                email: grailsApplication.config.getProperty("defaults.adminEmail"),
                                password: grailsApplication.config.getProperty("defaults.adminPassword"))
            tmp.save(flush:true,failOnError:true)
        }
    }


    @Transactional
    UserRole createUr(User user, Role role) {
        UserRole.findOrCreateWhere(user: user, role:role).save(flush: true)
    }

    def destroy = {
    }
}
