import com.gcl.Role
import com.gcl.User
import com.gcl.UserRole

class BootStrap {

    def init = { servletContext ->
		def adminRole = new Role('ROLE_ADMIN').save()
		def userRole = new Role('ROLE_USER').save()
  
		def testUser = new User('aaron', 'splatt66').save()
  
		UserRole.create testUser, adminRole, true
    }
    def destroy = {
    }
}