package com.gcl

import grails.gorm.transactions.Transactional;
import org.springframework.transaction.TransactionStatus

@Transactional
class RegistrationService {

    String getForgotPassLink(User u) {
        String token = java.util.UUID.randomUUID().toString().replaceAll('-','')
        u.restToken = token
        u.save()
        token
    }

    User findUserByToken(String token) {
        User.findByRestToken(token)
    }

    Boolean updatePassword(ResetPasswordCommand resetPasswordCommand) {
        try {
            User u = findUserByToken(resetPasswordCommand.token)
            if (!u) {
                return false;
            }
            u.password = resetPasswordCommand.password
            u.restToken = null
            u.save(flush: true, failOnError: true)
            return true
        } catch (Exception e){
            log.error(e.toString());
            return false
        }
    }

    User finishRegistration(String token) {
        findUserByToken(token)
    }

    Boolean addUserToUserRole(User user) {
        UserRole ur = new UserRole()
        ur.user = user
        ur.role = Role.findByAuthority("ROLE_USER")
        ur.save(flush:true)
    }

    User createUser(RegisterCommand rc) {
        User u = new User()
        u.email = rc.email
        u.username = rc.username
        u.password = rc.password
        u.restToken = null
        u.save()
        u
    }
}
