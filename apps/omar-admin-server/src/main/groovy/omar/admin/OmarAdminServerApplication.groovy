package omar.admin

import de.codecentric.boot.admin.config.EnableAdminServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableAdminServer
@SpringBootApplication
class OmarAdminServerApplication {
	static void main(String[] args) {
		SpringApplication.run OmarAdminServerApplication, args
	}
}
