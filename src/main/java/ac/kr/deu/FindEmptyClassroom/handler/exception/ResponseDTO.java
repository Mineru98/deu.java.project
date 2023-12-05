package ac.kr.deu.FindEmptyClassroom.handler.exception;

import ac.kr.deu.FindEmptyClassroom.enums.ResponseState;
import lombok.Data;

@Data
public class ResponseDTO<T> {

  private ResponseState state;
  private String message;
  private T data;

  public ResponseDTO(ResponseState state, String message, T data) {
    this.state = state;
    this.message = message;
    this.data = data;
  }

  public ResponseDTO(ResponseState state) {
    this.state = state;
  }

  public ResponseDTO(ResponseState state, String message) {
    this.state = state;
    this.message = message;
  }

  public ResponseDTO(ResponseState state, T data) {
    this.state = state;
    this.data = data;
  }
}
