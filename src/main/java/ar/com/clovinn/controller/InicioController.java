package ar.com.clovinn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class InicioController extends BaseController{

	@GetMapping
	public String inicio() {
		return "redirect:/producto";
	}
}