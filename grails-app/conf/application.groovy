grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.gcl.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.gcl.UserRole'
grails.plugin.springsecurity.authority.className = 'com.gcl.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
		[pattern: '/register/**',            access: ['permitAll']],
		[pattern: '/profile/**',             access: ['ROLE_USER']],
		[pattern: '/board/**',               access: ['ROLE_USER']],
		[pattern: '/user/**',                access: ['ROLE_ADMIN']],
		[pattern: '/role/**',                access: ['ROLE_ADMIN']],
		[pattern: '/securityInfo/**',        access: ['ROLE_ADMIN']],
		[pattern: '/registrationCode/**',    access: ['ROLE_ADMIN']],
		[pattern: '/',               access: ['permitAll']],
		[pattern: '/error',          access: ['permitAll']],
		[pattern: '/index',          access: ['permitAll']],
		[pattern: '/index.gsp',      access: ['permitAll']],
		[pattern: '/shutdown',       access: ['permitAll']],
		[pattern: '/assets/**',      access: ['permitAll']],
		[pattern: '/**/js/**',       access: ['permitAll']],
		[pattern: '/**/css/**',      access: ['permitAll']],
		[pattern: '/**/images/**',   access: ['permitAll']],
		[pattern: '/**/favicon.ico', access: ['permitAll']],
		[pattern: '/monitoring/**', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
		[pattern: '/assets/**',      filters: 'none'],
		[pattern: '/**/js/**',       filters: 'none'],
		[pattern: '/**/css/**',      filters: 'none'],
		[pattern: '/**/images/**',   filters: 'none'],
		[pattern: '/**/favicon.ico', filters: 'none'],
		[pattern: '/**',             filters: 'JOINED_FILTERS']
]
