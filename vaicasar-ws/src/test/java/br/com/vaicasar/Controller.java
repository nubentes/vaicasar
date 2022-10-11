package br.com.vaicasar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@GetMapping("/aws")
	public ResponseEntity<String> health(){
		return ResponseEntity.ok("Spring bood Application running");
	}


}
