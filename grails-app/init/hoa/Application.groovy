package hoa

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
		
		dataSource {
			pooled = true
			jmxExport = true
			driverClassName = "com.mysql.jdbc.Driver"
			username = "adminqkMB18R"
			password = "Tyf1RsAm6buT"
			dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
		}
		hibernate {
			cache.use_second_level_cache = true
			cache.use_query_cache = false
		//    cache.region.factory_class = 'org.hibernate.cache.SingletonEhCacheRegionFactory' // Hibernate 3
			cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory' // Hibernate 4
			singleSession = true // configure OSIV singleSession mode
			flush.mode = 'manual' // OSIV session flush mode outside of transactional context
		}
		
		// environment specific settings
		environments {
			development {
				dataSource {
					dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
		//           url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		//		   driverClassName = "org.h2.Driver"
		//		   username = "sa"
		//		   password = ""
					username = "root"
					password = "splatt66"
					url = "jdbc:mysql://localhost:3306/tomcat?useUnicode=yes&characterEncoding=UTF-8"
				}
			}
			test {
				dataSource {
					dbCreate = "update"
					url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
					driverClassName = "org.h2.Driver"
					username = "sa"
					password = ""
				}
			}
			tomcatdev {
				dataSource {
		//			username = "adminAyEnK7p"
					//			password = "wftJDhvqxheA"
					//			dbCreate = "update"
					////            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
					//			url = "jdbc:mysql://127.7.85.2:3306/tomcat?useUnicode=yes&characterEncoding=UTF-8"
					dbCreate = "update"
					username = "adminAyEnK7p"
					password = "wftJDhvqxheA"
		//            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
					url = "jdbc:mysql://127.7.85.2:3306/tomcat?useUnicode=yes&characterEncoding=UTF-8"
					properties {
					   // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
					   jmxEnabled = true
					   initialSize = 5
					   maxActive = 50
					   minIdle = 5
					   maxIdle = 25
					   maxWait = 10000
					   maxAge = 10 * 60000
					   timeBetweenEvictionRunsMillis = 5000
					   minEvictableIdleTimeMillis = 60000
					   validationQuery = "SELECT 1"
					   validationQueryTimeout = 3
					   validationInterval = 15000
					   testOnBorrow = true
					   testWhileIdle = true
					   testOnReturn = false
					   jdbcInterceptors = "ConnectionState"
					   defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
					}
				}
			}
			production {
				dataSource {
		//			username = "adminAyEnK7p"
					//			password = "wftJDhvqxheA"
					//			dbCreate = "update"
					////            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
					//			url = "jdbc:mysql://127.7.85.2:3306/tomcat?useUnicode=yes&characterEncoding=UTF-8"
					dbCreate = "update"
		//            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
					url = "jdbc:mysql://127.5.26.130:3306/gwyneddchase?useUnicode=yes&characterEncoding=UTF-8"
					properties {
					   // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
					   jmxEnabled = true
					   initialSize = 5
					   maxActive = 50
					   minIdle = 5
					   maxIdle = 25
					   maxWait = 10000
					   maxAge = 10 * 60000
					   timeBetweenEvictionRunsMillis = 5000
					   minEvictableIdleTimeMillis = 60000
					   validationQuery = "SELECT 1"
					   validationQueryTimeout = 3
					   validationInterval = 15000
					   testOnBorrow = true
					   testWhileIdle = true
					   testOnReturn = false
					   jdbcInterceptors = "ConnectionState"
					   defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
					}
				}
			}
		}
    }
}