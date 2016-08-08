package com.gcl

class HtmlFilters {

    def filters = {
        all(controller:'*', action:'*') {
            before = {

            }
            after = { Map model ->
				response.setHeader('Cache-Control', 'no-cache, no-store, must-revalidate')
				response.setDateHeader('Expires', (new Date()-1).time )
				response.setHeader('Pragma', 'no-cache')
            }
            afterView = { Exception e ->

            }
        }
    }
}
