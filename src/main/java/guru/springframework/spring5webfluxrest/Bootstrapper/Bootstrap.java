package guru.springframework.spring5webfluxrest.Bootstrapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repositories.CategoryRepository;
import guru.springframework.spring5webfluxrest.repositories.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;
	private VendorRepository vendorRepository;
	
	
	
	public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		this.LoadCategories();
		this.LoadVendors();
	}

	public void LoadCategories() {
		if (this.categoryRepository.count().block() < 6) {
			this.categoryRepository.save(Category.builder().description("Paper products").build()).block();
			this.categoryRepository.save(Category.builder().description("Electronics").build()).block();		
		}

		System.out.println("Total Categories loaded: " + this.categoryRepository.count().block());
		System.out.println("----------------------------------------------------");
		this.categoryRepository.findAll().collectList().block().forEach(o -> {
			System.out.println(o.getDescription());
		});
	}

	public void LoadVendors() {
		if (this.vendorRepository.count().block() < 10) {
			this.vendorRepository.save(Vendor.builder().firstName("Chao").lastName("Wu").build()).block();
			this.vendorRepository.save(Vendor.builder().firstName("Christina").lastName("Delmont-Small").build()).block();
			this.vendorRepository.save(Vendor.builder().firstName("Sezin").lastName("Palmer").build()).block();
			this.vendorRepository.save(Vendor.builder().firstName("Vicky").lastName("Cutroneo").build()).block();
		}
		
		System.out.println("Total Vendors loaded: " + this.vendorRepository.count().block());
		System.out.println("----------------------------------------------------");
		this.vendorRepository.findAll().collectList().block().forEach(o -> {
			System.out.println(o.getFirstName() + " "+ o.getLastName());
		});

	}
	
}
