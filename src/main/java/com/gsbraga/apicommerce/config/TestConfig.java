package com.gsbraga.apicommerce.config;

import com.gsbraga.apicommerce.model.*;
import com.gsbraga.apicommerce.model.enums.OrderStatus;
import com.gsbraga.apicommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StockRepository stockRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Brand brand1 = new Brand(null, "AAA");
        Brand brand2 = new Brand(null, "BBB");
        Brand brand3 = new Brand(null, "CCC");

        Product p1 = new Product(null, cat1, brand3, "TV.", 90.5, 2020);
        Product p2 = new Product(null, cat2, brand2, "Notebook DELL.",  2190.0, 2018);
        Product p3 = new Product(null, cat3, brand1, "Macbook Pro",1250.0, 2017);
        Product p4 = new Product(null, cat1, brand2, "PC Gamer", 1200.0, 2021);
        Product p5 = new Product(null, cat2, brand1, "Fone", 100.99, 2021);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        brandRepository.saveAll(Arrays.asList(brand1, brand2, brand3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        Store s1 = new Store(null, "Loja Centro");
        Store s2 = new Store(null, "Loja Matriz");

        storeRepository.saveAll(Arrays.asList(s1, s2));

        Stock sto1 = new Stock(null, s1, p1, 3);
        sto1.getQuantity();
        Stock sto2 = new Stock(null, s1, p2, 1);
        Stock sto3 = new Stock(null, s2, p3, 2);
        Stock sto4 = new Stock(null, s1, p4, 0);
        Stock sto5 = new Stock(null, s2, p5, 2);

        stockRepository.saveAll(Arrays.asList(sto1, sto2, sto3, sto4, sto5));

        Staff st1 = new Staff(null, "Carlos", "Costa", "caloscosta@gmail.com", Boolean.TRUE, s1);
        Staff st2 = new Staff(null, "Ana", "Sousa", "anasousa@gmail.com", Boolean.TRUE, s2);

//        List<Stock> stock1 = stockRepository.findByStoreIdAndProductId(p1, s1);



        staffRepository.saveAll(Arrays.asList(st1, st2));

        Customer c1 = new Customer(null, "Maria", "da Silva", "988888888", "maria@gmail.com", "Rua 2, quadra 2",  "São Luís", "Maranhão", "65036-800");
        Customer c2 = new Customer(null, "João", "da Silva", "980999988", "joao@gmail.com", "Rua 2, quadra 3",  "São Luís", "Maranhão", "65036-800");

        customerRepository.saveAll(Arrays.asList(c1, c2));

        Order o1 = new Order(null, c1, Instant.parse("2021-05-25T19:53:07Z"), OrderStatus.WAITING_SHIPMENT, st1, s1);
        Order o2 = new Order(null, c2, Instant.parse("2021-05-25T03:42:10Z"), OrderStatus.WAITING_SHIPMENT, st2, s2);
        Order o3 = new Order(null, c1, Instant.parse("2021-05-25T15:21:22Z"), OrderStatus.WAITING_SHIPMENT, st1, s1);

        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
    }
}
