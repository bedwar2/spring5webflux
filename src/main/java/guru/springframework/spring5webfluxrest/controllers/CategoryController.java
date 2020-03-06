package guru.springframework.spring5webfluxrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(path="api/v1/categories")
public class CategoryController {
	private final CategoryRepository categoryRepository;

	public CategoryController(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@GetMapping()
	public Flux<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	@GetMapping(path="/{id}")
	Mono<Category> getById(@PathVariable String id) {
		return categoryRepository.findById(id);
	}
	
	
}
