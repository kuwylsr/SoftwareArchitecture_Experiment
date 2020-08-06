package factory;

public class FileNotFormatException extends Exception {


  private String fileName;
  private String content;

  private String conTypeException;
  private String resultHandle;

  public FileNotFormatException(String fileName, String content, String typeException,
      String conTypeException, String resultHandle) {
    super(typeException);
    this.fileName = fileName;
    this.conTypeException = conTypeException;
    this.resultHandle = resultHandle;
    this.content = content;
  }


  public String get_file_name() {
    return this.fileName;
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
