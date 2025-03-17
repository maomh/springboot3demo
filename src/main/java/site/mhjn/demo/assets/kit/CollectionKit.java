package site.mhjn.demo.assets.kit;

import lombok.experimental.UtilityClass;

import java.util.Collection;

@UtilityClass
public class CollectionKit {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
