package site.mhjn.demo.consts;

public interface DictItem<T> {
    /**
     * 获取字典项的编码
     */
    T getCode();

    /**
     * 获取字典项的标签
     */
    String getLabel();
}
