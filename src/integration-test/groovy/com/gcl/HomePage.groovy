package com.gcl
import  geb.Page
class HomePage extends Page {

    static url = "/"

    static at = { title == "Gwynedd Chase Lansdale" }

    static content = {
        navMenu { module(NavMenuModule) }
    }
}
