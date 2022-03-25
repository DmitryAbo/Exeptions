package ru.netology.manager;

import ru.netology.domain.Product;
import ru.netology.repository.Repository;

public class Manager {
    private Repository repo = new Repository();

    public Manager(Repository repo) {
        this.repo = repo;
    }

    public Manager() {
    }

    public void add(Product product) {
        repo.saveProduct(product);
    }

    public Product[] searchBy(String text) {                        //Поиск продукта по name
        Product[] result = new Product[0];                          //массив в котором будем возвращать найденные продукты
        int i = 0;
        Product[] tmp = new Product[repo.getAllProducts().length];  //массив куда будем помещать найденные элементы во время поиска по умолчанию считаем что подощли все товары

        for (Product product : repo.getAllProducts()) {
            if (matches(product, text)) {
                tmp[i] = product;
                i++;
            }
        }

        if (i != 0) {
            result = new Product[i];
            System.arraycopy(tmp,0,result,0,i);
        }

        return result;
    }

    public boolean matches(Product product, String search) {
        return product.getName().contains(search);
    }


}
