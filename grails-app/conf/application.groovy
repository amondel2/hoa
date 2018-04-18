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


String pass = System.getProperty("DB_PASSWORD")?.toString() ?: System.getenv("DB_PASSWORD")?.toString()
String user = System.getProperty("DB_USER")?.toString()  ?: System.getenv("DB_USER")?.toString()
String dbString = System.getProperty("JDBC_CONNECTION_STRING")?.toString()  ?: System.getenv("JDBC_CONNECTION_STRING")?.toString()
dataSource {
	pooled = true
	jmxExport = true
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}

environments {
	development {
		dataSource {
			password = pass
			dbCreate = "update"
			username = user
			url= dbString
		}
	}
	test {
		dataSource {
			dbCreate = "create-drop"
			url = 'jdbc:h2:file:myDevDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE'
		}
	}
	production{
		dataSource {
			dbCreate = "none"
			username = user
			password = pass
			url= dbString
			properties {
				jmxEnabled = true
				initialSize = 5
				maxActive = 50
				minIdle = 5
				maxIdle = 25
				maxWait = 10000
				maxAge = 600000
				timeBetweenEvictionRunsMillis = 5000
				minEvictableIdleTimeMillis = 60000
				validationQuery = "SELECT 1"
				validationQueryTimeout = 3
				validationInterval = 15000
				testOnBorrow = true
				testWhileIdle= true
				testOnReturn = false
				jdbcInterceptors = "ConnectionState"
				defaultTransactionIsolation= 2 // TRANSACTION_READ_COMMITTED
			}
		}
	}
}
