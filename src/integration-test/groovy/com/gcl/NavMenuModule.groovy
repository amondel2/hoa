package com.gcl
import geb.Module

class NavMenuModule extends Module {
    static content = {
        mainbar { $("ul.navbar-nav") }
    }

    void clickon(String name) {
        def links =  mainbar.find("li")
//        println("Links size " + links.size())
//        links?.each{
//            println("link Name = " + it.text() )
//        }
       links?.filter(text:name)?.getAt(0)?.click()
    }
}
