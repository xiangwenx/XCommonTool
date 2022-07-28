package cn.xtool.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xwxu
 * @description
 * @date 2022-07-28
 */
@Data
@NoArgsConstructor
public class CellData {
    private int rowIndex;
    private int cellIndex;
    private Object value;

    public CellData(Object o, int rowIndex, int cellIndex) {
        this.cellIndex = cellIndex;
        this.rowIndex = rowIndex;
        this.value = o;
    }
}
