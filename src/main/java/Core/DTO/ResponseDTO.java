package Core.DTO;

import java.util.List;
import java.util.Map;

/**
 * Response DTO
 *
 * Author: DangNHH - 17/02/2019
 */
public class ResponseDTO {
    /**
     * Success: 1, Fail:0
     */
    private boolean status;
    /**
     * Message Response
     */
    private String message;
    /**
     * List Message Response
     */
    private List<String> listMessage;
    /**
     * Map Message Response
     */
    private Map<Integer, String> mapMessage;

    /**
     * Object Response
     */
    private Object objectResponse;

    public ResponseDTO() {
    }

    public ResponseDTO(boolean status, String message, List<String> listMessage, Map<Integer, String> mapMessage, Object objectResponse) {
        this.status = status;
        this.message = message;
        this.listMessage = listMessage;
        this.mapMessage = mapMessage;
        this.objectResponse = objectResponse;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getListMessage() {
        return listMessage;
    }

    public void setListMessage(List<String> listMessage) {
        this.listMessage = listMessage;
    }

    public Map<Integer, String> getMapMessage() {
        return mapMessage;
    }

    public void setMapMessage(Map<Integer, String> mapMessage) {
        this.mapMessage = mapMessage;
    }

    public Object getObjectResponse() {
        return objectResponse;
    }

    public void setObjectResponse(Object objectResponse) {
        this.objectResponse = objectResponse;
    }
}
