package cc.airson.common.server.exception;

import cc.airson.common.server.config.ResultCodeBase;
import cc.airson.common.server.model.api.ResponseBase;

public class RestException extends RuntimeException {

    private static final long serialVersionUID = -6573745616222110530L;

    public ResponseBase response = new ResponseBase(ResultCodeBase.SERVER_EXCEPTION);

    public RestException() {

    }

    public RestException(ResultCodeBase errorCode) {
        this.response = new ResponseBase(errorCode);
    }

    public RestException(ResultCodeBase errorCode, String error) {
        this.response = new ResponseBase(errorCode);
        this.response.setMessage(error);
    }

    public RestException(int code, String error) {
        this.response = new ResponseBase(code, error);
    }

    public RestException(ResponseBase response) {
        this.response = response;
    }

}