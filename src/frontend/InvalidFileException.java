package frontend;

class InvalidFileException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvalidFileException(){
	}
	
	InvalidFileException(String msg){
		super(msg);
	}
	
}

