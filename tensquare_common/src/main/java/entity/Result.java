package entity;

import lombok.Data;

/**
 * 项目封装结果集
 *
 * @author waver
 * @date 2019.5.27 9:19
 */
@Data
public class Result {
    /**
     * 是否成功
     */

    boolean flag;
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;


    public Result() {
        super();
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "";
        this.data = null;
    }

    public Result(String message) {
        super();
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = message;
        this.data = null;
    }

    public Result(Object data) {
        super();
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "";
        this.data = data;
    }

    public Result(String message, Object data) {
        super();
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        super();
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(String message) {
        return new Result(message);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok(String message, Object data) {
        return new Result(message, data);
    }

    public static Result ok(boolean flag, Integer code, String message) {
        return new Result(flag, code, message);
    }

    public static Result ok(boolean flag, Integer code, String message, Object data) {
        return new Result(flag, code, message, data);
    }

    public static Result build(String message) {
        return new Result(false, StatusCode.ERROR, message);
    }

    public static Result build(boolean flag, Integer code, String message) {
        return new Result(flag, code, message);
    }

    public static Result build(boolean flag, Integer code, String message, Object data) {
        return new Result(flag, code, message, data);
    }

}
