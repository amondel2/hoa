package com.gcl
import  geb.Page
class LoginPage extends Page{
    static url = "/login/auth"

    static at = { $('h1').text() == "Login" }

    static content = {
        navMenu { module(NavMenuModule) }
        username {$("#username")}
        password {$("#password")}
        loginBtn {$("#loginButton_submit")}
    }

    void login() {
        password.value('testme9866')
        username.value('amondel2')
        loginBtn.click()
    }
}
