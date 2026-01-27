package com.java4.study.Lab5;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
        Map<Integer, Item> map = new HashMap<>();

        @Override
        public Item add(Integer id) {
                Item item = map.get(id);
                if (item == null) {
                        // Lấy thông tin sản phẩm từ DB
                        Item dbItem = DB.items.get(id);
                        if (dbItem != null) {
                                // Tạo item mới với thông tin từ DB
                                item = new Item(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), 1);
                                map.put(id, item);
                        }
                } else {
                        // Tăng số lượng nếu đã tồn tại
                        item.setQty(item.getQty() + 1);
                }
                return item;
        }

        @Override
        public void remove(Integer id) {
                map.remove(id);
        }

        @Override
        public Item update(Integer id, int qty) {
                Item item = map.get(id);
                if (item != null) {
                        if (qty <= 0) {
                                map.remove(id);
                        } else {
                                item.setQty(qty);
                        }
                }
                return item;
        }

        @Override
        public void clear() {
                map.clear();
        }

        @Override
        public Collection<Item> getItems() {
                return map.values();
        }

        @Override
        public int getCount() {
                return map.values().stream()
                        .mapToInt(Item::getQty)
                        .sum();
        }

        @Override
        public double getAmount() {
                return map.values().stream()
                        .mapToDouble(item -> item.getPrice() * item.getQty())
                        .sum();
        }
}
