package com.gcl
import  geb.Page
class DocumentsPage extends Page{
    static url = "/documents"

    static at = {  title == "HOA Documents" }

    static content = {
        navMenu { module(NavMenuModule) }
        bylaws { $("#bylaws")}
        rules { $("#rules")}
        recordedDeclarationofGwyneddChasePlannedCommunity { $("#recordedDeclarationofGwyneddChasePlannedCommunity")}
        budget { $("#budget")}
    }


}
