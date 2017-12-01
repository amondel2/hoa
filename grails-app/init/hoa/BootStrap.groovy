package hoa

import com.gcl.Role
import com.gcl.User
import com.gcl.UserRole

class BootStrap {

    def init = { servletContext ->

        Role adminRole = Role.findOrCreateByAuthority('ROLE_ADMIN')
        Role userRole = Role.findOrCreateByAuthority('ROLE_USER')
        Role bm = Role.findOrCreateByAuthority('ROLE_BOARDMEMBER')
        adminRole.save()
        userRole.save()
        bm.save()
        def testUser = UserRole.findByRole(adminRole)
        if(!testUser) {
            testUser = new User(username: "amondel2",password: "testme9866", email: "test@gmail.com")
            testUser.save()
            UserRole.create testUser, adminRole, true
            UserRole.create testUser, userRole, true
            UserRole.create testUser, bm, true
        }


    }
    def destroy = {
    }
}
