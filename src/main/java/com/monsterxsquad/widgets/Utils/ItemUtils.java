package com.monsterxsquad.widgets.Utils;

import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.Base64;

public class ItemUtils {

    public String itemStackArrayToBase64(ItemStack[] itemArray) throws IllegalStateException {
        try {
            byte[] bytes = ItemStack.serializeItemsAsBytes(itemArray);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception err) {
            throw new IllegalStateException("Error items couldn't be saved!", err);
        }
    }

    public ItemStack[] itemStackArrayFromBase64(String data) throws IOException {
        try {
            byte[] bytes = Base64.getDecoder().decode(data);
            return ItemStack.deserializeItemsFromBytes(bytes);
        } catch (Exception err) {
            throw new IOException("Error items couldn't be loaded!", err);
        }
    }
}
