package site.mhjn.demo.dict;

public interface Dict<C> {
    /**
     * 获取字典项的编码
     */
    C getCode();

    /**
     * 获取字典项的标签
     */
    String getLabel();

}
