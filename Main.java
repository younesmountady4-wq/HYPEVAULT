package com.hypevault.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class HypevaultBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(HypevaultBackendApplication.class, args);
    }
}

@RestController
@RequestMapping("/api")
class HypevaultController {

    // Lista completa dei prodotti dell'archivio vintage
    private final List<Product> inventory = List.of(
        new Product(1L, "Navy 3-Stripes Track Pants", "Bottoms", "90s Nylon", 52.00, true),
        new Product(2L, "Piping Baggy Track Pants", "Bottoms", "Y2K Fit", 64.00, true),
        new Product(3L, "Cobalt Colorblock Windbreaker", "Jackets", "Archive 1994", 85.00, true),
        new Product(4L, "Purple & Teal Retro Track Jacket", "Jackets", "Rare 90s", 98.00, true),
        new Product(5L, "Carhartt Distressed Work Jacket", "Jackets", "Vintage 1998", 145.00, true),
        new Product(6L, "Boxy Faded Heavy Hoodie", "Hoodies", "Archive 2002", 110.00, false),
        new Product(7L, "Vintage Parachute Cargo Pants", "Bottoms", "Y2K Era", 95.00, true)
    );

    // Endpoint per ottenere tutti i prodotti o filtrarli per categoria (es. /api/products?category=Jackets)
    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            return inventory.stream()
                    .filter(p -> p.category().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }
        return inventory;
    }

    // Endpoint extra per statistiche del progetto scolastico
    @GetMapping("/stats")
    public VaultStats getStats() {
        long totalProducts = inventory.size();
        long availableCount = inventory.stream().filter(Product::available).count();
        return new VaultStats("HYPEVAULT Archive", totalProducts, availableCount, "2026 Edition");
    }
}

// Record Java pulito e moderno per i prodotti
record Product(
    Long id,
    String name,
    String category,
    String era,
    double price,
    boolean available
) {}

// Record per le statistiche del sistema
record VaultStats(
    String vaultName,
    long totalItems,
    long availableItems,
    String projectYear
) {}
