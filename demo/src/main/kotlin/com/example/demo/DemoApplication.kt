package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

interface apwebsiteRepository: JpaRepository<emails,String>
{
	@Query(value = "call getall()", nativeQuery = true)
	fun GetAll():emails

	@Query(value = "call InsertEmail(:Email)", nativeQuery = true)
	fun InsertEmail(@Param("Email")Email: String):emails
}
@RestController
@RequestMapping("api")
class PlayerDataRestController(val apwebsiteRepo: apwebsiteRepository)
{
	@GetMapping("Example")
	fun ExampleFunction() = apwebsiteRepo.findAll();

	@GetMapping("GETALL")
	fun GETALL() = apwebsiteRepo.GetAll()

	@GetMapping("InsertEmail/{Email}")
	fun INSERTEMAIL(@PathVariable(value = "Email") Email: String) : emails
	{
		println(Email.toString())
		return apwebsiteRepo.InsertEmail(Email)
	}
}
@Entity
class emails(
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	val idemails: String = ""
)
