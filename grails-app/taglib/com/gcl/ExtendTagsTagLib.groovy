package com.gcl

import com.gcl.Profile
import com.gcl.HouseType

class ExtendTagsTagLib {
    static namespace="gcl"
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [renderMonthlyBox: 'raw',renderCheckAllBox: 'raw',renderHouseTypes:'raw']
    def springSecurityService
    def houseMonthService

    def getUserFName = {attrs,body->
        Profile user = Profile.findByUser(springSecurityService.currentUser)
        def name =  user ? user.firstName : ""
        out << body() <<name
    }

    def renderAmountOwed = { attrs, body ->
        out << body() << houseMonthService.calculateAmountOwed(House.load(attrs.house.id))
    }

    def renderMonthlyBox = {attrs,body->

        def hmCal = new GregorianCalendar(attrs.year,attrs.month,1)
        def hmeCal = new GregorianCalendar(attrs.year,attrs.month,1)
        hmeCal.add(hmCal.DAY_OF_MONTH, hmCal.getActualMaximum(hmCal.DAY_OF_MONTH))
        hmeCal.add(hmCal.SECOND, -1)
        def output=""
        def hmd = HouseMonth.withCriteria {
            eq("house",attrs.hm)
            'in'( 'months', DueMonths.withCriteria {
                between('startDate',hmCal.getTime(),hmeCal.getTime())
            })

        }?.getAt(0)
        if(hmd) {
            output = "<input type='checkbox' name='${attrs.hm.number}myCheckbox${attrs.month}' hn='${attrs.hm.number}' hmdm='${hmd.months.id}' hmhn='${attrs.hm.id}' year='${attrs.year}' month='${attrs.month}' id='${attrs.hm.number}myCheckbox${attrs.month}' class='dueMonthCheckBox'" + (hmd.paid ? "checked" :  '')  +  '/>'
        } else {
            output = "<button id='${attrs.hm.number}addMonthsBtn_${attrs.month}' data-toggle=\"modal\" data-target=\"#feeAddSingle\" class='dueMonthAddButton' hnid='${attrs.hm.id}' hn='${attrs.hm.number}' year='${attrs.year}' month='${attrs.month}' name='${attrs.hm.number}addMonthsBtn'>Add Due Month</button>"
        }
        out << body() << output
    }

    def renderCheckAllBox = { attrs, body ->
        out << body() <<  """
		<ul class='nav navbar-nav'><li class='dropdown nav-item'><a class="dropdown-toggle nav-link" data-toggle="dropdown" href='#' style="padding:0px;">${attrs.header}
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
		    <li class="nav-item dropdown-item checkAllBtn" orient="${attrs.orient}"><a class="nav-link" href='#'>All</a></li>
		    <li class="nav-item dropdown-item checkNoneBtn" orient="${attrs.orient}"><a class="nav-link" href='#'>None</a></li>
		</ul>
		</li>
		</ul>
	 """

    }

    def renderHouseTypes = { attrs, body ->
        def id = attrs.id ?: "housetype"
        def name = attrs.name ?: id

        out << body() <<g.select([name:name,id:id,noSelection:['all':'ALL'],from:HouseType])

    }

}
