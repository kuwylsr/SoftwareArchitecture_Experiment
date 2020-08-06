package factory;

public class FileNotFormat_re_Exception extends Exception {
	
	private String file_name;
	private String content;

	private String con_type_exception;
	private String result_handle;
	
	public FileNotFormat_re_Exception(String file_name,String content,String type_exception,String con_type_exception,String result_handle) {
		super(type_exception);
		this.file_name=file_name;
		this.con_type_exception=con_type_exception;
		this.result_handle=result_handle;
		this.content=content;
	}
	
	
	public String get_file_name() {
		return this.file_name;
	}
	
	public String get_con_type_exception() {
		return this.con_type_exception;
	}
	
	public String get_result_handle() {
		return this.result_handle;
	}
	
	public String get_content() {
		return this.content;
	}
}
