package com.gcl

class ResetPasswordCommand implements  grails.validation.Validateable {
    String password
    String password2
    String token
    static constraints = {
        password validator: RegisterController.passwordValidator
        password2 nullable: true, validator: RegisterController.password2Validator
    }
}
