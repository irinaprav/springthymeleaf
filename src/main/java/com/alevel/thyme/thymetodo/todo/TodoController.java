package com.alevel.thyme.thymetodo.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller //отдаем джейсон или хмл то есть данные а не хтмл если бы хтмл то тогда контроллер
@RequestMapping("/")
public class TodoController {

    private final TodoOperations todoOperations;
    private Logger logger = LoggerFactory.getLogger(TodoController.class);
    //@Autowired// говорит что тут инъекция зависимости спринг сам поймет
    public TodoController(TodoOperations todoOperations) {
        this.todoOperations = todoOperations;
    }

    @RequestMapping(value = "/todo",method = RequestMethod.GET)
    @ResponseStatus(value= HttpStatus.OK)
    public String getAllNotDone(Model model) {
        model.addAttribute("todos", todoOperations.getAllNotDone());
        model.addAttribute("textobj", new TextForm());
        model.addAttribute("checkedobj", new Checked());
        return "index";
    }

    @RequestMapping(value = "/checkedsave",method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(value= HttpStatus.OK)
    public String change(Model model, @ModelAttribute("checkedobj") Checked checked)
    {
        List<Todo> todos = new ArrayList<>();
        for (String text: checked.getCheckedItems()) {
            Todo todo = todoOperations.getTodo(text);
            todo.setDone(true);
            todos.add(todo);
        }
        todoOperations.updateAll(todos);
        model.addAttribute("todos", todoOperations.getAllNotDone());
        model.addAttribute("textobj", new TextForm());
        model.addAttribute("checkedobj", new Checked());
        return "index";
    }

    @RequestMapping(value = "/savetext",method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping
    public String save(Model model, @ModelAttribute("textobj") TextForm textobj) {
        Long id = todoOperations.save(new Todo(textobj.getText()));
        logger.warn("WAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAARN " + textobj.getText());
        return "redirect:/todo";
    }


}