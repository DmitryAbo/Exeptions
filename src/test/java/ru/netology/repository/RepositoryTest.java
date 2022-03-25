package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.SmartPhone;
import ru.netology.exeptions.AlreadyExistsException;
import ru.netology.exeptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    Product prod1 = new Product(1, "Молоко", 65);
    Product prod2 = new Book(2, "О дивный новый мир", 500, "Хаскли");
    Product prod3 = new SmartPhone(3, "Apple", 100_000, "Китай");
    Product prod4 = new Book(4, "Молоко как смысл жизни", 100, "Богомолов");

    @Test
    void shouldSaveOneProduct() {
        Product[] expected = new Product[]{prod1};
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        Product[] actual = repo.getAllProducts();
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldSaveSameProduct() {
        Product[] expected = new Product[]{prod1, prod2};
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.saveProduct(prod2);
        Product[] actual = repo.getAllProducts();
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldReturnEmpty() {
        Product[] expected = new Product[0];
        Repository repo = new Repository();
        Product[] actual = repo.getAllProducts();
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldRemoveOneProductLastAdded() {
        Product[] expected = new Product[]{prod1, prod2};
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.saveProduct(prod2);
        repo.saveProduct(prod3);
        repo.removeById(3);
        Product[] actual = repo.getAllProducts();
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldRemoveOneProductNoLastAdded() {
        Product[] expected = new Product[]{prod1, prod3};
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.saveProduct(prod2);
        repo.saveProduct(prod3);
        repo.removeById(2);
        Product[] actual = repo.getAllProducts();
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldRemoveOnlyProduct() {
        Product[] expected = new Product[0];
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.removeById(1);
        Product[] actual = repo.getAllProducts();
        assertArrayEquals(actual, expected);
    }

    @Test
    void shouldFindById() {
        Product expected = prod2;
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.saveProduct(prod2);
        repo.saveProduct(prod3);
        Product actual = repo.findById(2);
        assertEquals(actual, expected);
    }

    @Test
    void shouldNotFindById() {
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.saveProduct(prod2);
        repo.saveProduct(prod3);
        Product actual = repo.findById(100);
        assertNull(actual);
    }

    @Test
    void shouldRemoveByExsistingId() {
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.saveProduct(prod2);
        repo.saveProduct(prod3);
        repo.saveProduct(prod4);
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(100);
        });
    }

    @Test
    void shouldSaveExsistingProduct() {
        Repository repo = new Repository();
        repo.saveProduct(prod1);
        repo.saveProduct(prod2);
        repo.saveProduct(prod3);
        repo.saveProduct(prod4);
        assertThrows(AlreadyExistsException.class, () -> {
            repo.saveProduct(prod1);
        });
    }



}