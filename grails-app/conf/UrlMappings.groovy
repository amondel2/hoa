

class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		"/home/contact" (view:"/home/contact")
        "/"(controller:"home",params:params)
        "500"(view:'/error')
	}
}
