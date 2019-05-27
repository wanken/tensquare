package entity;

import lombok.Data;

import java.util.List;

/**
 * 分页工具类
 *
 * @author waver
 * @date 2019.5.27 10:53
 */
@Data
public class PageResult<T> {
    private long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public static <T> PageResult<T> getInstance(long total, List<T> rows) {
        return new PageResult<>(total, rows);
    }
}
