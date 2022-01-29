package cn.knightzz.response;

import lombok.Data;


@Data
public class JsonResult<T> {

    private T data;
    private Integer code;
    private String msg;

    /**
     * 没有数据返回 默认状态码是 200
     */
    public JsonResult() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMessage();
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     *
     * @param code
     * @param msg
     */
    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     *
     * @param data 携带的数据
     * @param code 响应码
     * @param msg  响应信息
     */
    public JsonResult(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为200，默认提示信息为：操作成功！
     *
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMessage();
    }

    /**
     * 有数据返回时, 状态码为200, 认为指定提示信息
     *
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = ResponseCode.SUCCESS.getCode();
        this.msg = ResponseCode.SUCCESS.getMessage();
    }

}