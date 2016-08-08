

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		"/home/contact" (view:"/home/contact")
        "/"(controller:"home",params:params)
		"/dueMonths/v1/$action/$id?(.$format)?"(controller:"dueMonths", namespace:'v1')
		"/dueMonths/v2/$action/$id?(.$format)?"(controller:"dueMonths", namespace:'v2')
        "500"(view:'/error')
	}
}
