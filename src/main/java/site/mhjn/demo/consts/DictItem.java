package site.mhjn.demo.consts;

public interface DictItem<C> {
    /**
     * 获取字典项的编码
     */
    C getCode();

    /**
     * 获取字典项的标签
     */
    String getLabel();

}
