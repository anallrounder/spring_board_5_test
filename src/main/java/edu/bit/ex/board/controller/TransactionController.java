package edu.bit.ex.board.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.bit.ex.board.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */

@Log4j
@AllArgsConstructor
@Controller
public class TransactionController {

	private TransactionService tranService;

	@GetMapping("/tran/{num}") //RESTful형태 
	public void transiotn(@PathVariable("num") int num) throws SQLException {

		if (num == 1) {
			log.info("transionTest1");
			tranService.transionTest1();
		} else if (num == 2) {
			log.info("transionTest2");
			tranService.transionTest2();
		} else if (num == 3) {
			log.info("transionTest3");
			tranService.transionTest3();
		} else if (num == 4) {
			log.info("transionTest4");
			tranService.transionTest4();
		} else if (num == 5) {
			log.info("transionTest5");
			tranService.transionTest5();
		} else if (num == 6) {
			log.info("transionTest6");
			tranService.transionTest6();
		} else if (num == 7) {
			log.info("transionTest7");
			tranService.transionTest7();
		}
	}
}
