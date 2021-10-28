package com.example.simplecrud.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.simplecrud.model.Tutorial;
import com.example.simplecrud.service.TutorialService;

@Controller
public class GreetingController {
	//@Autowired
	//private TutorialService service;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required = false, defaultValue = "World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";//greeting.html
	}
	

	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/")
	public String index(Model model) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy : HH:mm:ss");
		Date now = new Date();
		String dateStr = dateFormat.format(now);
		model.addAttribute("time", dateStr);
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/rest")
	public String getTutorials() {
		TutorialService service = new TutorialService();
		service.printTutorial();
		return "index";
	}
	
	
	
	
	@GetMapping("/rest2")
	public String getTutorials2() {
		TutorialService service = new TutorialService();
		Tutorial tutorial = service.getTutorialsById();
    	System.out.println("Title : " + tutorial.getTitle());
    	System.out.println("Description : " + tutorial.getDescription());
		return "index";
	}
	
	
	
	
	@GetMapping("/rest3")
	public String getTutorials3(Model model) {
		TutorialService service = new TutorialService();
		List<Tutorial> tutorials = service.getAllTutorials();
		model.addAttribute("listTutorials", tutorials);
		return "list";
	}
	
	
	@GetMapping("/add")
	public String showAdd(Model model) {
		TutorialService service = new TutorialService();
		List<Tutorial> tutorials = service.getAllTutorials();
		Tutorial newTutorial = new Tutorial();
        model.addAttribute("tutorial", newTutorial);
		model.addAttribute("listTutorials", tutorials);
		return "list-add";
	}
	
	@PostMapping("/addTutorial")
	public String addTutorial(@ModelAttribute Tutorial tutorial, Model model) {
		TutorialService service = new TutorialService();
		service.postTutorials(tutorial);
		
		
		Tutorial newTutorial = new Tutorial();
        model.addAttribute("tutorial", newTutorial);
		List<Tutorial> tutorials = service.getAllTutorials();
		model.addAttribute("listTutorials", tutorials);
		
		//showAddAndList(model);
		//@Autowired
		//private TutorialService service;
		
		return "list-add";
	}
	
	
	public void showAddAndList(Model model) {
		Tutorial newTutorial = new Tutorial();
        model.addAttribute("tutorial", newTutorial);
		TutorialService service = new TutorialService();
		List<Tutorial> tutorials = service.getAllTutorials();
		model.addAttribute("listTutorials", tutorials);
	}
	
	
	
	@GetMapping("/list")
	public String listTutorial(@ModelAttribute Tutorial tutorial, Model model) {
		showAddAndList(model);		
		return "list-final";
	}
	
    @GetMapping("/update")
	public String update(@RequestParam Long id, Model model) {
    	TutorialService service = new TutorialService();
    	Tutorial tutorial = service.getTutorialsById(id);
        model.addAttribute("tutorial", tutorial);
		return "update";
	}
    
	@PostMapping("/add2")
	public String add(@ModelAttribute Tutorial tutorial, Model model) {
		TutorialService service = new TutorialService();
		service.postTutorials(tutorial);
		showAddAndList(model);
		return "list-final";
	}
        
	@GetMapping("/save")
	public String saveTutorial(@RequestParam Long id,@ModelAttribute Tutorial tutorial, Model model) {
		TutorialService service = new TutorialService();
		service.update(id, tutorial);
		showAddAndList(model);		
		return "list-final";
	}
	
    @GetMapping("/delete")
	public String delete(@RequestParam Long id, Model model) {
    	TutorialService service = new TutorialService();
    	service.delete(id);
		showAddAndList(model);
		return "list-final";
	}
}
