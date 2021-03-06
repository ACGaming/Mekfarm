package mekfarm.machines.wrappers;

import mekfarm.machines.wrappers.animals.VanillaGenericAnimal;
import mekfarm.machines.wrappers.animals.VanillaHorse;
import mekfarm.machines.wrappers.animals.VanillaLlama;
import net.minecraft.entity.passive.*;
import net.minecraft.item.Item;

import java.util.List;

/**
 * Created by CF on 2016-12-10.
 */
public class AnimalWrapperFactory {
    /**
     * @param entity
     * the entity to be wrapped
     * @return
     * return a nicely wrapped entity ready to be exploited in many many ways
     */
    public static IAnimalWrapper getAnimalWrapper(EntityAnimal entity) {
        IAnimalWrapper wrapper = null;

        if ((entity instanceof EntityHorse) || (entity instanceof EntityDonkey)) {
            return new VanillaHorse((AbstractHorse) entity);
        } else if (entity instanceof EntityLlama) {
            return new VanillaLlama((EntityLlama) entity);
        }

        if (wrapper == null) {
            wrapper = new VanillaGenericAnimal(entity);
        }
        return wrapper;
    }

    public static void populateFoodItems(List<Item> food) {
        VanillaGenericAnimal.populateFoodItems(food);
        VanillaHorse.populateFoodItems(food);
        VanillaLlama.populateFoodItems(food);
    }
}
