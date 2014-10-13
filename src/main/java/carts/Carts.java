package carts;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by andgra on 2014-10-10.
 */
public class Carts {
    private static Carts ourInstance = new Carts();
    private HashMap<Long, Cart> cartsHashMap;

    public static Carts getInstance() {
        return ourInstance;
    }

    private Carts() {
        cartsHashMap = new HashMap<Long, Cart>();
    }

    public ArrayList<Cart> getCarts() {
        ArrayList<Cart> cartArrayList = new ArrayList<Cart>(cartsHashMap.values());
        return cartArrayList;
    }

    public void createCarts(Long id) {
        cartsHashMap.put(id, new Cart());
    }
}
