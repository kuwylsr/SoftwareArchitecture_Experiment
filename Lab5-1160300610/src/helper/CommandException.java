package helper;

public class CommandException extends Exception {

  private String content;

  private String conTypeException;
  private String resultHandle;

  public CommandException(String content, String typeException, String conTypeException,
      String resultHandle) {
    super(typeException);
    this.conTypeException = conTypeException;
    this.resultHandle = resultHandle;
    this.content = content;
  }


  public String get_con_type_exception() {
    return this.conTypeException;
  }

  public String get_result_handle() {
    return this.resultHandle;
  }

  public String get_content() {
    return this.content;
  }
}
