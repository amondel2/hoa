package com.gcl

class RegisterCommand implements  grails.validation.Validateable {
    protected static Class<User> User
    protected static String usernamePropertyName

    String username
    String email
    String password
    String password2

    static constraints = {
        username validator: { value, command ->
            if (!value) {
                return  'registerCommand.username.unique'
            }

            if (User.findWhere((usernamePropertyName): value)) {
                return 'registerCommand.username.unique'
            }
        }
        email email: true
        password validator: RegisterController.passwordValidator
        password2 nullable: true, validator: RegisterController.password2Validator
    }
}
