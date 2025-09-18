package practica_spring_boot.Todo_List_API.exception;

public class CustomException extends RuntimeException{

    private String message;
    private int statusCode;

    public CustomException(String message, int statusCode){
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage(){
        return message;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
