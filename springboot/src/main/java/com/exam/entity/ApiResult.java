package com.exam.entity;


public class ApiResult<T> {
    /**
     * 错误码，表示一种错误类型
     * 请求成功，状态码为200
     */
    private int Code;

    /**
     * 对错误代码的详细解释
     */
    private String Message;

    /**
     * 返回的结果包装在value中，value可以是单个对象
     */
    private T Data;

    public ApiResult() {
    }

    public ApiResult(int Code, String Message, T Data) {
        this.Code = Code;
        this.Message = Message;
        this.Data = Data;
    }

    public int getCode()
    {
        return this.Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return this.Message;
    }

    public void setMessage(String Message)
    {
        this.Message = Message;
    }

    public T getData() {
        return this.Data;
    }

    public void setData(T Data) {
        this.Data = Data;
    }
}
